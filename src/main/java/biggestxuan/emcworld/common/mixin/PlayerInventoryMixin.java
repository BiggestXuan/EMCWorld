package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.recipes.ItemStageLimit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.INameable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/06/19
 */

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin implements IInventory, INameable {

    @Shadow private int timesChanged;

    @Shadow @Final public PlayerEntity player;

    /**
     * @author Biggest_Xuan
     * @reason NULL
     */
    @Overwrite
    @Override
    public void setChanged() {
        PlayerInventory inventory = (PlayerInventory) (Object) this;
        for (int i = 0; i < inventory.armor.size(); i++) {
            if(PreventItem(player,inventory.getItem(i))){
                player.drop(inventory.getItem(i),false);
                inventory.setItem(i, ItemStack.EMPTY);
            }
        }
        for (int i = 0; i < inventory.items.size(); i++) {
            if(PreventItem(player,inventory.getItem(i))){
                player.drop(inventory.getItem(i),false);
                inventory.setItem(i,ItemStack.EMPTY);
            }
        }
        ++this.timesChanged;
    }

    private static boolean PreventItem(PlayerEntity p,ItemStack stack){
        if(p instanceof ServerPlayerEntity){
            ServerPlayerEntity player = (ServerPlayerEntity) p;
            MinecraftServer server = player.getServer();
            RecipeManager manager = server.getRecipeManager();
            List<ItemStageLimit> recipes = manager.getAllRecipesFor(ItemStageLimit.ItemStageLimitType.INSTANCE);
            for(ItemStageLimit limit : recipes){
                if(limit.getItem().equals(stack.getItem()) && !GameStageManager.hasStage(player,limit.getStage())){
                    return true;
                }
            }
        }
        return false;
    }
}
