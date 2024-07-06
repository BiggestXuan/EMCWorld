#priority 68
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.IIngredient;

import mods.emcworld.CrTFoodValue;

public class FoodValue{

    public this(){

    }

    public static var money_value = new CrTFoodValue().put("inedible",1.0f);
}

public function addFoodValue(food as IIngredient,value as CrTFoodValue) as void{
    <recipetype:crockpot:food_values>.addRecipe(name(food.items[0])+"_"+food.items.length,food,value,false);
}