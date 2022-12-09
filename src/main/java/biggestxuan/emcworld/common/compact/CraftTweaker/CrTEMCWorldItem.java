package biggestxuan.emcworld.common.compact.CraftTweaker;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/07
 */

import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.EWItem")
public class CrTEMCWorldItem {
    @ZenCodeType.Method
    public static long getItemEMC(IItemStack stack){
        return EMCHelper.getItemEMC(stack.getImmutableInternal());
    }
}
