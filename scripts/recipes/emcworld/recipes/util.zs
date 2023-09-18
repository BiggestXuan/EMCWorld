#priority 56
import crafttweaker.api.item.IItemStack;

public function kt(a as IItemStack,b as IItemStack,c as IItemStack[][]) as void{
    for i in c{
        pul(a,b,i[0],i[1]);
    }
}

public function add_emc_stage(item as IItemStack[],stage as int) as void{
    for i in item{
        addEMCStage(i,stage);
    }
}

public function GodShardRecipe() as void{
    terraPlateRecipe([
        <item:atum:anput_godshard>,
        <item:atum:anubis_godshard>,
        <item:atum:atem_godshard>,
        <item:atum:geb_godshard>,
        <item:atum:horus_godshard>,
        <item:atum:isis_godshard>,
        <item:atum:montu_godshard>,
        <item:atum:nepthys_godshard>,
        <item:atum:nuit_godshard>,
        <item:atum:osiris_godshard>,
        <item:atum:ptah_godshard>,
        <item:atum:ra_godshard>,
        <item:atum:seth_godshard>,
        <item:atum:shu_godshard>,
        <item:atum:tefnut_godshard>,
        <item:mythicbotany:alfsteel_ingot>,
        <item:bloodmagic:reagentbinding>
    ],<item:emcworld:god_steel_ingot>,200000);
}