package biggestxuan.emcworld.common.compact.Champions;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/14
 */

import biggestxuan.emcworld.common.compact.Champions.Affix.*;
import top.theillusivec4.champions.Champions;
import top.theillusivec4.champions.api.IChampionsApi;

public class ChampionsInit {
    public static void init(){
        IChampionsApi api = Champions.API;
        api.registerAffixes(
                new EMCBrokenAffix(),new EMCFlameAffix(),new EMCProtectAffix(),new EMCShieldFlamingAffix(),new HealingAffix()
        );
    }
}
