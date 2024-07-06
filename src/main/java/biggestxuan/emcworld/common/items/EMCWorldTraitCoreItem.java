package biggestxuan.emcworld.common.items;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.trait.IHasTraitItem;
import biggestxuan.emcworld.common.blocks.tile.EMCCoreTileEntity;
import biggestxuan.emcworld.api.trait.ITrait;
import biggestxuan.emcworld.common.traits.TraitUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/11/15
 */

@EMCWorldSince("1.1.0")
public class EMCWorldTraitCoreItem extends EWItem implements IHasTraitItem {
    public EMCWorldTraitCoreItem(){
        super(1);
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        p_77624_3_.addAll(TraitUtils.getDesc(p_77624_1_,true));
        if(p_77624_1_.hasTag()){
            CompoundNBT nbt = p_77624_1_.getTag();
            ITrait trait = TraitUtils.getTrait(p_77624_1_,1);
            p_77624_3_.add(EMCWorld.tc("tooltip.trait.core_progress",nbt.getInt("core_progress"), EMCCoreTileEntity.Generator.getEMCCoreRequirePoint(trait.getTraitLevel())));
        }

    }

    @Override
    public ITextComponent getName(ItemStack p_200295_1_) {
        TranslationTextComponent text = new TranslationTextComponent(getDescriptionId(p_200295_1_));
        ITrait trait = TraitUtils.getTrait(p_200295_1_,1);
        if(!trait.isFake()){
            text.withStyle(Style.EMPTY.withColor(Color.fromRgb(trait.getColor())));
            return text;
        }
        return super.getName(p_200295_1_);
    }

    @Override
    public int getMaxTraits(ItemStack stack){
        return 1;
    }

}
