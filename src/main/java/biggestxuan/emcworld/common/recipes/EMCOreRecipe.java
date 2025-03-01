package biggestxuan.emcworld.common.recipes;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.Projecte.EMCGemsMapping;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/09
 */

public enum EMCOreRecipe {
    A1(EMCWorld.getItem("minecraft:iron_ore"),1,0),
    A2(EMCWorld.getItem("minecraft:coal_ore"),2,0),
    A3(EMCWorld.getItem("mekanism:copper_ore"),1,0),
    B1(EMCWorld.getItem("mekanism:osmium_ore"),2,1),
    B2(EMCWorld.getItem("emcworld:drystone_ore"),1,1),
    B3(EMCWorld.getItem("emcworld:nickel_ore"),2,1),
    B4(EMCWorld.getItem("emcworld:silver_ore"),1,1),
    B5(EMCWorld.getItem("emcworld:hardcore_stone"),5,1),
    C1(EMCWorld.getItem("minecraft:gold_ore"),3,2),
    C2(EMCWorld.getItem("minecraft:diamond_ore"),1,2),
    C3(EMCWorld.getItem("minecraft:redstone_ore"),5,2),
    C4(EMCWorld.getItem("minecraft:lapis_ore"),5,2),
    C5(EMCWorld.getItem("minecraft:emerald_ore"),1,2),
    C6(EMCWorld.getItem("emcworld:sunlit_ore"),2,2),
    C7(EMCWorld.getItem("atum:nebu_ore"),3,2),
    C8(EMCWorld.getItem("emcworld:chlorophyte_ore"),4,2),
    C9(EMCWorld.getItem("emcworld:cold_ore"),4,2),
    C10(EMCWorld.getItem("mekanism:dust_steel"),4,2),
    D1(EMCWorld.getItem("botania:manasteel_ingot"),10,3),
    D2(EMCWorld.getItem("botania:mana_pearl"),10,3),
    D3(EMCWorld.getItem("botania:mana_diamond"),9,3),
    D4(EMCWorld.getItem("botania:elementium_ingot"),8,3),
    D5(EMCWorld.getItem("botania:dragonstone"),7,3),
    D6(EMCWorld.getItem("botania:pixie_dust"),7,3),
    D7(EMCWorld.getItem("mekanism:ingot_refined_glowstone"),12,3),
    D8(EMCWorld.getItem("gobber2:gobber2_ore"),3,3),
    D9(EMCWorld.getItem("emcworld:illager_gem"),1,3),
    D10(EMCWorld.getItem("gobber2:gobber2_lucky_block"),6,3),
    E1(EMCWorld.getItem("botania:terrasteel_ingot"),2,4),
    E2(EMCWorld.getItem("mekanism:ingot_refined_obsidian"),1,4),
    E3(EMCWorld.getItem("mekanism:uranium_ore"),10,4),
    E4(EMCWorld.getItem("mekanism:fluorite_ore"),12,4),
    E5(EMCWorld.getItem("allthemodium:allthemodium_ore"),2,4),
    E6(EMCWorld.getItem("emcworld:orichalcos_ore"),3,4),
    E7(EMCWorld.getItem("bloodmagic:dungeon_ore"),5,4),
    F1(EMCWorld.getItem("twilightforest:fiery_ingot"),10,5),
    F2(EMCWorld.getItem("twilightforest:knightmetal_ingot"),7,5),
    F3(EMCWorld.getItem("twilightforest:steeleaf_ingot"),12,5),
    F4(EMCWorld.getItem("allthemodium:vibranium_ore"),1,5),
    F5(EMCWorld.getItem("rats:oratchalcum_ore"),3,5),
    F6(EMCWorld.getItem("astralsorcery:aquamarine_sand_ore"),10,5),
    F7(EMCWorld.getItem("astralsorcery:starmetal_ore"),3,5),
    F8(EMCWorld.getItem("rats:ratlantean_gem_ore"),8,5),
    F9(EMCWorld.getItem("emcworld:scroll_voracious_wolf"),1,5),
    G1(EMCWorld.getItem("undergarden:cloggrum_ore"),500,6),
    G2(EMCWorld.getItem("undergarden:forgotten_ingot"),200,6),
    G3(EMCWorld.getItem("undergarden:utherium_ore"),500,6),
    G4(EMCWorld.getItem("aerialhell:fluorite_ore"),500,6),
    G5(EMCWorld.getItem("undergarden:regalium_ore"),500,6),
    G6(EMCWorld.getItem("gobber2:gobber2_ore_nether"),100,6),
    G7(EMCWorld.getItem("emcworld:indium_ore"),400,6),
    G8(EMCWorld.getItem("emcworld:tungsten_ore"),400,6),
    G9(EMCWorld.getItem("minecraft:ancient_debris"),100,6),
    G10(EMCWorld.getItem("emcworld:scroll_super_emc"),1,6),
    G11(EMCWorld.getItem("emcworld:scroll_voracious_wolf"),6,6),
    H1(EMCWorld.getItem("mekanism:pellet_polonium"),300,7),
    H2(EMCWorld.getItem("mekanism:pellet_plutonium"),300,7),
    H3(EMCWorld.getItem("mekanism:reprocessed_fissile_fragment"),300,7),
    H4(EMCWorld.getItem("allthemodium:unobtainium_ore"),45,7),
    H5(EMCWorld.getItem("gobber2:gobber2_ore_end"),150,7),
    H6(EMCWorld.getItem("emcworld:super_emc_gem"),35,7),
    H7(EMCWorld.getItem("emcworld:scroll_super_emc"),3,7),
    H8(EMCWorld.getItem("emcworld:scroll_celestial_azure"),1,7),
    H9(EMCWorld.getItem("emcworld:scroll_voracious_wolf"),10,7),
    I1(EMCWorld.getItem("mekanism:pellet_antimatter"),1500,8),
    I2(EMCWorld.getItem("emcworld:illager_shard"),500,8),
    I3(EMCWorld.getItem("extendedcrafting:the_ultimate_nugget"),6000,8),
    I4(EMCWorld.getItem("emcworld:emc_ore_core"),12,8),
    I5(EMCWorld.getItem("emcworld:epic_emc_gem"),60,8),
    I6(EMCWorld.getItem("emcworld:scroll_celestial_azure"),3,8),
    I7(EMCWorld.getItem("emcworld:scroll_super_emc"),5,8),
    I8(EMCWorld.getItem("emcworld:scroll_voracious_wolf"),2,8),
    ;
    private final ItemStack output;
    private final int weight;
    private final int level;

    EMCOreRecipe(ItemStack stack,int weight,int level){
        this.output = stack;
        this.weight = weight;
        this.level = level;
    }

    public static Ingredient getInput(){
        List<ItemStack> list = new ArrayList<>();
        for(var r : EMCGemsMapping.values()){
            list.add(new ItemStack(r.getItem()));
        }
        return Ingredient.of(list.stream());
    }

    public static int getTotalWeight(int level){
        int c = 0;
        for(var r : EMCOreRecipe.values()){
            if(r.level == level){
                c += r.weight;
            }
        }
        return c;
    }

    public static List<EMCOreRecipe> getRequireLevelRecipe(int level){
        List<EMCOreRecipe> list = new ArrayList<>();
        for(var r : EMCOreRecipe.values()){
            if(r.level == level){
                list.add(r);
            }
        }
        return list;
    }

    public int getLevel() {
        return level;
    }

    @Nonnull
    public ItemStack getOutput() {
        return output.copy();
    }

    public int getWeight() {
        return weight;
    }
}
