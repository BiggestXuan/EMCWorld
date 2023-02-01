package biggestxuan.emcworld.common.compact.JEI.Afterlight.Ancient;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/31
 */

import biggestxuan.emcworld.EMCWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import java.util.List;

public enum AncientRecipe {
    GB(new ItemStack[]{EMCWorld.getItem("the_afterlight:glyph_of_knowledge"),EMCWorld.getItem("the_afterlight:glyph_of_knowledge"),EMCWorld.getItem("the_afterlight:glyph_of_radiance"),EMCWorld.getItem("the_afterlight:glyph_of_radiance"),EMCWorld.getItem("the_afterlight:golden_shards_of_radiance"),EMCWorld.getItem("the_afterlight:golden_shards_of_radiance"),EMCWorld.getItem("the_afterlight:radiant_shards"),EMCWorld.getItem("the_afterlight:radiant_shards"), EMCWorld.getItem("minecraft:stick")},EMCWorld.getItem("the_afterlight:geode_blade")),
    EA(new ItemStack[]{EMCWorld.getItem("the_afterlight:glyph_of_knowledge"),EMCWorld.getItem("the_afterlight:glyph_of_knowledge"),EMCWorld.getItem("the_afterlight:glyph_of_radiance"),EMCWorld.getItem("the_afterlight:glyph_of_radiance"),EMCWorld.getItem("the_afterlight:aqua_crystal"),EMCWorld.getItem("the_afterlight:living_star"),EMCWorld.getItem("the_afterlight:bloodstone_shard"),EMCWorld.getItem("the_afterlight:verdant_quartz"),EMCWorld.getItem("the_afterlight:empty_amulet")},EMCWorld.getItem("the_afterlight:elemental_amulet")),
    MB(new ItemStack[]{EMCWorld.getItem("the_afterlight:glyph_of_power"),EMCWorld.getItem("the_afterlight:glyph_of_sickness"),EMCWorld.getItem("the_afterlight:spectral_glyph"),EMCWorld.getItem("the_afterlight:glyph_of_radiance"),EMCWorld.getItem("the_afterlight:moonsteel_ingot"),EMCWorld.getItem("the_afterlight:living_star"),EMCWorld.getItem("the_afterlight:lunariteingot"),EMCWorld.getItem("the_afterlight:lunariteingot"),EMCWorld.getItem("minecraft:stick")},EMCWorld.getItem("the_afterlight:moon_blade"))
    ;

    private final ItemStack[] input;
    private final ItemStack output;

    AncientRecipe(ItemStack[] input,ItemStack output){
        this.input = input;
        this.output = output;
    }

    public ItemStack[] getInputItem(){
        return input;
    }

    public List<Ingredient> getInput() {
        return EMCWorld.itemstack2ingredient(input);
    }

    public ItemStack getOutput() {
        return output;
    }
}
