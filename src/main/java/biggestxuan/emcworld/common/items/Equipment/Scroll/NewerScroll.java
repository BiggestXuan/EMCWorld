package biggestxuan.emcworld.common.items.Equipment.Scroll;

import biggestxuan.emcworld.EMCWorld;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/8/1
 */
public class NewerScroll extends ScrollItem{
    public NewerScroll(float difficulty, int weight) {
        super(difficulty, weight);
    }


    @Override
    public void appendHoverText(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, @Nonnull List<ITextComponent> p_77624_3_, @Nonnull ITooltipFlag p_77624_4_) {
        super.appendHoverText(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld."+getRegistryName().getPath()).setStyle(Style.EMPTY.withColor(TextFormatting.GREEN)));
    }
}
