package biggestxuan.emcworld.common.items.Equipment.Weapon.WarHammer;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/04
 */

import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.equipment.warhammer.IWarHammerTier;
import biggestxuan.emcworld.api.item.equipment.weapon.ICriticalWeapon;
import biggestxuan.emcworld.api.item.equipment.weapon.IRangeAttackWeapon;
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

public class WarHammerItem extends TieredItem implements IUpgradeableWeapon,IRangeAttackWeapon, IPrefixItem, ICriticalWeapon {
    protected final IWarHammerTier tier;
    private final ImmutableMultimap<Attribute, AttributeModifier> defaultModifiers;

    public WarHammerItem(IWarHammerTier tier) {
        super(tier,new Properties().tab(EWCreativeTabs.EW_EQUIPMENT_TAB));
        this.tier = tier;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon Attack Speed modifier", 1.6 * tier.lossSpeed() - 4, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon Attack Damage modifier", tier.getAttackDamageBonus(), AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Nonnull
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@Nonnull EquipmentSlotType p_111205_1_) {
        return p_111205_1_ == EquipmentSlotType.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(p_111205_1_);
    }

    protected double getPrefixCommonRate(ItemStack stack){
        double b = 1;
        Prefix prefix = getPrefix(stack);
        if(prefix == Prefix.NULL) return b;
        if(prefix.getLevel() <= 3){
            b = 1 - 0.13 * (4 - prefix.getLevel());
        }else{
            b = 0.02 * (prefix.getLevel()-4) + 1;
        }
        return b;
    }

    @Override
    public DamageUtils getAttackRange(PlayerEntity player,ItemStack stack) {
        return DamageUtils.of(tier.getAttackRange() * getPrefixCommonRate(stack) + tier.getAttackRange() * 0.15 * getLevel(stack));
    }

    @Override
    public boolean canAttackBlock(BlockState p_195938_1_, World p_195938_2_, BlockPos p_195938_3_, PlayerEntity p_195938_4_) {
        return false;
    }

    @Override
    public double costEMCWhenAttack(ItemStack stack) {
        return 1 / getPrefixCommonRate(stack);
    }

    @Override
    public int getWeightRequired(ItemStack stack){
        return (int) (IUpgradeableWeapon.super.getWeightRequired(stack) * 5);
    }

    @Override
    public long getTickCost(ItemStack stack) {
        return 0;
    }

    @Override
    public long EMCModifySecond(ItemStack stack) {
        return 0;
    }

    @Override
    public int getMaxLevel() {
        return (int) ((tier.getLevel() + 1) * ConfigManager.DIFFICULTY.get() * 0.75d);
    }

    @Override
    public DamageUtils getAdditionsDamage(PlayerEntity player,ItemStack stack) {
        return DamageUtils.of((float) ((tier.getAttackDamageBonus() * 0.13 * getLevel(stack)) + tier.getAttackDamageBonus() * (getPrefixCommonRate(stack)-1)));
    }

    protected int lv(ItemStack stack){
        return IUpgradeableWeapon.super.getWeightRequired(stack);
    }

    @Override
    public double getCriticalChance(ItemStack stack) {
        return tier.getCriticalChance() * getPrefixCommonRate(stack) + 0.01 * tier.getLevel() * getLevel(stack);
    }

    @Override
    public double getCriticalRate(ItemStack stack) {
        return tier.getCriticalRate() * getPrefixCommonRate(stack) + 0.025 * tier.getLevel() * getLevel(stack);
    }
}
