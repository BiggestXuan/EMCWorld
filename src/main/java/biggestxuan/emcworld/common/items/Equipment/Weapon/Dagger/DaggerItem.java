package biggestxuan.emcworld.common.items.Equipment.Weapon.Dagger;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/09
 */

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.equipment.IAttackSpeedItem;
import biggestxuan.emcworld.api.item.equipment.dagger.IDaggerTier;
import biggestxuan.emcworld.api.item.equipment.weapon.IAdditionsDamageWeapon;
import biggestxuan.emcworld.api.item.equipment.weapon.IUpgradeableWeapon;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import biggestxuan.emcworld.common.utils.DamageUtils;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class DaggerItem extends TieredItem implements IUpgradeableWeapon,IAttackSpeedItem, IPrefixItem, IAdditionsDamageWeapon {
    protected final IDaggerTier tier;
    private final ImmutableMultimap<Attribute, AttributeModifier> defaultModifiers;

    @Override
    public double getEMCCostRate() {
        return 1d;
    }

    public DaggerItem(IDaggerTier tier) {
        super(tier,new Properties().tab(EWCreativeTabs.EW_EQUIPMENT_TAB));
        this.tier = tier;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon Attack Speed modifier", tier.getSpeed(), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon Attack Damage modifier", tier.getAttackDamageBonus(), AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @EMCWorldSince("1.0.2")
    public float getDamage(){
        return tier.getAttackDamageBonus();
    }

    @Nonnull
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@Nonnull EquipmentSlotType p_111205_1_) {
        return p_111205_1_ == EquipmentSlotType.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(p_111205_1_);
    }

    @Override
    public boolean canAttackBlock(BlockState p_195938_1_, World p_195938_2_, BlockPos p_195938_3_, PlayerEntity p_195938_4_) {
        return false;
    }

    @Override
    public double getAttackSpeed(ItemStack stack) {
        return tier.getAttackSpeed(stack) * getPrefixCommonRate(stack);
    }

    @Override
    public double costEMCWhenAttack(ItemStack stack) {
        return 1 / getPrefixCommonRate(stack);
    }

    @Override
    public long EMCModifySecond(ItemStack stack) {
        return 0;
    }

    @Override
    public int getWeightRequired(ItemStack stack){
        return (int) (IUpgradeableWeapon.super.getWeightRequired(stack) * 4.75);
    }

    @Override
    public int getMaxLevel() {
        return (int) ((tier.getLevel() + 1) * ConfigManager.DIFFICULTY.get() * 0.65d);
    }

    @Override
    public DamageUtils getAdditionsDamage(PlayerEntity player,ItemStack stack) {
        return DamageUtils.of((float) (tier.getAttackDamageBonus() * 0.1 * getLevel(stack) * getPrefixCommonRate(stack)));
    }

    protected int lv(ItemStack stack){
        return IUpgradeableWeapon.super.getWeightRequired(stack);
    }

    protected double getPrefixCommonRate(ItemStack stack){
        double b = 1;
        Prefix prefix = getPrefix(stack);
        if(prefix == Prefix.NULL) return b;
        if(prefix.getLevel() <= 3){
            b *= 1 - 0.1 * (4 - prefix.getLevel());
        }else{
            b *= 0.015 * (prefix.getLevel()-4) + 1;
        }
        return b;
    }
}