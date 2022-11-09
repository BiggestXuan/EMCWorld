package biggestxuan.emcworld.common.compact.JEI.PiglinRecipe;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/06
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public enum JEIPiglinRecipe {
    N1(new ItemStack(EWItems.NIOBIUM_NUGGET.get(),1),0.15),
    N2(new ItemStack(EWItems.NIOBIUM_NUGGET.get(),2),0.07),
    N3(new ItemStack(EWItems.NIOBIUM_NUGGET.get(),3),0.03),
    N4(new ItemStack(EWItems.NIOBIUM_NUGGET.get(),5),0.03),
    BIG_GEM(new ItemStack(EWItems.BIG_EMC_GEM.get(),2),0.08),
    BIGGEST_GEM(new ItemStack(EWItems.BIGGEST_EMC_GEM.get(),1),0.02)
    ;
    private final ItemStack output;
    private final double chance;

    JEIPiglinRecipe(ItemStack output,double chance){
        this.output = output;
        this.chance = chance;
    }

    public ResourceLocation getRL(){
        return EMCWorld.rl(output.getItem().getRegistryName().getPath()+"_"+output.getCount());
    }

    public String getChance() {
        return String.format("%.0f",this.chance * MathUtils.difficultyLoss() * 100) + "%";
    }

    public ItemStack getOutput() {
        return output;
    }

    public ItemStack getInput(){
        return new ItemStack(Items.GOLD_INGOT,1);
    }
}
