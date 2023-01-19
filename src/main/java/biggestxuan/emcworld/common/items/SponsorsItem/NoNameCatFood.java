package biggestxuan.emcworld.common.items.SponsorsItem;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/08
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.ISponsorItem;
import biggestxuan.emcworld.common.items.EWItem;
import biggestxuan.emcworld.common.utils.Sponsors.Sponsors;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class NoNameCatFood extends EWItem implements ISponsorItem {

    @Override
    public Sponsors getSponsor(){
        return Sponsors.HIEHEIHEICAT;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.nonamecatfood"));
    }
}
