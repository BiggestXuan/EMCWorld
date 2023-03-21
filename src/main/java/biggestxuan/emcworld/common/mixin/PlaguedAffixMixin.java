package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/15
 */

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.champions.api.IChampion;
import top.theillusivec4.champions.common.affix.PlaguedAffix;
import top.theillusivec4.champions.common.config.ChampionsConfig;

@Mixin(PlaguedAffix.class)
public abstract class PlaguedAffixMixin {
    @Inject(
            method = "onUpdate",
            at = @At(value = "FIELD", target = "Ltop/theillusivec4/champions/common/config/ChampionsConfig;plaguedRange:I",ordinal = 1),
            remap = false,
            cancellable = true
    )
    public void onUpdate(IChampion champion, CallbackInfo ci){
        LivingEntity livingEntity = champion.getLivingEntity();
        livingEntity.removeEffectNoUpdate(ChampionsConfig.plaguedEffect.getEffect());
        ci.cancel();
    }
}
