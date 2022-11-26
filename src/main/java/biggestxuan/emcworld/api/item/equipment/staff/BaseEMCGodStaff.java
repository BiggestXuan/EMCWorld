package biggestxuan.emcworld.api.item.equipment.staff;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/24
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.item.IEMCRepairableItem;
import biggestxuan.emcworld.api.item.ISecondEMCItem;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.api.item.IUpgradeableMaterial;
import biggestxuan.emcworld.api.item.equipment.weapon.BaseEMCGodWeapon;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Staff.StaffItem;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseEMCGodStaff extends StaffItem implements IEMCRepairableItem, IUpgradeableItem, IUpgradeableMaterial, ISecondEMCItem {
    public BaseEMCGodStaff() {
        super(EMCWorldAPI.getInstance().getStaffTier("god"));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, @Nonnull ITooltipFlag p_77624_4_) {
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.weapon_god"));
    }

    @Nonnull
    @Override
    public ITextComponent getName(@Nonnull ItemStack p_200295_1_) {
        int level = getLevel(p_200295_1_);
        String name = this.toString();
        return EMCWorld.tc("item.emcworld."+name).append(" (+"+level+")");
    }

    protected abstract long getBaseEMCModify(ItemStack stack);

    protected abstract double getBaseCostRate(ItemStack stack);

    protected abstract float getBaseBurstDamage(ItemStack stack);

    protected abstract double getBaseCriticalChance(ItemStack stack);

    protected abstract double getBaseCriticalRate(ItemStack stack);

    protected abstract double getBaseBurstSpeed(ItemStack stack);

    @Override
    protected abstract int getColor();

    @Override
    public double getCriticalChance(ItemStack stack) {
        return tier.getCriticalChance() + getBaseCriticalChance(stack);
    }

    @Override
    public double getCriticalRate(ItemStack stack) {
        return tier.getCriticalRate() + getBaseCriticalRate(stack);
    }

    @Override
    public double costEMCWhenAttack(ItemStack stack) {
        return getBaseCostRate(stack);
    }

    @Override
    public long getTickCost(ItemStack stack) {
        return (long) (40L * getLevel(stack) * MathUtils.difficultyLoss());
    }

    @Override
    public long EMCModifySecond(ItemStack stack) {
        return getBaseEMCModify(stack);
    }

    @Override
    public int getMaxLevel() {
        return BaseEMCGodWeapon.getWeaponMaxLevel();
    }

    @Override
    public float getBaseDamage(ItemStack stack){
        return tier.getAttackDamageBonus() + getBaseBurstDamage(stack);
    }

    @Override
    protected double getManaBurstSpeed(ItemStack stack){
        return tier.getBurstSpeed() + getBaseBurstSpeed(stack);
    }

    @Nonnull
    @Override
    public Rarity getRarity(@Nonnull ItemStack p_77613_1_){
        int level = getLevel(p_77613_1_);
        if(level <= 8) return Rarity.COMMON;
        if(level <= 14) return Rarity.UNCOMMON;
        if(level <= 20) return Rarity.RARE;
        return Rarity.EPIC;
    }
}
