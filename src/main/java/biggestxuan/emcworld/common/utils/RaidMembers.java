package biggestxuan.emcworld.common.utils;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/07
 */

import biggestxuan.emcworld.common.registry.EWEntities;
import com.legacy.conjurer_illager.registry.IllagerEntityTypes;
import com.litewolf101.illagers_plus.init.EntityInit;
import com.minecraftabnormals.savageandravage.core.registry.SREntities;
import net.minecraft.entity.EntityType;
import net.minecraft.world.raid.Raid;

public class RaidMembers {
    public static void init(){
        int b = g(1);
        Raid.WaveMember.create("biggest_xuan", EWEntities.biggest_xuan,new int[]{0,0,0,0,0,0,1,0});
        Raid.WaveMember.create("dctor",EWEntities.dctor_0415,new int[]{0,0,0,0,1,0,0,0});
        Raid.WaveMember.create("tulye",EWEntities.tulye,new int[]{0,0,0,0,0,0,0,1});
        Raid.WaveMember.create("illusioner", EntityType.ILLUSIONER,new int[]{0,0,0,1,0,0,b, g(2)});
        Raid.WaveMember.create("frost", EntityInit.FROSTMANCER,new int[]{0,0,1,1,b,2,g(2),g(3)});
        Raid.WaveMember.create("conjurer", IllagerEntityTypes.CONJURER,new int[]{0,0,0,1,0,0,g(1),g(1)});
        Raid.WaveMember.create("ice", SREntities.ICEOLOGER.get(), new int[]{0,0,0,1,1,1,g(2),g(2)});
        Raid.WaveMember.create("conjurer", IllagerEntityTypes.CONJURER,new int[]{0,0,0,1,0,0,g(1),g(1)});
    }

    private static int g(int base){
        return MathUtils.isMaxDifficulty() ? (int) (2.5 * base) : base;
    }
}
