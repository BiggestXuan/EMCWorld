package biggestxuan.emcworld.common.items.CDReduceScroll;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.item.IEMCInfuserItem;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.api.item.equipment.IStarItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/09/26
 */

@EMCWorldSince("1.0.5")
public class CommonCDReduceScrollItem extends CDReduceScrollItem implements IUpgradeableItem, IPrefixItem, IStarItem, IEMCInfuserItem {
    public CommonCDReduceScrollItem() {
        super(1);
    }

    @Override
    public CDScrollType[] getCanReduceType() {
        return CDScrollType.values();
    }

    @Override
    public long getCanReduceCD(ItemStack stack, PlayerEntity player, CDScrollType type) {
        if(type.getWeight() == 0){
            return 0;
        }
        return (int) (getInfuser(stack) / 1000000 / type.getWeight() * 200);
    }

    @Override
    public int getMaxLevel() {
        return 16;
    }

    @Override
    public int getWeightRequired(ItemStack stack) {
        return IUpgradeableItem.super.getWeightRequired(stack) * 8;
    }

    @Override
    protected boolean reduceCD(ItemStack stack, PlayerEntity player) {
        if(super.reduceCD(stack, player)){
            setInfuser(stack,0);
            return true;
        }
        return false;
    }

    @Override
    public double getBuffer(ItemStack stack) {
        double buffer = 1;
        if(stack.getItem() instanceof IStarItem){
            IStarItem item = (IStarItem) stack.getItem();
            double base = 0.4d;
            for (int i = 0; i < item.getStar(stack); i++) {
                buffer += base;
                base += 0.65d;
            }
        }
        return buffer;
    }

    @Override
    public long getMaxInfuser(ItemStack stack) {
        return (long) (10_000_000 * Math.pow(1.13,getLevel(stack)) * getBuffer(stack) * (1 + 0.1 * getPrefix(stack).getLevel()));
    }
}
