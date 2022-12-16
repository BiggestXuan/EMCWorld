package biggestxuan.emcworld.api.crafttweaker.CrockPot;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/16
 */

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.sihenzhang.crockpot.recipe.cooking.requirement.*;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.CTRequirement")
@SuppressWarnings("unused")
public class CrTRequirement {
    private List<IRequirement> requirementList = new ArrayList<>();

    @ZenCodeType.Constructor
    public CrTRequirement(){}

    @ZenCodeType.Method
    public CrTRequirement addCategoryMax(CrTFoodValue value){
        for (int i = 0; i < value.getCategory().size(); i++) {
            RequirementCategoryMaxExclusive max = new RequirementCategoryMaxExclusive(CrTFoodValue.getCategory(value.getCategory().get(i)),value.getValue().get(i));
            requirementList.add(max);
        }
        return this;
    }

    @ZenCodeType.Method
    public CrTRequirement addCategoryMin(CrTFoodValue value){
        for (int i = 0; i < value.getCategory().size(); i++) {
            RequirementCategoryMinExclusive max = new RequirementCategoryMinExclusive(CrTFoodValue.getCategory(value.getCategory().get(i)),value.getValue().get(i));
            requirementList.add(max);
        }
        return this;
    }

    @ZenCodeType.Method
    public CrTRequirement addMustItem(IIngredient ingredient,int amount){
        RequirementMustContainIngredient mustItem = new RequirementMustContainIngredient(ingredient.asVanillaIngredient(),amount);
        requirementList.add(mustItem);
        return this;
    }

    @ZenCodeType.Method
    public CrTRequirement addMustItemLessThan(IIngredient ingredient,int amount){
        RequirementMustContainIngredientLessThan mustItem = new RequirementMustContainIngredientLessThan(ingredient.asVanillaIngredient(),amount);
        requirementList.add(mustItem);
        return this;
    }

    public List<IRequirement> get(){
        return requirementList;
    }
}
