# priority 33

import crafttweaker.api.item.IItemStack;

public function dsk(a as IItemStack,p as float) as void{
    var n = "rats:chest/dutchrat_ship";
    modifyLootTable(a,0.07f*p,getRL(n));
    modifyLootTable(a*2,0.03f*p,getRL(n));
    modifyLootTable(a*3,0.01f*p,getRL(n));
}

public function ratl() as void{
    dsk(<item:rats:oratchalcum_ingot>,1f);
    dsk(<item:rats:ratlantean_flame>,3.5f);
}