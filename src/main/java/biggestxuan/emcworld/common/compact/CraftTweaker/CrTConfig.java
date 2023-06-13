package biggestxuan.emcworld.common.compact.CraftTweaker;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/26
 */

import biggestxuan.emcworld.common.config.ConfigManager;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.configHelper")
@SuppressWarnings("unused")
public class CrTConfig {

    @ZenCodeType.Method
    public static double getWorldDifficulty(){
        return ConfigManager.DIFFICULTY.get();
    }

    @ZenCodeType.Method
    public static boolean isFreeMode(){
        return ConfigManager.FREE_MODE.get();
    }

    @ZenCodeType.Method
    public static boolean enableRS(){
        return ConfigManager.RSAutomation.get();
    }

    @ZenCodeType.Method
    public static boolean easyDarkMatter(){
        return ConfigManager.CRAFT_LOOT_EASY_DARK_MATTER.get();
    }

    @ZenCodeType.Method
    public static double bloodEyeChance(){
        return ConfigManager.CRAFT_LOOT_BLOOD_EYE.get();
    }

    @ZenCodeType.Method
    public static double medalChance(){
        return ConfigManager.CRAFT_LOOT_ATUM_MEDAL.get();
    }
}
