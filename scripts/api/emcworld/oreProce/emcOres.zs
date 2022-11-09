#priority 51
import crafttweaker.api.item.ItemStack;

public class emcOres{
    public var item as ItemStack;
    public var rate as int;

    public this(item as ItemStack,rate as int){
        this.item = item;
        this.rate = rate;
    }

    public getItem() as ItemStack{
        return this.item;
    }

    public getRate() as int{
        return this.rate;
    }
}