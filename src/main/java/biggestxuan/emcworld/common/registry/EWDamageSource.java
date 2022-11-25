package biggestxuan.emcworld.common.registry;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/07
 */

import biggestxuan.emcworld.EMCWorld;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

import javax.annotation.Nullable;

@MethodsReturnNonnullByDefault
public class EWDamageSource extends DamageSource {
    private final PlayerEntity player;
    public static final EWDamageSource REALLY = new EWDamageSource("really").bypassArmor().bypassInvul().bypassMagic();

    public EWDamageSource(String p_i1566_1_) {
        super(EMCWorld.MODID+"."+p_i1566_1_);
        this.player = null;
    }

    public EWDamageSource(String p_i1566_1_,PlayerEntity player){
        super(p_i1566_1_);
        this.player = player;
    }

    @Nullable
    public PlayerEntity getPlayer() {
        return player;
    }

    @Override
    public EWDamageSource bypassArmor() {
        super.bypassArmor();
        return this;
    }

    @Override
    public EWDamageSource bypassInvul() {
        super.bypassInvul();
        return this;
    }

    @Override
    public EWDamageSource bypassMagic() {
        super.bypassMagic();
        return this;
    }

    public static class ReallyDamage extends EWDamageSource{

        public ReallyDamage(PlayerEntity player) {
            super("really",player);
        }
    }
}
