#priority 62
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.ItemStack;

import mods.emcworld.configHelper;

public function setItemStage() as void{
    if(configHelper.isFreeMode()){
        return;
    }
    val c as ItemStack[] = new Getter().getCollector();
    val r as ItemStack[] = new Getter().getRelay();
    val t = sitem.INSTANCE;
    val item as ItemStack[][] = [
        t.getS1(),
        t.getS2(),
        t.getS3(),
        t.getS4(),
        t.getS5(),
        t.getS6(),
        t.getS7(),
        t.getS8()
    ];

    val mod_name as string[][] = [
        ["aether","bloodmagic","atum"],
        ["good_nights_sleep","atlantis","mythicbotany","extendedcrafting"],
        ["twilightforest","ratlantis","astralsorcery"],
        ["hem","naturesaura"],
        ["the_afterlight","undergarden"],
        [""],
        [""],
        [""]
    ];
    val stages as string[] = [
        "one","two","three","four","five","six","seven","eight"
    ];

    for i in 0 .. stages.length{
        var s as string = stages[i];
        addModStage(mod_name[i],s);
        addStage(item[i],s);
    }
}