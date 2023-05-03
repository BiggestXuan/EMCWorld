package biggestxuan.emcworld.common.compact.BloodMagic;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/08
 */

import net.minecraft.entity.player.PlayerEntity;
import wayoftime.bloodmagic.core.data.SoulNetwork;
import wayoftime.bloodmagic.util.helper.NetworkHelper;

public class BloodMagicHelper {
    public static SoulNetwork getPlayerSoulNetwork(PlayerEntity player){
        return NetworkHelper.getSoulNetwork(player);
    }
    public static int getPlayerLP(PlayerEntity player){
        return getPlayerSoulNetwork(player).getCurrentEssence();
    }
    public static void modifyPlayerLP(PlayerEntity player,int lp){
        getPlayerSoulNetwork(player).setCurrentEssence(Math.max(getPlayerLP(player) + lp,0));
    }
    public static void setPlayerLP(PlayerEntity player,int lp){
        getPlayerSoulNetwork(player).setCurrentEssence(lp);
    }
}
