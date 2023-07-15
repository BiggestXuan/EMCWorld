package biggestxuan.emcworld.client.models;

import biggestxuan.emcworld.api.EMCWorldSince;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

import javax.annotation.Nullable;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/02
 */

@EMCWorldSince("0.9.0")
public class GodGunModel implements IItemPropertyGetter {
    @Override
    public float call(ItemStack p_call_1_, @Nullable ClientWorld p_call_2_, @Nullable LivingEntity p_call_3_) {
        if(p_call_1_.hasTag()){
            CompoundNBT nbt = p_call_1_.getTag();
            if(nbt != null){
                return nbt.getInt("god_gun");
            }
        }
        return 0;
    }
}
