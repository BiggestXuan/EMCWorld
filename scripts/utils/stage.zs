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
            <item:astralsorcery:ritual_pedestal>.mutable(),
            <item:astralsorcery:ritual_link>.mutable()
        ];
    }

    public getS4() as ItemStack[]{
        return [
            r[5],r[6],c[5],c[6],
            <item:mekanism:sps_casing>.mutable(),
            <item:mekanism:sps_port>.mutable(),
            <item:mekanism:supercharged_coil>.mutable(),
            <item:mekanism:dust_netherite>.mutable(),
            <item:emcworld:nether_emc_ore>.mutable(),
            <item:minecraft:nether_wart_block>.mutable(),
            <item:minecraft:netherite_hoe>.mutable(),
            <item:minecraft:netherite_shovel>.mutable(),
            <item:minecraft:netherite_axe>.mutable(),
            <item:rats:nether_cheese>.mutable(),
            <item:stalwart_dungeons:nether_keeper_altar>.mutable(),
            <item:crockpot:netherosia>.mutable(),
            <item:minecraft:netherrack>.mutable(),
            <item:minecraft:nether_quartz_ore>.mutable(),
            <item:minecraft:nether_wart>.mutable(),
            <item:minecraft:netherite_ingot>.mutable(),
            <item:minecraft:netherite_scrap>.mutable(),
            <item:minecraft:quartz>.mutable(),
            <item:minecraft:nether_brick>.mutable(),
            <item:byg:yellow_nether_brick>.mutable(),
            <item:gobber2:gobber2_globette_nether>.mutable(),
            <item:gobber2:gobber2_seed_nether>.mutable(),
            <item:gobber2:gobber2_glob_nether>.mutable(),
            <item:gobber2:gobber2_ingot_nether>.mutable(),
            <item:gobber2:gobber2_ore_nether>.mutable(),
            <item:gobber2:gobber2_pickaxe_nether>.mutable(),
            <item:gobber2:gobber2_foo_nether>.mutable(),
            <item:gobber2:gobber2_sword_nether>.mutable(),
            <item:gobber2:gobber2_helmet_nether>.mutable(),
            <item:gobber2:gobber2_chestplate_nether>.mutable(),
            <item:gobber2:gobber2_leggings_nether>.mutable(),
            <item:gobber2:gobber2_boots_nether>.mutable(),
            <item:minecraft:netherite_sword>.mutable(),
            <item:minecraft:netherite_pickaxe>.mutable(),
            <item:minecraft:netherite_helmet>.mutable(),
            <item:minecraft:netherite_chestplate>.mutable(),
            <item:minecraft:netherite_leggings>.mutable(),
            <item:minecraft:netherite_boots>.mutable()
        ];
    }

    public getS3() as ItemStack[]{
        return [
            r[3],r[4],c[3],c[4],
            <item:mekanismgenerators:fusion_reactor_controller>.mutable(),
            <item:mekanismgenerators:fusion_reactor_frame>.mutable(),
            <item:mekanismgenerators:fusion_reactor_port>.mutable(),
            <item:mekanismgenerators:fusion_reactor_logic_adapter>.mutable(),
            <item:mekanismgenerators:hohlraum>.mutable(),
            <item:mekanismgenerators:hohlraum>,
            <item:mekanism:upgrade_filter>.mutable()
        ];
    }

    public getS2() as ItemStack[]{
        return [
            c[2],r[2],
            <item:mekanism:induction_casing>.mutable(),
            <item:mekanism:induction_port>.mutable(),
            <item:mekanism:basic_induction_cell>.mutable(),
            <item:mekanism:advanced_induction_cell>.mutable(),
            <item:mekanism:elite_induction_cell>.mutable(),
            <item:mekanism:ultimate_induction_cell>.mutable(),
            <item:mekanism:basic_induction_provider>.mutable(),
            <item:mekanism:advanced_induction_provider>.mutable(),
            <item:mekanism:elite_induction_provider>.mutable(),
            <item:mekanism:ultimate_induction_provider>.mutable()
        ];
    }

    public getS1() as ItemStack[]{
        return [
            c[1],
            r[1],
            <item:mekanism:advanced_control_circuit>.mutable(),
            <item:mekanism:elite_control_circuit>.mutable(),
            <item:mekanism:boiler_casing>.mutable(),
            <item:mekanism:boiler_valve>.mutable(),
            <item:mekanismgenerators:turbine_blade>.mutable(),
            <item:mekanismgenerators:turbine_rotor>.mutable(),
            <item:mekanismgenerators:turbine_casing>.mutable(),
            <item:mekanismgenerators:turbine_valve>.mutable(),
            <item:mekanismgenerators:turbine_vent>.mutable(),
            <item:mekanism:thermal_evaporation_controller>.mutable(),
            <item:mekanism:thermal_evaporation_valve>.mutable(),
            <item:mekanism:thermal_evaporation_block>.mutable(),
            <item:mekanism:qio_drive_array>.mutable(),
            <item:mekanism:portable_qio_dashboard>.mutable(),
            <item:mekanism:qio_drive_base>.mutable(),
            <item:mekanism:qio_dashboard>.mutable(),
            <item:mekanism:qio_importer>.mutable(),
            <item:mekanism:qio_redstone_adapter>.mutable(),
            <item:mekanism:qio_exporter>.mutable(),
            <item:mekanism:reprocessed_fissile_fragment>.mutable(),
            <item:mekanismgenerators:fission_reactor_casing>.mutable(),
            <item:mekanismgenerators:fission_reactor_port>.mutable(),
            <item:mekanismgenerators:fission_reactor_logic_adapter>.mutable(),
            <item:mekanismgenerators:fission_fuel_assembly>.mutable(),
            <item:mekanismgenerators:rotational_complex>.mutable(),
            <item:mekanism:pressure_disperser>.mutable()
        ];
    }
}
