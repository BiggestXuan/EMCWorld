package biggestxuan.emcworld.common.utils;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/23
 */

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import net.minecraft.client.resources.I18n;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.tooltip")
public class ToolTipLang {
    @ZenCodeType.Method
    public static String updateAddTime(){
        return I18n.get("tooltip.emcworld.update_add_time");
    }
    @ZenCodeType.Method
    public static String updateLossTime(){
        return I18n.get("tooltip.emcworld.update_loss_time");
    }
    @ZenCodeType.Method
    public static String updateAddCost(){
        return I18n.get("tooltip.emcworld.update_add_cost");
    }
    @ZenCodeType.Method
    public static String updateLossCost(){
        return I18n.get("tooltip.emcworld.update_loss_cost");
    }
    @ZenCodeType.Method
    public static String updateAddAddon(){
        return I18n.get("tooltip.emcworld.update_add_addon");
    }
    @ZenCodeType.Method
    public static String getBiggestEMCGemDesc(){
        return I18n.get("tooltip.emcworld.biggest_emc_gem");
    }
    @ZenCodeType.Method
    public static String getNiobiumNuggetDesc(){
        return I18n.get("tooltip.emcworld.niobium_nugget");
    }
}
