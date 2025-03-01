package biggestxuan.emcworld.common.raid;

import biggestxuan.emcworld.common.raid.effects.*;
import net.minecraft.world.raid.Raid;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/12
 */
public class RaidEffectsRegistry {
    public static void init(Raid raid){
        if(RaidEffect.effects.isEmpty()){
            new Aevitas(raid);
            new Armara(raid);
            new Bootes(raid);
            new Discidia(raid);
            new Emc(raid);
            new Evorsio(raid);
            new Fornax(raid);
            new Gelu(raid);
            new Horologium(raid);
            new Lucerna(raid);
            new Mineralis(raid);
            new Octans(raid);
            new Pelotrio(raid);
            new Vicio(raid);
            new Vorux(raid);
        }
    }
}
