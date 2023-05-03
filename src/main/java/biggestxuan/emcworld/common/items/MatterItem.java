package biggestxuan.emcworld.common.items;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/22
 */

import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;

import javax.annotation.Nonnull;

public class MatterItem extends FinalItem{
    public MatterItem(double difficulty) {
        super(difficulty);
    }

    @Nonnull
    @Override
    public Rarity getRarity(ItemStack stack){
        return Rarity.COMMON;
    }
}
