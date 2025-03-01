package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import mekanism.api.Coord4D;
import mekanism.api.MekanismAPI;
import mekanism.api.radiation.IRadiationManager;
import mekanism.client.render.RenderTickHandler;
import mekanism.common.lib.radiation.RadiationManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/06/30
 */

@Mixin(value = RenderTickHandler.class,remap = false)
public abstract class RenderTickHandlerMixin {
    @Shadow @Final public Minecraft minecraft;

    @Inject(method = "tickEnd",at = @At(value = "INVOKE",target = "Lnet/minecraft/entity/player/PlayerEntity;getCapability(Lnet/minecraftforge/common/capabilities/Capability;)Lnet/minecraftforge/common/util/LazyOptional;"), cancellable = true)
    public void _inject(TickEvent.RenderTickEvent event, CallbackInfo ci){
        IRadiationManager manager = RadiationManager.INSTANCE;
        PlayerEntity player = minecraft.player;
        try{
            IUtilCapability cap = EMCWorldAPI.getInstance().getUtilCapability(player);
            if(cap.getShield() * 100 >= manager.getRadiationLevel(new Coord4D(player))){
                ci.cancel();
            }
        }catch (NullPointerException ignored){

        }
    }
}
