package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/19
 */

import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.IUpgradeableTool;
import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.item.ToolItem;
import net.minecraftforge.common.ToolType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(value = ToolItem.class,priority = 1001)
public abstract class ToolItemMixin extends TieredItem implements IUpgradeableTool, IPrefixItem {
    private final IItemTier tier = this.getTier();

    @Shadow
    @Mutable
    @Final
    private final Set<Block> blocks;

    @Mutable
    @Shadow
    @Final
    protected final float speed;

    public ToolItemMixin(IItemTier p_i48459_1_, Properties p_i48459_2_, Set<Block> blocks, float speed) {
        super(p_i48459_1_, p_i48459_2_);
        this.blocks = blocks;
        this.speed = speed;
    }

    private double getPrefixCommonRate(ItemStack stack){
        double b = 1;
        Prefix prefix = getPrefix(stack);
        if(prefix == Prefix.NULL) return b;
        if(prefix.getLevel() <= 3){
            b = 1 - (4 - prefix.getLevel()) * 0.1;
        }else{
            b = (prefix.getLevel()-4) * 0.025 + 1;
        }
        return b;
    }

    @Inject(method = "getDestroySpeed",at = @At("HEAD"),cancellable = true)
    public void speed(ItemStack p_150893_1_, BlockState p_150893_2_, CallbackInfoReturnable<Float> cir){
        Material material = p_150893_2_.getMaterial();
        float speed = (float) (tier.getSpeed() * getAdditionSpeed(p_150893_1_));
        if (p_150893_1_.getItem().getToolTypes(p_150893_1_).contains(p_150893_2_.getHarvestTool()) || this.blocks.contains(p_150893_2_.getBlock()) || (p_150893_1_.getItem().getToolTypes(p_150893_1_).contains(ToolType.PICKAXE)) && (material == Material.METAL || material == Material.HEAVY_METAL || material == Material.STONE)){
            cir.setReturnValue(speed);
            cir.cancel();
        }else{
            cir.setReturnValue(1.0F);
            cir.cancel();
        }
    }

    @Override
    public double getEMCCostRate() {
        return 1d;
    }

    @Override
    public double getAdditionSpeed(ItemStack stack){
        return (getLevel(stack) > 0 ? 1 + (float) (tier.getSpeed() * 0.05 * getLevel(stack)) : 1) * getPrefixCommonRate(stack);
    }

    @Override
    public double costEMCWhenAttack(ItemStack stack) {
        return 1;
    }

    @Override
    public long getTickCost(ItemStack stack) {
        return 0;
    }

    @Override
    public long EMCModifySecond(ItemStack stack) {
        return 0;
    }

    @Override
    public int getMaxLevel() {
        return (int) (tier.getLevel() * ConfigManager.DIFFICULTY.get() * 0.7);
    }

    @Override
    public int getWeightRequired(ItemStack stack) {
        int l = getLevel(stack);
        int weight = 10;
        for (int i = 0; i < l; i++) {
            weight = (int) (1.9f * weight);
        }
        return (int) (weight * 1.25);
    }
}
