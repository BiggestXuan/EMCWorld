package biggestxuan.emcworld.common.compact.CraftTweaker;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/09
 */

import biggestxuan.emcworld.EMCWorld;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.CrTLang")
public class CrTLang {
    @ZenCodeType.Method
    public static String DimTip(){
        return EMCWorld.tc("message.dim.deny").getString();
    }
}
