package biggestxuan.emcworld.common.registry;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/07
 */

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

import javax.annotation.Nullable;

@MethodsReturnNonnullByDefault
public class EWDamageSource extends DamageSource {
    private PlayerEntity player;
    public final EWDamageSource REALLY_PLAYER = this.bypassArmor().bypassMagic();
    public static final EWDamageSource REALLY = new EWDamageSource("really").bypassArmor().bypassInvul().bypassMagic();

    public EWDamageSource(String p_i1566_1_){
        super(p_i1566_1_);
    }

    public EWDamageSource(PlayerEntity player){
        super("really");
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

    public static boolean isReallyDamage(DamageSource source){
        return source.equals(REALLY) || source instanceof EWDamageSource;
    }
}
