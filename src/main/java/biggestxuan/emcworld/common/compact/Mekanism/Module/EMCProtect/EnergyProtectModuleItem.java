package biggestxuan.emcworld.common.compact.Mekanism.Module.EMCProtect;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/15
 */

import biggestxuan.emcworld.api.item.IPlayerDifficultyItem;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import mekanism.api.MekanismAPI;
import mekanism.api.gear.ModuleData;
import mekanism.api.providers.IModuleDataProvider;
import mekanism.api.text.EnumColor;
import mekanism.api.text.TextComponentUtil;
import mekanism.client.key.MekKeyHandler;
import mekanism.client.key.MekanismKeyHandler;
import mekanism.common.MekanismLang;
import mekanism.common.item.ItemModule;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;

public class EnergyProtectModuleItem extends ItemModule implements IPlayerDifficultyItem {
    private IModuleDataProvider<?> data;

    public EnergyProtectModuleItem(IModuleDataProvider<?> moduleData) {
        super(moduleData,new Properties().stacksTo(1).tab(EWCreativeTabs.EW_CREATIVE_TAB).rarity(Rarity.EPIC));
        data = moduleData;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@Nonnull ItemStack stack, World world, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flag) {
        super.appendHoverText(stack,world,tooltip,flag);
        //Maybe add something else.
    }

    @Override
    public double requireDifficulty() {
        return 2.25D;
    }
}
