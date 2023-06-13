package biggestxuan.emcworld.common.compact.CraftTweaker;

import biggestxuan.emcworld.EMCWorld;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;

/**
 * EMC WORLD MOD
 * @Author Biggest_Xuan
 * 2023/06/12
 */

@ZenRegister
@ZenCodeType.Name("mods.emcworld.CrTSingularity")
@SuppressWarnings("unused")
public class CrTSingularity {
    private final List<ItemStack> stacks = new ArrayList<>();

    @ZenCodeType.Constructor
    public CrTSingularity(String name,int amt){
        addSingularity(name, amt);
    }

    @ZenCodeType.Method
    public CrTSingularity addSingularity(String name,int amt){
        ItemStack stack = getSingularity(name);
        stack.setCount(amt);
        stacks.add(stack);
        return this;
    }

    @ZenCodeType.Method
    public CrTSingularity addSingularity(String ... name){
        for(String n : name){
            stacks.add(getSingularity(n));
        }
        return this;
    }

    @ZenCodeType.Method
    public IIngredient asIIngredient(){
        return IIngredient.fromIngredient(Ingredient.of(stacks.stream()));
    }

    @ZenCodeType.Method
    public static ItemStack getSingularity(String name){
        ItemStack stack = EMCWorld.getItem("extendedcrafting:singularity");
        CompoundNBT nbt = stack.getOrCreateTag();
        if(!name.contains(":")){
            name = "extendedcrafting:"+name;
        }
        nbt.putString("Id",name);

        return stack;
    }

    @ZenCodeType.Method
    public static IIngredient getCrTSingularity(String name){
        return IIngredient.fromIngredient(Ingredient.of(getSingularity(name)));
    }

    @ZenCodeType.Method
    public static IIngredient getManyCrTSingularity(String ... name){
        return IIngredient.fromIngredient(Ingredient.of(getItemStackArray(name)));
    }

    private static ItemStack[] getItemStackArray(String[] name){
        ItemStack[] array = new ItemStack[name.length];
        for (int i = 0; i < name.length; i++) {
            array[i] = getSingularity(name[i]);
        }
        return array;
    }
}
