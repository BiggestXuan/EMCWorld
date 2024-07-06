package biggestxuan.emcworld.common.compact.AstralSorcery;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/10
 */

import biggestxuan.emcworld.common.registry.EWEffects;
import hellfirepvp.astralsorcery.common.constellation.IWeakConstellation;
import hellfirepvp.astralsorcery.common.constellation.mantle.MantleEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.common.ForgeConfigSpec;

public class EMCMantleEffects extends MantleEffect{
    public static final EMCConfig CONFIG =  new EMCConfig();

    public EMCMantleEffects(IWeakConstellation constellation) {
        super(constellation);
    }

    @Override
    public Config getConfig() {
        return CONFIG;
    }

    @Override
    protected boolean usesTickMethods() {
        return true;
    }

    @Override
    protected void tickServer(PlayerEntity player){
        if(player.level.isClientSide) return;
        if(player.level.getDayTime() % 20 == 0){
            player.addEffect(new EffectInstance(EWEffects.EMC_PROTECT.get(),100,0));
        }
    }

    public static class EMCConfig extends Config{

        public EMCConfig() {
            super("emc");
        }

        @Override
        public void createEntries(ForgeConfigSpec.Builder builder) {
            builder.push(this.getPath());
            super.createEntries(builder);
        }
        @Override
        protected String translationKey(String key) {
            return "config." + this.getFullPath() + '.' + key;
        }
    }
}
