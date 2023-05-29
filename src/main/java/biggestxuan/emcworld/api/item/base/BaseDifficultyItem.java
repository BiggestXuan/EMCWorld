package biggestxuan.emcworld.api.item.base;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/17
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.CraftTweaker.CrTConfig;
import biggestxuan.emcworld.common.items.EWItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseDifficultyItem extends EWItem {

    protected float difficulty;
    protected boolean isHardCore;
    protected TranslationTextComponent usage = null;

    public BaseDifficultyItem(float difficulty,boolean isHardCore,TranslationTextComponent usage){
        this.difficulty = difficulty;
        this.isHardCore = isHardCore;
        this.usage = usage;
    }

    public BaseDifficultyItem(float difficulty,boolean isHardCore){
        this.difficulty = difficulty;
        this.isHardCore = isHardCore;
    }

    public BaseDifficultyItem(Properties properties){
        super(properties);
    }

    public float getDifficulty() {
        return difficulty;
    }

    public boolean isHardCore() {
        return isHardCore;
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, @Nonnull List<ITextComponent> p_77624_3_, @Nonnull ITooltipFlag p_77624_4_) {
        if(CrTConfig.getWorldDifficulty() < this.difficulty){
            p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.difficulty_cant_use",difficulty));
            return;
        }
        if(usage == null) return;
        p_77624_3_.add(usage);
    }
}
