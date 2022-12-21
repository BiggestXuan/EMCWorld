#priority 74
import crafttweaker.api.item.ItemStack;
import crafttweaker.api.item.IItemStack;

public function modifyEMCWorldTags() as void{
    val items as IItemStack[] = [
        <item:emcworld:god_ice_sword>,
        <item:emcworld:god_fire_sword>,
        <item:emcworld:god_nature_sword>,
        <item:emcworld:god_null_sword>,
        <item:emcworld:chara_sword>,
        <item:emcworld:purple_staff>,
        <item:emcworld:nature_staff>,
        <item:emcworld:creation>,
        <item:emcworld:super_star>,
        <item:emcworld:night_light>,
        <item:emcworld:red_green_dagger>
    ];
    val c as ItemStack[] = new Getter().getCollector();
    val r as ItemStack[] = new Getter().getRelay();
    for i in [<item:emcworld:profession_sword>,<item:emcworld:profession_tank>,<item:emcworld:nopower_staff>]{
        <tag:items:emcworld:setting_profession>.add(i);
    }
    for i in [<item:emcworld:bright_stone>,<item:emcworld:dark_stone>]{
        <tag:items:emcworld:modify_profession>.add(i);
    }
    for i in items{
        <tag:items:emcworld:god_weapon>.add(i);
    }
    for i in [c[0],r[0]]{
        <tag:items:emcworld:stage_one>.add(i.asIItemStack());
    }
    for i in [c[1],r[1]]{
        <tag:items:emcworld:stage_two>.add(i.asIItemStack());
    }
    for i in [c[2],r[2]]{
        <tag:items:emcworld:stage_three>.add(i.asIItemStack());
    }
    for i in [c[4],r[4]]{
        <tag:items:emcworld:stage_four>.add(i.asIItemStack());
    }
    for i in [c[6],r[6]]{
        <tag:items:emcworld:stage_five>.add(i.asIItemStack());
    }
    for i in [c[7],r[7]]{
        <tag:items:emcworld:stage_six>.add(i.asIItemStack());
    }
    for i in [c[8],r[8]]{
        <tag:items:emcworld:stage_seven>.add(i.asIItemStack());
    }
    for i in [c[9],r[9]]{
        <tag:items:emcworld:stage_eight>.add(i.asIItemStack());
    }
}
