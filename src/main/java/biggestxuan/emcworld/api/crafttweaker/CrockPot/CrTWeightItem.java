package biggestxuan.emcworld.api.crafttweaker.CrockPot;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/28
 */

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.sihenzhang.crockpot.recipe.WeightedItem;
import net.minecraft.item.Item;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.CrTWeightItem")
@SuppressWarnings("unused")
public class CrTWeightItem {
    private final Item item;
    private final int min;
    private final int max;
    private final int weight;

    @ZenCodeType.Constructor
    public CrTWeightItem(IItemStack stack,int min,int max,int weight){
        this.item = stack.getImmutableInternal().getItem();
        this.min = min;
        this.max = max;
        this.weight = weight;
    }

    @ZenCodeType.Constructor
    public CrTWeightItem(IItemStack stack,int min,int max){
        this(stack,min,max,1);
    }

    public WeightedItem get(){
        return new WeightedItem(item,min,max,weight);
    }
}
