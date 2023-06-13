package biggestxuan.emcworld.common.items.Equipment.Scroll;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/20
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.ISponsorItem;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.utils.Sponsors.Sponsors;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class TulyeScroll extends ScrollItem implements ISponsorItem {
    public TulyeScroll() {
        super(1.5f,2500);
    }

    @Override
    public double breakWeaponRate(){
        return ConfigManager.UPGRADE_TULYE_SCROLL.get() ? 0.3d : 0;
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, @Nonnull List<ITextComponent> p_77624_3_, @Nonnull ITooltipFlag p_77624_4_) {
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.scroll_weight_fail",this.weight,(int) (breakWeaponRate()*100)+"%"));
    }

    @Nullable
    @Override
    public Sponsors getSponsor() {
        return Sponsors.all.TULYE.getSponsors();
    }
}
