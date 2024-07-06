#priority 52
import crafttweaker.api.item.IItemStack;

import mods.emcworld.CrTWeightItem;

public function gemTrade() as void{
    var matters = [
        <item:emcworld:violet_matter>,
        <item:emcworld:blue_matter>,
        <item:emcworld:cyan_matter>,
        <item:emcworld:green_matter>,
        <item:emcworld:lime_matter>
    ] as IItemStack[];
    var rate = [1,2,16,64,256] as int[];
    var core = <item:emcworld:gemstone_core>;
    var gems = [
        <item:emcworld:blood_gemstone>,
        <item:emcworld:nature_gemstone>,
        <item:emcworld:lake_gemstone>,
        <item:emcworld:abyss_gemstone>
    ] as IItemStack[];
    for i in 0 .. matters.length{
        var outputs = [
            new CrTWeightItem(core,1,1,1*rate[i]),
            new CrTWeightItem(gems[0],1,1,3*rate[i]),
            new CrTWeightItem(gems[1],1,1,3*rate[i]),
            new CrTWeightItem(gems[2],1,1,3*rate[i]),
            new CrTWeightItem(gems[3],1,1,3*rate[i]),
            new CrTWeightItem(<item:emcworld:scroll_green>,1,4,1000),
            new CrTWeightItem(<item:emcworld:scroll_blue>,1,2,750),
            new CrTWeightItem(<item:emcworld:scroll_purple>,0,1,500)
        ] as CrTWeightItem[];
        piglinTrade(matters[i],outputs);
    }
    piglinTrade(<item:minecraft:netherite_ingot>,[
        new CrTWeightItem(<item:emcworld:shengxuan>,1,1,1),
        new CrTWeightItem(<item:minecraft:netherite_scrap>,1,1,99)
    ] as CrTWeightItem[]);
}