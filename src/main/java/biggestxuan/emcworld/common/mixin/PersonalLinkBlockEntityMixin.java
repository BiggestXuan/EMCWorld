package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.blocks.container.PersonalLinkContainer;
import biggestxuan.emcworld.common.items.EMCGemItem;
import dev.latvian.mods.projectex.block.entity.LinkBaseBlockEntity;
import dev.latvian.mods.projectex.block.entity.PersonalLinkBlockEntity;
import moze_intel.projecte.utils.EMCHelper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.spongepowered.asm.mixin.Mixin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigInteger;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/11
 */

@EMCWorldSince("1.0.5")
@Mixin(PersonalLinkBlockEntity.class)
public abstract class PersonalLinkBlockEntityMixin extends LinkBaseBlockEntity implements INamedContainerProvider {
    public PersonalLinkBlockEntityMixin(TileEntityType<?> type, int in, int out) {
        super(type, in, out);
    }

    private LazyOptional<IItemHandler> lazyHandler = LazyOptional.of(() -> new ItemStackHandler(27){
        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            return EMCHelper.getEmcValue(stack) > 0;
        }
    });

    private final ItemStackHandler handler = new ItemStackHandler(27){
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            setChanged();
        }
    };

    @Override
    public void load(@Nonnull BlockState p_230337_1_, @Nonnull CompoundNBT p_230337_2_) {
        super.load(p_230337_1_,p_230337_2_);
        handler.deserializeNBT(p_230337_2_.getCompound("inventory"));
    }

    @Nonnull
    @Override
    public CompoundNBT save(@Nonnull CompoundNBT p_189515_1_){
        var nbt = super.save(p_189515_1_);
        nbt.put("inventory",handler.serializeNBT());
        return nbt;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyHandler = LazyOptional.of(() -> handler);
    }

    @Override
    public void tick(){
        super.tick();
        if(level != null && !level.isClientSide){
            for (int i = 0; i < handler.getSlots(); i++) {
                ItemStack s = handler.getStackInSlot(i);
                long emc = EMCHelper.getEmcValue(s);
                if(!s.isEmpty() && emc > 0){
                    s.shrink(1);
                    storedEMC = storedEMC.add(BigInteger.valueOf(emc));
                    break;
                }
            }
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyHandler.invalidate();
    }

    @Override
    public ITextComponent getDisplayName() {
        return EMCWorld.tc("block.projectex.personal_link");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new PersonalLinkContainer(p_createMenu_1_,p_createMenu_2_,this);
    }
}
