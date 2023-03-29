package biggestxuan.emcworld.client.models;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/20
 */

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class EMCLevelModel implements IItemPropertyGetter {
    @Override
    public float call(ItemStack p_call_1_, @Nullable ClientWorld p_call_2_, @Nullable LivingEntity p_call_3_) {
        if(p_call_1_.hasTag()){
            CompoundNBT nbt = p_call_1_.getTag();
            if(nbt != null){
                return nbt.getInt("level");
            }
        }
        return 0;
    }
}
