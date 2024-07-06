package biggestxuan.emcworld.api.item.equipment.armor;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/28
 */

import biggestxuan.emcworld.api.item.IEMCInfuserItem;
import net.minecraft.item.ItemStack;

public interface IEMCShieldArmor extends IEMCInfuserItem {
    float maxShield(ItemStack stack);
    float shieldSpeed(ItemStack stack);

    default float getMaxShield(ItemStack stack){
        return stack.getOrCreateTag().getFloat("emc_maxShield");
    }

    default float getShield(ItemStack stack){
        float value = stack.getOrCreateTag().getFloat("emc_shield");
        if(Double.isNaN(value)){
            setShield(stack,0);
        }
        return stack.getOrCreateTag().getFloat("emc_shield");
    }

    default float getShieldSpeed(ItemStack stack){
        return stack.getOrCreateTag().getFloat("emc_shield_speed");
    }

    default void setMaxShield(ItemStack stack,float value){
        stack.getOrCreateTag().putFloat("emc_maxShield",value);
    }

    default void setShield(ItemStack stack,float value){
        value = Double.isNaN(value) ? 0 : value;
        stack.getOrCreateTag().putFloat("emc_shield",value);
    }

    default void setShieldSpeed(ItemStack stack,float value){
        stack.getOrCreateTag().putFloat("emc_shield_speed",value);
    }

    default void modifyShield(ItemStack stack,float value){
        if(value >= 0){
            setShield(stack, Math.min(maxShield(stack), getShield(stack) + value));
        }else setShield(stack, Math.max(0, getShield(stack) + value));
    }

    default void heal(ItemStack stack){
        modifyShield(stack,getShieldSpeed(stack)/100f);
    }
}
