package biggestxuan.emcworld.common.utils;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/18
 */

import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;

public class VillagerTradeBuilder {
    private static final int xp = 3;

    private final ItemStack buy1;
    private final ItemStack buy2;
    private final ItemStack sell;
    private final int max;

    public VillagerTradeBuilder(ItemStack buy1,ItemStack buy2,ItemStack sell,int max){
        this.buy1 = buy1;
        this.buy2 = buy2;
        this.sell = sell;
        this.max = max;
    }

    public VillagerTradeBuilder(ItemStack buy,ItemStack sell,int max){
        this(buy,ItemStack.EMPTY,sell,max);
    }

    public VillagerTrades.ITrade get(){
        return (p_221182_1_, p_221182_2_) -> new MerchantOffer(buy1,buy2,sell,0,max,xp,0.05f,1);
    }

}
