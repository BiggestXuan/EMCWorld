package biggestxuan.emcworld.common.compact.Botania;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/21
 */

import biggestxuan.emcworld.common.blocks.EWBlock;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.botania.api.subtile.TileEntitySpecialFlower;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.api.wand.IWandable;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class FlowerBlock extends EWBlock implements ITileEntityProvider, IWandable, IWandHUD {
    private final Supplier<? extends TileEntitySpecialFlower> teProvider;

    public FlowerBlock(float strength, Supplier<? extends TileEntitySpecialFlower> teProvider) {
        super(strength);
        this.teProvider = teProvider;
    }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader p_196283_1_) {
        return teProvider.get();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void renderHUD(MatrixStack ms, Minecraft mc, World world, BlockPos pos) {
        ((TileEntitySpecialFlower) world.getBlockEntity(pos)).renderHUD(ms, mc);
    }

    @Override
    public boolean onUsedByWand(PlayerEntity player, ItemStack stack, World world, BlockPos pos, Direction side) {
        return true;
    }
}
