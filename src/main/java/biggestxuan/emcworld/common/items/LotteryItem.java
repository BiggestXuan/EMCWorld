package biggestxuan.emcworld.common.items;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/05/07
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.data.LotteryData;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.Lottery.Lottery;
import biggestxuan.emcworld.common.utils.Lottery.LotteryMode;
import biggestxuan.emcworld.common.utils.Lottery.LotteryUtils;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.Message;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LotteryItem extends EWItem{
    public LotteryItem(){
        super(new Properties().stacksTo(1));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, @Nonnull List<ITextComponent> p_77624_3_, @Nonnull ITooltipFlag p_77624_4_) {
        String s = "tooltip.emcworld.lottery.";
        CompoundNBT nbt = p_77624_1_.getOrCreateTag();
        Lottery lottery = getLottery(p_77624_1_);
        switch (lottery.getMode()){
            case SIMPLEX:
                s += "simple";
                break;
            case COMPOUND:
                s += "double";
                break;
            case TOW_BALL:
                s += "tb";
        }
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.lottery.mode").append(EMCWorld.tc(s)));
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.lottery.index",nbt.getInt("lottery_index")));
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.lottery.rate",lottery.getRate()));
        if(lottery.getMode() != LotteryMode.TOW_BALL){
            var list = lottery.getNum();
            list.addAll(lottery.getAdd());
            p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.lottery.num",getString(list)));
        }else{
            p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.lottery.tow",getString(lottery.getNum())));
            p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.lottery.ball",getString(lottery.getAdd())));
        }
        if(nbt.getBoolean("lottery_drawn")){
            p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.lottery.finish", MathUtils.format(nbt.getLong("lottery_drawn_emc"))));
        }
    }

    @Override
    public boolean isFoil(@Nonnull ItemStack p_77636_1_) {
        return true;
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> use(@Nonnull World p_77659_1_, PlayerEntity player,@Nonnull Hand p_77659_3_) {
        ItemStack stack = player.getItemInHand(p_77659_3_);
        if(player.level.isClientSide){
            return ActionResult.fail(stack);
        }
        MinecraftServer server = player.getServer();
        assert server != null;
        CompoundNBT nbt = stack.getOrCreateTag();
        int index = nbt.getInt("lottery_index");
        LotteryData data = LotteryData.getInstance(server);
        int serverIndex = data.getIndex();
        List<Integer> list = data.getNum();
        Message.sendMessage(player,EMCWorld.tc("message.lottery.num",getString(list)));
        if(index == serverIndex){
            long emc = LotteryUtils.getLotteryPrice(getLottery(stack),list,server);
            if(emc > 0){
                if(nbt.getBoolean("lottery_drawn")){
                    Message.sendMessage(player,EMCWorld.tc("message.lottery.been"));
                }else{
                    nbt.putLong("lottery_drawn_emc",emc);
                    nbt.putBoolean("lottery_drawn",true);
                    EMCHelper.modifyPlayerEMC(player,new EMCSource.LotteryEMCSource(emc,player,getLottery(stack)),true);
                    data.setStoredEMC(data.getStoredEMC() - emc);
                }
            }else{
                Message.sendMessage(player,EMCWorld.tc("message.lottery.fail"));
            }
        }else if(index > serverIndex){
            Message.sendMessage(player,EMCWorld.tc("message.lottery.no"));
        }else{
            Message.sendMessage(player,EMCWorld.tc("message.lottery.out"));
        }
        return ActionResult.success(stack);
    }

    public static void setLottery(ItemStack stack, Lottery lottery,int index){
        CompoundNBT nbt = stack.getOrCreateTag();
        nbt.putInt("lottery_mode",lottery.getMode().getIndex());
        nbt.putInt("lottery_rate",lottery.getRate());
        nbt.putIntArray("lottery_list",lottery.getNum());
        nbt.putIntArray("lottery_list_add",lottery.getAdd());
        nbt.putInt("lottery_index",index);
        nbt.putBoolean("lottery_drawn",false);
        nbt.putLong("lottery_drawn_emc",0);
    }

    public static Lottery getLottery(ItemStack stack){
        CompoundNBT nbt = stack.getOrCreateTag();
        return new Lottery(t(nbt.getIntArray("lottery_list")),t(nbt.getIntArray("lottery_list_add")),nbt.getInt("lottery_rate"),LotteryMode.getMode(nbt.getInt("lottery_mode")));
    }

    public static String getString(List<Integer> list){
        Collections.sort(list);
        StringBuilder builder = new StringBuilder();
        for(int i : list){
            builder.append(i);
            builder.append(" ");
        }
        return builder.toString();
    }

    public static List<Integer> t(int[] array){
        List<Integer> list = new ArrayList<>();
        for(int i : array){
            list.add(i);
        }
        return list;
    }
}
