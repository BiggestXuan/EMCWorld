package biggestxuan.emcworld.common.network;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/05
 */

import biggestxuan.emcworld.common.blocks.WeaponUpgradeBlock.WeaponUpgradeBlockTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class WeaponCoreButtonPacket {
    private final BlockPos pos;

    public WeaponCoreButtonPacket(BlockPos pos){
        this.pos = pos;
    }

    public static void encode(WeaponCoreButtonPacket msg, PacketBuffer buf) {
        buf.writeBlockPos(msg.pos);
    }

    public static WeaponCoreButtonPacket decode(PacketBuffer buf) {
        return new WeaponCoreButtonPacket(buf.readBlockPos());
    }

    public static void handle(WeaponCoreButtonPacket msg, Supplier<NetworkEvent.Context> context){
        context.get().enqueueWork(()->{
            if(context.get().getDirection().getReceptionSide().isServer()){
                PlayerEntity player = context.get().getSender();
                if(player == null) return;
                TileEntity tile = player.level.getBlockEntity(msg.pos);
                if(tile instanceof WeaponUpgradeBlockTileEntity){
                    WeaponUpgradeBlockTileEntity entity = (WeaponUpgradeBlockTileEntity) tile;
                    entity.setStates(WeaponUpgradeBlockTileEntity.States.STARTING);
                }
            }
        });
        context.get().setPacketHandled(true);
    }
}
