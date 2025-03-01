package biggestxuan.emcworld.common.utils;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/20
 */

import biggestxuan.emcworld.EMCWorld;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModUtils {
    private static final String[] modlist = {
            "pylons","loadedmodsexporter","i18nupdatemod","cookingforblockheads","i18n","emc_stage","randomraid","mysticalagradditions","mysticalagriculture","constructionwand","storagedrawers","ftbchunks","entity_nan_fix_potato_edition","libipn","inventoryprofilesnext","sleeping_bags","prefab","polymorph","naturescompass","iknowwhatimdoing","pipez","rpmtw_platform_mod","rpmtw_update_mod","i18n","mcjtylib","nerb","hats","ichunutil","litewolfcore","notenoughcrashes","dungeonsmod","betterdungeons","auudio","astralsorcery","candyworld","extendedcrafting","hem","torohealth","stalwart_dungeons","rubidium","controlling","placebo","citadel","yungsapi","atlantis","bookshelf","mekanismgenerators","waila","jeresources","aerialhell","dummmmmmy","twilightforest","emcworld","a_foe_on_fire","refinedstorage","konkrete","rsinfinitybooster","structure_gel","packmenu","farmersdelight","repurposed_structures","netherportalspread","endrem","ironfurnaces","item_render_rebirth","yungsbridges","botania","spark","dimstages","curios","cataclysm","patchouli","oculus","collective","yungsextras","gobber2","naturesaura","mekanismtools","architectury","observerlib","lanserverproperties","cloth-config","undergarden","the_bumblezone","instantunify","illageandspillage","classicbar","scalinghealth","codechickenlib","bettermineshafts","divinerpg","rhino","cucumber","ftblibrary","ftbteams","customskinloader","bountifulbaubles","jei","itemfilters","worleycaves","attributefix","conjurer_illager","abnormals_core","waystones","clumps","shutupexperimentalsettings","journeymap","libx","stoneholm","champions","good_nights_sleep","dead_guys_untitled_deep_dark_","crockpot","explorerscompass","blockswap","jeitweaker","magnesium_extras","enderstorage","crafttweaker","projecte","projectex","gamestages","mekanism","extradisks","forge","atum","bountiful","wailaharvestability","recipestages","castle_dungeons","bloodmagic","mythicbotany","dungeons_arise","drippyloadingscreen","minecraft","cofh_core","thermal","thermal_foundation","thermal_expansion","radon","mousetweaks","itemstages","ftbquests","silentlib","allthemodium","iceberg","savageandravage","rats","ratlantis","ftbbackups","autoreglib","akashictome","quark","torcherino","performant","jecharacters","entityculling","appleskin","lootr","ferritecore","modernui","byg","illagers_plus","refinedstorageaddons"
    };
    private static final List<String> list = Arrays.asList(modlist);
    public static void modList(){
        StringBuilder s = new StringBuilder();
        for(ModInfo info : ModList.get().getMods()){
            s.append("\"");
            s.append(info.getModId());
            s.append("\",");
        }
        EMCWorld.LOGGER.info(s.toString());
    }

    public static boolean addMod(){
        ArrayList<String> add = new ArrayList<>();
        for(ModInfo info : ModList.get().getMods()){
            if(!list.contains(info.getModId())){
                add.add(info.getModId());
            }
        }
        if(!add.isEmpty()){
            EMCWorld.LOGGER.error("Client added additions mods:"+ add);
            return true;
        }
        return false;
    }
}
