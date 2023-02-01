package biggestxuan.emcworld.api.item.equipment.dagger;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/09
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.item.IEMCInfuserItem;
import biggestxuan.emcworld.api.item.ISecondEMCItem;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.api.item.IUpgradeableMaterial;
import biggestxuan.emcworld.api.item.equipment.IEMCGodWeaponLevel;
import biggestxuan.emcworld.api.item.equipment.weapon.BaseEMCGodWeapon;
import biggestxuan.emcworld.api.item.equipment.weapon.IAdditionsDamageWeapon;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Dagger.DaggerItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseEMCGodDagger extends DaggerItem implements ISecondEMCItem, IEMCInfuserItem, IAdditionsDamageWeapon, IEMCGodWeaponLevel,IUpgradeableItem, IUpgradeableMaterial {
    public BaseEMCGodDagger() {
        super(EMCWorldAPI.getInstance().getDaggerTier("god"));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, @Nonnull ITooltipFlag p_77624_4_) {
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.weapon_god"));
    }

    @Override
    public boolean canBeHurtBy(@Nonnull DamageSource p_234685_1_){
        return false;
    }

    @Override
    public double getEMCCostRate() {
        return 0d;
    }

    @Override
    public long getMaxInfuser(ItemStack stack){
        return (long) ((Math.pow(1.417,getLevel(stack)) * 500000) * getPrefixCommonRate(stack));
    }

    @Override
    public int getMaxLevel() {
        return BaseEMCGodWeapon.getWeaponMaxLevel();
    }

    @Override
    public double getAttackSpeed(ItemStack stack) {
        double b = AttackSpeed(stack);
        long costEMC = getCostEMC(stack);
        if(costEMC >= 1){
            b *= (1 + Math.log(costEMC)/550);
        }
        return (float) b * getPrefixCommonRate(stack);
    }

    @Override
    protected double getPrefixCommonRate(ItemStack stack) {
        return super.getPrefixCommonRate(stack) * 1.1;
    }

    protected abstract double AttackSpeed(ItemStack stack);

    protected abstract double EMCCost(ItemStack stack);

    protected abstract long EMCModify(ItemStack stack);

    protected abstract float AddonDamage(ItemStack stack);

    @Override
    public double costEMCWhenAttack(ItemStack stack) {
        return EMCCost(stack) / getPrefixCommonRate(stack);
    }

    @Override
    public long EMCModifySecond(ItemStack stack) {
        return (long) (EMCModify(stack) * getPrefixCommonRate(stack));
    }

    @Override
    public long getTickCost(ItemStack stack) {
        return 45L * getLevel(stack);
    }

    @Override
    public float getAdditionsDamage(ItemStack stack) {
        double b = AddonDamage(stack);
        long costEMC = getCostEMC(stack);
        if(costEMC >= 1){
            b *= (1 + Math.log(costEMC)/115);
        }
        return (float) ((float) b * getPrefixCommonRate(stack));
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

    @Override
    public int getWeightRequired(ItemStack stack){
        return super.lv(stack);
    }
}
