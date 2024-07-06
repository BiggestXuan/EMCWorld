#priority 62
import mods.dimstages.DimensionStages;
import mods.emcworld.configHelper;
import mods.emcworld.CrTLang;

public function addDIMStage() as void{
    if(configHelper.isFreeMode()){
        return;
    }
    var dim as string[][]=[
        ["good_nights_sleep:good_dream","good_nights_sleep:nightmare","atlantis:altantis"],
        ["rats:ratlantis","mythicbotany:alfheim"],
        ["hem:blueleaf"],
        ["undergarden:undergarden"],
        ["aerialhell:aerial_hell"],
        ["minecraft:the_end"]
    ];
    var dm as string[] = [
        "eden","wildwood","apalachia","skythern","mortum"
    ];
    var s as string[] = [
        "one","two","three","four","five","six"
    ];
    for i in 0 .. s.length{
        st(dim[i],s[i]);
    }
    for i in dm{
        st(["divinerpg:"+i],"six");
    }
    st(["twilightforest:twilightforest"],"twilight");
    st(["minecraft:nether"],"nether");
}

public function st(dim as string[],s as string) as void{
    for d in dim{
       DimensionStages.stageDimensionWithMessage(d,CrTLang.DimTip(),s);  
    }
}