package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/19
 */

import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import vazkii.botania.common.entity.EntityDoppleganger;

@Mixin(EntityDoppleganger.class)
public abstract class GaiaClientMixin {
    @Redirect(method = "bossBarRenderCallback",at = @At(value = "INVOKE",target = "Ljava/lang/Integer;toString(I)Ljava/lang/String;"),remap = false)
    @OnlyIn(Dist.CLIENT)
    public String playerCount(int i){
        if(!ConfigManager.SUNDRY_GAIA_MULTI.get()){
            return "1";
        }
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if(player != null && !player.isDeadOrDying()){
            return String.valueOf(EMCWorldAPI.getInstance().getUtilCapability(player).gaiaPlayer());
        }
        return "0";
    }
}
