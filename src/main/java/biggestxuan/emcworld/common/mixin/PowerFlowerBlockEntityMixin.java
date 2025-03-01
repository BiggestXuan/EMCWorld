package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import dev.latvian.mods.projectex.block.PowerFlowerBlock;
import dev.latvian.mods.projectex.block.entity.PowerFlowerBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.world.ForgeChunkManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/08/22
 */

@EMCWorldSince("1.0.3")
@Mixin(PowerFlowerBlockEntity.class)
public abstract class PowerFlowerBlockEntityMixin extends TileEntity implements ITickableTileEntity {
    private final Logger logger = EMCWorld.LOGGER;

    @Shadow (remap = false)
    public int tick;

    @Shadow (remap = false)
    public UUID owner;

    public PowerFlowerBlockEntityMixin(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
    }

    /**
     * @author Biggest_Xuan
     * @reason o:
     */
    @Overwrite
    public void setChanged(){
        super.setChanged();
    }

    /**
     * @author Biggest_Xuan
     * @reason o:
     */
    @Overwrite(remap = false)
    public void onLoad() {
        super.onLoad();
    }

    /**
     * @author Biggest_Xuan
     * @reason Try to fix a bug.
     */
    @Override
    @Overwrite
    public void tick(){
        PowerFlowerBlockEntity power = (PowerFlowerBlockEntity) (Object) this;
        if (power.getLevel() != null && !power.getLevel().isClientSide){
            tick = 0;
            if(power.getLevel().getDayTime() % 20 == 0){
                BlockState state = power.getBlockState();
                if (state.getBlock() instanceof PowerFlowerBlock){
                    long eff = ((PowerFlowerBlock)state.getBlock()).matter.getPowerFlowerOutput();
                    ServerPlayerEntity player = power.getLevel().getServer().getPlayerList().getPlayer(owner);
                    if(player != null){
                        player.getCapability(EMCWorldCapability.UTIL).ifPresent(cap -> {
                            long cache = cap.getPowerFlowerCache();
                            cap.setPowerFlowerCache(cache + eff);
                        });
                    }
                }
            }
        }
    }
}
