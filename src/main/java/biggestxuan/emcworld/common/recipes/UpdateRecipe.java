package biggestxuan.emcworld.common.recipes;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/10
 */

import biggestxuan.emcworld.api.recipe.IUpdateRecipe;
import com.mystic.atlantis.init.BlockInit;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.registries.MekanismItems;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import vazkii.botania.common.block.ModBlocks;
import wayoftime.bloodmagic.common.item.BloodMagicItems;

public enum UpdateRecipe implements IUpdateRecipe {
    GLOW_STONE(new ItemStack(Items.COAL_BLOCK),new ItemStack(Items.GLOWSTONE),384),
    THERMAL_BLOCK(MekanismItems.STEEL_INGOT.getItemStack(), MekanismBlocks.THERMAL_EVAPORATION_BLOCK.getItemStack(),3000),
    BASIC_CONTROL(MekanismItems.BASIC_CONTROL_CIRCUIT.getItemStack(),MekanismItems.ADVANCED_CONTROL_CIRCUIT.getItemStack(1),20000),
    OCEAN_LANTERN(new ItemStack(Blocks.SEA_LANTERN.asItem()),new ItemStack(BlockInit.OCEAN_LANTERN.get().asItem()),50000),
    IRON_KEY(new ItemStack(Blocks.IRON_BLOCK),new ItemStack(BloodMagicItems.DUNGEON_SIMPLE_KEY.get()),75000),
    ADVANCED_CONTROL(MekanismItems.ADVANCED_CONTROL_CIRCUIT.getItemStack(1),MekanismItems.ELITE_CONTROL_CIRCUIT.getItemStack(1),100000),
    GAIA_PYLON(new ItemStack(ModBlocks.naturaPylon.asItem()),new ItemStack(ModBlocks.gaiaPylon.asItem()),250000);

    private final ItemStack input;
    private final ItemStack output;
    private final long cost;

    UpdateRecipe(ItemStack input, ItemStack output, long cost) {
        this.input = input;
        this.output = output;
        this.cost = cost;
    }

    public ItemStack getInput() {
        input.setCount(1);
        return input;
    }
    public ItemStack getOutput(){
        ItemStack g = output.copy();
        g.setCount(1);
        return g;
    }

    @Override
    public long costEMC() {
        return cost;
    }
}
