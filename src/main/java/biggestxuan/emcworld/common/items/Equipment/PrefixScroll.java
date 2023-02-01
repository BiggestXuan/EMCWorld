package biggestxuan.emcworld.common.items.Equipment;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/01
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.common.items.EWItem;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import biggestxuan.emcworld.common.utils.MathUtils;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

@SuppressWarnings("unused")
public class PrefixScroll extends EWItem {
    public static final int MAX = 50000;

    public PrefixScroll(){
        super(new Properties().tab(EWCreativeTabs.EW_CREATIVE_TAB).stacksTo(1));
    }

    @Override
    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_){
        super.use(p_77659_1_,p_77659_2_,p_77659_3_);
        ItemStack stack = p_77659_2_.getMainHandItem();
        if(p_77659_2_.isShiftKeyDown()){
            update(stack,50000);
        }else update(stack,10000);
        return ActionResult.success(stack);
    }

    private void init(ItemStack stack){
        CompoundNBT nbt = stack.getOrCreateTag();
        nbt.putInt("total",0);
        for(IPrefixItem.Prefix prefix : IPrefixItem.Prefix.values()){
            if(prefix == IPrefixItem.Prefix.NULL) continue;
            nbt.putInt(prefix.toString(),0);
        }
        nbt.putBoolean("dirty",true);
    }

    @Override
    public void inventoryTick(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        if(p_77663_1_.getTag() == null || !p_77663_1_.getTag().contains("dirty")){
            init(p_77663_1_);
            update(p_77663_1_,1);
        }
    }

    public void update(ItemStack stack,int total){
        CompoundNBT nbt = stack.getOrCreateTag();
        init(stack);
        int m = 1;
        if(total >= 10) m++;
        if(total >= 20) m++;
        if(total >= 50) m++;
        if(total >= 300) m++;
        if(total >= 1000) m++;
        if(total >= 4000) m++;
        if(total >= 10000) m++;
        if(total >= 20000) m++;
        if(total >= 35000) m++;
        for (int i = 0; i < total; i++) {
            int c = MathUtils.getRangeRandom(1,m+1);
            IPrefixItem.Prefix prefix = IPrefixItem.getPrefix(c);
            nbt.putInt(prefix.toString(),nbt.getInt(prefix.toString())+c);
        }
        nbt.putInt("total",getTotal(stack));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        CompoundNBT nbt = p_77624_1_.getOrCreateTag();
        int total = getTotal(p_77624_1_);
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.prefix.scroll_main"));
        for(IPrefixItem.Prefix prefix : IPrefixItem.Prefix.values()){
            if(prefix == IPrefixItem.Prefix.NULL) continue;
            if(nbt.contains(prefix.toString())){
                int c = nbt.getInt(prefix.toString());
                if(c > 0){
                    TranslationTextComponent text = (TranslationTextComponent) prefix.getName().withStyle(Style.EMPTY.withColor(Color.fromRgb(prefix.getColor()))).append(" : "+String.format("%.2f", 100d * c / total)+" %");
                    p_77624_3_.add(text);
                }
            }
        }
    }

    public static int getTotal(ItemStack stack){
        CompoundNBT nbt = stack.getOrCreateTag();
        int total = 0;
        for(IPrefixItem.Prefix prefix : IPrefixItem.Prefix.values()){
            if(!nbt.contains(prefix.toString()) || nbt.getInt(prefix.toString()) == 0){
                continue;
            }
            total += nbt.getInt(prefix.toString());
        }
        return total;
    }
}
