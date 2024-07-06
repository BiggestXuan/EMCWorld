package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.compact.BloodMagic.BloodMagicHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wayoftime.bloodmagic.common.item.ItemSacrificialDagger;

import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/28
 */

@Mixin(ItemSacrificialDagger.class)
@EMCWorldSince("1.0.2")
public abstract class ItemSacrificialDaggerMixin extends Item {
    public ItemSacrificialDaggerMixin(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    @OnlyIn(Dist.CLIENT)
    @Inject(method = "appendHoverText",at = @At("HEAD"))
    public void __inject(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag, CallbackInfo ci){
        if(BloodMagicHelper.isEMCModifyDagger(stack)){
            tooltip.add(EMCWorld.tc("tooltip.blood_dagger.desc").withStyle(TextFormatting.RED));
        }
    }
}
