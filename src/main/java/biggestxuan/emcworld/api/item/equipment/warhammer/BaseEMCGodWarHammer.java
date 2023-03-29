package biggestxuan.emcworld.api.item.equipment.warhammer;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/05
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.item.IEMCInfuserItem;
import biggestxuan.emcworld.api.item.IEMCRepairableItem;
import biggestxuan.emcworld.api.item.ISecondEMCItem;
import biggestxuan.emcworld.api.item.IUpgradeableMaterial;
import biggestxuan.emcworld.api.item.equipment.IAttackSpeedItem;
import biggestxuan.emcworld.api.item.equipment.IEMCGodWeaponLevel;
import biggestxuan.emcworld.api.item.equipment.IGemInlaidItem;
import biggestxuan.emcworld.api.item.equipment.weapon.BaseEMCGodSword;
import biggestxuan.emcworld.api.item.equipment.weapon.IAdditionsDamageWeapon;
import biggestxuan.emcworld.common.items.Equipment.Weapon.WarHammer.WarHammerItem;
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

public abstract class BaseEMCGodWarHammer extends WarHammerItem implements IEMCRepairableItem, IGemInlaidItem, ISecondEMCItem, IEMCInfuserItem, IAdditionsDamageWeapon, IAttackSpeedItem, IEMCGodWeaponLevel,IUpgradeableMaterial {
    public BaseEMCGodWarHammer(){
        super(EMCWorldAPI.getInstance().getWarHammerTier("god"));
    }

    protected abstract double getAttackCostRate(ItemStack stack);

    protected abstract long modifyEMCSecond(ItemStack stack);

    protected abstract double criticalChance(ItemStack stack);

    protected abstract double criticalRate(ItemStack stack);

    protected abstract float damage(ItemStack stack);

    protected abstract double attackRange(ItemStack stack);

    @Override
    protected double getPrefixCommonRate(ItemStack stack) {
        return super.getPrefixCommonRate(stack) * 1.025;
    }

    @Override
    public double getEMCCostRate() {
        return 0d;
    }

    @Override
    public abstract double getAttackSpeed(ItemStack stack);

    @Override
    public boolean canBeHurtBy(@Nonnull DamageSource p_234685_1_){
        return false;
    }

    @Override
    public double getAttackRange(ItemStack stack) {
        double b = attackRange(stack) * getPrefixCommonRate(stack) * getBuffer(stack);
        long costEMC = getCostEMC(stack);
        if(costEMC >= 1){
            b *= (1 + Math.log(costEMC)/85);
        }
        switch (getGemType(stack)){
            case 1:
                b *= 1.07;
                break;
            case 2:
                b *= 0.9;
                break;
            case 3:
                b *= 0.98;
                break;
            case 4:
                b *= 1.02;
                break;
        }
        return (float) b;
    }

    @Override
    public double getCriticalChance(ItemStack stack) {
        double b = criticalChance(stack) * getPrefixCommonRate(stack) * getBuffer(stack);
        long costEMC = getCostEMC(stack);
        if(costEMC >= 1){
            b += Math.log(costEMC) / 100 * 1.02;
        }
        switch (getGemType(stack)){
            case 1:
                b *= 1.03;
                break;
            case 2:
                b *= 0.9;
                break;
            case 3:
                b *= 0.98;
                break;
            case 4:
                b *= 1.005;
                break;
        }
        return (float) b;
    }

    @Override
    public double getCriticalRate(ItemStack stack) {
        double b = criticalRate(stack) * getPrefixCommonRate(stack) * getBuffer(stack);
        long costEMC = getCostEMC(stack);
        if(costEMC >= 1){
            b += Math.log(costEMC) / 100 * 1.03;
        }
        switch (getGemType(stack)){
            case 1:
                b *= 1.03;
                break;
            case 2:
                b *= 0.9;
                break;
            case 3:
                b *= 0.98;
                break;
            case 4:
                b *= 1.01;
                break;
        }
        return (float) b;
    }

    @Override
    public double costEMCWhenAttack(ItemStack stack) {
        double b = getAttackCostRate(stack);
        switch (getGemType(stack)){
            case 1:
                b *= 1.25;
                break;
            case 2:
                b *= 0.68;
                break;
            case 3:
                b *= 1.04;
                break;
            case 4:
                b *= 1.16;
                break;
        }
        return b / getPrefixCommonRate(stack) / getBuffer(stack);
    }

    @Override
    public long getMaxInfuser(ItemStack stack) {
        return (long) ((Math.pow(1.4,getLevel(stack)) * 500000) * getPrefixCommonRate(stack) * getBuffer(stack));
    }

    @Override
    public long getTickCost(ItemStack stack) {
        return 40L * getLevel(stack);
    }

    @Override
    public long EMCModifySecond(ItemStack stack) {
        return (long) (modifyEMCSecond(stack) * getBuffer(stack));
    }

    @Override
    public int getMaxLevel() {
        return BaseEMCGodSword.getWeaponMaxLevel();
    }

    @Override
    public float getAdditionsDamage(ItemStack stack){
        double b = damage(stack) * getPrefixCommonRate(stack) * getBuffer(stack);
        long costEMC = getCostEMC(stack);
        if(costEMC >= 1){
            b *= (1 + Math.log(costEMC)/85);
        }
        switch (getGemType(stack)){
            case 1:
                b *= 1.1;
                break;
            case 2:
                b *= 0.7;
                break;
            case 3:
                b *= 0.8;
                break;
            case 4:
                b *= 0.9;
                break;
        }
        return (float) b;
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
