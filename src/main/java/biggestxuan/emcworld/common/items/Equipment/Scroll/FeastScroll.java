package biggestxuan.emcworld.common.items.Equipment.Scroll;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/8/1
 */
public class FeastScroll extends NewerScroll{

    int max;

    public FeastScroll(int max) {
        super(3,0);
        this.max = max;
    }

    @Override
    public int getActWeight(ItemStack stack, ItemStack target, TileEntity tileEntity) {
        return (int) (Math.random() * max);
    }
}
