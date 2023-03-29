package biggestxuan.emcworld.common.compact.Champions.Affix;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/15
 */

import biggestxuan.emcworld.common.registry.EWDamageSource;
import net.minecraft.util.DamageSource;
import top.theillusivec4.champions.api.AffixCategory;
import top.theillusivec4.champions.api.IChampion;
import top.theillusivec4.champions.common.affix.core.BasicAffix;

public class EMCProtectAffix extends BasicAffix {
    public EMCProtectAffix() {
        super("emc_protect",AffixCategory.DEFENSE);
    }

    @Override
    public float onHurt(IChampion champion, DamageSource source, float amount, float newAmount) {
        if(source.isMagic()){
            return 0;
        }
        if(EWDamageSource.isReallyDamage(source)){
            return 0.25F * newAmount;
        }
        return newAmount;
    }
}
