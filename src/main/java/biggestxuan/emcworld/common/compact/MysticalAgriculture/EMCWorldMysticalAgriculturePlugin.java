package biggestxuan.emcworld.common.compact.MysticalAgriculture;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/12/14
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.registry.EWItems;
import com.blakebr0.mysticalagradditions.init.ModBlocks;
import com.blakebr0.mysticalagriculture.api.IMysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.MysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.api.lib.LazyIngredient;
import com.blakebr0.mysticalagriculture.api.lib.PluginConfig;
import com.blakebr0.mysticalagriculture.api.registry.IAugmentRegistry;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.util.text.TextFormatting;

@EMCWorldSince("1.1.0")
@MysticalAgriculturePlugin
public class EMCWorldMysticalAgriculturePlugin implements IMysticalAgriculturePlugin {
    public static final CropTier CROP_TIER_7;
    public static final CropTier CROP_TIER_8;

    public static final Crop ANTIMATTER;

    static {
        CROP_TIER_7 = new CropTier(EMCWorld.rl("7"),7,0x007a7c, TextFormatting.AQUA);
        CROP_TIER_8 = new CropTier(EMCWorld.rl("8"),8,0x242424,TextFormatting.GRAY);
        ANTIMATTER = new Crop(EMCWorld.rl("antimatter"),CROP_TIER_8, CropType.RESOURCE,0x784975,LazyIngredient.item("mekanism:pellet_antimatter"));
    }

    @Override
    public void configure(PluginConfig config) {
        config.disableDynamicSeedCraftingRecipes();
        //config.disableDynamicSeedInfusionRecipes();
        config.disableDynamicSeedReprocessingRecipes();
    }

    @Override
    public void onRegisterCrops(ICropRegistry registry) {
        registry.register(ANTIMATTER);
    }

    @Override
    public void onPostRegisterCrops(ICropRegistry registry) {
        CROP_TIER_7.setEssence(EWItems.EPIC_ESSENCE).setFarmland(() -> (FarmlandBlock) ModBlocks.INSANIUM_FARMLAND.get()).setSecondarySeedDrop(false).setFertilizable(false);
        CROP_TIER_8.setEssence(EWItems.FINAL_ESSENCE).setFarmland(() -> (FarmlandBlock) ModBlocks.INSANIUM_FARMLAND.get()).setSecondarySeedDrop(false).setFertilizable(false);
    }

    @Override
    public void onRegisterAugments(IAugmentRegistry registry) {

    }

    @Override
    public void onPostRegisterAugments(IAugmentRegistry registry) {

    }
}
