package biggestxuan.emcworld.common.compact.JEI.Afterlight.Glyph;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/31
 */

import biggestxuan.emcworld.EMCWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import javax.annotation.Nullable;
import java.util.List;

public enum GlyphRecipe {
    G(new ItemStack[]{EMCWorld.getItem("the_afterlight:spectral_glyph"),EMCWorld.getItem("the_afterlight:pearl_shards")},EMCWorld.getItem("the_afterlight:glyph_of_radiance")),
    P(new ItemStack[]{EMCWorld.getItem("the_afterlight:glyph_of_radiance"),EMCWorld.getItem("the_afterlight:bloodstone_shard")},EMCWorld.getItem("the_afterlight:glyph_of_power")),
    S(new ItemStack[]{EMCWorld.getItem("the_afterlight:glyph_of_knowledge"),EMCWorld.getItem("the_afterlight:verdant_quartz")},EMCWorld.getItem("the_afterlight:glyph_of_sickness")),
    K(new ItemStack[]{EMCWorld.getItem("the_afterlight:moon_shard"),EMCWorld.getItem("the_afterlight:eclipse_shard")},EMCWorld.getItem("the_afterlight:spectral_glyph")),
    C(new ItemStack[]{EMCWorld.getItem("the_afterlight:verdant_quartz"),EMCWorld.getItem("the_afterlight:crushed_moon_crystal_dust")},EMCWorld.getItem("the_afterlight:glyph_of_knowledge"))
    ;

    private final ItemStack[] input;
    private final ItemStack output;

    GlyphRecipe(@Nullable ItemStack[] input, @Nullable ItemStack output){
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
