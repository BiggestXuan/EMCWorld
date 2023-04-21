package biggestxuan.emcworld.common.items.Equipment;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/01
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.items.EWItem;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

@SuppressWarnings("unused")
public class PrefixScroll extends EWItem {
    public static final int MAX = 70000;

    public PrefixScroll(){
        super(new Properties().tab(EWCreativeTabs.EW_CREATIVE_TAB).stacksTo(1));
    }

    private static void init(ItemStack stack){
        CompoundNBT nbt = stack.getOrCreateTag();
        nbt.putInt("total",0);
        for(IPrefixItem.Prefix prefix : IPrefixItem.Prefix.values()){
            if(prefix == IPrefixItem.Prefix.NULL) continue;
            nbt.putInt(prefix.toString(),0);
        }
        nbt.putBoolean("dirty",true);
        nbt.putInt("weight",0);
    }

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return false;
    }

    @Override
    public void inventoryTick(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        if(p_77663_1_.getTag() == null || !p_77663_1_.getTag().contains("dirty")){
            init(p_77663_1_);
            new UpdateClass(p_77663_1_,1).start();
        }
    }

    /*public void update(ItemStack stack,int total){
        double diff = ConfigManager.DIFFICULTY.get();
        CompoundNBT nbt = stack.getOrCreateTag();
        int w = nbt.getInt("weight");
        init(stack);
        nbt.putInt("weight",Math.min(w+total,MAX));
        int t = nbt.getInt("weight");
        int m = getMaxReachPrefix(t);
        m = Math.min(m,IPrefixItem.getHighestPrefix().getLevel());
        for (int i = 0; i < t; i++) {
            int p = Math.min(m,getMaxReachPrefix(i));
            int c = MathUtils.getRangeRandom(Math.max(1,p-2),p+1);
            IPrefixItem.Prefix prefix = IPrefixItem.getPrefix(c);
            nbt.putInt(prefix.toString(),nbt.getInt(prefix.toString())+c);
        }
        nbt.putInt("total",getTotal(stack));
    }*/

    public int getWeight(ItemStack stack){
        return stack.getOrCreateTag().getInt("weight");
    }

    public static class UpdateClass extends Thread{
        private final ItemStack stack;
        private final int total;
        public UpdateClass(ItemStack stack, int total){
            this.stack = stack;
            this.total = total;
        }
        @Override
        public void run(){
            double diff = ConfigManager.DIFFICULTY.get();
            CompoundNBT nbt = stack.getOrCreateTag();
            int w = nbt.getInt("weight");
            init(stack);
            nbt.putInt("weight",Math.min(w+total,MAX));
            int t = nbt.getInt("weight");
            int m = getMaxReachPrefix(t);
            m = Math.min(m,IPrefixItem.getHighestPrefix().getLevel());
            for (int i = 0; i < t; i++) {
                int p = Math.min(m,getMaxReachPrefix(i));
                int c = MathUtils.getRangeRandom(Math.max(1,p-2),p+1);
                IPrefixItem.Prefix prefix = IPrefixItem.getPrefix(c);
                nbt.putInt(prefix.toString(),nbt.getInt(prefix.toString())+c);
            }
            nbt.putInt("total",getTotal(stack));
        }
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
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.prefix.scroll_weight",getWeight(p_77624_1_),MAX));
    }

    @Override
    public ITextComponent getName(ItemStack p_200295_1_) {
        return super.getName(p_200295_1_).copy().setStyle(Style.EMPTY.withColor(Color.fromRgb(IPrefixItem.getPrefix(getMaxReachPrefix(getWeight(p_200295_1_))).getColor())));
    }

    public static int getMaxReachPrefix(int t){
        int m = 1;
        if(t >= 10) m++;
        if(t >= 20) m++;
        if(t >= 30) m++;
        if(t >= 50) m++;
        if(t >= 100) m++;
        if(t >= 3000) m++;
        if(t >= 10000) m++;
        if(t >= 20000) m++;
        if(t >= 35000) m++;
        return m;
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
