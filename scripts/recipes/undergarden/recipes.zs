#priority 51
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.ItemStack;
import crafttweaker.api.item.IIngredient;

public function modifyUnderGradenRecipe() as void{
    var ca = <item:undergarden:catalyst>;
    var test = <item:emcworld:test_block>;
    removeRecipe([ca]);
    nucleosyRecipe(<item:cataclysm:ignitium_ingot>,<gas:mekanism:antimatter>*15,ca,400);
    natureSpawnerRecipe([
        <item:undergarden:forgotten_block>,
        <item:undergarden:brute_tusk>,
        <item:undergarden:cloggrum_battleaxe>,
        test,
    ],7500000,<entitytype:undergarden:masticator>,"under");
    natureSpawnerRecipe([
        <item:undergarden:masticator_scales>,
        test,
        <item:naturesaura:birth_spirit>
    ],2500000,<entitytype:undergarden:masticator>,"under_after");
}