package biggestxuan.emcworld.common.items.Equipment.Weapon.WarHammer;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/04
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.IUpgradeableMaterial;
import biggestxuan.emcworld.api.item.equipment.warhammer.IWarHammerTier;
import biggestxuan.emcworld.api.item.equipment.weapon.IRangeAttackWeapon;
import biggestxuan.emcworld.api.item.equipment.weapon.IUpgradeableWeapon;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class WarHammerItem extends TieredItem implements IUpgradeableWeapon, IUpgradeableMaterial,IRangeAttackWeapon {
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
    public ITextComponent getName(@Nonnull ItemStack p_200295_1_) {
        int level = getLevel(p_200295_1_);
        String name = this.toString();
        return EMCWorld.tc("item.emcworld."+name).append(" (+"+level+")");
    }

    @Nonnull
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@Nonnull EquipmentSlotType p_111205_1_) {
        return p_111205_1_ == EquipmentSlotType.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(p_111205_1_);
    }

    @Override
    public double getAttackRange(ItemStack stack) {
        return tier.getAttackRange() + tier.getAttackRange() * 0.15 * getLevel(stack);
    }

    @Override
    public boolean canAttackBlock(BlockState p_195938_1_, World p_195938_2_, BlockPos p_195938_3_, PlayerEntity p_195938_4_) {
        return false;
    }

    @Override
    public double costEMCWhenAttack(ItemStack stack) {
        return 1;
    }

    @Override
    public int getWeightRequired(ItemStack stack){
        return (int) (IUpgradeableWeapon.super.getWeightRequired(stack) * 3.2);
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
    public float getAdditionsDamage(ItemStack stack) {
        return (float) (tier.getAttackDamageBonus() * 0.13 * getLevel(stack));
    }

    protected int lv(ItemStack stack){
        return IUpgradeableWeapon.super.getWeightRequired(stack);
    }
}
