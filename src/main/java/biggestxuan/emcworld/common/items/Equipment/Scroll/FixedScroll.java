package biggestxuan.emcworld.common.items.Equipment.Scroll;

import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/8/1
 */
public abstract class FixedScroll extends NewerScroll{
    public FixedScroll() {
        super(3,0);
    }

    abstract double getRate(ItemStack stack,ItemStack target,TileEntity entity);

    @Override
    public int getActWeight(ItemStack stack, ItemStack target, TileEntity tileEntity) {
        Item item = target.getItem();
        if(item instanceof IUpgradeableItem){
            IUpgradeableItem upgradeableItem = (IUpgradeableItem)item;
            int require = upgradeableItem.getWeightRequired(target);
            return (int) (require * getRate(stack, target, tileEntity));
        }
        return super.getActWeight(stack, target, tileEntity);
    }

    public static class EnlighteningScroll extends FixedScroll{

        @Override
        double getRate(ItemStack stack, ItemStack target, TileEntity entity) {
            double base  = 0.05;
            World world = entity.getLevel();
            if(world != null){
                int l = world.getLightEmission(entity.getBlockPos());
                return base + 0.005 * l / 15;
            }
            return 0;
        }
    }

    public static class MysticScroll extends FixedScroll{

        @Override
        double getRate(ItemStack stack, ItemStack target, TileEntity entity) {
            double base = 0.12;
            World world = entity.getLevel();
            if(world != null){
                int p = 0;
                BlockPos pos = entity.getBlockPos();
                int y = pos.getY();
                return base - (p  * 0.01 / 7) - (0.01 * y / 255);
            }
            return 0;
        }
    }

    public static class AzureScroll extends FixedScroll{

        @Override
        double getRate(ItemStack stack, ItemStack target, TileEntity entity) {
            double base = 0.15;
            World world = entity.getLevel();
            if(world != null){
                int p = 0;
                BlockPos pos = entity.getBlockPos();
                int y = pos.getY();
                return base + (7 - p) * (0.015 / 7) + (0.015 * y / 255);
            }
            return 0;
        }
    }


}
