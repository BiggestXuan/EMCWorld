package biggestxuan.emcworld.common.network.toServer;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/04
 */

import biggestxuan.emcworld.api.item.equipment.weapon.BaseEMCGodSword;
import biggestxuan.emcworld.common.compact.Mekanism.MekUtils;
import biggestxuan.emcworld.common.exception.EMCWorldIllegalPacketException;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Sword.InfinitySword;
import biggestxuan.emcworld.common.registry.EWDamageSource;
import biggestxuan.emcworld.common.registry.EWItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

import static biggestxuan.emcworld.common.events.PlayerEvent.PlayerAttackEvent.getNearEntity;

public class LeftClickPacket {
    public static void encode(LeftClickPacket msg, PacketBuffer buf) {}

    public static LeftClickPacket decode(PacketBuffer buf) {
        return new LeftClickPacket();
    }

    public static void handle(LeftClickPacket msg, Supplier<NetworkEvent.Context> ctx){
        if (ctx.get().getDirection().getReceptionSide().isServer()) {
            if(ctx.get().getSender().getAttackStrengthScale(0) == 1){
                ctx.get().enqueueWork(() -> (
                        (BaseEMCGodSword) EWItems.ICE_SWORD.get()).spawnManaBurst(ctx.get().getSender()));
            }
            try{
                ItemStack stack = ctx.get().getSender().getMainHandItem();
                if(stack.getItem() instanceof InfinitySword || MekUtils.isInfinityMekaTool(stack)){
                    ctx.get().enqueueWork(()-> {
                        List<? extends LivingEntity> canRangeAttack = getNearEntity(ctx.get().getSender(),ctx.get().getSender(),64);
                        for(LivingEntity entity:canRangeAttack){
                            entity.hurt(new EWDamageSource(ctx.get().getSender()).REALLY_PLAYER,114514);
                        }
                    });
                }
            }catch (NullPointerException e){
                throw new EMCWorldIllegalPacketException(msg);
            }

        }
        ctx.get().setPacketHandled(true);
    }
}
