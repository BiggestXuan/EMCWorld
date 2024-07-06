package biggestxuan.emcworld.common.items;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.FTBQJeiUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/14
 */

@EMCWorldSince("1.0.6")
public class EMCWorldQuestsItem extends Item {
    public EMCWorldQuestsItem() {
        super(new Properties().tab(EWCreativeTabs.EW_FTBQ_TAB).stacksTo(1));
    }

    @Override
    @Nonnull
    public ITextComponent getName(@Nonnull ItemStack p_200295_1_) {
        if(!p_200295_1_.hasTag()){
            return super.getName(p_200295_1_);
        }
        var nbt = p_200295_1_.getTag();
        return EMCWorld.tc("item.emcworld.ftbq",nbt.getString("emcworld_ftbq_title"));
    }

    public void fillItemCategory(ItemGroup p_150895_1_, NonNullList<ItemStack> p_150895_2_){
        super.fillItemCategory(p_150895_1_, p_150895_2_);
        if(allowdedIn(p_150895_1_)){
            for(String s : FTBQJeiUtils.getAllQuestsName(false)){
                p_150895_2_.add(getQuestItemByName(s));
            }
        }
    }

    public static ItemStack getQuestItemByName(String name){
        ItemStack stack = new ItemStack(EWItems.EMCWORLD_QUEST_ITEM.get());
        var nbt = stack.getOrCreateTag();
        nbt.putString("emcworld_ftbq_title",name);
        return stack;
    }
}
