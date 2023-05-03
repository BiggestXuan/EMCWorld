package biggestxuan.emcworld.api.item;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/10
 */

import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;

public abstract class EMCWorldBaseItem extends Item implements ICostEMCItem {
    public EMCWorldBaseItem() {
        super(new Properties().tab(EWCreativeTabs.EW_CREATIVE_TAB));
    }

    public EMCWorldBaseItem(Properties properties){
        super(properties);
    }

    public EMCWorldBaseItem(int rarity){
        super(new Properties().tab(EWCreativeTabs.EW_CREATIVE_TAB).rarity(getRarity(rarity)));
    }

    private static Rarity getRarity(int r){
        switch (r){
            case 1:
                return Rarity.UNCOMMON;
            case 2:
                return Rarity.RARE;
            case 3:
                return Rarity.EPIC;
            default:
                return Rarity.COMMON;
        }
    }

    @Override
    public double costEMCWhenAttack(ItemStack stack) {
        return 1d;
    }

    @Override
    public double getEMCCostRate(){
        if(MathUtils.isMaxDifficulty()) return 1.25d;
        return 1d;
    }
}
