package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/26
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.IEMCGod;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Mixin(Item.class)
public abstract class ItemClientMixin {
    /**
     * @author Biggest_Xuan
     * @reason Add EMC God Weapon Text
     */
    @Overwrite
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, @Nonnull ITooltipFlag p_77624_4_) {
        if(p_77624_1_.getItem() instanceof IEMCGod){
            p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.weapon_god"));
        }
    }
}
