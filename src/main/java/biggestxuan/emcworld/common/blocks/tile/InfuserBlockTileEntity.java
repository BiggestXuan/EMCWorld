package biggestxuan.emcworld.common.blocks.tile;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/13
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.block.BaseContainerTileEntity;
import biggestxuan.emcworld.api.item.IEMCInfuserItem;
import biggestxuan.emcworld.common.blocks.container.InfuserContainer;
import biggestxuan.emcworld.common.blocks.InfuserMultiBlock;
import biggestxuan.emcworld.common.compact.Projecte.EMCGemsMapping;
import biggestxuan.emcworld.common.items.Curios.NuclearBall;
import biggestxuan.emcworld.common.items.EMCGemItem;
import biggestxuan.emcworld.common.recipes.InfuserRecipe;
import biggestxuan.emcworld.common.registry.EWTileEntityTypes;
import biggestxuan.emcworld.common.utils.MathUtils;
import mekanism.api.Coord4D;
import mekanism.api.MekanismAPI;
import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.capabilities.tile.IEmcStorage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class InfuserBlockTileEntity extends BaseContainerTileEntity implements ITickableTileEntity, INamedContainerProvider, IEmcStorage {
    private int progress;
    private int craftLevel;
    private int maxProgress;
    private int emc;
    private int maxEMC;
    private int radiation;
    private int maxRadiation;

    public IIntArray data;
    private LazyOptional<IItemHandler> lazyHandler = LazyOptional.of(() -> new ItemStackHandler(7){
        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            if(slot == 2){
                return stack.getItem() instanceof EMCGemItem;
            }
            return super.isItemValid(slot, stack);
        }
    });
    private LazyOptional<IEmcStorage> emcStorageCapability;

    private final ItemStackHandler handler = new ItemStackHandler(7){
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            setChanged();
        }
    };

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyHandler.invalidate();
        if(emcStorageCapability != null && emcStorageCapability.isPresent()){
            emcStorageCapability.invalidate();
            emcStorageCapability = null;
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyHandler.cast();
        }
        if(cap == ProjectEAPI.EMC_STORAGE_CAPABILITY){
            if(emcStorageCapability == null || !emcStorageCapability.isPresent()){
                emcStorageCapability = LazyOptional.of(() -> this);
            }
            return emcStorageCapability.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyHandler = LazyOptional.of(() -> handler);
    }

    public int getProgress() {
        return progress;
    }

    public int getRadiation() {
        return radiation;
    }

    public int getEmc() {
        return emc;
    }

    public int getMaxEMC() {
        return maxEMC;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public int getMaxRadiation() {
        return maxRadiation;
    }

    public InfuserBlockTileEntity() {
        super(EWTileEntityTypes.InfuserBlockTileEntity.get());
        this.data = new IIntArray() {
                @Override
                public int get(int index){
                    switch (index) {
                        case 0:
                            return InfuserBlockTileEntity.this.progress;
                        case 1:
                            return InfuserBlockTileEntity.this.maxProgress;
                        case 2:
                            return InfuserBlockTileEntity.this.emc;
                        case 3:
                            return InfuserBlockTileEntity.this.radiation;
                        case 4:
                            return InfuserBlockTileEntity.this.craftLevel;
                        case 5:
                            return InfuserBlockTileEntity.this.maxEMC;
                        case 6:
                            return InfuserBlockTileEntity.this.maxRadiation;
                        default:
                            return 0;
                    }
                }
                @Override
                public void set(int index, int value){
                    switch (index) {
                        case 0:
                            InfuserBlockTileEntity.this.progress = value;
                            break;
                        case 1:
                            InfuserBlockTileEntity.this.maxProgress = value;
                            break;
                        case 2:
                            InfuserBlockTileEntity.this.emc = value;
                            break;
                        case 3:
                            InfuserBlockTileEntity.this.radiation = value;
                            break;
                        case 4:
                            InfuserBlockTileEntity.this.craftLevel = value;
                            break;
                        case 5:
                            InfuserBlockTileEntity.this.maxEMC = value;
                            break;
                        case 6:
                            InfuserBlockTileEntity.this.maxRadiation = value;
                            break;
                    }
                }
                @Override
                public int getCount () {
                    return 7;
                }
            };
    }
    @Nonnull
    @Override
    public final CompoundNBT getUpdateTag() {
        CompoundNBT nbt = super.getUpdateTag();
        nbt.putInt("progress",progress);
        nbt.putInt("maxProgress",maxProgress);
        nbt.putInt("level",craftLevel);
        nbt.putInt("EMC",emc);
        nbt.putInt("maxEMC",maxEMC);
        nbt.putInt("radiation",radiation);
        nbt.putInt("maxRadiation",maxRadiation);
        return nbt;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        progress = tag.getInt("progress");
        maxProgress = tag.getInt("maxProgress");
        craftLevel = tag.getInt("level");
        emc = tag.getInt("EMC");
        maxEMC = tag.getInt("maxEMC");
        radiation = tag.getInt("radiation");
        maxRadiation = tag.getInt("maxRadiation");
    }

    @Override
    public void load(@Nonnull BlockState p_230337_1_, @Nonnull CompoundNBT p_230337_2_) {
        super.load(p_230337_1_,p_230337_2_);
        this.progress = p_230337_2_.getInt("progress");
        this.emc = p_230337_2_.getInt("emc");
        this.radiation = p_230337_2_.getInt("radiation");
        this.craftLevel = p_230337_2_.getInt("craftLevel");
        handler.deserializeNBT(p_230337_2_.getCompound("inventory"));
    }

    @Nonnull
    @Override
    public CompoundNBT save(@Nonnull CompoundNBT p_189515_1_){
        p_189515_1_.putInt("progress",this.progress);
        p_189515_1_.putInt("emc",this.emc);
        p_189515_1_.putInt("radiation",this.radiation);
        p_189515_1_.putInt("craftLevel",this.craftLevel);
        p_189515_1_.put("inventory",handler.serializeNBT());
        super.save(p_189515_1_);
        return p_189515_1_;
    }

    @Override
    @Nullable
    public Inventory getInventory() {
        return null;
    }

    public BlockPos getPos() {
        return this.getBlockPos();
    }

    @Override
    public void tick() {
        if(this.level == null || this.level.isClientSide) return;
        firstSet();
        writeData();
        ItemStack mainItem = handler.getStackInSlot(2);
        int nowEMC = this.data.get(2);
        level.sendBlockUpdated(getBlockPos(),getBlockState(),getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
        for (int i = 0; i < this.handler.getSlots(); i++) {
            ItemStack stack = this.handler.getStackInSlot(i);
            if(stack.equals(ItemStack.EMPTY)) continue;
            if(stack.getItem() instanceof NuclearBall){
                int level = ((NuclearBall) stack.getItem()).getLevel();
                if(stack.getDamageValue() == 0 || this.emc < getRadiationCost(level)) continue;
                stack.setDamageValue(stack.getDamageValue() - 1);
                this.emc -= this.getRadiationCost(level);
            }
            if(stack.getItem() instanceof IEMCInfuserItem){
                IEMCInfuserItem item = (IEMCInfuserItem) stack.getItem();
                long baseEMC = (long) (MathUtils.min((long)(maxEMC * 0.01),emc,item.getUse(stack)));
                if(emc > baseEMC && item.getMaxInfuser(stack) != item.getInfuser(stack)){
                    emc -= baseEMC;
                    item.addInfuser(stack, baseEMC);
                }
            }
        }
        if(mainItem.getItem() instanceof EMCGemItem){
            for(EMCGemsMapping emcGem : EMCGemsMapping.values()){
                if(mainItem.getItem().equals(emcGem.getItem()) && emcGem.getBaseEMC() < this.maxEMC - this.emc && this.emc < this.maxEMC){
                    this.emc = (int) Math.min(emcGem.getBaseEMC()+nowEMC,this.maxEMC);
                    this.handler.getStackInSlot(2).setCount(mainItem.getCount()-1);
                    break;
                }
            }
        }
        if(isRecipe(this)){
            this.progress += getRadiationRate();
            if(this.progress >= this.maxProgress){
                craftItem(this);
            }
        }
        else{
            resetRecipe(this);
        }
        this.data.set(0,this.progress);
        this.data.set(2,this.emc);
    }

    private int getRadiationCost(int level){
        double addon = MathUtils.difficultyLoss();
        switch (level){
            case 1:
                return (int) (65 * addon);
            case 2:
                return (int) (475 * addon);
            case 3:
                return (int) (2225 * addon);
        }
        return 0;
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

    private void craftItem(InfuserBlockTileEntity entity){
        World world = entity.level;
        assert world != null;
        Inventory inventory = new Inventory(7);
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            inventory.setItem(i,handler.getStackInSlot(i));
        }
        Optional<InfuserRecipe> recipe = world.getRecipeManager().getRecipeFor(InfuserRecipe.Type.INSTANCE,inventory,world);
        int count = 1;
        if(recipe.isPresent()){
            InfuserRecipe r = recipe.get();
            count = r.getResultItem().getCount();
        }
        for (int i = 0; i < handler.getSlots(); i++) {
            if(i == 2 || i == 6) continue;
            ItemStack cache = handler.getStackInSlot(i);
            cache.shrink(1);
            entity.handler.setStackInSlot(i,cache);
        }
        if(handler.getStackInSlot(6).equals(ItemStack.EMPTY)){
            int finalCount = count;
            recipe.ifPresent(infuserRecipe -> entity.handler.setStackInSlot(6, new ItemStack(infuserRecipe.getResultItem().getItem(), finalCount)));
        }
        else {
            ItemStack c = handler.getStackInSlot(6);
            c.grow(count);
        }
        resetRecipe(entity);
    }

    private void resetRecipe(InfuserBlockTileEntity entity){
        entity.progress = 0;
    }

    private boolean isRecipe(InfuserBlockTileEntity entity){
        World world = this.level;
        assert world != null;
        Inventory inventory = new Inventory(7);
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            inventory.setItem(i,handler.getStackInSlot(i));
        }
        /*boolean isNull = false;
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            if(handler.getStackInSlot(i).isEmpty()){
                isNull = true;
            }
        }
        if(isNull) return false;*/
        Optional<InfuserRecipe> match = world.getRecipeManager().getRecipeFor(InfuserRecipe.Type.INSTANCE,inventory,world);
        boolean out = match.isPresent() && canOutPut(match.get().getResultItem()) && this.craftLevel >= match.get().getCraftLevel() && this.emc >= match.get().getCostEMCRate();
        if(out){
            this.maxProgress = match.get().getCostTime();
            this.data.set(1,this.maxProgress);
            this.emc -= match.get().getCostEMCRate();
            return true;
        }else{
            return false;
        }
    }

    private int getRadiationRate(){
        int rad = this.radiation;
        if(rad <= 50000) return Math.max(rad / 25000,1);
        if(rad <= 150000) return 2 + rad / 30000;
        if(rad <= 1000000) return 5 + rad / 50000;
        else return 10 + rad /100000;
    }

    private boolean canOutPut(ItemStack output){
        return handler.getStackInSlot(6).getMaxStackSize() > handler.getStackInSlot(6).getCount() &&
                handler.getStackInSlot(6).getCount() < 64 &&
                (handler.getStackInSlot(6).getItem().equals(output.getItem()) || handler.getStackInSlot(6).isEmpty());
    }

    private void writeData(){
        this.data.set(4,craftLevel);
        this.data.set(5,maxEMC);
        this.data.set(6,maxRadiation);
        this.emc = Math.min(this.emc,this.maxEMC);
        this.data.set(2,this.emc);
        if(this.level == null) return;
        this.data.set(3,Math.min(this.maxRadiation,(int) MekanismAPI.getRadiationManager().getRadiationLevel(new Coord4D(this.getBlockPos(),this.level))));
    }

    private void firstSet(){
        setCraftLevel();
        setMaxEMC();
        setMaxRadiation();
    }

    private void setCraftLevel(){
        this.craftLevel = new InfuserMultiBlock(this).getLevel();
    }

    private void setMaxRadiation(){
        switch (this.craftLevel){
            case 1:
                this.maxRadiation = 50000;
                break;
            case 2:
                this.maxRadiation = 150000;
                break;
            case 3:
                this.maxRadiation = 1000000;
                break;
            case 4:
                this.maxRadiation = 10000000;
                break;
        }
    }
    public static int[] maxAmount = {800000,5000000,15000000,Integer.MAX_VALUE};

    private void setMaxEMC(){
        switch (this.craftLevel){
            case 1:
                this.maxEMC = maxAmount[0];
                break;
            case 2:
                this.maxEMC = maxAmount[1];
                break;
            case 3:
                this.maxEMC = maxAmount[2];
                break;
            case 4:
                this.maxEMC = maxAmount[3];
                break;
        }
    }

    public IIntArray getData(){
        return this.data;
    }

    public int getCraftLevel(){
        return this.craftLevel;
    }

    @Nullable
    @Override
    public Container createMenu(int sycID, @Nonnull PlayerInventory inventory, @Nonnull PlayerEntity player) {
        assert this.level != null;
        return new InfuserContainer(sycID, inventory, this, this.data);
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return EMCWorld.tc("gui.emcworld.infuser_core");
    }

    @Override
    public long getStoredEmc() {
        return emc;
    }

    @Override
    public long getMaximumEmc() {
        return maxEMC;
    }

    @Override
    public long extractEmc(long l, EmcAction emcAction) {
        long toRemove = Math.min(emc,l);
        if(emcAction == EmcAction.EXECUTE){
            emc -= toRemove;
        }
        return toRemove;
    }

    @Override
    public long insertEmc(long l, EmcAction emcAction) {
        long toAdd = Math.min(maxEMC-emc,l);
        if(emcAction == EmcAction.EXECUTE){
            emc += toAdd;
        }
        return toAdd;
    }
}