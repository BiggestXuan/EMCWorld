package biggestxuan.emcworld.common.compact.CraftTweaker;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/29
 */

import biggestxuan.emcworld.api.EMCWorldAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import net.minecraft.entity.player.PlayerEntity;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Expansion("crafttweaker.api.player.MCPlayerEntity")
@SuppressWarnings("unused")
public class CrTPlayerExpand {
    @ZenCodeType.Method
    public static int LogCount(PlayerEntity player){
        return EMCWorldAPI.getInstance().getUtilCapability(player).getLogAmount();
    }
}
