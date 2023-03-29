package biggestxuan.emcworld.common.recipes;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/19
 */

import biggestxuan.emcworld.api.recipe.IUpdateRecipe;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.registry.EWItems;
import com.github.alexthe666.rats.server.blocks.RatlantisBlockRegistry;
import com.github.alexthe666.rats.server.items.RatsItemRegistry;
import com.mystic.atlantis.init.BlockInit;
import com.teammetallurgy.atum.init.AtumBlocks;
import de.ellpeck.naturesaura.items.ModItems;
import hellfirepvp.astralsorcery.common.lib.BlocksAS;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.registries.MekanismItems;
import moze_intel.projecte.gameObjs.registries.PEItems;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import quek.undergarden.registry.UGItems;
import vazkii.botania.common.block.ModBlocks;
import wayoftime.bloodmagic.common.block.BloodMagicBlocks;
import wayoftime.bloodmagic.common.item.BloodMagicItems;

public enum AdvancedUpdateRecipe implements IUpdateRecipe {
    GLOW_STONE(Items.COAL_BLOCK,Items.GLOWSTONE,384),
    THERMAL_BLOCK(MekanismItems.STEEL_INGOT.getItem(), MekanismBlocks.THERMAL_EVAPORATION_BLOCK.getItem(),3000),
    RAT_FLOWER(ModBlocks.whiteShinyFlower.asItem(), RatlantisBlockRegistry.RATGLOVE_FLOWER.asItem(), 10000),
    BASIC_CONTROL(MekanismItems.BASIC_CONTROL_CIRCUIT.getItem(),MekanismItems.ADVANCED_CONTROL_CIRCUIT.getItem(),20000),
    OCEAN_LANTERN(Blocks.SEA_LANTERN.asItem(), BlockInit.OCEAN_LANTERN.get().asItem(),50000),
    IRON_KEY(Blocks.IRON_BLOCK.asItem(),BloodMagicItems.DUNGEON_SIMPLE_KEY.get(),75000),
    ADVANCED_CONTROL(MekanismItems.ADVANCED_CONTROL_CIRCUIT.getItem(),MekanismItems.ELITE_CONTROL_CIRCUIT.getItem(),100000),
    GAIA_PYLON(ModBlocks.naturaPylon.asItem(),ModBlocks.gaiaPylon.asItem(),250000),
    BX_INGOT(AtumBlocks.GODFORGED_BLOCK.asItem(), EWItems.BIGGEST_XUAN_INGOT.get(),300000),
    DARK_MATTER(PEItems.DARK_MATTER.get(),PEItems.RED_MATTER.get(),250000,1),
    ULTIMATE_CONTROL(MekanismItems.ELITE_CONTROL_CIRCUIT.getItem(),MekanismItems.ULTIMATE_CONTROL_CIRCUIT.getItem(),250000),
    FILTH(RatsItemRegistry.FILTH.getItem(),RatsItemRegistry.FILTH_CORRUPTION.asItem(),2000000,3),
    WELL(BloodMagicBlocks.BLOOD_ALTAR.get().asItem(), BlocksAS.WELL.asItem(),2500000,3),
    PLAGUE_TOME(Items.BOOK,RatsItemRegistry.PLAGUE_TOME,3000000,4),
    EMC_LEAF(ModItems.GOLD_LEAF.getItem(),EWItems.EMC_LEAF.get(),100000,4),
    STAR_ORE(Blocks.IRON_ORE.asItem(),BlocksAS.STARMETAL_ORE.asItem(),300000,4 ),
    //QIO_DRIVE(MekanismItems.BASE_QIO_DRIVE.getItem(),MekanismItems.HYPER_DENSE_QIO_DRIVE.getItem(),3500000,2),
    AS_INFUSER(EWItems.INFUSE_CORE.get(),BlocksAS.INFUSER.asItem(),3500000,4),
    MAGENTA_MATTER(PEItems.RED_MATTER.get(),EWItems.MAGENTA_MATTER.get(),350000,3),
    FANTASY_GEM(Items.DIAMOND.getItem(),EWItems.FANTASY_GEM.get(), 1000000,3),
    BRIGHT_STONE(MekanismItems.POLONIUM_PELLET.getItem(), EWItems.BRIGHT_STONE.get(), 3000000000L,7),
    DARK_STONE(MekanismItems.PLUTONIUM_PELLET.getItem(), EWItems.DARK_STONE.get(), 3000000000L,7),
    EVIL_BOOK(RatsItemRegistry.PLAGUE_TOME.getItem(),EWItems.EVIL_BOOK.get(), 7000000000L,8),
    INFINITY(EWItems.INFINITY_CATALYST.get(), EWItems.ULTIMATE_SINGULARITY.get(),100000000000L,8),
    BROKEN_DIAMOND_SWORD(UGItems.CLOGGRUM_SWORD.get(),EWItems.BROKEN_DIAMOND_SWORD.get(),100000000,7),
    ANOTHER_WORLD_CORE(EWItems.FANTASY_GEM.get(), EWItems.ANOTHER_WORLD_CORE.get(), 5000000,4),
    DEMON_DUST(MekanismItems.REFINED_OBSIDIAN_DUST.getItem(), EWItems.DEMON_DUST.get(), 20000000,5),
    HERO_MEDAL(EWItems.GOLD_MEDAL.get(), EWItems.HERO_MEDAL.get(), 100000000,6),
    PURPLE_MATTER(EWItems.PINK_MATTER.get(),EWItems.PURPLE_MATTER.get(),3000000,4),
    VIOLET_MATTER(EWItems.PURPLE_MATTER.get(), EWItems.VIOLET_MATTER.get(), 6500000,4),
    BLUE_MATTER(EWItems.VIOLET_MATTER.get(), EWItems.BLUE_MATTER.get(), 12500000,5),
    CYAN_MATTER(EWItems.BLUE_MATTER.get(),EWItems.CYAN_MATTER.get(), 25000000,5),
    GREEN_MATTER(EWItems.CYAN_MATTER.get(),EWItems.GREEN_MATTER.get(), 50000000,6),
    //TIME_DRIVE(MekanismItems.HYPER_DENSE_QIO_DRIVE.getItem(), MekanismItems.TIME_DILATING_QIO_DRIVE.getItem(), 15000000,4),
    //SUPER_DRIVE(MekanismItems.TIME_DILATING_QIO_DRIVE.getItem(), MekanismItems.SUPERMASSIVE_QIO_DRIVE.getItem(), 120000000,6),
    BIRTH_SPIRIT(ModItems.CALLING_SPIRIT.getItem(),ModItems.BIRTH_SPIRIT.getItem(),1000000,4),
    PINK_MATTER(EWItems.MAGENTA_MATTER.get(), EWItems.PINK_MATTER.get(),750000,4),
    YELLOW_GREEN_MATTER(EWItems.GREEN_MATTER.get(), EWItems.LIME_MATTER.get(), 100000000,6),
    YELLOW_MATTER(EWItems.LIME_MATTER.get(),EWItems.YELLOW_MATTER.get(),300000000,7),
    ORANGE_MATTER(EWItems.YELLOW_MATTER.get(), EWItems.ORANGE_MATTER.get(), 1000000000,8),
    FADING_MATTER(EWItems.EMC_CORE.get(),EWItems.FADING_MATTER.get(), 2500000000L,8),
    CLAY_MATTER(EWItems.FADING_MATTER.get(),EWItems.CLAY_MATTER.get(), 5000000000L,8),
    WHITE_MATTER(EWItems.CLAY_MATTER.get(),EWItems.WHITE_MATTER.get(), 15000000000L,8)
    ;

    private final Item input;
    private final Item output;
    private final long cost;
    private final int level;

    AdvancedUpdateRecipe(Item input, Item output, long cost) {
        this(input,output,cost,1);
    }

    AdvancedUpdateRecipe(Item input, Item output, long cost,int level) {
        this.input = input;
        this.output = output;
        this.cost = cost;
        this.level = level;
    }

    @Override
    public ItemStack getInput() {
        ItemStack out = new ItemStack(this.input);
        out.setCount(1);
        return out;
    }
    @Override
    public ItemStack getOutput(){
        ItemStack out = new ItemStack(this.output);
        out.setCount(1);
        return out;
    }
    @Override
    public long costEMC(){
        return level >= 6 && ConfigManager.DIFFICULTY.get() == 3.0D ? cost << (level - 4)  : cost;
    }

    @Override
    public int recipeLevel(){
        return level;
    }
}
