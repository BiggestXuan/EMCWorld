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
        <item:emcworld:red_green_dagger>,
        <item:emcworld:shengxuan>
    ];
    val c as IItemStack[] = new Getter().getCollector();
    val r as IItemStack[] = new Getter().getRelay();
    val f as IItemStack[] = new Getter().getFlowers();
    for i in [<item:emcworld:profession_sword>,<item:emcworld:profession_tank>,<item:emcworld:nopower_staff>,
    <item:emcworld:broken_dagger>,<item:emcworld:broken_hammer>,<item:emcworld:broken_gun>]{
        <tag:items:emcworld:setting_profession>.add(i);
    }
    for i in [<item:emcworld:bright_stone>,<item:emcworld:dark_stone>]{
        <tag:items:emcworld:modify_profession>.add(i);
    }
    for i in items{
        <tag:items:emcworld:god_weapon>.add(i);
    }
    for i in [c[0],r[0],f[0]]{
        <tag:items:emcworld:stage_one>.add(i);
    }
    for i in [c[1],r[1],f[1]]{
        <tag:items:emcworld:stage_two>.add(i);
    }
    for i in [c[2],r[2],f[2]]{
        <tag:items:emcworld:stage_three>.add(i);
    }
    for i in [c[4],r[4],f[4]]{
        <tag:items:emcworld:stage_four>.add(i);
    }
    for i in [c[6],r[6],f[6]]{
        <tag:items:emcworld:stage_five>.add(i);
    }
    for i in [c[7],r[7],f[7]]{
        <tag:items:emcworld:stage_six>.add(i);
    }
    for i in [c[8],r[8],f[8]]{
        <tag:items:emcworld:stage_seven>.add(i);
    }
    for i in [c[9],r[9],f[9]]{
        <tag:items:emcworld:stage_eight>.add(i);
    }
    for i in [c[10],r[10],f[10]]{
        <tag:items:emcworld:level_eleven>.add(i);
    }
    for i in [c[11],r[11],f[11]]{
        <tag:items:emcworld:level_twelve>.add(i);
    }
    for i in [c[12],r[12],f[12]]{
        <tag:items:emcworld:level_thirteen>.add(i);
    }
    for i in [c[13],r[13],f[13]]{
        <tag:items:emcworld:level_fourteen>.add(i);
    }
}
