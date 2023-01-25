package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/19
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.api.item.IUpgradeableMaterial;
import biggestxuan.emcworld.api.item.IUpgradeableTool;
import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nonnull;
import java.util.Set;

@Mixin(value = ToolItem.class,priority = 1001)
public abstract class ToolItemMixin extends TieredItem implements IUpgradeableTool, IUpgradeableMaterial {
    private final IItemTier tier = this.getTier();

    @Shadow
    @Mutable
    @Final
    private final Set<Block> blocks;

    public ToolItemMixin(IItemTier p_i48459_1_, Properties p_i48459_2_, Set<Block> blocks) {
        super(p_i48459_1_, p_i48459_2_);
        this.blocks = blocks;
    }

    @Inject(method = "getDestroySpeed",at = @At("HEAD"),cancellable = true)
    public void speed(ItemStack p_150893_1_, BlockState p_150893_2_, CallbackInfoReturnable<Float> cir){
        float speed = getLevel(p_150893_1_) > 0 ? (float) (tier.getSpeed() * getAdditionSpeed(p_150893_1_)) : tier.getSpeed();
        if (p_150893_1_.getItem().getToolTypes(p_150893_1_).contains(p_150893_2_.getHarvestTool()) || this.blocks.contains(p_150893_2_.getBlock())){
            cir.setReturnValue(speed);
            cir.cancel();
            return;
        }
        cir.setReturnValue(1.0F);
        cir.cancel();
    }

    @Nonnull
    @Override
    public ITextComponent getName(@Nonnull ItemStack p_200295_1_) {
        int level = getLevel(p_200295_1_);
        String name = this.toString();
        ResourceLocation rl = getRegistryName();
        if(rl == null || getMaxLevel() == 0){
            return super.getName(p_200295_1_);
        }
        return EMCWorld.tc("item."+rl.getNamespace()+"."+name).append(" (+"+level+")");
    }

    @Override
    public double getAdditionSpeed(ItemStack stack){
        return getLevel(stack) > 0 ? 1 + (float) (tier.getSpeed() * 0.05 * getLevel(stack)) : 0;
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
            weight = (int) (1.65f * weight);
        }
        return (int) (weight * 1.25);
    }

    @Override
    public int getWeight(ItemStack stack) {
        if(stack.getItem() instanceof IUpgradeableItem) {
            IUpgradeableItem item = (IUpgradeableItem) stack.getItem();
            int l = item.getLevel(stack);
            int weight = 10;
            for (int i = 0; i < l; i++) {
                weight = (int) (1.33f * weight);
            }
            return (int) (weight * 1.15);
        }
        return 0;
    }
}
