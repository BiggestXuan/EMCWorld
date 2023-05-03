package biggestxuan.emcworld.common.blocks.VisConversionBlock;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/30
 */

import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.registry.EWTileEntityTypes;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.MathUtils;
import de.ellpeck.naturesaura.api.aura.chunk.IAuraChunk;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

public class VisConversionTileEntity extends TileEntity implements ITickableTileEntity {
    public VisConversionTileEntity() {
        super(EWTileEntityTypes.VisConversionTileEntity.get());
    }

    @Override
    public void tick() {
        if(this.level.isClientSide) return;
        List<? extends PlayerEntity> players = this.level.players();
        List<PlayerEntity> actPlayers = new ArrayList<>();
        for(PlayerEntity p :players){
            if(MathUtils.isTwoLivingDistance(p.getCommandSenderWorld(),this.level,p.position(),this.worldPosition,5) && EMCHelper.getPlayerEMC(p) >= 100){
                actPlayers.add(p);
            }
        }
        int baseConversion = (int) (5000 * 1.0d / MathUtils.difficultyLoss());
        for(PlayerEntity p:actPlayers){
            EMCHelper.modifyPlayerEMC(p,new EMCSource.VisCoreEMCSource(-1000,p,getBlockPos(),0),false);
            IAuraChunk.getAuraChunk(this.level,this.worldPosition).storeAura(this.worldPosition,baseConversion);
        }
    }

}
