package biggestxuan.emcworld.common.items.RaidItem;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/13
 */

import biggestxuan.emcworld.api.item.base.BaseDifficultyItem;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;

public abstract class BaseRaidItem extends BaseDifficultyItem {
    public BaseRaidItem(float difficulty, boolean isHardCore, TranslationTextComponent usage,int maxDamage) {
        super(new Properties().tab(EWCreativeTabs.EW_CREATIVE_TAB).durability(maxDamage));
        this.difficulty = difficulty;
        this.isHardCore = isHardCore;
        this.usage = usage;
    }

    @Override
    public boolean isEnchantable(@Nonnull ItemStack p_77616_1_) {
        return false;
    }

    @Override
    public boolean isRepairable(@Nonnull ItemStack stack) {
        return false;
    }
}
