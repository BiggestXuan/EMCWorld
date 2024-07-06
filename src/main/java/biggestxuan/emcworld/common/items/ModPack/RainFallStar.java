package biggestxuan.emcworld.common.items.ModPack;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.item.IModpackItem;
import biggestxuan.emcworld.common.creativeTab.EWCreativeTab;
import biggestxuan.emcworld.common.items.EWItem;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import hellfirepvp.astralsorcery.common.constellation.ConstellationItem;
import hellfirepvp.astralsorcery.common.constellation.IMinorConstellation;
import hellfirepvp.astralsorcery.common.constellation.IWeakConstellation;
import hellfirepvp.astralsorcery.common.crafting.nojson.attunement.AttunementRecipe;
import hellfirepvp.astralsorcery.common.item.crystal.ItemAttunedCrystalBase;
import hellfirepvp.astralsorcery.common.item.crystal.ItemCrystalBase;
import hellfirepvp.astralsorcery.common.tile.TileAttunementAltar;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.LogicalSide;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/01/17
 */

@EMCWorldSince("1.1.0")
public class RainFallStar extends ItemAttunedCrystalBase implements IModpackItem, ICurioItem, ConstellationItem {
    public RainFallStar() {
        super(new Properties().tab(EWCreativeTabs.EW_CREATIVE_TAB).durability(5));
    }

    @Override
    public String getModpackName() {
        return "[THD]诸神匠心（Tinkers Heartwork and Deities）";
    }

    @Override
    public ItemAttunedCrystalBase getTunedItemVariant() {
        return this;
    }

    @Override
    public ItemCrystalBase getInertDuplicateItem() {
        return this;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> list, ITooltipFlag flag) {
        super.appendHoverText(stack, world, list, flag);
        for (int i = 0; i < 4; i++) {
            list.add(EMCWorld.tc("tooltip.emcworld.fxt_"+i));
        }
    }
}
