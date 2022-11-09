package biggestxuan.emcworld.common.world.gen;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/31
 */

import biggestxuan.emcworld.common.registry.EWBlocks;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class oreGen {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBiomeLoadingEvent(BiomeLoadingEvent event){
        event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.configured(
                        new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                EWBlocks.ORICHALCOS_ORE.get().defaultBlockState(),5)
                ).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0,3,16)))
                        .squared().count(3));
        event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.configured(
                                new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                        EWBlocks.ALUMINUM_ORE.get().defaultBlockState(),8)
                        ).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(16,3,64)))
                        .squared().count(8));
        event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.configured(
                                new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                        EWBlocks.HARDCORE_STONE.get().defaultBlockState(),31)
                        ).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0,8,48)))
                        .squared().count(30));
        event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.configured(
                                new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                        EWBlocks.MAGNESIUM_ORE.get().defaultBlockState(),12)
                        ).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0,6,48)))
                        .squared().count(6));
    }
}
