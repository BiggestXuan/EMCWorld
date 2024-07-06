package biggestxuan.emcworld.common.compact.BloodMagic;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/08
 */

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import wayoftime.bloodmagic.common.item.BloodMagicItems;
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

    @EMCWorldSince("1.0.2")
    public static boolean isEMCModifyDagger(ItemStack stack){
        if(!stack.hasTag()){
            return false;
        }
        CompoundNBT nbt = stack.getTag();
        return nbt.getInt("emc_modify") >= 1 && stack.getItem().getRegistryName().toString().equals("bloodmagic:selfsacrificerune");
    }

}
