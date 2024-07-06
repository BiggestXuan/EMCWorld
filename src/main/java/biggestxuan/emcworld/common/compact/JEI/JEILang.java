package biggestxuan.emcworld.common.compact.JEI;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/19
 */

import biggestxuan.emcworld.EMCWorld;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.compact.jei")
public class JEILang {
    @ZenCodeType.Method
    public static String getAdvancedCoreDesc(){
        return EMCWorld.tc("jei.emcworld.advanced_update_desc").getString();
    }
}
