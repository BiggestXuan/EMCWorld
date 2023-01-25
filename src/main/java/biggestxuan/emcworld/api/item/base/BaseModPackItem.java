package biggestxuan.emcworld.api.item.base;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/25
 */

import biggestxuan.emcworld.common.items.EWItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static biggestxuan.emcworld.EMCWorld.tc;

public abstract class BaseModPackItem extends EWItem {
    @Nonnull
    private final String name;
    @Nullable
    private final String usage;

    public BaseModPackItem(@Nonnull String name, @Nullable String usage){
        this.name = name;
        this.usage = usage;
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, @Nonnull List<ITextComponent> p_77624_3_, @Nonnull ITooltipFlag p_77624_4_) {
        if(usage != null){
            p_77624_3_.add(tc(usage));
        }
        p_77624_3_.add(tc("tooltip.emcworld.modpack",name).withStyle(Style.EMPTY.withItalic(true).withColor(Color.fromRgb(0x3d9140))));
    }
}
