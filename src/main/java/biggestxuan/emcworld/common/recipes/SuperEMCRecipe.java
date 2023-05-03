package biggestxuan.emcworld.common.recipes;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/28
 */

import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.api.item.equipment.IStarItem;
import biggestxuan.emcworld.common.exception.EMCWorldCommonException;
import biggestxuan.emcworld.common.registry.EWItems;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nonnull;

public enum SuperEMCRecipe {
    R1(EWItems.FIRE_RED_HELMET,EWItems.GUARDIAN_HELMET,true),
    R2(EWItems.FIRE_RED_CHESTPLATE,EWItems.GUARDIAN_CHESTPLATE,true),
    R3(EWItems.FIRE_RED_LEGGINGS,EWItems.GUARDIAN_LEGGINGS,true),
    R4(EWItems.FIRE_RED_BOOTS,EWItems.GUARDIAN_BOOTS,true)
    ;
    private final ItemStack input;
    private final CraftCondition input1;
    private final ItemStack output;
    private final boolean costLevel;

    SuperEMCRecipe(ItemStack input,CraftCondition input1,ItemStack output,boolean costLevel){
        this.input = input;
        this.input1 = input1;
        this.output = output;
        this.costLevel = costLevel;
    }

    public ItemStack getInput(){
        return input;
    }

    public ItemStack getInput1(){
        return input1.stack;
    }

    public ItemStack getOutput(){
        return output;
    }

    SuperEMCRecipe(RegistryObject<Item> input,RegistryObject<Item> output,boolean costLevel){
        this(new ItemStack(EWItems.DRAGON_STEEL.get(),1),getEMCGodArmorCondition(new ItemStack(input.get(),1)),new ItemStack(output.get(),1),costLevel);
    }

    public static CraftCondition getEMCGodArmorCondition(ItemStack stack){
        CompoundNBT nbt = stack.getOrCreateTag();
        nbt.putInt("level",12);
        stack.setTag(nbt);
        return new CraftCondition(stack, new ICraftCondition() {
            @Override
            public boolean getCraftLevel(ItemStack stack) {
                return ICraftCondition.super.getCraftLevel(stack) && ((IUpgradeableItem) stack.getItem()).getLevel(stack) == 12;
            }
        });
    }

    @Nonnull
    public static ItemStack match(@Nonnull Inventory inventory){
        if(inventory.getContainerSize() < 3) throw new EMCWorldCommonException("Inventory size must more than 3!");
        ItemStack input = inventory.getItem(0);
        ItemStack input1 = inventory.getItem(1);
        for(SuperEMCRecipe EMCRecipe:SuperEMCRecipe.values()){
            if(input.getItem().equals(EMCRecipe.input.getItem()) && input1.getItem().equals(EMCRecipe.input1.stack.getItem())){
                boolean flag = true;
                ICraftCondition condition = EMCRecipe.input1.condition;
                if(!condition.getCraftLevel(input1)){
                    flag = false;
                }
                if(!condition.getStackPrefix(input1)){
                    flag = false;
                }
                if(!condition.getStar(input1)){
                    flag = false;
                }
                if(flag){
                    ItemStack result = EMCRecipe.output.copy();
                    CompoundNBT nbt = input1.getOrCreateTag();
                    result.setTag(nbt);
                    if(EMCRecipe.costLevel && result.getItem() instanceof IUpgradeableItem){
                        IUpgradeableItem item = (IUpgradeableItem) result.getItem();
                        item.setLevel(result,0);
                        return result;
                    }
                }
            }
        }
        return input1;
    }

    private static class CraftCondition extends BaseCondition{
        private final ItemStack stack;
        private final ICraftCondition condition;

        private CraftCondition(ItemStack stack,ICraftCondition condition){
            this.stack = stack;
            this.condition = condition;
        }
    }

    private abstract static class BaseCondition implements ICraftCondition{

    }

    private interface ICraftCondition{
        default boolean getCraftLevel(ItemStack stack){
            return stack.getItem() instanceof IUpgradeableItem;
        }

        default boolean getStackPrefix(ItemStack stack){
            return stack.getItem() instanceof IPrefixItem;
        }

        default boolean getStar(ItemStack stack){
            return stack.getItem() instanceof IStarItem;
        }
    }
}
