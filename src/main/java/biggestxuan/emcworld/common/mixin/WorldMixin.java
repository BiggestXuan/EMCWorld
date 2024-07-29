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

import java.util.IdentityHashMap;
import java.util.Set;
import java.util.WeakHashMap;

@Mixin(World.class)
public abstract class WorldMixin {
    @Shadow
    @Final
    protected Set<TileEntity> blockEntitiesToUnload;

    private final WeakHashMap<TileEntity, Object> ban = new WeakHashMap<>();

    @Inject(method = "tickBlockEntities",at = @At("HEAD"))
    public void tickEntities(CallbackInfo ci) {
        if (!ConfigManager.RAID_BLOCK_SLEEP.get()) {
            return;
        }
        World world = (World) (Object) this;
        MinecraftServer server = world.getServer();
        if (server == null) return;
        ServerWorld serverWorld = server.overworld();
        if (!ban.isEmpty()) {
            IdentityHashMap<TileEntity, Object> temp = new IdentityHashMap<>(ban);
            world.tickableBlockEntities.forEach(temp::remove);
            for (TileEntity te : temp.keySet()) {
                if (!serverWorld.isRaided(te.getBlockPos())) {
                    world.tickableBlockEntities.add(te);
                }
            }
        }
        for (TileEntity tileEntity : world.tickableBlockEntities) {
            Raid raid = serverWorld.getRaidAt(tileEntity.getBlockPos());
            blockEntitiesToUnload.remove(tileEntity);
            if (raid != null && tileEntity instanceof ITickableTileEntity && !(tileEntity instanceof TileEntitySynchronized)) {
                blockEntitiesToUnload.add(tileEntity);
                ban.put(tileEntity, null);
            }
        }
    }
}
