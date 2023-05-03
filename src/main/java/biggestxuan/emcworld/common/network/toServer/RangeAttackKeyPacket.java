package biggestxuan.emcworld.common.network.toServer;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/22
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.equipment.weapon.IRangeAttackWeapon;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Staff.StaffItem;
import biggestxuan.emcworld.common.utils.Message;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class RangeAttackKeyPacket {
    public static void encode(RangeAttackKeyPacket msg, PacketBuffer buf) {}

    public static RangeAttackKeyPacket decode(PacketBuffer buf) {
        return new RangeAttackKeyPacket();
    }

    public static void handle(RangeAttackKeyPacket msg, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection().getReceptionSide().isServer()) {
            ServerPlayerEntity player = ctx.get().getSender();
            if(player == null){
                ctx.get().setPacketHandled(true);
                return;
            }
            ItemStack stack = player.getMainHandItem();
            if(stack.getItem() instanceof IRangeAttackWeapon){
                IRangeAttackWeapon weapon = (IRangeAttackWeapon) stack.getItem();
                if(weapon.getAttackRange(player,stack).total() > 0){
                    weapon.switchAttackMode(player,stack);
                    Message.sendMessage(player, EMCWorld.tc("tooltip.emcworld.attack_range").append(EMCWorld.tc(weapon.getAttackMode(stack).getName())));
                }
            }
            if(stack.getItem() instanceof StaffItem){
                StaffItem.switchStaffAttackMode(stack);
                Message.sendMessage(player, EMCWorld.tc("tooltip.emcworld.attack_range").append(EMCWorld.tc(StaffItem.getMode(stack).getName())));
            }
        }
        ctx.get().setPacketHandled(true);
    }
}
