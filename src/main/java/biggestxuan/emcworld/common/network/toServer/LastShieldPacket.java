package biggestxuan.emcworld.common.network.toServer;

/**
 * EMC WORLD MOD
 * @Author Biggest_Xuan
 * 2023/1/6
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.api.item.equipment.armor.IEMCShieldArmor;
import biggestxuan.emcworld.common.utils.Message;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public class LastShieldPacket {
    public static void encode(LastShieldPacket message, PacketBuffer bf){
    }

    public static LastShieldPacket decode(PacketBuffer bf){
        return new LastShieldPacket();
    }

    public static void handle(LastShieldPacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(()-> {
            boolean flag = true;
            boolean showMessage = true;
            ServerPlayerEntity player = context.get().getSender();
            if(player != null){
                List<ItemStack> stack = player.inventory.armor;
                IUtilCapability cap = EMCWorldAPI.getInstance().getUtilCapability(player);
                if(stack.size() == 4){
                    for(ItemStack s : stack){
                        if(s.getItem() instanceof IEMCShieldArmor){
                            IEMCShieldArmor armor = (IEMCShieldArmor) s.getItem();
                            if(armor.getInfuser(s) < 10000000L){
                                if(cap.isLastShield()){
                                    cap.setLastShield(false);
                                }
                                showMessage = false;
                                flag = false;
                                break;
                            }
                        }else flag = false;
                    }
                }
                if(flag){
                    if(cap.isLastShield()){
                        Message.sendMessage(player, EMCWorld.tc("message.shield.off"));
                        cap.setLastShield(false);
                    }else{
                        Message.sendMessage(player, EMCWorld.tc("message.shield.on"));
                        cap.setLastShield(true);
                    }
                }else {
                    //if(showMessage){
                        Message.sendMessage(player,EMCWorld.tc("message.shield.deny"));
                    //}
                }
            }
        });
        context.get().setPacketHandled(true);
    }
}
