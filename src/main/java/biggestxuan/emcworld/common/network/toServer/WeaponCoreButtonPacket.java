package biggestxuan.emcworld.common.network.toServer;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/05
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.blocks.tile.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class WeaponCoreButtonPacket {
    private final BlockPos pos;
    private final int mode;

    public WeaponCoreButtonPacket(BlockPos pos,int mode){
        this.pos = pos;
        this.mode = mode;
    }

    public static void encode(WeaponCoreButtonPacket msg, PacketBuffer buf) {
        buf.writeBlockPos(msg.pos);
        buf.writeInt(msg.mode);
    }

    public static WeaponCoreButtonPacket decode(PacketBuffer buf) {
        return new WeaponCoreButtonPacket(buf.readBlockPos(),buf.readInt());
    }

    public int getMode(){
        return mode;
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
                if(tile instanceof SuperEMCTileEntity){
                    SuperEMCTileEntity tileEntity = (SuperEMCTileEntity) tile;
                    tileEntity.start();
                }
                if(tile instanceof PrefixTileEntity){
                    PrefixTileEntity tileEntity = (PrefixTileEntity) tile;
                    if(msg.mode == 1){
                        tileEntity.setAllIn();
                    }
                    tileEntity.setPlayer(player);
                    tileEntity.start();
                }
                if(tile instanceof EMCOreCoreTileEntity){
                    EMCOreCoreTileEntity tileEntity = (EMCOreCoreTileEntity) tile;
                    tileEntity.setOreLevel(msg.mode);
                }
                if(tile instanceof EMCCoreTileEntity){
                    EMCCoreTileEntity core = (EMCCoreTileEntity) tile;
                    core.work();
                }
            }
        });
        context.get().setPacketHandled(true);
    }
}
