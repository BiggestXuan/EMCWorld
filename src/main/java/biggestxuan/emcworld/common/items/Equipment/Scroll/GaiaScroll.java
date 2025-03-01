package biggestxuan.emcworld.common.items.Equipment.Scroll;

import biggestxuan.emcworld.EMCWorld;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaUsingItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/28
 */
public class GaiaScroll extends ScrollItem{
    public GaiaScroll() {
        super(3.0F,100);
    }

    @Override
    public int getActWeight(ItemStack stack, ItemStack target, TileEntity entity) {
        if(target.getItem() instanceof IManaItem || target.getItem() instanceof IManaUsingItem){
            return super.getActWeight(stack, target, entity) * 2;
        }
        return super.getActWeight(stack,target,entity);
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, @Nonnull List<ITextComponent> p_77624_3_, @Nonnull ITooltipFlag p_77624_4_) {
        super.appendHoverText(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.gaia_scroll"));
    }
}
