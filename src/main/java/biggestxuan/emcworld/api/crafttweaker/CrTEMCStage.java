package biggestxuan.emcworld.api.crafttweaker;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/26
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.recipes.EMCStageLimit;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.EMCStage")
public class CrTEMCStage implements IRecipeManager {
    @ZenCodeType.Method
    public void addRecipe(String name, IItemStack item,String stage){
        ResourceLocation rl = EMCWorld.rl(name);
        Item item1 = item.getImmutableInternal().getItem();
        EMCStageLimit recipe = new EMCStageLimit(rl,item1,stage);
        CraftTweakerAPI.apply(new ActionAddRecipe(this,recipe,""));
    }

    @Override
    public IRecipeType<EMCStageLimit> getRecipeType() {
        return EMCStageLimit.EMCStageLimitType.INSTANCE;
    }
}
