package biggestxuan.emcworld.common.compact.Champions.Affix;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/14
 */

import biggestxuan.emcworld.common.registry.EWEffects;
import top.theillusivec4.champions.api.AffixCategory;

public class EMCFlameAffix extends BaseEMCNegAffix{
    public EMCFlameAffix() {
        super("emc_flaming",AffixCategory.CC, EWEffects.EMC_FLAMING.get());
    }
}
