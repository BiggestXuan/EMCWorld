package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/06
 */

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.darkhax.gamestages.data.IStageData;
import net.darkhax.itemstages.Restriction;
import net.darkhax.itemstages.RestrictionManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RestrictionManager.class)
public class RestrictionManagerMixin {
    @Final
    @Shadow(remap = false)
    private final Multimap<String, Restriction> restrictions = HashMultimap.create();

   /* @Inject(method = "getRestriction*",at = @At("HEAD"),remap = false,cancellable = true)
    public void getRestriction(PlayerEntity player, IStageData stageData, ItemStack stack, CallbackInfoReturnable<Restriction> ci){

    }*/
}
