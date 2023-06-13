package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/21
 */

import biggestxuan.emcworld.common.config.ConfigManager;
import hellfirepvp.astralsorcery.common.tile.base.TileEntitySynchronized;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.raid.Raid;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.Set;

@Mixin(World.class)
public abstract class WorldMixin {
    @Shadow
    @Final
    protected final Set<TileEntity> blockEntitiesToUnload = java.util.Collections.newSetFromMap(new java.util.IdentityHashMap<>());

    private final Set<TileEntity> ban = new HashSet<>();

    @Inject(method = "tickBlockEntities",at = @At("HEAD"))
    public void tickEntities(CallbackInfo ci){
        if(!ConfigManager.RAID_BLOCK_SLEEP.get()){
            return;
        }
        World world = (World)(Object)this;
        MinecraftServer server = world.getServer();
        if(server == null) return;
        ServerWorld serverWorld = server.overworld();
        if(ban.size() != 0){
            for(TileEntity entity:ban){
                if(!world.tickableBlockEntities.contains(entity) && !serverWorld.isRaided(entity.getBlockPos())){
                    world.tickableBlockEntities.add(entity);
                }
            }
        }
        for(TileEntity tileEntity : world.tickableBlockEntities){
            Raid raid = serverWorld.getRaidAt(tileEntity.getBlockPos());
            blockEntitiesToUnload.remove(tileEntity);
            if(raid != null && tileEntity instanceof ITickableTileEntity && !(tileEntity instanceof TileEntitySynchronized)){
                blockEntitiesToUnload.add(tileEntity);
                ban.add(tileEntity);
            }
        }
    }
}
