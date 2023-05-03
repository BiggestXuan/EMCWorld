package biggestxuan.emcworld.common.items;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/20
 */

import biggestxuan.emcworld.api.item.IPlayerDifficultyItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;

import javax.annotation.Nonnull;

public class FinalItem extends EWItem implements IPlayerDifficultyItem {
    private final double difficulty;

    public FinalItem(double difficulty){
        this.difficulty = difficulty;
    }

    @Nonnull
    @Override
    public Rarity getRarity(ItemStack stack){
        return Rarity.EPIC;
    }

    @Override
    public double requireDifficulty() {
        return difficulty;
    }
}
