package biggestxuan.emcworld.common.network.toServer;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/22
 */

import biggestxuan.emcworld.api.trait.ITrait;
import biggestxuan.emcworld.api.trait.TraitType;
import biggestxuan.emcworld.common.traits.TraitUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class TraitActivePacket {
    public static void encode(TraitActivePacket msg, PacketBuffer buf) {}

    public static TraitActivePacket decode(PacketBuffer buf) {
        return new TraitActivePacket();
    }

    public static void handle(TraitActivePacket msg, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection().getReceptionSide().isServer()) {
            ServerPlayerEntity player = ctx.get().getSender();
            if(player == null){
                ctx.get().setPacketHandled(true);
                return;
            }
            List<ItemStack> list = new ArrayList<>();
            list.add(player.getMainHandItem());
            list.add(player.getOffhandItem());
            list.addAll(player.inventory.armor);
            for(ItemStack s : list){
                for(ITrait trait : TraitUtils.getStackTraits(s)){
                    if((trait.getTraitType() == TraitType.ARMOR && !(s.getItem() instanceof ArmorItem)) || (trait.getTraitType() == TraitType.TOOL && s.getItem() instanceof ArmorItem)){
                        continue;
                    }
                    trait.onKeyPress(player,s);
                }
            }
        }
        ctx.get().setPacketHandled(true);
    }
}
