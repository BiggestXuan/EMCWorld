package biggestxuan.emcworld.common.mixin;

import com.github.alexthe666.rats.RatsMod;
import com.github.alexthe666.rats.server.entity.ratlantis.RatlantisEntityRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/06/19
 */

@Mixin(value = RatsMod.class,remap = false)
public abstract class RatsModMixin {
    @Inject(method = "onBiomeLoadFromJSON",at = @At("HEAD"))
    public void _inject(BiomeLoadingEvent event, CallbackInfo ci){
        if (event.getName() != null) {
            if(event.getName().getPath().equals("ratlantis_biome")){
                event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(RatlantisEntityRegistry.FERAL_RATLANTEAN, 100,30,30));
            }
        }
    }
}
