package biggestxuan.emcworld.common.items.Curios;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/24
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.base.BaseCuriosItem;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;

public class NuclearBall extends BaseCuriosItem {
    private final int level;
    private int max_radiation;

    public NuclearBall(int durability,int level) {
        super(durability);
        this.level = level;
        switch (level){
            case 1:
                this.max_radiation = 300000;
                break;
            case 2:
                this.max_radiation = 1500000;
                break;
        }
    }

    public int getLevel() {
        return level;
    }

    public int getMax_radiation() {
        return max_radiation;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(@Nonnull ItemStack stack, World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn){
        if(this.level == 3){
            tooltip.add(EMCWorld.tc("tooltip.emcworld.nuclear_ball_all"));
        }
        else{
            tooltip.add(EMCWorld.tc("tooltip.emcworld.nuclear_ball", MathUtils.thousandSign(this.max_radiation)));
        }
        tooltip.add(EMCWorld.tc("tooltip.emcworld.nuclear_ball_time",MathUtils.thousandSign((stack.getMaxDamage()-stack.getDamageValue())/20)));
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
