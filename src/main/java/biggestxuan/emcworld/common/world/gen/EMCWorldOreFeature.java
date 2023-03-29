package biggestxuan.emcworld.common.world.gen;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/07
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.registry.EWBlocks;
import hellfirepvp.astralsorcery.common.lib.BlocksAS;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.registries.ForgeRegistries;
import vazkii.botania.common.block.ModBlocks;

public class EMCWorldOreFeature {
    public static ConfiguredFeature<?, ?> COLD_ORE;
    public static ConfiguredFeature<?, ?> END_EMC_ORE;
    public static ConfiguredFeature<?, ?> RICH_END_EMC_ORE;
    public static ConfiguredFeature<?, ?> TITAN_ORE;
    public static ConfiguredFeature<?, ?> DRYSTONE_ORE;
    public static ConfiguredFeature<?, ?> CHLOROPHYTE_ORE;
    public static ConfiguredFeature<?, ?> AQUA_ORE;
    public static ConfiguredFeature<?, ?> STAR_ORE;
    public static ConfiguredFeature<?, ?> MARBLE;
    public static ConfiguredFeature<?, ?> SUNLIT;

    public static Block marble = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark","marble"));
    public static Block honey = Blocks.HONEYCOMB_BLOCK;

    private static ConfiguredFeature<?, ?> get(String name,ConfiguredFeature<?, ?> config){
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,EMCWorld.rl(name),config);
        return config;
    }

    static{
        COLD_ORE = get("cold_ore", Feature.ORE.configured(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.PACKED_ICE), EWBlocks.COLD_ORE.get().defaultBlockState(),6)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0,8,128))).squared().count(120));
        DRYSTONE_ORE = get("drystone_ore", Feature.ORE.configured(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.TERRACOTTA), EWBlocks.DRYSTONE_ORE.get().defaultBlockState(),6)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0,8,128))).squared().count(50));
        AQUA_ORE = get("aqua_ore", Feature.ORE.configured(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.SAND), EWBlocks.AQUAMARINE_ORE.get().defaultBlockState(),6)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0,8,128))).squared().count(75));
        CHLOROPHYTE_ORE = get("chlorophyte_ore", Feature.ORE.configured(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.DIRT), EWBlocks.CHLOROPHYTE_ORE.get().defaultBlockState(),6)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0,8,128))).squared().count(95));
        END_EMC_ORE = get("end_ore", Feature.ORE.configured(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.END_STONE), EWBlocks.END_EMC_ORE.get().defaultBlockState(),6)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0,8,64))).squared().count(24));
        RICH_END_EMC_ORE = get("rich_end_ore", Feature.ORE.configured(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.END_STONE), EWBlocks.END_RICH_EMC_ORE.get().defaultBlockState(),3)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0,8,64))).squared().count(6));
        TITAN_ORE = get("titan_end_ore", Feature.ORE.configured(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.END_STONE), EWBlocks.TITANIUM_ORE.get().defaultBlockState(),3)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0,8,64))).squared().count(3));
        STAR_ORE = get("star_ore", Feature.ORE.configured(new OreFeatureConfig(new BlockMatchRuleTest(ModBlocks.livingrock), BlocksAS.STARMETAL_ORE.defaultBlockState(),3)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0,8,64))).squared().count(4));
        MARBLE = get("marble", Feature.ORE.configured(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.STONE), marble.defaultBlockState(),30)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0,8,64))).squared().count(15));
        SUNLIT = get("sunlit_ore", Feature.ORE.configured(new OreFeatureConfig(new BlockMatchRuleTest(honey), EWBlocks.SUNLIT_ORE.get().defaultBlockState(),7)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(128,16,256)).squared().count(24)));
    }
}
