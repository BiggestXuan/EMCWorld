package biggestxuan.emcworld.common.blocks.tile;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/30
 */

import net.minecraft.util.IIntArray;

public class AdvancedUpdateNumber implements IIntArray {
    int i = 0;

    @Override
    public int get(int p_221476_1_) {
        return i;
    }

    @Override
    public void set(int p_221477_1_, int p_221477_2_) {
        i = p_221477_2_;
    }

    @Override
    public int getCount() {
        return 1;
    }
}
