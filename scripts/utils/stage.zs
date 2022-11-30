#priority 42

import crafttweaker.api.item.ItemStack;

public class sitem{
    public static var INSTANCE = new sitem();

    val c as ItemStack[] = new Getter().getCollector();
    val r as ItemStack[] = new Getter().getRelay();

    this(){

    }

    public getS8() as ItemStack[]{
        return [
            c[11],c[12],c[13],r[11],r[12],r[13]
        ];
    }

    public getS7() as ItemStack[]{
        return [
            c[10],r[10]
        ];
    }

    public getS6() as ItemStack[]{
        return [
            c[8],c[9],r[8],r[9]
        ];
    }
    
    public getS5() as ItemStack[]{
        return [
            r[7],c[7],
            <item:astralsorcery:ritual_pedestal>,
            <item:astralsorcery:ritual_link>
        ];
    }

    public getS4() as ItemStack[]{
        return [
            r[5],r[6],c[5],c[6],
            <item:mekanism:sps_casing>,
            <item:mekanism:sps_port>,
            <item:mekanism:supercharged_coil>,
            <item:mekanism:dust_netherite>,
            <item:emcworld:nether_emc_ore>,
            <item:minecraft:nether_wart_block>,
            <item:minecraft:netherite_hoe>,
            <item:minecraft:netherite_shovel>,
            <item:minecraft:netherite_axe>,
            <item:rats:nether_cheese>,
            <item:stalwart_dungeons:nether_keeper_altar>,
            <item:crockpot:netherosia>,
            <item:minecraft:netherrack>,
            <item:minecraft:nether_quartz_ore>,
            <item:minecraft:nether_wart>,
            <item:minecraft:netherite_ingot>,
            <item:minecraft:netherite_scrap>,
            <item:minecraft:quartz>,
            <item:minecraft:nether_brick>,
            <item:byg:yellow_nether_brick>,
            <item:gobber2:gobber2_globette_nether>,
            <item:gobber2:gobber2_seed_nether>,
            <item:gobber2:gobber2_glob_nether>,
            <item:gobber2:gobber2_ingot_nether>,
            <item:gobber2:gobber2_ore_nether>,
            <item:gobber2:gobber2_pickaxe_nether>,
            <item:gobber2:gobber2_foo_nether>,
            <item:gobber2:gobber2_sword_nether>,
            <item:gobber2:gobber2_helmet_nether>,
            <item:gobber2:gobber2_chestplate_nether>,
            <item:gobber2:gobber2_leggings_nether>,
            <item:gobber2:gobber2_boots_nether>,
            <item:minecraft:netherite_sword>,
            <item:minecraft:netherite_pickaxe>,
            <item:minecraft:netherite_helmet>,
            <item:minecraft:netherite_chestplate>,
            <item:minecraft:netherite_leggings>,
            <item:minecraft:netherite_boots>
        ];
    }

    public getS3() as ItemStack[]{
        return [
            r[3],r[4],c[3],c[4],
            <item:mekanismgenerators:fusion_reactor_controller>,
            <item:mekanismgenerators:fusion_reactor_frame>,
            <item:mekanismgenerators:fusion_reactor_port>,
            <item:mekanismgenerators:fusion_reactor_logic_adapter>,
            <item:mekanismgenerators:hohlraum>,
            <item:mekanismgenerators:hohlraum>,
            <item:mekanism:upgrade_filter>
        ];
    }

    public getS2() as ItemStack[]{
        return [
            c[2],r[2],
            <item:mekanism:induction_casing>,
            <item:mekanism:induction_port>,
            <item:mekanism:basic_induction_cell>,
            <item:mekanism:advanced_induction_cell>,
            <item:mekanism:elite_induction_cell>,
            <item:mekanism:ultimate_induction_cell>,
            <item:mekanism:basic_induction_provider>,
            <item:mekanism:advanced_induction_provider>,
            <item:mekanism:elite_induction_provider>,
            <item:mekanism:ultimate_induction_provider>
        ];
    }

    public getS1() as ItemStack[]{
        return [
            c[1],
            r[1],
            <item:mekanism:advanced_control_circuit>,
            <item:mekanism:elite_control_circuit>,
            <item:mekanism:boiler_casing>,
            <item:mekanism:boiler_valve>,
            <item:mekanismgenerators:turbine_blade>,
            <item:mekanismgenerators:turbine_rotor>,
            <item:mekanismgenerators:turbine_casing>,
            <item:mekanismgenerators:turbine_valve>,
            <item:mekanismgenerators:turbine_vent>,
            <item:mekanism:thermal_evaporation_controller>,
            <item:mekanism:thermal_evaporation_valve>,
            <item:mekanism:thermal_evaporation_block>,
            <item:mekanism:qio_drive_array>,
            <item:mekanism:portable_qio_dashboard>,
            <item:mekanism:qio_drive_base>,
            <item:mekanism:qio_dashboard>,
            <item:mekanism:qio_importer>,
            <item:mekanism:qio_redstone_adapter>,
            <item:mekanism:qio_exporter>,
            <item:mekanism:reprocessed_fissile_fragment>,
            <item:mekanismgenerators:fission_reactor_casing>,
            <item:mekanismgenerators:fission_reactor_port>,
            <item:mekanismgenerators:fission_reactor_logic_adapter>,
            <item:mekanismgenerators:fission_fuel_assembly>,
            <item:mekanismgenerators:rotational_complex>,
            <item:mekanism:pressure_disperser>
        ];
    }
}