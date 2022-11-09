#priority 62
import mods.dimstages.DimensionStages;
import mods.emcworld.configHelper;
import mods.emcworld.CrTLang;

public function addDIMStage() as void{
    if(configHelper.isFreeMode()) return;
    var dim as string[][]=[
        ["good_nights_sleep:good_dream","good_nights_sleep:nightmare","bloodmagic:dungeon","atlantis:altantis"],
        ["rats:ratlantis","mythicbotany:alfheim"],
        ["hem:blueleaf"],
        ["the_afterlight:the_afterlight","undergarden:undergarden"]
    ];
    var s as string[] = [
        "one","two","three","four"
    ];
    for i in 0 .. s.length{
        st(dim[i],s[i]);
    }
    st(["twilightforest:twilightforest"],"twilight");
    st(["minecraft:nether"],"nether");
}

private function st(dim as string[],s as string) as void{
    for d in dim{
       DimensionStages.stageDimensionWithMessage(d,CrTLang.DimTip(),s);  
    }
}