package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.common.compact.Projecte.itf.ICollectorLifeSpan;
import dev.latvian.mods.projectex.block.CollectorBlock;
import dev.latvian.mods.projectex.block.entity.CollectorBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * EMC WORLD MOD
 * @Author Biggest_Xuan
 * 2023/06/12
 */

@Mixin(CollectorBlock.class)
public abstract class CollectorBlockMixin extends Block {
    public CollectorBlockMixin(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    @Nonnull
    @Override
    @Deprecated
    public List<ItemStack> getDrops(@Nonnull BlockState state, @Nonnull LootContext.Builder builder) {
        return new ArrayList<>();
    }

    @Override
    public void onRemove(BlockState state, World world, BlockPos pos, BlockState p_196243_4_, boolean p_196243_5_) {
        TileEntity tile = world.getBlockEntity(pos);
        if(!world.isClientSide && tile instanceof CollectorBlockEntity){
            CollectorBlockEntity collectorBlock = (CollectorBlockEntity) tile;
            ICollectorLifeSpan lifeSpan = (ICollectorLifeSpan) collectorBlock;
            ItemStack stack = new ItemStack(state.getBlock());
            CompoundNBT nbt = stack.getOrCreateTag();
            nbt.putInt("lifespan",lifeSpan.getLifeSpan());
            nbt.putInt("maxLife",lifeSpan.getMaxLifeSpan());
            world.addFreshEntity(new ItemEntity(world,pos.getX(),pos.getY()+1,pos.getZ(),stack));
        }
        super.onRemove(state, world, pos, p_196243_4_, p_196243_5_);

    }

    @Override
    public void setPlacedBy(World p_180633_1_, BlockPos p_180633_2_, BlockState p_180633_3_, @Nullable LivingEntity p_180633_4_, ItemStack p_180633_5_) {
        super.setPlacedBy(p_180633_1_, p_180633_2_, p_180633_3_, p_180633_4_, p_180633_5_);
        int lifespan = p_180633_5_.getOrCreateTag().getInt("lifespan");
        TileEntity tile = p_180633_1_.getBlockEntity(p_180633_2_);
        if(!p_180633_1_.isClientSide && tile instanceof CollectorBlockEntity){
            CollectorBlockEntity collector = (CollectorBlockEntity) tile;
            ICollectorLifeSpan ls = (ICollectorLifeSpan) collector;
            ls.setLifeSpan(lifespan);
        }
    }
}
