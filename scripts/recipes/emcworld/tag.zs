#priority 74
import crafttweaker.api.item.IItemStack;

public function modifyEMCWorldTags() as void{
    val items as IItemStack[] = [
        <item:emcworld:god_ice_sword>,
        <item:emcworld:god_fire_sword>,
        <item:emcworld:god_nature_sword>,
        <item:emcworld:god_null_sword>,
        <item:emcworld:chara_sword>
    ];
    for i in [<item:emcworld:profession_sword>,<item:emcworld:profession_tank>,<item:emcworld:nopower_staff>]{
        <tag:items:emcworld:setting_profession>.add(i);
    }
    for i in [<item:emcworld:bright_stone>,<item:emcworld:dark_stone>]{
        <tag:items:emcworld:modify_profession>.add(i);
    }
    for i in items{
        <tag:items:emcworld:god_weapon>.add(i);
    }
}
