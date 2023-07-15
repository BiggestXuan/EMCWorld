package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.client.screen.CompassScreen.FindBiomeScreen;
import biggestxuan.emcworld.client.screen.CompassScreen.TeleportBiomeScreen;
import com.chaosthedude.naturescompass.gui.NaturesCompassScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/06/24
 */

@Mixin(NaturesCompassScreen.class)
@EMCWorldSince("0.9.0")
public abstract class NaturesCompassScreenMixin extends Screen {

    protected NaturesCompassScreenMixin(ITextComponent p_i51108_1_) {
        super(p_i51108_1_);
    }

    @Inject(method = "searchForBiome",at = @At("HEAD"),remap = false,cancellable = true)
    public void _inject(Biome biome, CallbackInfo ci){
        Minecraft.getInstance().setScreen(new FindBiomeScreen(biome));
        ci.cancel();
    }

    @Inject(method = "teleport",at = @At("HEAD"),remap = false,cancellable = true)
    public void _inject(CallbackInfo ci){
        Minecraft.getInstance().setScreen(new TeleportBiomeScreen());
        ci.cancel();
    }
}
