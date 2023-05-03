package biggestxuan.emcworld.common.items.SponsorsItem;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/25
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.ISponsorItem;
import biggestxuan.emcworld.common.items.EWItem;
import biggestxuan.emcworld.common.utils.Sponsors.Sponsors;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.botania.common.item.ModItems;

import javax.annotation.Nullable;
import java.util.List;

public class AbunanaLoot extends EWItem implements ISponsorItem {

    public AbunanaLoot(){
    }

    @Override
    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_){
        ItemStack stack = p_77659_2_.getItemInHand(p_77659_3_);
        ItemStack i;
        if(p_77659_2_.level.isClientSide) return ActionResult.fail(stack);
        double math = Math.random();
        if(math <= 0.0001){
            i = new ItemStack(ModItems.dice);
        }else if(math <= 0.1999){
            i = new ItemStack(ModItems.manaDiamond);
        }else if(math <= 0.3){
            i = new ItemStack(ModItems.manaSteel);
            i.setCount(3);
        }else {
            i = new ItemStack(ModItems.blackLotus);
            i.setCount(10);
        }
        p_77659_2_.addItem(i);
        stack.shrink(1);
        return ActionResult.consume(stack);
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.abunana_loot"));
    }


    @Override
    public Sponsors getSponsor(){
        return Sponsors.all.ABunana.getSponsors();
    }
}
