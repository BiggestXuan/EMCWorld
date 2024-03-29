#priority 53
import mods.emcworld.EMCHelper;
import mods.emcworld.math;

import crafttweaker.api.item.ItemStack;
import crafttweaker.api.item.IItemStack;

public function setCrTItemEMC(item as ItemStack,emc as long) as void{
    EMCHelper.setItemEMC(item,emc);
}

public function setItemsEMC(item as ItemStack[],emc as long) as void{
    for i in item{
        EMCHelper.setItemEMC(i,emc);
    }
}

public function clearEMC(i as IItemStack[]) as void{
    for k in i{
        EMCHelper.clearItemEMC(k);
    }
}

public function getVoucherEMC() as int{
    var diff as double = getDifficultyLoss();
    if(diff >= 1) return 114514;
    else return (-10 * diff + 11) as int * 114514;
}

public function setEMC() as void{
    var emcValue as long[]=[16384,0,2048,2048,0,128,1024,2048,32767,128,getVoucherEMC(),24652,34206,176254,12068,5622,3780
    ,148306,2048,2048,4682,6026,920816,512,math.getEMCWhenUseGem(1000)+1024 as long
    ];
    var item as ItemStack[]=[
        <item:byg:pendorite_scraps>,
        <item:mekanism:uranium_ore>,
        <item:minecraft:emerald>,
        <item:byg:emeraldite_shards>,
        <item:botania:ender_air_bottle>,
        <item:minecraft:prismarine_shard>,
        <item:minecraft:sea_lantern>,
        <item:emcworld:stone_shard>,
        <item:bloodmagic:reagentbinding>,
        <item:byg:subzero_crystal_shard>,
        <item:emcworld:voucher>,
        <item:allthemodium:allthemodium_ingot>,
        <item:minecraft:netherite_scrap>,
        <item:allthemodium:vibranium_ingot>,
        <item:emcworld:orichalcos_ingot>,
        <item:emcworld:chlorophyte_ingot>,
        <item:emcworld:cold_ingot>,
        <item:emcworld:titanium_ingot>,
        <item:emcworld:niobium_nugget>,
        <item:emcworld:magnesium_ingot>,
        <item:emcworld:drystone_ingot>,
        <item:emcworld:sunlit_ingot>,
        <item:allthemodium:unobtainium_ingot>,
        <item:emcworld:aluminum_ingot>,
        <item:waystones:warp_stone>
    ];
    clearEMC(Ice.INSTANCE.getIce());
    for i in 0 .. item.length{
        setCrTItemEMC(item[i],emcValue[i]);
    }
    clearEMC(emcItem.INSTANCE.getStack());
    for i in <tag:items:minecraft:flowers>.elements{
        setCrTItemEMC(i.getDefaultInstance(),0);
    }
    for i in <tag:items:minecraft:carpets>.elements{
        setCrTItemEMC(i.getDefaultInstance(),0);
    }
    for i in <tag:items:atum:godshards>.elements{
        setCrTItemEMC(i.getDefaultInstance(),0);
    }
    if(getGameDifficulty()<=1){
        setCrTItemEMC(<item:atum:godforged_block>,82400);
    }
}