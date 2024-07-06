package biggestxuan.emcworld.common.items.SponsorsItem;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/19
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.ISponsorItem;
import biggestxuan.emcworld.common.items.EWItem;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.Sponsors.Sponsors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

import static biggestxuan.emcworld.common.utils.Sponsors.Sponsors.all.xiangshushumiao;

public class XiangshushumiaoPillow extends EWItem implements ISponsorItem {

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.xiangshushumiao_pillow"));
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if(player != null && !player.isDeadOrDying() && MathUtils.getSponsor(player) != null && MathUtils.getSponsor(player).equals(xiangshushumiao.getSponsors()) && !EMCWorld.isOffline){
            p_77624_3_.add(EMCWorld.tc("\u6a61\u6811\u6811\u82d7\u9171\u9171\u8f6f\u8f6f\u7684\uff0c\u751c\u751c\u7684\uff0c\u9999\u9999\u7684\uff0c\u53ef\u7231\u6ef4\u634f~").withStyle(Style.EMPTY.withItalic(true).withColor(Color.fromRgb(0x3d9140))));
        }
    }

    @Override
    public Rarity getRarity(ItemStack stack){
        return Rarity.RARE;
    }

    @Nullable
    @Override
    public Sponsors getSponsor() {
        return xiangshushumiao.getSponsors();
    }
}
