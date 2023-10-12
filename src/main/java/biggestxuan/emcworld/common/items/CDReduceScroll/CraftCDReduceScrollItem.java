package biggestxuan.emcworld.common.items.CDReduceScroll;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/09/26
 */

@EMCWorldSince("1.0.5")
public class CraftCDReduceScrollItem extends CDReduceScrollItem{
    public CraftCDReduceScrollItem() {
        super(16);
    }

    @Override
    public CDScrollType[] getCanReduceType() {
        return new CDScrollType[]{CDScrollType.CRAFT};
    }

    @Override
    protected boolean reduceCD(ItemStack stack, PlayerEntity player) {
        if(super.reduceCD(stack, player)){
            stack.shrink(1);
            return true;
        }
        return false;
    }

    @Override
    public long getCanReduceCD(ItemStack stack, PlayerEntity player, CDScrollType type) {
        return ConfigManager.SUNDRY_CRAFT_CD.get();
    }
}
