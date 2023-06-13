package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.common.compact.Projecte.itf.CollectorLifeSpan;
import biggestxuan.emcworld.common.config.ConfigManager;
import dev.latvian.mods.projectex.block.entity.CollectorBlockEntity;
import moze_intel.projecte.api.capabilities.tile.IEmcStorage;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * EMC WORLD MOD
 * @Author Biggest_Xuan
 * 2023/06/12
 */

@Mixin(CollectorBlockEntity.class)
public abstract class CollectorBlockEntityMixin extends TileEntity implements ITickableTileEntity, IEmcStorage, CollectorLifeSpan {
    private int lifespan;
    public CollectorBlockEntityMixin(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    @Override
    public int getMaxLifeSpan() {
        return ConfigManager.SUNDRY_COLLECTOR_LIFESPAN.get();
    }

    @Inject(method = "load",at = @At("TAIL"))
    public void load_mixin(BlockState state, CompoundNBT tag, CallbackInfo ci) {
        lifespan = tag.getInt("lifespan");
    }

    @Inject(method = "save",at = @At("RETURN"))
    public void save_mixin(CompoundNBT tag, CallbackInfoReturnable<CompoundNBT> cir) {
        tag.putInt("lifespan",lifespan);
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

    @Nonnull
    @Override
    public final CompoundNBT getUpdateTag() {
        CompoundNBT nbt = super.getUpdateTag();
        nbt.putInt("lifespan",lifespan);
        return nbt;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        lifespan = tag.getInt("lifespan");
    }

    @Inject(method = "tick",at = @At("HEAD"), cancellable = true)
    public void tick_mixin(CallbackInfo ci){
        if(level != null && !level.isClientSide){
            level.sendBlockUpdated(getBlockPos(),getBlockState(),getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
            if(lifespan < getMaxLifeSpan()){
                lifespan++;
            }else{
                ci.cancel();
            }
        }
    }

    @Override
    public int getLifeSpan(){
        return this.lifespan;
    }

    @Override
    public void setLifeSpan(int lifeSpan){
        this.lifespan = lifeSpan;
    }
}
