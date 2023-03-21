package biggestxuan.emcworld.common.compact.CraftTweaker;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/07
 */

import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.EWItem")
@SuppressWarnings("unused")
public class CrTEMCWorldItem {
    @ZenCodeType.Method
    public static long getItemEMC(IItemStack stack){
        return EMCHelper.getItemEMC(stack.getImmutableInternal());
    }

    @ZenCodeType.Method
    public static boolean cantTrans(IItemStack stack){
        Item item = stack.getImmutableInternal().getItem();
        ResourceLocation rl = item.getRegistryName();
        if(rl != null){
            String name = rl.getPath();
            for(String s:whiteList()){
                if(name.contains(s)){
                    return false;
                }
            }
            for(String s:blackList()){
                if(name.contains(s)){
                    return true;
                }
            }
        }
        return false;
    }

    private static String[] blackList(){
        return new String[]{"dust","block","nugget","storage_disk"};
    }

    private static String[] whiteList(){
        return new String[]{"sulfur"};
    }
}
