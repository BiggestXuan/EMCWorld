# priority 36
import mods.emcworld.configHelper;

public function gns() as void{
    var names as string[] = [
        "good_nights_sleep:entities/baby_creeper",
        "good_nights_sleep:entities/gns_spawner",
        "good_nights_sleep:entities/herobrine",
        "good_nights_sleep:entities/tormenter"
    ];
    var bi = <item:extendedcrafting:black_iron_ingot>;
    if(configHelper.isFreeMode()) return;
    for i in names{
        modifyLootTable(<item:bloodmagic:ingot_hellforged>,0.1f,getRL(i));
        modifyLootTable(<item:bloodmagic:ingot_hellforged>*2,0.07f,getRL(i));
        modifyLootTable(bi*2,0.15f,getRL(i));
        modifyLootTable(bi*1,0.2f,getRL(i));
        modifyLootTable(bi*3,0.1f,getRL(i));
    }
}
