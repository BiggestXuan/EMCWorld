package biggestxuan.emcworld.common.compact.Mekanism.Module.Infinity;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/15
 */

import biggestxuan.emcworld.api.item.IPlayerDifficultyItem;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import mekanism.api.providers.IModuleDataProvider;
import mekanism.common.item.ItemModule;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;

public class InfinityModuleItem extends ItemModule implements IPlayerDifficultyItem {
    private IModuleDataProvider<?> data;

    public InfinityModuleItem(IModuleDataProvider<?> moduleData) {
        super(moduleData,new Properties().stacksTo(1).tab(EWCreativeTabs.EW_CREATIVE_TAB).rarity(Rarity.EPIC));
        data = moduleData;
    }

    @Override
    public double requireDifficulty() {
        return 3.0D;
    }
}
