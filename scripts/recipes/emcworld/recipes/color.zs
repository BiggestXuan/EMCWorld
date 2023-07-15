#priority 52
import crafttweaker.api.item.IItemStack;

public function yellow(c as IItemStack,r as IItemStack) as void{
    pul(<item:emcworld:yellow_matter>,<item:emcworld:dragon_steel>,c,r);
}

public function white() as void{
    kt(<item:emcworld:clay_matter>,<item:extendedcrafting:the_ultimate_ingot>,[[<item:projectex:orange_relay>,<item:projectex:white_relay>],[<item:projectex:orange_collector>,<item:projectex:white_collector>]]);
}

public function violet(c as IItemStack,r as IItemStack) as void{
    var evm = <item:emcworld:violet_matter>;
    treeRitualRecipe([
        <item:cataclysm:ignitium_block>,evm,evm,<item:emcworld:advanced_emc_gem>,<item:gobber2:gobber2_block_nether>,
        c,<item:byg:death_cap>,<item:naturesaura:token_euphoria>
    ],<item:byg:blue_enchanted_sapling>,r);
}