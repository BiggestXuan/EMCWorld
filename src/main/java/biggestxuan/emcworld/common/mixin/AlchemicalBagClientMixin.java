package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/04
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.utils.UnknownPouchUtils;
import moze_intel.projecte.gameObjs.items.AlchemicalBag;
import moze_intel.projecte.gameObjs.items.ItemPE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(AlchemicalBag.class)
public abstract class AlchemicalBagClientMixin extends ItemPE {
    @Shadow(remap = false)
    @Final
    public DyeColor color;

    public AlchemicalBagClientMixin(Properties props) {
        super(props);
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        if(color.equals(DyeColor.BLACK) || color.equals(DyeColor.WHITE)){
            p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.alch_bag"));
        }
        if(color.equals(DyeColor.YELLOW) && Minecraft.getInstance().player != null){
            //p_77624_3_.add(EMCWorld.tc("message.unknown_pouch.info", UnknownPouchUtils.getCount(Minecraft.getInstance().player)));
            p_77624_3_.add(EMCWorld.tc("message.unknown_pouch.info1"));
        }
    }
}
