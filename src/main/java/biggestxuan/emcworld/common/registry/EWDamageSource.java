package biggestxuan.emcworld.common.registry;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/07
 */

import biggestxuan.emcworld.EMCWorld;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.util.DamageSource;

@MethodsReturnNonnullByDefault
public class EWDamageSource extends DamageSource {
    public static final EWDamageSource REALLY = new EWDamageSource("really").bypassArmor().bypassInvul().bypassMagic();

    public EWDamageSource(String p_i1566_1_) {
        super(EMCWorld.MODID+"."+p_i1566_1_);
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
}
