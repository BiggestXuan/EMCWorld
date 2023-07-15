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
