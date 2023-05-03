package biggestxuan.emcworld.common.items;

import biggestxuan.emcworld.EMCWorld;
import hellfirepvp.astralsorcery.common.data.research.PlayerProgress;
import hellfirepvp.astralsorcery.common.data.research.ResearchHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.LogicalSide;

import javax.annotation.Nullable;
import java.util.List;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/14
 */

public class EMCGemItem extends EWItem{
    public EMCGemItem(){}

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        PlayerEntity player = Minecraft.getInstance().player;
        PlayerProgress progress = ResearchHelper.getProgress(player, LogicalSide.CLIENT);
        if(progress.isValid() && !progress.hasConstellationDiscovered(EMCWorld.rl("emc"))){
            p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.find_star"));
        }
    }
}
