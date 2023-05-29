package biggestxuan.emcworld.common.network.toServer;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/20
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.data.LotteryData;
import biggestxuan.emcworld.common.exception.EMCWorldIllegalPacketException;
import biggestxuan.emcworld.common.items.LotteryItem;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.Lottery.Lottery;
import biggestxuan.emcworld.common.utils.Lottery.LotteryMode;
import biggestxuan.emcworld.common.utils.Lottery.LotteryUtils;
import biggestxuan.emcworld.common.utils.Message;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public class BuyLotteryPacket {
    private final int[] num;
    private final int[] add;
    private final int rate;
    private final int mode;

    public BuyLotteryPacket(int[] num,int[] add,int rate,int mode){
        this.num = num;
        this.add = add;
        this.rate = rate;
        this.mode = mode;
    }

    public static void encode(BuyLotteryPacket msg, PacketBuffer buf) {
        buf.writeVarIntArray(msg.num);
        buf.writeVarIntArray(msg.add);
        buf.writeInt(msg.rate);
        buf.writeInt(msg.mode);
    }

    public static BuyLotteryPacket decode(PacketBuffer buf) {
        return new BuyLotteryPacket(buf.readVarIntArray(), buf.readVarIntArray(), buf.readInt(),buf.readInt());
    }

    public static void handle(BuyLotteryPacket msg, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection().getReceptionSide().isServer()) {
            ServerPlayerEntity player = ctx.get().getSender();
            if(player != null){
                MinecraftServer server = player.server;
                LotteryData data = LotteryData.getInstance(server);
                ItemStack stack = new ItemStack(EWItems.LOTTERY.get(),1);
                Lottery lottery = new Lottery(t(msg.num),t(msg.add),msg.rate, LotteryMode.getMode(msg.mode));
                try{
                    if(LotteryUtils.getLotteryRule(lottery)){
                        throw new EMCWorldIllegalPacketException(player.getScoreboardName());
                    }
                    LotteryItem.setLottery(stack,lottery,data.getIndex()+1);
                    long emc = LotteryUtils.getBuyPrice(lottery);
                    if(EMCHelper.getPlayerEMC(player) >= emc && emc <= 10000000){
                        player.addItem(stack);
                        EMCHelper.modifyPlayerEMC(player,new EMCSource.LotteryEMCSource(-emc,player,lottery),true);
                        data.setStoredEMC(data.getStoredEMC()+(long) (emc*0.3));
                    }
                    if(emc > 10000000){
                        Message.sendMessage(player, EMCWorld.tc("tooltip.emcworld.lottery.max"));
                    }
                }catch (EMCWorldIllegalPacketException e){
                    EMCWorld.LOGGER.error(e.getMessage());
                }
            }
        }
        ctx.get().setPacketHandled(true);
    }

    public static List<Integer> t(int[] array){
        return LotteryItem.t(array);
    }
}
