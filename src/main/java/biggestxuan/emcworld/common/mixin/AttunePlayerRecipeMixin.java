package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.utils.Message;
import hellfirepvp.astralsorcery.common.crafting.nojson.attunement.AttunePlayerRecipe;
import hellfirepvp.astralsorcery.common.tile.TileAttunementAltar;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/12/3
 */

@Mixin(value = AttunePlayerRecipe.class,remap = false)
public abstract class AttunePlayerRecipeMixin {
    @Shadow
    @Nullable
    private static ServerPlayerEntity findEligiblePlayer(TileAttunementAltar altar) {
        return null;
    }

    @Inject(method = "canStartCrafting",at = @At("HEAD"),cancellable = true)
    public void _inject(TileAttunementAltar altar, CallbackInfoReturnable<Boolean> cir){
        var player = findEligiblePlayer(altar);
        World world = altar.getLevel();
        if(player != null && world != null && world.getDayTime() % 20 == 0){
            Message.sendMessage(player, EMCWorld.tc("message.emcworld.attune_disable").setStyle(Style.EMPTY.withColor(TextFormatting.RED)));
        }
        cir.setReturnValue(false);
    }
}
