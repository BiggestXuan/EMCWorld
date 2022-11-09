package biggestxuan.emcworld.api.crafttweaker.CrockPot;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/29
 */

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.sihenzhang.crockpot.base.FoodCategory;
import com.sihenzhang.crockpot.base.FoodValues;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.CrTFoodValue")
public class CrTFoodValue{
    private final List<String> Category = new ArrayList<>();
    private final List<Float> value = new ArrayList<>();

    @ZenCodeType.Constructor
    public CrTFoodValue(){

    }

    @ZenCodeType.Method
    public FoodValues get(){
        FoodValues values = FoodValues.create();
        for (int i = 0; i < Category.size(); i++) {
            values.put(getCategory(this.Category.get(i)),this.value.get(i));
        }
        return values;
    }

    @ZenCodeType.Method
    public CrTFoodValue put(String Category,float value){
        this.Category.add(Category);
        this.value.add(value);
        return this;
    }

    private static FoodCategory getCategory(String name) throws NullPointerException{
        switch (name.toLowerCase()){
            case "meat":
                return FoodCategory.MEAT;
            case "monster":
                return FoodCategory.MONSTER;
            case "fish":
                return FoodCategory.FISH;
            case "egg":
                return FoodCategory.EGG;
            case "fruit":
                return FoodCategory.FRUIT;
            case "veggie":
                return FoodCategory.VEGGIE;
            case "dairy":
                return FoodCategory.DAIRY;
            case "sweetener":
                return FoodCategory.SWEETENER;
            case "frozen":
                return FoodCategory.FROZEN;
            case "inedible":
                return FoodCategory.INEDIBLE;
            default:
                throw new NullPointerException();
        }
    }
}
