package biggestxuan.emcworld.common.utils;

/**
 * EMC WORLD MOD
 * @Author Biggest_Xuan
 * 2022/10/12
 */

import biggestxuan.emcworld.api.item.equipment.weapon.IUpgradeableWeapon;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.WeaponUtils")
public class WeaponUtils {
    @ZenCodeType.Method
    public static int getWeaponMaxLevel(IItemStack stack){
        ItemStack is = stack.getInternal();
        if(is.getItem() instanceof IUpgradeableWeapon){
            IUpgradeableWeapon item = (IUpgradeableWeapon) is.getItem();
            return item.getMaxLevel();
        }
        return 0;
    }
}
