package biggestxuan.emcworld.common.blocks.tile;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.block.BaseContainerTileEntity;
import biggestxuan.emcworld.api.block.BaseUpgradeTileEntity;
import biggestxuan.emcworld.common.blocks.container.EMCOreCoreContainer;
import biggestxuan.emcworld.common.compact.Projecte.EMCGemsMapping;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.items.EMCGemItem;
import biggestxuan.emcworld.common.items.Equipment.Scroll.FeastScroll;
import biggestxuan.emcworld.common.recipes.EMCOreRecipe;
import biggestxuan.emcworld.common.registry.EWTileEntityTypes;
import biggestxuan.emcworld.common.utils.MathUtils;
import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.capabilities.tile.IEmcStorage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.stream.IntStream;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/09
 */

public class EMCOreCoreTileEntity extends BaseUpgradeTileEntity implements ITickableTileEntity, INamedContainerProvider, ISidedInventory, IEmcStorage {
    public static ITextComponent NAME = EMCWorld.tc("block.emcworld.emc_ore_core");
    private final int[] DIRECTION_DOWN = IntStream.range(1,15).toArray();
    private final int[] DIRECTION_ELSE = new int[]{0};
    public NonNullList<ItemStack> items = NonNullList.withSize(16,ItemStack.EMPTY);

    public EMCOreCoreTileEntity() {
        super(EWTileEntityTypes.emc_oreTileEntity.get());
    }

    public int maxProgress;
    public int progress;
    public long emc;
    public long maxEMC;
    public int selectLevel;

    LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this,Direction.UP,Direction.DOWN);
    LazyOptional<IEmcStorage> emcStorageCapability;

    @Override
    public void load(@Nonnull BlockState p_230337_1_,@Nonnull CompoundNBT nbt) {
        super.load(p_230337_1_, nbt);
        ItemStackHelper.loadAllItems(nbt,items);
        maxProgress = nbt.getInt("maxProgress");
        progress = nbt.getInt("progress");
        emc = nbt.getLong("emc");
        maxEMC = nbt.getLong("maxEMC");
        selectLevel = nbt.getInt("selectLevel");
    }

    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        for (var handler : handlers) handler.invalidate();
        if(emcStorageCapability != null && emcStorageCapability.isPresent()){
            emcStorageCapability.invalidate();
            emcStorageCapability = null;
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(side != null && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            if(side == Direction.DOWN){
                return handlers[1].cast();
            }
            return handlers[0].cast();
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
    @Nonnull
    public CompoundNBT save(@Nonnull CompoundNBT nbt) {
        super.save(nbt);
        ItemStackHelper.saveAllItems(nbt,items);
        nbt.putInt("maxProgress",maxProgress);
        nbt.putInt("progress",progress);
        nbt.putLong("emc",emc);
        nbt.putLong("maxEMC",maxEMC);
        nbt.putInt("selectLevel",selectLevel);
        return nbt;
    }

    @Nonnull
    @Override
    public final CompoundNBT getUpdateTag() {
        var nbt = super.getUpdateTag();
        nbt.putInt("maxProgress",maxProgress);
        nbt.putInt("progress",progress);
        nbt.putLong("emc",emc);
        nbt.putLong("maxEMC",maxEMC);
        nbt.putInt("selectLevel",selectLevel);
        return nbt;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT nbt) {
        super.handleUpdateTag(state,nbt);
        maxProgress = nbt.getInt("maxProgress");
        progress = nbt.getInt("progress");
        emc = nbt.getLong("emc");
        maxEMC = nbt.getLong("maxEMC");
        selectLevel = nbt.getInt("selectLevel");
    }

    public void setOreLevel(int level){
        if(level >= 0 && level <= star){
            selectLevel = level;
            stop();
        }
    }

    @Nonnull
    @Override
    public int[] getSlotsForFace(@Nonnull Direction p_180463_1_) {
        if(p_180463_1_ == Direction.DOWN){
            return DIRECTION_DOWN;
        }
        return DIRECTION_ELSE;
    }

    @Override
    public boolean canPlaceItemThroughFace(int p_180462_1_, ItemStack p_180462_2_, @Nullable Direction p_180462_3_) {
        if(p_180462_1_ != 0){
            return false;
        }
        return p_180462_2_.getItem() instanceof EMCGemItem;
    }

    @Override
    public boolean canTakeItemThroughFace(int p_180461_1_, ItemStack p_180461_2_, Direction p_180461_3_) {
        return p_180461_1_ != 0;
    }

    @Override
    public int getContainerSize() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        for(var i : items){
            if(!i.isEmpty()){
                return false;
            }
        }
        return true;
    }

    @Override
    @Nonnull
    public ItemStack getItem(int p_70301_1_) {
        return items.get(p_70301_1_);
    }

    @Override
    @Nonnull
    public ItemStack removeItem(int p_70298_1_, int p_70298_2_) {
        return ItemStackHelper.removeItem(items,p_70298_1_,p_70298_2_);
    }

    @Override
    @Nonnull
    public ItemStack removeItemNoUpdate(int p_70304_1_) {
        return ItemStackHelper.takeItem(items,p_70304_1_);
    }

    @Override
    public void setItem(int p_70299_1_, @Nonnull ItemStack p_70299_2_) {
        items.set(p_70299_1_,p_70299_2_);
    }

    @Override
    public boolean stillValid(@Nonnull PlayerEntity p_70300_1_) {
        return true;
    }

    @Override
    public void clearContent() {
        items.clear();
    }

    @Override
    public ITextComponent getDisplayName() {
        return NAME;
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new EMCOreCoreContainer(p_createMenu_1_,p_createMenu_2_,this);
    }

    @Override
    public void tick() {
        super.tick();
        if(level == null || level.isClientSide){
            return;
        }
        flushEMCAndTime();
        useEMCGem();
        doing();
    }

    public void stop(){
        progress = 0;
        flushEMCAndTime();
    }

    private void craftItem(){
        int recipeLevel = selectLevel;
        int weight = MathUtils.getRangeRandom(0,EMCOreRecipe.getTotalWeight(recipeLevel));
        EMCOreRecipe recipe = null;
        for(var r : EMCOreRecipe.getRequireLevelRecipe(recipeLevel)){
            weight -= r.getWeight();
            if(weight <= 0){
                recipe = r;
                break;
            }
        }
        if(recipe != null){
            ItemStack stack = recipe.getOutput();
            if(stack.getItem() instanceof FeastScroll){
                emc = 0;
            }
            insertItem(stack);
        }
    }

    private void insertItem(ItemStack stack){
        for (int i = 1; i <= 15; i++) {
            if(items.get(i).isEmpty()){
                items.set(i,stack);
                return;
            }
            if(items.get(i).getItem().equals(stack.getItem())){
                if(items.get(i).getCount() + stack.getCount() <= stack.getMaxStackSize()){
                    items.get(i).grow(stack.getCount());
                    return;
                }else{
                    stack.setCount(stack.getCount() - (items.get(i).getMaxStackSize() - items.get(i).getCount()));
                    items.get(i).setCount(items.get(i).getMaxStackSize());
                }
            }
        }
    }

    private void doing(){
        if(canStart()){
            emc -= getTickCost(selectLevel,upgradeLevel,prefix);
            progress++;
            if(progress >= maxProgress){
                stop();
                craftItem();
            }
        }
    }

    private boolean isFull(){
        for (int i = 1; i <= 15; i++) {
            if(items.get(i).isEmpty()){
                return false;
            }
        }
        return true;
    }

    private boolean canStart(){
        return emc >= getTickCost(star,upgradeLevel,prefix) && !isFull() && !ConfigManager.FREE_MODE.get();
    }

    public void flushEMCAndTime(){
        maxEMC = getMaxEMC(star);
        maxProgress = getProduceTick(upgradeLevel);
    }

    public void useEMCGem(){
        ItemStack stack = items.get(0);
        if(stack.getItem() instanceof EMCGemItem){
            for(EMCGemsMapping emcGem : EMCGemsMapping.values()){
                if(stack.getItem().equals(emcGem.getItem()) && emcGem.getBaseEMC() < this.maxEMC - this.emc && this.emc < this.maxEMC){
                    this.emc = (int) Math.min(emcGem.getBaseEMC()+emc,this.maxEMC);
                    stack.shrink(1);
                    break;
                }
            }
        }
    }

    public static long getMaxEMC(int star){
        switch (star){
            case 0:
                return 500_000L;
            case 1:
                return 1_000_000L;
            case 2:
                return 5_000_000L;
            case 3:
                return 15_000_000L;
            case 4:
                return 100_000_000L;
            case 5:
                return 300_000_000L;
            case 6:
                return 1_500_000_000L;
            case 7:
                return 10_000_000_000L;
            case 8:
                return 60_000_000_000L;
        }
        return 0;
    }

    public static long getTickCost(int star,int upgradeLevel,int prefix){
        return (long) (1D * getProduceCost(star,prefix) / getProduceTick(upgradeLevel));
    }

    public static int getProduceTick(int level){
        if(level <= 5){
            return 1200 - level * 20; //base:1200
        }
        return 1000 - (level - 5) * 100;  //1000
    }

    public static long getProduceCost(int level,int prefix){
        int base = 0;
        switch (level){
            case 0:
                base = 3_000;
                break;
            case 1:
                base = 10_000;
                break;
            case 2:
                base = 30_000;
                break;
            case 3:
                base = 50_000;
                break;
            case 4:
                base = 200_000;
                break;
            case 5:
                base = 500_000;
                break;
            case 6:
                base = 5_000_000;
                break;
            case 7:
                base = 100_000_000;
                break;
            case 8:
                base = 1_000_000_000;
                break;
        }
        if(prefix <= 3){
            base *= 1 + (0.15 * prefix);
        }
        if(prefix >= 5){
            base *= 1 - (0.05 * prefix);
        }
        return base;
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
