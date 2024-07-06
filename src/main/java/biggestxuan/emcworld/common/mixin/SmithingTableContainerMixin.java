package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.api.EMCWorldSince;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.AbstractRepairContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.SmithingTableContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SmithingRecipe;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/06/29
 */

@Mixin(SmithingTableContainer.class)
@EMCWorldSince("0.9.0")
public abstract class SmithingTableContainerMixin extends AbstractRepairContainer {

    @Shadow @Nullable private SmithingRecipe selectedRecipe;

    @Shadow protected abstract void shrinkStackInSlot(int p_234654_1_);

    @Shadow @Final private World level;

    public SmithingTableContainerMixin(@Nullable ContainerType<?> p_i231587_1_, int p_i231587_2_, PlayerInventory p_i231587_3_, IWorldPosCallable p_i231587_4_) {
        super(p_i231587_1_, p_i231587_2_, p_i231587_3_, p_i231587_4_);
    }

    @Inject(method = "onTake",at = @At("RETURN"))
    public void _inject(PlayerEntity p_230301_1_, ItemStack p_230301_2_, CallbackInfoReturnable<ItemStack> cir){
        if(this.selectedRecipe != null){
            Ingredient item = this.selectedRecipe.addition;
            ItemStack stack = this.inputSlots.getItem(1);
            for(ItemStack stack1 : item.getItems()){
                if(stack.getItem().equals(stack1.getItem()) && stack1.getCount() > 1){
                    for (int i = 0; i < stack1.getCount()-1; i++) {
                        this.shrinkStackInSlot(1);
                    }
                }
            }
        }
    }

    /**
     * @author Biggest_Xuan
     * @reason Support multi items.
     */
    @Override
    @Overwrite
    public void createResult() {
        List<SmithingRecipe> list = this.level.getRecipeManager().getRecipesFor(IRecipeType.SMITHING, this.inputSlots, this.level);
        if (list.isEmpty()) {
            resultSlots.setItem(0, ItemStack.EMPTY);
        } else {
            selectedRecipe = list.get(0);
            ItemStack add = this.inputSlots.getItem(1);
            if(selectedRecipe != null){
                Ingredient gre = selectedRecipe.addition;
                for(ItemStack s : gre.getItems()){
                    if(add.getItem().equals(s.getItem())){
                        if(add.getCount() < s.getCount()){
                            resultSlots.setItem(0,ItemStack.EMPTY);
                            return;
                        }
                    }
                }
                ItemStack itemstack = selectedRecipe.assemble(this.inputSlots);
                resultSlots.setRecipeUsed(selectedRecipe);
                resultSlots.setItem(0, itemstack);
            }
        }
    }
}
