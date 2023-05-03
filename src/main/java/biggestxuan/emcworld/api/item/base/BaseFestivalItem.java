package biggestxuan.emcworld.api.item.base;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/22
 */

import biggestxuan.emcworld.EMCWorld;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseFestivalItem extends BaseFoodItem{
    private final String info;

    public BaseFestivalItem(int h,float s,String info){
        super(h,s,false);
        this.info = info;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, @Nonnull List<ITextComponent> p_77624_3_, @Nonnull ITooltipFlag p_77624_4_){
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.festival",info).append(EMCWorld.tc(info)));
    }

    @Override
    public double getEMCCostRate() {
        return 0d;
    }
}
