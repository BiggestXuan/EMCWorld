package biggestxuan.emcworld.common.recipes;

import biggestxuan.emcworld.EMCWorld;
import dev.latvian.mods.projectex.Matter;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import javax.annotation.Nullable;

/**
 * EMC WORLD MOD
 * @Author Biggest_Xuan
 * 2023/06/17
 */

public enum MatterFixRecipe {
    S3(Matter.MAGENTA,1,0.25),
    S3a(Matter.MAGENTA,2,0.52),
    S3b(Matter.MAGENTA,3,0.79),
    S4(Matter.PINK,1,0.26),
    S4a(Matter.PINK,2,0.54),
    S4b(Matter.PINK,3,0.82),
    S5(Matter.PURPLE,1,0.27),
    S5a(Matter.PURPLE,2,0.57),
    S5b(Matter.PURPLE,3,0.86),
    S6(Matter.VIOLET,1,0.28),
    S6a(Matter.VIOLET,2,0.58),
    S6b(Matter.VIOLET,3,0.88),
    S7(Matter.BLUE,1,0.29),
    S7a(Matter.BLUE,2,0.6),
    S7b(Matter.BLUE,3,0.91),
    S8(Matter.CYAN,1,0.3),
    S8a(Matter.CYAN,2,0.62),
    S8b(Matter.CYAN,3,0.93),
    S9(Matter.GREEN,1,0.31),
    S9a(Matter.GREEN,2,0.63),
    S9b(Matter.GREEN,3,0.95),
    S10(Matter.LIME,1,0.32),
    S10a(Matter.LIME,2,0.65),
    S10b(Matter.LIME,3,0.98),
    S11(Matter.YELLOW,1,0.33),
    S11a(Matter.YELLOW,2,0.69),
    S12(Matter.ORANGE,1,0.34),
    S12a(Matter.ORANGE,2,0.7),
    S13(Matter.WHITE,1,0.35),
    S13a(Matter.WHITE,2,0.72),
    ;

    private final Matter matter;
    private final int count;
    private final double rate;

    MatterFixRecipe(Matter matter,int count,double rate){
        this.matter = matter;
        this.count = count;
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }

    public ItemStack getMatter() {
        ItemStack s = EMCWorld.getItem("emcworld:"+matter.name+"_matter");
        s.setCount(count);
        return s;
    }

    public Matter matter(){
        return matter;
    }

    public int getCount() {
        return count;
    }

    @Nullable
    public static MatterFixRecipe getRecipe(ItemStack stack){
        if(stack.getItem().getRegistryName() == null || !(stack.getItem().getRegistryName().getPath().contains("matter"))){
            return null;
        }
        MatterFixRecipe temp = null;
        for(MatterFixRecipe r : MatterFixRecipe.values()){
            if(r.getMatter().getItem().equals(stack.getItem())){
                if(stack.getCount() >= r.getCount()){
                    temp = r;
                }else{
                    return temp;
                }
            }
        }
        return temp;
    }
}
