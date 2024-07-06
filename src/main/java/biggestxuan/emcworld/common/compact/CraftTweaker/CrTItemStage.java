package biggestxuan.emcworld.common.compact.CraftTweaker;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.recipes.ItemStageLimit;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import org.openzen.zencode.java.ZenCodeType;

/**
 * EMC WORLD MOD
 * @Author Biggest_Xuan
 * 2023/06/13
 */

@ZenRegister
@ZenCodeType.Name("mods.emcworld.CrTItemStage")
@SuppressWarnings("unused")
public class CrTItemStage implements IRecipeManager {
    @ZenCodeType.Method
    public void addRecipe(String name, ItemStack item,String stage){
        ItemStageLimit recipe = new ItemStageLimit(EMCWorld.rl(name),item.getItem(),stage);
        CraftTweakerAPI.apply(new ActionAddRecipe(this,recipe,""));
    }

    @Override
    public IRecipeType<ItemStageLimit> getRecipeType() {
        return ItemStageLimit.ItemStageLimitType.INSTANCE;
    }
}
