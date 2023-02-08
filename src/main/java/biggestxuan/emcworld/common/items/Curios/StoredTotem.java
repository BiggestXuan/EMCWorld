package biggestxuan.emcworld.common.items.Curios;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/25
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.base.BaseCuriosItem;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;

public class StoredTotem extends BaseCuriosItem {
    private final int MAX_EMC;

    public StoredTotem(int emc){
        super(emc);
        this.MAX_EMC = emc;
    }

    public int getMAX_EMC(){
        return this.MAX_EMC;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(@Nonnull ItemStack stack, World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn){
        super.appendHoverText(stack,worldIn,tooltip,flagIn);
        tooltip.add(EMCWorld.tc("tooltip.curios.emcstored.desc"));
        TranslationTextComponent t = EMCWorld.tc("tooltip.curios.emcstored", MathUtils.format(String.valueOf(getMaxDamage(stack)-getDamage(stack))));
        tooltip.add(EMCWorld.tc("tooltip.curios.all"));
        tooltip.add(t);
    }

    @Override
    public boolean isValidRepairItem(ItemStack p_82789_1_, ItemStack p_82789_2_) {
        return false;
    }

    @Override
    public boolean isEnchantable(@Nonnull ItemStack p_77616_1_){
        return false;
    }

    @Override
    public boolean isRepairable(@Nonnull ItemStack stack){
        return false;
    }
}
