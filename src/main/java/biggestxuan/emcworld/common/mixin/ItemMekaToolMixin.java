package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/17
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.ICostEMCItem;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.ISecondEMCItem;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.api.item.equipment.IStarItem;
import biggestxuan.emcworld.api.item.equipment.weapon.IAdditionsDamageWeapon;
import biggestxuan.emcworld.api.item.equipment.weapon.IRangeAttackWeapon;
import biggestxuan.emcworld.common.compact.Mekanism.MekUtils;
import biggestxuan.emcworld.common.utils.DamageUtils;
import mekanism.api.annotations.NonNull;
import mekanism.api.inventory.AutomationType;
import mekanism.api.math.FloatingLongSupplier;
import mekanism.common.content.gear.IModuleContainerItem;
import mekanism.common.item.ItemEnergized;
import mekanism.common.item.gear.ItemMekaTool;
import mekanism.common.item.interfaces.IModeItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.Predicate;

@Mixin(ItemMekaTool.class)
public abstract class ItemMekaToolMixin extends ItemEnergized implements IModuleContainerItem, IModeItem, ISecondEMCItem, IRangeAttackWeapon, IAdditionsDamageWeapon, IStarItem, IUpgradeableItem,IPrefixItem, ICostEMCItem {
    public ItemMekaToolMixin(FloatingLongSupplier chargeRateSupplier, FloatingLongSupplier maxEnergySupplier, Predicate<@NonNull AutomationType> canExtract, Predicate<@NonNull AutomationType> canInsert, Properties properties) {
        super(chargeRateSupplier, maxEnergySupplier, canExtract, canInsert, properties);
    }

    @Override
    public double costEMCWhenAttack(ItemStack stack) {
        return isInfinity(stack) ? 0 : 1;
    }

    @Override
    public double getEMCCostRate() {
        return 0;
    }

    @Override
    public long EMCModifySecond(ItemStack stack) {
        return isInfinity(stack) ? EMCWorld.MAX_EMC : 0;
    }

    @Override
    public DamageUtils getAdditionsDamage(PlayerEntity player,ItemStack stack) {
        int baseDamage = 8;
        int level = getLevel(stack);
        if(level >= 1){
            baseDamage = (int) (baseDamage * (0.03 * level + 1));
        }
        baseDamage <<= Math.round(MekUtils.getAttackInitAmount(stack)*1.8);
        int star = getStar(stack);
        baseDamage *= star + 1;
        baseDamage = Math.min(EMCWorld.HOMO, baseDamage);
        return DamageUtils.of(isInfinity(stack) ? EMCWorld.HOMO : baseDamage);
    }

    @Override
    public DamageUtils getAttackRange(PlayerEntity player,ItemStack stack) {
        return DamageUtils.of(isInfinity(stack) ? 64 : 0);
    }

    @Override
    public int getMaxLevel() {
        return 30;
    }

    private static boolean isInfinity(ItemStack stack){
        return MekUtils.isInfinityMekaTool(stack);
    }
}
