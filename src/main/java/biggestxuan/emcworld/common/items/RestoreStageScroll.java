package biggestxuan.emcworld.common.items;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.compact.ScalingHealth.DifficultyHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.DifficultySetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/06/21
 */

public class RestoreStageScroll extends EWItem{
    public RestoreStageScroll(){
        super(Integer.parseInt("1"));
    }

    @Override
    public boolean canBeHurtBy(DamageSource p_234685_1_) {
        return false;
    }

    @Override
    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
        ItemStack stack = p_77659_2_.getItemInHand(p_77659_3_);
        CompoundNBT nbt = stack.getOrCreateTag();
        if(p_77659_2_.getScoreboardName().equals(nbt.getString("player")) && p_77659_2_.getUUID().equals(nbt.getUUID("uuid")) && !p_77659_1_.isClientSide){
            String stage = nbt.getString("stage");
            if(!GameStageManager.hasStage(p_77659_2_,stage)){
                GameStageManager.addStage(p_77659_2_,stage);
                for(DifficultySetting ds : DifficultySetting.values()){
                    if(stage.equals(ds.getGameStage().toLowerCase())){
                        DifficultyHelper.lossPlayerDifficulty(p_77659_2_,ds.getDifficulty() * ConfigManager.SUNDRY_DIFFICULTY_STAGE.get());
                    }
                }
                return ActionResult.success(stack);
            }
        }
        return ActionResult.fail(stack);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        CompoundNBT nbt = p_77624_1_.getOrCreateTag();
        String stage = nbt.getString("stage");
        String player = nbt.getString("player");
        String uuid = "";
        try{
            uuid = nbt.getUUID("uuid").toString();
        }catch (Exception ignored){

        }
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.restore_scroll1"));
        if(!stage.equals("") & !player.equals("") & !uuid.equals("")){
            p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.restore_scroll2",stage));
            p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.restore_scroll3",player));
            p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.restore_scroll4",uuid));

        }
    }

    public static ItemStack init(PlayerEntity player, String stage){
        ItemStack stack = new ItemStack(EWItems.RESTORE_SCROLL.get());
        stack.setCount(1);
        CompoundNBT nbt = stack.getOrCreateTag();
        nbt.putString("stage",stage);
        nbt.putString("player",player.getScoreboardName());
        nbt.putUUID("uuid",player.getUUID());
        return stack;
    }
}
