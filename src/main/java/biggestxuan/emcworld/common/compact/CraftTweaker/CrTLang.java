package biggestxuan.emcworld.common.compact.CraftTweaker;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/09
 */

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import net.minecraft.client.resources.I18n;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.CrTLang")
public class CrTLang {
    @ZenCodeType.Method
    public static String DimTip(){
        return I18n.get("message.dim.deny");
    }
}
