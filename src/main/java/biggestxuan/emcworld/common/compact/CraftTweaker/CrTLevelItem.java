package biggestxuan.emcworld.common.compact.CraftTweaker;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/27
 */

import biggestxuan.emcworld.api.item.IUpgradeableItem;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.Level")
public class CrTLevelItem {
    @ZenCodeType.Method
    public static int get(IItemStack stack){
        return stack instanceof IUpgradeableItem ? ((IUpgradeableItem) stack.getImmutableInternal().getItem()).getLevel(stack.getImmutableInternal()):0;
    }
}
