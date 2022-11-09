#priority 42
import crafttweaker.api.item.IItemStack;

public function modifyHemRecipe() as void{
    var cop = <item:hem:copparite>;
    var gp = <item:hem:generator_piping>;
    var ifo = <item:mekanism:ingot_refined_obsidian>;
    var ifg = <item:mekanism:ingot_refined_glowstone>;
    var uuc = <item:mekanism:ultimate_universal_cable>;
    var bc = <item:mekanism:boiler_casing>;
    var bo = <item:hem:boileritem>;
    var tc = <item:mekanismgenerators:turbine_casing>;
    var se = <item:hem:steam_engine>;
    var cb = <item:hem:circuit_board>;
    var gr = <item:gobber2:gobber2_rod>;
    var aa = <item:mekanism:alloy_atomic>;
    var qe = <item:mekanism:quantum_entangloporter>;
    var toc = <item:hem:teir_one_computer>;
    var bp = <item:thermal:bronze_plate>;

    removeCraftRecipe([cb,cop]);
    modifyShapedRecipe([
        [ifo,cop,ifg],
        [uuc,uuc,uuc],
        [ifo,cop,ifg]
    ],gp*15);
    modifyShapedRecipe([
        [bc,bc,bc],
        [bc,<item:mekanism:dynamic_tank>,<item:mekanism:boiler_valve>],
        [bc,<item:twilightforest:fiery_block>,<item:thermal:device_water_gen>]
    ],bo);
    modifyShapedRecipe([
        [tc,tc,tc],
        [tc,<item:mekanismgenerators:turbine_rotor>,<item:mekanismgenerators:turbine_valve>],
        [tc,bo,gp]
    ],se);
    modifyShapedRecipe([
        [<item:minecraft:black_stained_glass>,cb,aa],
        [aa,<item:mekanism:energy_tablet>,cop],
        [cop,cop,gp]
    ],toc);
    modifyShapedRecipe([
        [gr,<item:minecraft:iron_door>,gr],
        [cop,cb,cop],
        [gp,qe,gp]
    ],<item:hem:transporter_bottom>);
    modifyShapedRecipe([
        [cop,cop,cop],
        [bp,<item:mekanism:teleportation_core>,bp],
        [gr,cb,gr]
    ],<item:hem:transporter_top>);
    combiningRecipe(<item:mekanism:ultimate_control_circuit>,cop,cb);
    DLRecipeSlurry(<item:hem:bronze_ingot>,<gas:mekanism:sulfuric_acid>,<slurry:emcworld:dirty_cupric_sulfate>*100,"");
    washingRecipe(<fluid:minecraft:water>*10,<slurry:emcworld:dirty_cupric_sulfate>,<slurry:emcworld:clean_cupric_sulfate>);
    crystallizingRecipe(<slurry:emcworld:clean_cupric_sulfate>*100,<item:hem:copparite>);
}