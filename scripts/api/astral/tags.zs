#priority 29
import crafttweaker.api.item.IItemStack;

public function addMarbleTags() as void{
    var marbles as IItemStack[]=[
        <item:astralsorcery:marble_arch>,
        <item:astralsorcery:marble_bricks>,
        <item:astralsorcery:marble_chiseled>,
        <item:astralsorcery:marble_engraved>,
        <item:astralsorcery:marble_pillar>,
        <item:astralsorcery:marble_raw>,
        <item:astralsorcery:marble_runed>
    ];
    for item in marbles{
        <tag:items:forge:marble>.add(item);
    }
}