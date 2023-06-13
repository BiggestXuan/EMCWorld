package biggestxuan.emcworld.common.items;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/22
 */

import dev.latvian.mods.projectex.Matter;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;

import javax.annotation.Nonnull;

public class MatterItem extends FinalItem{
    private final Matter matter;
    public MatterItem(Matter matter,double difficulty){
        super(difficulty);
        this.matter = matter;
    }
    public MatterItem(Matter matter) {
        this(matter,0.5);
    }

    @Nonnull
    @Override
    public Rarity getRarity(ItemStack stack){
        return Rarity.COMMON;
    }
}
