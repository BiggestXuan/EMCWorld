package biggestxuan.emcworld.common.compact.CraftTweaker;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/23
 */

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TieredItem;
import net.minecraft.item.ToolItem;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.ItemUtils")
@SuppressWarnings("unused")
public class CrTItemUtils {
    @ZenCodeType.Method
    public static boolean isSword(IItemStack stack){
        return stack.getImmutableInternal().getItem() instanceof SwordItem;
    }

    @ZenCodeType.Method
    public static boolean isArmor(IItemStack stack){
        return stack.getImmutableInternal().getItem() instanceof ArmorItem;
    }

    @ZenCodeType.Method
    public static boolean isTool(IItemStack stack){
        return stack.getImmutableInternal().getItem() instanceof ToolItem;
    }

    @ZenCodeType.Method
    public static boolean isTier(IItemStack stack){
        return stack.getImmutableInternal().getItem() instanceof TieredItem;
    }
}
