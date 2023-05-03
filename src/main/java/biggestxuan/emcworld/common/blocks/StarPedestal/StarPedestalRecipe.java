package biggestxuan.emcworld.common.blocks.StarPedestal;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/06
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.registry.EWStarlight;
import hellfirepvp.astralsorcery.common.constellation.IConstellation;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum StarPedestalRecipe {
    s1(List.of(new ArrayList<>(trans(EMCWorld.getItem("astralsorcery:starmetal_ingot"), EMCWorld.getItem("astralsorcery:starmetal_ingot"), EMCWorld.getItem("astralsorcery:starmetal_ingot"), EMCWorld.getItem("astralsorcery:starmetal_ingot")))),
            MODE.MAX_STAR,0,1),
    s2(List.of(new ArrayList<>(trans(EMCWorld.getItem("astralsorcery:starmetal"),EMCWorld.getItem("astralsorcery:starmetal"),EMCWorld.getItem("astralsorcery:starmetal"),EMCWorld.getItem("astralsorcery:starmetal")))),
            MODE.STAR,0,1),
    v1(List.of(new ArrayList<>(trans(EMCWorld.getItem("allthemodium:vibranium_ingot"),EMCWorld.getItem("rats:idol_of_ratlantis"),EMCWorld.getItem("allthemodium:vibranium_ingot"),EMCWorld.getItem("allthemodium:vibranium_ingot")))),
            MODE.MAX_STAR,1,1),
    v2(List.of(new ArrayList<>(trans(EMCWorld.getItem("allthemodium:vibranium_block"),EMCWorld.getItem("cataclysm:ignitium_block"),EMCWorld.getItem("allthemodium:vibranium_block"),EMCWorld.getItem("allthemodium:vibranium_block")))),
            MODE.STAR,1,1),
    n1(List.of(new ArrayList<>(trans(EMCWorld.getItem("minecraft:netherite_ingot"),EMCWorld.getItem("minecraft:netherite_ingot"),EMCWorld.getItem("minecraft:netherite_ingot"),EMCWorld.getItem("minecraft:netherite_ingot"))),
            new ArrayList<>(trans(EMCWorld.getItem("mekanism:pellet_polonium"),EMCWorld.getItem("mekanism:pellet_polonium"),EMCWorld.getItem("mekanism:pellet_polonium"),EMCWorld.getItem("mekanism:pellet_polonium"),
                    EMCWorld.getItem("mekanism:pellet_plutonium"),EMCWorld.getItem("mekanism:pellet_plutonium"),EMCWorld.getItem("mekanism:pellet_plutonium"),EMCWorld.getItem("mekanism:pellet_plutonium")))),
            MODE.MAX_STAR,2,2),
    n2(List.of(new ArrayList<>(trans(EMCWorld.getItem("minecraft:nether_star"),EMCWorld.getItem("minecraft:nether_star"),EMCWorld.getItem("minecraft:nether_star"),EMCWorld.getItem("minecraft:nether_star"))),
            new ArrayList<>(trans(EMCWorld.getItem("mekanism:pellet_polonium"),EMCWorld.getItem("mekanism:pellet_polonium"),EMCWorld.getItem("mekanism:pellet_polonium"),EMCWorld.getItem("mekanism:pellet_polonium"),
                    EMCWorld.getItem("mekanism:pellet_plutonium"),EMCWorld.getItem("mekanism:pellet_plutonium"),EMCWorld.getItem("mekanism:pellet_plutonium"),EMCWorld.getItem("mekanism:pellet_plutonium")))),
            MODE.STAR,2,2),
    m1(List.of(new ArrayList<>(trans(EMCWorld.getItem("minecraft:nether_star"),EMCWorld.getItem("minecraft:nether_star"),EMCWorld.getItem("mythicbotany:alfsteel_ingot"),EMCWorld.getItem("mythicbotany:alfsteel_ingot"))),
            new ArrayList<>(trans(EMCWorld.getItem("undergarden:forgotten_ingot"),EMCWorld.getItem("undergarden:forgotten_ingot"),EMCWorld.getItem("undergarden:utherium_ingot"),EMCWorld.getItem("undergarden:utherium_ingot"),
                EMCWorld.getItem("undergarden:regalium_ingot"),EMCWorld.getItem("undergarden:regalium_ingot"),EMCWorld.getItem("undergarden:regalium_ingot"),EMCWorld.getItem("undergarden:regalium_ingot")))),
            MODE.MAX_STAR,3,2),
    m2(List.of(new ArrayList<>(trans(EMCWorld.getItem("minecraft:netherite_block"),EMCWorld.getItem("minecraft:netherite_block"),EMCWorld.getItem("minecraft:netherite_block"),EMCWorld.getItem("minecraft:netherite_block"))),
            new ArrayList<>(trans(EMCWorld.getItem("undergarden:forgotten_ingot"),EMCWorld.getItem("undergarden:forgotten_ingot"),EMCWorld.getItem("undergarden:utherium_ingot"),EMCWorld.getItem("undergarden:utherium_ingot"),
                    EMCWorld.getItem("undergarden:regalium_ingot"),EMCWorld.getItem("undergarden:regalium_ingot"),EMCWorld.getItem("undergarden:regalium_ingot"),EMCWorld.getItem("undergarden:regalium_ingot")))),
            MODE.STAR,3,2),
    c1(List.of(new ArrayList<>(trans(EMCWorld.getItem("emcworld:scroll_red"),EMCWorld.getItem("emcworld:scroll_red"),EMCWorld.getItem("emcworld:scroll_red"),EMCWorld.getItem("emcworld:scroll_red"))),
            new ArrayList<>(trans(EMCWorld.getItem("emcworld:lucky_gem_purple"),EMCWorld.getItem("emcworld:lucky_gem_purple"),EMCWorld.getItem("emcworld:lucky_gem_purple"),EMCWorld.getItem("emcworld:lucky_gem_purple"),
                    EMCWorld.getItem("emcworld:lucky_gem_purple"),EMCWorld.getItem("emcworld:lucky_gem_purple"),EMCWorld.getItem("emcworld:lucky_gem_purple"),EMCWorld.getItem("emcworld:lucky_gem_purple"))),
            new ArrayList<>(trans(EMCWorld.getItem("gobber2:gobber2_ingot_end"),EMCWorld.getItem("gobber2:gobber2_ingot_end"),EMCWorld.getItem("gobber2:gobber2_ingot_end"),EMCWorld.getItem("gobber2:gobber2_ingot_end"),
                    EMCWorld.getItem("cataclysm:enderite_ingot"),EMCWorld.getItem("cataclysm:enderite_ingot"),EMCWorld.getItem("cataclysm:enderite_ingot"),EMCWorld.getItem("cataclysm:enderite_ingot")))),
            MODE.MAX_STAR,4,3),
    c2(List.of(new ArrayList<>(trans(EMCWorld.getItem("emcworld:scroll_red"),EMCWorld.getItem("emcworld:scroll_red"),EMCWorld.getItem("emcworld:scroll_red"),EMCWorld.getItem("emcworld:scroll_red"))),
            new ArrayList<>(trans(EMCWorld.getItem("emcworld:lucky_gem_purple"),EMCWorld.getItem("emcworld:lucky_gem_purple"),EMCWorld.getItem("emcworld:lucky_gem_purple"),EMCWorld.getItem("emcworld:lucky_gem_purple"),
                    EMCWorld.getItem("emcworld:lucky_gem_purple"),EMCWorld.getItem("emcworld:lucky_gem_purple"),EMCWorld.getItem("emcworld:lucky_gem_purple"),EMCWorld.getItem("emcworld:lucky_gem_purple"))),
            new ArrayList<>(trans(EMCWorld.getItem("gobber2:gobber2_block_end"),EMCWorld.getItem("gobber2:gobber2_block_end"),EMCWorld.getItem("gobber2:gobber2_block_end"),EMCWorld.getItem("gobber2:gobber2_block_end"),
                    EMCWorld.getItem("allthemodium:unobtainium_block"),EMCWorld.getItem("allthemodium:unobtainium_block"),EMCWorld.getItem("allthemodium:unobtainium_block"),EMCWorld.getItem("allthemodium:unobtainium_block")))),
            MODE.STAR,4,3),
    w1(List.of(new ArrayList<>(trans(EMCWorld.getItem("dead_guys_untitled_deep_dark_:warden_antler"),EMCWorld.getItem("dead_guys_untitled_deep_dark_:warden_antler"),EMCWorld.getItem("dead_guys_untitled_deep_dark_:warden_antler"),EMCWorld.getItem("dead_guys_untitled_deep_dark_:warden_antler"))),
            new ArrayList<>(trans(EMCWorld.getItem("emcworld:lime_matter"),EMCWorld.getItem("emcworld:lime_matter"),EMCWorld.getItem("emcworld:lime_matter"),EMCWorld.getItem("emcworld:lime_matter"),
                    EMCWorld.getItem("emcworld:lime_matter"),EMCWorld.getItem("emcworld:lime_matter"),EMCWorld.getItem("emcworld:lime_matter"),EMCWorld.getItem("emcworld:lime_matter"))),
            new ArrayList<>(trans(EMCWorld.getItem("divinerpg:apalachia_chunk"),EMCWorld.getItem("divinerpg:apalachia_chunk"),EMCWorld.getItem("divinerpg:apalachia_chunk"),EMCWorld.getItem("divinerpg:apalachia_chunk"),
                    EMCWorld.getItem("divinerpg:skythern_chunk"),EMCWorld.getItem("divinerpg:skythern_chunk"),EMCWorld.getItem("divinerpg:skythern_chunk"),EMCWorld.getItem("divinerpg:skythern_chunk")))),
            MODE.MAX_STAR,5,3),
    w2(List.of(new ArrayList<>(trans(EMCWorld.getItem("dead_guys_untitled_deep_dark_:warden_antler"),EMCWorld.getItem("dead_guys_untitled_deep_dark_:warden_antler"),EMCWorld.getItem("dead_guys_untitled_deep_dark_:warden_antler"),EMCWorld.getItem("dead_guys_untitled_deep_dark_:warden_antler"))),
            new ArrayList<>(trans(EMCWorld.getItem("emcworld:epic_emc_gem"),EMCWorld.getItem("emcworld:epic_emc_gem"),EMCWorld.getItem("emcworld:epic_emc_gem"),EMCWorld.getItem("emcworld:epic_emc_gem"),
                    EMCWorld.getItem("emcworld:epic_emc_gem"),EMCWorld.getItem("emcworld:epic_emc_gem"),EMCWorld.getItem("emcworld:epic_emc_gem"),EMCWorld.getItem("emcworld:epic_emc_gem"))),
            new ArrayList<>(trans(EMCWorld.getItem("allthemodium:unobtainium_vibranium_alloy_ingot"),EMCWorld.getItem("allthemodium:unobtainium_vibranium_alloy_ingot"),EMCWorld.getItem("allthemodium:unobtainium_vibranium_alloy_ingot"),EMCWorld.getItem("allthemodium:unobtainium_vibranium_alloy_ingot"),
                    EMCWorld.getItem("allthemodium:unobtainium_allthemodium_alloy_ingot"),EMCWorld.getItem("allthemodium:unobtainium_allthemodium_alloy_ingot"),EMCWorld.getItem("allthemodium:unobtainium_allthemodium_alloy_ingot"),EMCWorld.getItem("allthemodium:unobtainium_allthemodium_alloy_ingot")))),
            MODE.STAR,5,3),
    k1(List.of(new ArrayList<>(trans(EMCWorld.getItem("emcworld:green_matter"),EMCWorld.getItem("emcworld:green_matter"),EMCWorld.getItem("emcworld:green_matter"),EMCWorld.getItem("emcworld:green_matter"))),
            new ArrayList<>(trans(EMCWorld.getItem("emcworld:lime_matter"),EMCWorld.getItem("emcworld:lime_matter"),EMCWorld.getItem("emcworld:lime_matter"),EMCWorld.getItem("emcworld:lime_matter"),
                    EMCWorld.getItem("emcworld:lime_matter"),EMCWorld.getItem("emcworld:lime_matter"),EMCWorld.getItem("emcworld:lime_matter"),EMCWorld.getItem("emcworld:lime_matter"))),
            new ArrayList<>(trans(EMCWorld.getItem("emcworld:universal_ball"),EMCWorld.getItem("emcworld:universal_ball"),EMCWorld.getItem("emcworld:universal_ball"),EMCWorld.getItem("emcworld:universal_ball"),
                    EMCWorld.getItem("emcworld:universal_ball"),EMCWorld.getItem("emcworld:universal_ball"),EMCWorld.getItem("emcworld:universal_ball"),EMCWorld.getItem("emcworld:universal_ball"))),
            new ArrayList<>(trans(EMCWorld.getItem("emcworld:universal_ball"),EMCWorld.getItem("emcworld:universal_ball"),EMCWorld.getItem("emcworld:universal_ball"),EMCWorld.getItem("emcworld:universal_ball")))),
            MODE.MAX_STAR,6,4),
   k2(List.of(new ArrayList<>(trans(EMCWorld.getItem("emcworld:yellow_matter"),EMCWorld.getItem("emcworld:yellow_matter"),EMCWorld.getItem("emcworld:yellow_matter"),EMCWorld.getItem("emcworld:yellow_matter"))),
           new ArrayList<>(trans(EMCWorld.getItem("emcworld:lime_matter"),EMCWorld.getItem("emcworld:lime_matter"),EMCWorld.getItem("emcworld:lime_matter"),EMCWorld.getItem("emcworld:lime_matter"),
                   EMCWorld.getItem("emcworld:lime_matter"),EMCWorld.getItem("emcworld:lime_matter"),EMCWorld.getItem("emcworld:lime_matter"),EMCWorld.getItem("emcworld:lime_matter"))),
           new ArrayList<>(trans(EMCWorld.getItem("emcworld:dragon_steel"),EMCWorld.getItem("emcworld:dragon_steel"),EMCWorld.getItem("emcworld:dragon_steel"),EMCWorld.getItem("emcworld:dragon_steel"),
                   EMCWorld.getItem("emcworld:dragon_steel"),EMCWorld.getItem("emcworld:dragon_steel"),EMCWorld.getItem("emcworld:dragon_steel"),EMCWorld.getItem("emcworld:dragon_steel"))),
           new ArrayList<>(trans(EMCWorld.getItem("emcworld:universal_ball"),EMCWorld.getItem("emcworld:universal_ball"),EMCWorld.getItem("emcworld:universal_ball"),EMCWorld.getItem("emcworld:universal_ball")))),
           MODE.STAR,6,4),
    u1(List.of(new ArrayList<>(trans(EMCWorld.getItem("emcworld:emc_core"),EMCWorld.getItem("emcworld:emc_core"),EMCWorld.getItem("emcworld:emc_core"),EMCWorld.getItem("emcworld:emc_core"))),
            new ArrayList<>(trans(EMCWorld.getItem("emcworld:emc_core"),EMCWorld.getItem("emcworld:fading_matter"),EMCWorld.getItem("emcworld:fading_matter"),EMCWorld.getItem("emcworld:fading_matter"),
                    EMCWorld.getItem("emcworld:fading_matter"),EMCWorld.getItem("emcworld:fading_matter"),EMCWorld.getItem("emcworld:fading_matter"),EMCWorld.getItem("emcworld:fading_matter"))),
            new ArrayList<>(trans(EMCWorld.getItem("emcworld:clay_matter"),EMCWorld.getItem("emcworld:clay_matter"),EMCWorld.getItem("emcworld:clay_matter"),EMCWorld.getItem("emcworld:clay_matter"),
                    EMCWorld.getItem("emcworld:clay_matter"),EMCWorld.getItem("emcworld:clay_matter"),EMCWorld.getItem("emcworld:clay_matter"),EMCWorld.getItem("emcworld:clay_matter"))),
            new ArrayList<>(trans(EMCWorld.getItem("extendedcrafting:the_ultimate_ingot"),EMCWorld.getItem("extendedcrafting:the_ultimate_ingot"),EMCWorld.getItem("extendedcrafting:the_ultimate_ingot"),EMCWorld.getItem("extendedcrafting:the_ultimate_ingot")))),
            MODE.MAX_STAR,7,4),
    u2(List.of(new ArrayList<>(trans(EMCWorld.getItem("mekanism:pellet_antimatter"),EMCWorld.getItem("mekanism:pellet_antimatter"),EMCWorld.getItem("mekanism:pellet_antimatter"),EMCWorld.getItem("mekanism:pellet_antimatter"))),
            new ArrayList<>(trans(EMCWorld.getItem("emcworld:infinity_emc_gem"),EMCWorld.getItem("emcworld:infinity_emc_gem"),EMCWorld.getItem("emcworld:infinity_emc_gem"),EMCWorld.getItem("emcworld:infinity_emc_gem"),
                    EMCWorld.getItem("emcworld:infinity_emc_gem"),EMCWorld.getItem("emcworld:infinity_emc_gem"),EMCWorld.getItem("emcworld:infinity_emc_gem"),EMCWorld.getItem("emcworld:infinity_emc_gem"))),
            new ArrayList<>(trans(EMCWorld.getItem("emcworld:illager_shard"),EMCWorld.getItem("emcworld:illager_shard"),EMCWorld.getItem("emcworld:illager_shard"),EMCWorld.getItem("emcworld:illager_shard"),
                    EMCWorld.getItem("emcworld:illager_shard"),EMCWorld.getItem("emcworld:illager_shard"),EMCWorld.getItem("emcworld:illager_shard"),EMCWorld.getItem("emcworld:illager_shard"))),
            new ArrayList<>(trans(EMCWorld.getItem("extendedcrafting:the_ultimate_ingot"),EMCWorld.getItem("extendedcrafting:the_ultimate_ingot"),EMCWorld.getItem("extendedcrafting:the_ultimate_ingot"),EMCWorld.getItem("extendedcrafting:the_ultimate_ingot")))),
            MODE.STAR,7,4),
    ;

    private final List<List<ItemStack>> inputs;
    private final MODE mode;
    private final int requireStar;
    private final int level;
    private final IConstellation star;

    StarPedestalRecipe(List<List<ItemStack>> inputs,MODE mode,int requireStar,int level,IConstellation star){
        this.inputs = inputs;
        this.mode = mode;
        this.requireStar = requireStar;
        this.level = level;
        this.star = star;
    }

    StarPedestalRecipe(List<List<ItemStack>> inputs,MODE mode,int requireStar,int level){
        this(inputs,mode,requireStar,level, EWStarlight.EMC_STAR.get());
    }

    public IConstellation getStar() {
        return star;
    }

    public int getRequireStar() {
        return requireStar;
    }

    public MODE getMode() {
        return mode;
    }

    public List<List<ItemStack>> getInputs() {
        List<List<ItemStack>> ar = new ArrayList<>(inputs);
        int size = ar.size();
        if(size != 4){
            for (int i = 0; i < 4-size; i++) {
                ar.add(new ArrayList<>());
            }
        }
        return ar;
    }

    public int getLevel(){
        return level;
    }

    @SafeVarargs
    public static <T> List<T> trans(T... array){
        return new ArrayList<>(Arrays.asList(array));
    }

    public enum MODE{
        MAX_STAR,STAR
    }
}
