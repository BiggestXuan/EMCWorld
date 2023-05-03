package biggestxuan.emcworld.common.compact.CraftTweaker;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/30
 */

import biggestxuan.emcworld.api.item.IDifficultyItem;
import biggestxuan.emcworld.api.item.IPlayerDifficultyItem;
import biggestxuan.emcworld.api.item.base.BaseDifficultyItem;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.DifficultyItem")
@SuppressWarnings("unused")
public class CrTDifficultyItem {
    @ZenCodeType.Method
    public static double getItemDifficulty(IItemStack stack){
        ItemStack s = stack.getInternal();
        if(s.getItem() instanceof BaseDifficultyItem){
            return ((BaseDifficultyItem) s.getItem()).getDifficulty();
        }
        if(s.getItem() instanceof IDifficultyItem){
            return ((IDifficultyItem) s.getItem()).getDifficulty();
        }
        if(s.getItem() instanceof IPlayerDifficultyItem){
            return ((IPlayerDifficultyItem) s.getItem()).requireDifficulty();
        }
        return 0;
    }

    @ZenCodeType.Method
    public static boolean isReachDifficulty(IItemStack stack){
        return CrTConfig.getWorldDifficulty() >= getItemDifficulty(stack);
    }
}
