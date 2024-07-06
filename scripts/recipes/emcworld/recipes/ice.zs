#priority 62

import crafttweaker.api.item.IItemStack;

public function addEMCWorldIceInfuser() as void{
    var sn as IItemStack[] = Ice.INSTANCE.getIce();

    EMCWorldIce(sn[0],1);
    EMCWorldIce(sn[1],4);
    EMCWorldIce(sn[2],4);
    EMCWorldIce(sn[3],36);
    EMCWorldIce(sn[4],324);
    EMCWorldIce(sn[5],600);
}