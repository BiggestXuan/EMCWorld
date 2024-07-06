package biggestxuan.emcworld.common.utils;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/23
 */

import biggestxuan.emcworld.EMCWorld;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.tooltip")
public class ToolTipLang {
    @ZenCodeType.Method
    public static String updateAddTime(){
        return EMCWorld.tc("tooltip.emcworld.update_add_time").getString();
    }
    @ZenCodeType.Method
    public static String updateLossTime(){
        return EMCWorld.tc("tooltip.emcworld.update_loss_time").getString();
    }
    @ZenCodeType.Method
    public static String updateAddCost(){
        return EMCWorld.tc("tooltip.emcworld.update_add_cost").getString();
    }
    @ZenCodeType.Method
    public static String updateLossCost(){
        return EMCWorld.tc("tooltip.emcworld.update_loss_cost").getString();
    }
    @ZenCodeType.Method
    public static String updateAddAddon(){
        return EMCWorld.tc("tooltip.emcworld.update_add_addon").getString();
    }
    @ZenCodeType.Method
    public static String getBiggestEMCGemDesc(){
        return EMCWorld.tc("tooltip.emcworld.biggest_emc_gem").getString();
    }
    @ZenCodeType.Method
    public static String getNiobiumNuggetDesc(){
        return EMCWorld.tc("tooltip.emcworld.niobium_nugget").getString();
    }
}
