package biggestxuan.emcworld.api.item;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/27
 */

import biggestxuan.emcworld.common.registry.EWEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

public interface ICostEMCItem {
    default double getEMCCostRate(){
        return 1D;
    }

    double costEMCWhenAttack(ItemStack stack);

    default double costEMCWhenAttackActually(ItemStack stack){
        int level = EnchantmentHelper.getItemEnchantmentLevel(EWEnchantments.EMC_REDUCE.get(),stack) ;
        double base = costEMCWhenAttack(stack);
        if(level > 0){
            for (int i = 0; i < level; i++) {
                base *= 0.95;
            }
        }
        return base;
    }
}
