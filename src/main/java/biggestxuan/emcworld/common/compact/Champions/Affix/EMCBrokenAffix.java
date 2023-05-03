package biggestxuan.emcworld.common.compact.Champions.Affix;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/14
 */

import biggestxuan.emcworld.common.registry.EWEffects;
import top.theillusivec4.champions.api.AffixCategory;

public class EMCBrokenAffix extends BaseEMCNegAffix {
    public EMCBrokenAffix() {
        super("emc_broken",AffixCategory.CC,EWEffects.EMC_BROKEN.get());
    }
}
