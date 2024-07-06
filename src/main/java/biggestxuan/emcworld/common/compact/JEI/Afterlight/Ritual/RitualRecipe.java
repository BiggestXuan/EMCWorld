package biggestxuan.emcworld.common.compact.JEI.Afterlight.Ritual;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/31
 */

import biggestxuan.emcworld.EMCWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import java.util.List;

public enum RitualRecipe {
    SO(new ItemStack[]{EMCWorld.getItem("the_afterlight:crushed_moon_crystal_dust"),EMCWorld.getItem("the_afterlight:crushed_moon_crystal_dust"),EMCWorld.getItem("the_afterlight:void_rock")},EMCWorld.getItem("the_afterlight:strange_obsidian")),
    CI(new ItemStack[]{EMCWorld.getItem("the_afterlight:living_essence"),EMCWorld.getItem("the_afterlight:crushed_moon_crystal_dust"),EMCWorld.getItem("the_afterlight:moon_crystal")},EMCWorld.getItem("the_afterlight:crystillium_ingot")),
    LS(new ItemStack[]{EMCWorld.getItem("the_afterlight:pearl_shards"),EMCWorld.getItem("the_afterlight:living_essence"),EMCWorld.getItem("the_afterlight:verdant_quartz")},EMCWorld.getItem("the_afterlight:living_star")),
    LI(new ItemStack[]{EMCWorld.getItem("the_afterlight:living_essence"),EMCWorld.getItem("the_afterlight:living_essence"),EMCWorld.getItem("the_afterlight:crystillium_ingot")},EMCWorld.getItem("the_afterlight:livingessenceingot")),
    MS(new ItemStack[]{EMCWorld.getItem("the_afterlight:moon_crystal"),EMCWorld.getItem("the_afterlight:moon_shard"),EMCWorld.getItem("the_afterlight:livingessenceingot")},EMCWorld.getItem("the_afterlight:moonsteel_ingot")),
    TA(new ItemStack[]{EMCWorld.getItem("the_afterlight:moon_crystal"),EMCWorld.getItem("the_afterlight:crushed_moon_crystal_dust"),EMCWorld.getItem("minecraft:stick")},EMCWorld.getItem("the_afterlight:the_afterlight"))
    ;
    private final ItemStack[] input;
    private final ItemStack output;

    RitualRecipe(ItemStack[] input,ItemStack output){
        this.input = input;
        this.output = output;
    }

    public List<Ingredient> getInput(){
        return EMCWorld.itemstack2ingredient(input);
    }

    public ItemStack[] getInputItem(){
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }
}
