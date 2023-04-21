package biggestxuan.emcworld.common.compact.Mekanism;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/17
 */

import biggestxuan.emcworld.EMCWorld;
import mekanism.common.content.gear.Module;
import mekanism.common.item.gear.ItemMekaTool;
import net.minecraft.item.ItemStack;

import java.util.List;

public class MekUtils {
    public static boolean isInfinityMekaTool(ItemStack stack){
        if(stack.getItem() instanceof ItemMekaTool){
            ItemMekaTool tool = (ItemMekaTool) stack.getItem();
            List<Module<?>> ms = tool.getModules(stack);
            for(Module<?> m : ms){
                if (m.getData().getRegistryName() != null && m.getData().getRegistryName().equals(EMCWorld.rl("infinity_module"))) {
                    return true;
                }
            }
        }
        return false;
    }
}
