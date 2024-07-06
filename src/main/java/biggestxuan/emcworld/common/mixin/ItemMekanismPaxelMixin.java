package biggestxuan.emcworld.common.mixin;

import mekanism.tools.common.item.ItemMekanismPaxel;
import mekanism.tools.common.material.IPaxelMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/08/18
 */

@Mixin(ItemMekanismPaxel.class)
public abstract class ItemMekanismPaxelMixin extends ToolItem {
    @Shadow(remap = false)
    @Final
    private IPaxelMaterial material;

    public ItemMekanismPaxelMixin(float p_i48512_1_, float p_i48512_2_, IItemTier p_i48512_3_, Set<Block> p_i48512_4_, Properties p_i48512_5_) {
        super(p_i48512_1_, p_i48512_2_, p_i48512_3_, p_i48512_4_, p_i48512_5_);
    }

    /**
     * @author Biggest_Xuan
     * @reason Add speed.
     */
    @Override
    @Overwrite
    public float getDestroySpeed(@Nonnull ItemStack stack, BlockState state) {
        return this.material.getPaxelEfficiency() + super.getDestroySpeed(stack,state);
    }
}
