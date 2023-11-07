package biggestxuan.emcworld.api.block;

import biggestxuan.emcworld.api.EMCWorldSince;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/28
 */

@EMCWorldSince("1.0.6")
public abstract class BaseUpgradeTileEntity extends TileEntity implements ITickableTileEntity {
    public int upgradeLevel;
    public int prefix;
    public int star;
    public int maxStar;

    public BaseUpgradeTileEntity(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    @Override
    public void load(@Nonnull BlockState p_230337_1_, @Nonnull CompoundNBT nbt) {
        super.load(p_230337_1_, nbt);
        upgradeLevel = nbt.getInt("level");
        prefix = nbt.getInt("prefix");
        star = nbt.getInt("star");
        maxStar = nbt.getInt("max_star");
    }

    @Override
    @Nonnull
    public CompoundNBT save(@Nonnull CompoundNBT nbt) {
        super.save(nbt);
        nbt.putInt("level", upgradeLevel);
        nbt.putInt("prefix",prefix);
        nbt.putInt("star",star);
        nbt.putInt("max_star",maxStar);
        return nbt;
    }

    @Nonnull
    @Override
    public CompoundNBT getUpdateTag() {
        var nbt = super.getUpdateTag();
        nbt.putInt("level", upgradeLevel);
        nbt.putInt("prefix",prefix);
        nbt.putInt("star",star);
        nbt.putInt("max_star",maxStar);
        return nbt;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT nbt) {
        upgradeLevel = nbt.getInt("level");
        prefix = nbt.getInt("prefix");
        star = nbt.getInt("star");
        maxStar = nbt.getInt("max_star");
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(getBlockPos(), 1, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        handleUpdateTag(level.getBlockState(pkt.getPos()), pkt.getTag());
    }

    @Override
    public void tick() {
        if(level != null && !level.isClientSide){
            level.sendBlockUpdated(getBlockPos(),getBlockState(),getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
        }
    }
}
