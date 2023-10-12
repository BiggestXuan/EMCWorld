package biggestxuan.emcworld.common.events;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.recipes.InfuserRecipe;
import io.github.noeppi_noeppi.libx.event.DatapacksReloadedEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.NonNullList;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/09/23
 */

@Mod.EventBusSubscriber(modid = EMCWorld.MODID)
public class reloadEvent {
    @SubscribeEvent
    public static void ReloadEvent(DatapacksReloadedEvent event){
        /*NonNullList<Ingredient> list = NonNullList.create();
        for (int i = 0; i < 5; i++) {
            list.add(Ingredient.of(new ItemStack(Items.BEACON,1)));
        }
        InfuserRecipe test = new InfuserRecipe(EMCWorld.rl("test11"),new ItemStack(Items.COAL),list,1,100,100);
        RecipeManager manager = event.getServer().getRecipeManager();
        manager.getAllRecipesFor(InfuserRecipe.Type.INSTANCE).add(test);*/
        //test failed.
    }
}
