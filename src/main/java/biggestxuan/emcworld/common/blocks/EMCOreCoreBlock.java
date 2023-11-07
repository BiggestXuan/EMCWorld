package biggestxuan.emcworld.common.blocks;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.block.BaseUpgradeBlock;
import biggestxuan.emcworld.common.blocks.EWBlock;
import biggestxuan.emcworld.common.blocks.tile.EMCOreCoreTileEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/09
 */

@EMCWorldSince("1.0.5")
public class EMCOreCoreBlock extends BaseUpgradeBlock {
    public EMCOreCoreBlock() {
        super(AbstractBlock.Properties.of(Material.GLASS).noOcclusion().sound(SoundType.STONE).isViewBlocking(EMCOreCoreBlock::never).strength(5F).harvestTool(ToolType.PICKAXE));
    }

    private static boolean never(BlockState p_235436_0_, IBlockReader p_235436_1_, BlockPos p_235436_2_) {
        return false;
    }

    public VoxelShape getVisualShape(BlockState p_230322_1_, IBlockReader p_230322_2_, BlockPos p_230322_3_, ISelectionContext p_230322_4_) {
        return VoxelShapes.empty();
    }

    public boolean propagatesSkylightDown(BlockState p_200123_1_, IBlockReader p_200123_2_, BlockPos p_200123_3_) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new EMCOreCoreTileEntity();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public List<ItemStack> getDrops(BlockState p_220076_1_, LootContext.Builder p_220076_2_) {
        return new ArrayList<>();
    }

    @Override
    public void onRemove(BlockState p_196243_1_, World world, BlockPos pos, BlockState p_196243_4_, boolean p_196243_5_) {
        TileEntity t = world.getBlockEntity(pos);
        if(!world.isClientSide && t instanceof EMCOreCoreTileEntity){
            EMCOreCoreTileEntity tile = (EMCOreCoreTileEntity) t;
            tile.stop();
            ItemStack stack = new ItemStack(p_196243_1_.getBlock());
            var nbt = getRemoveNBT(tile);
            nbt.putLong("emc",tile.emc);
            nbt.putLong("maxEMC",tile.maxEMC);
            ItemStackHelper.saveAllItems(nbt,tile.items);
            stack.setTag(nbt);
            world.addFreshEntity(new ItemEntity(world,pos.getX(),pos.getY()+0.5D,pos.getZ(),stack));
        }
        super.onRemove(p_196243_1_,world,pos, p_196243_4_, p_196243_5_);
    }

    @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState p_180633_3_, @Nullable LivingEntity p_180633_4_, ItemStack stack) {
        super.setPlacedBy(world,pos, p_180633_3_, p_180633_4_, stack);
        if(!world.isClientSide){
            var nbt = stack.getOrCreateTag();
            TileEntity t = world.getBlockEntity(pos);
            if(t instanceof EMCOreCoreTileEntity){
                EMCOreCoreTileEntity tile = (EMCOreCoreTileEntity) t;
                setPlaceNBT(tile,nbt);
                tile.emc = nbt.getLong("emc");
                tile.maxEMC= nbt.getLong("maxEMC");
                ItemStackHelper.loadAllItems(nbt,tile.items);
                tile.stop();
            }
        }
    }

    @Nonnull
    @Override
    public ActionResultType use(@Nonnull BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand handIn, @Nonnull BlockRayTraceResult hit){
        if(!worldIn.isClientSide){
            EMCOreCoreTileEntity entity = (EMCOreCoreTileEntity) worldIn.getBlockEntity(pos);
            NetworkHooks.openGui((ServerPlayerEntity) player,entity,(PacketBuffer p)-> p.writeBlockPos(pos));
        }
        return ActionResultType.SUCCESS;
    }
}
