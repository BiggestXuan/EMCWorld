package biggestxuan.emcworld.api.item.base;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/04
 */

import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.items.EWItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BaseGetStageItem extends EWItem {
    private final String stage;
    public BaseGetStageItem(String stage){
        this.stage = stage;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, @Nonnull Hand p_77659_3_){
        ItemStack stack = p_77659_2_.getItemInHand(p_77659_3_);
        if(p_77659_1_.isClientSide) return ActionResult.fail(stack);
        if(GameStageManager.hasStage(p_77659_2_,stage)){
            return ActionResult.fail(stack);
        }
        stack.shrink(1);
        GameStageManager.addStage(p_77659_2_,stage);
        return ActionResult.success(stack);
    }
}
