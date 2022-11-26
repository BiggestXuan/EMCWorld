package biggestxuan.emcworld.common.network;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/22
 */

import biggestxuan.emcworld.common.items.Equipment.Weapon.Staff.StaffItem;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class StaffAttackPacket {
    public static void encode(StaffAttackPacket msg, PacketBuffer buf) {}

    public static StaffAttackPacket decode(PacketBuffer buf) {
        return new StaffAttackPacket();
    }

    public static void handle(StaffAttackPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(()->{
            if(ctx.get().getDirection().getReceptionSide().isServer()) {
                ServerPlayerEntity player = ctx.get().getSender();
                ItemStack stack = player.getMainHandItem();
                if(stack.getItem() instanceof StaffItem && player.getAttackStrengthScale(0) == 1){
                    StaffItem item = (StaffItem) stack.getItem();
                    item.spawnManaBurst(player);
                    stack.setDamageValue(stack.getDamageValue()+1);
                    if(stack.getMaxDamage() - stack.getDamageValue() <= -1){
                        stack.shrink(1);
                    }
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
