package biggestxuan.emcworld.api.item.equipment;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/17
 */

import biggestxuan.emcworld.common.items.Equipment.BaseWeaponGemItem;
import net.minecraft.item.ItemStack;

public interface IGemInlaidItem {
    default int getGemIndex(ItemStack stack){
        return stack.getOrCreateTag().getInt("weapon_gem");
    }

    default void setGemIndex(ItemStack stack,int index){
        stack.getOrCreateTag().putInt("weapon_gem",index);
    }

    default int getGemType(ItemStack stack){
        return (getGemIndex(stack) / 10);
    }

    default int getColor(ItemStack stack){
        int t = getGemType(stack);
        int c;
        if(t >= 40){
            c = BaseWeaponGemItem.gem.ABYSS.getColor();
        }else if(t >= 30){
            c = BaseWeaponGemItem.gem.LAKE.getColor();
        }else if(t >= 20){
            c = BaseWeaponGemItem.gem.NATURE.getColor();
        }else c = BaseWeaponGemItem.gem.BLOOD.getColor();
        return c;
    }
}
