package biggestxuan.emcworld.common.items;

/**
 * EMC WORLD MOD
 * @Author Biggest_Xuan
 * 2022/10/11
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.blocks.EWUpdateBlock;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class UpdateBlockItem extends BlockItem {
    private final Block block;

    public UpdateBlockItem(Block p_i48527_1_) {
        super(p_i48527_1_,new Properties().tab(EWCreativeTabs.EW_ORES_TAB));
        this.block = p_i48527_1_;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, @Nonnull ITooltipFlag p_77624_4_) {
        if(this.block instanceof EWUpdateBlock){
            EWUpdateBlock b = (EWUpdateBlock) block;
            double addon = b.getAddon();
            double time = b.getTime();
            double cost = b.getCost();
            if(addon != 1){
                p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.update_add_addon").append(getRate(addon)));
            }
            if(time >1){
                p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.update_add_time").append(getRate(time)));
            }
            if(time <1){
                p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.update_loss_time").append(getRate(time)));
            }
            if(cost >1){
                p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.update_add_cost").append(getRate(cost)));
            }
            if(cost <1){
                p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.update_loss_cost").append(getRate(cost)));
            }
        }
    }

    private static String getRate(double value){
        double base = (Math.abs(value-1))*100;
        return String.format("%.1f", base)+" %";
    }
}
