package biggestxuan.emcworld.common.utils;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/07
 */

import biggestxuan.emcworld.common.compact.CraftTweaker.CrTConfig;
import biggestxuan.emcworld.common.registry.EWEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.world.raid.Raid;

public class RaidMembers {
    public static void init(){
        int base = getAdditionsRate(1);
        Raid.WaveMember.create("biggest_xuan", EWEntities.biggest_xuan,new int[]{0,0,0,0,0,0,base,base});
        Raid.WaveMember.create("dctor",EWEntities.dctor_0415,new int[]{0,0,0,0,base,base,0,0});
        Raid.WaveMember.create("tulye",EWEntities.tulye,new int[]{0,0,0,0,0,0,0,base});
        Raid.WaveMember.create("illusioner", EntityType.ILLUSIONER,new int[]{0,0,0,1,0,0,base,getAdditionsRate(2)});
    }

    private static int getAdditionsRate(int base){
        if(MathUtils.isMaxDifficulty()) return base * 3;
        if(CrTConfig.getWorldDifficulty() >= 2.5) return base * 2;
        return base;
    }
}
