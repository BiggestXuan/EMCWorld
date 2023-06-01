package biggestxuan.emcworld.common.items.SponsorsItem;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.IFakeEMCItem;
import biggestxuan.emcworld.api.item.ISponsorItem;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.items.EWItem;
import biggestxuan.emcworld.common.utils.Sponsors.Sponsors;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * EMC WORLD MOD
 * @Author Biggest_Xuan
 * 2023/06/01
 */

public class NoEqualItem extends EWItem implements ISponsorItem, IFakeEMCItem {
    @Override
    public long getFakeEMC(ItemStack stack) {
        return 1000000000;
    }

    @Override
    public long getActEMC(ItemStack stack) {
        return 1;
    }

    @Override
    public void doSomething(PlayerEntity player) {
        EMCHelper.setPlayerEMC(player,(long)(EMCHelper.getPlayerActEMC(player)*0.5));
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.unequal"));
    }

    @Nullable
    @Override
    public Sponsors getSponsor() {
        return Sponsors.all.LITTLE_YU.getSponsors();
    }
}
