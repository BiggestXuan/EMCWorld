package biggestxuan.emcworld.common.blocks;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/04
 */

import biggestxuan.emcworld.api.block.BaseTileBlock;
import biggestxuan.emcworld.common.blocks.tile.StarPedestalTileEntity;
import com.blakebr0.cucumber.helper.StackHelper;
import hellfirepvp.astralsorcery.common.item.wand.ItemWand;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class StarPedestalBlock extends BaseTileBlock {
    public static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8D, 16.0D);

    public StarPedestalBlock() {
        super(15F);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new StarPedestalTileEntity();
    }

    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
        TileEntity tile = world.getBlockEntity(pos);
        if (tile instanceof StarPedestalTileEntity) {
            StarPedestalTileEntity pedestal = (StarPedestalTileEntity)tile;
            Inventory inventory = pedestal.getInventory();
            ItemStack input = inventory.getItem(0);
            ItemStack held = player.getItemInHand(hand);
            if(held.getItem() instanceof ItemWand){
                pedestal.start();
                return ActionResultType.SUCCESS;
            }
            else if (input.isEmpty() && !held.isEmpty()) {
                inventory.setItem(0, StackHelper.withSize(held, 1, false));
                player.setItemInHand(hand, StackHelper.shrink(held, 1, false));
                world.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0F);
            } else if (!input.isEmpty()) {
                ItemEntity item = new ItemEntity(world, player.getX(), player.getY(), player.getZ(), input);
                item.setNoPickUpDelay();
                world.addFreshEntity(item);
                inventory.setItem(0, ItemStack.EMPTY);
            }
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}
