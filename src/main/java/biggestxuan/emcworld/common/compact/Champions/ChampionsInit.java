package biggestxuan.emcworld.common.compact.Champions;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/14
 */

import biggestxuan.emcworld.common.compact.Champions.Affix.*;
import biggestxuan.emcworld.common.config.ConfigManager;
import top.theillusivec4.champions.Champions;
import top.theillusivec4.champions.api.IChampionsApi;

public class ChampionsInit {
    public static void init(){
        IChampionsApi api = Champions.API;
        if(ConfigManager.CHAMPIONS_EMC_BROKEN.get()) api.registerAffixes(new EMCBrokenAffix());
        if(ConfigManager.CHAMPIONS_EMC_FLAMING.get()) api.registerAffixes(new EMCFlameAffix());
        if(ConfigManager.CHAMPIONS_EMC_PROTECTING.get()) api.registerAffixes(new EMCProtectAffix());
        if(ConfigManager.CHAMPIONS_SHIELD_FLAMING.get()) api.registerAffixes(new EMCShieldFlamingAffix());
        if(ConfigManager.CHAMPIONS_HEALING.get()) api.registerAffixes(new HealingAffix());
    }
}
