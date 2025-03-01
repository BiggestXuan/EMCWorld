package biggestxuan.emcworld.common.items.Equipment.Scroll;

import biggestxuan.emcworld.api.item.IEMCGod;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/8/1
 */
public class EMCGodScroll extends NewerScroll{
    public EMCGodScroll(float difficulty, int weight) {
        super(difficulty, weight);
    }

    @Override
    public int getActWeight(ItemStack stack, ItemStack target, TileEntity tileEntity) {
        if(target.getItem() instanceof IEMCGod){
            return weight * 3;
        }
        return super.getActWeight(stack, target, tileEntity);
    }

}
