package biggestxuan.emcworld.common.blocks.TopCore;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/27
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.block.BaseContainerTileEntity;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.common.registry.EWTileEntityTypes;
import biggestxuan.emcworld.common.utils.MathUtils;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@EMCWorldSince("1.0.3")
public class TopCoreTileEntity extends BaseContainerTileEntity implements ITickableTileEntity, INamedContainerProvider{
    private int chance = 0;
    private States states;
    private final BlockPos pos = this.getBlockPos();
    private final Inventory inventory = new Inventory(5);

    public TopCoreTileEntity() {
        super(EWTileEntityTypes.topCoreTileEntity.get());
        this.states = States.STOP;
    }

    @Override
    public void load(@Nonnull BlockState p_230337_1_, @Nonnull CompoundNBT p_230337_2_){
        super.load(p_230337_1_,p_230337_2_);
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item")));
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item1")));
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item2")));
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item3")));
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item4")));
    }

    @Nonnull
    @Override
    public CompoundNBT save(@Nonnull CompoundNBT p_189515_1_){
        p_189515_1_.put("item",this.inventory.getItem(0).copy().serializeNBT());
        p_189515_1_.put("item1",this.inventory.getItem(1).copy().serializeNBT());
        p_189515_1_.put("item2",this.inventory.getItem(2).copy().serializeNBT());
        p_189515_1_.put("item3",this.inventory.getItem(3).copy().serializeNBT());
        p_189515_1_.put("item4",this.inventory.getItem(4).copy().serializeNBT());
        super.save(p_189515_1_);
        return p_189515_1_;
    }

    @Override
    public Inventory getInventory(){
        return this.inventory;
    }

    public BlockPos getPos() {
        return this.getBlockPos();
    }

    @Override
    public void tick() {
        if(level != null && !level.isClientSide && canStart()){
            level.sendBlockUpdated(getBlockPos(),getBlockState(),getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
            setClientChance();
            if(MathUtils.isRandom(getSuccessChance())){
                success();
            }else{
                fail();
            }
            stop();
        }
        stop();
    }

    private void cost(){
        for (int i = 1; i <= 4; i++) {
            ItemStack stack = inventory.getItem(i);
            if(!stack.isEmpty()){
                inventory.setItem(i,ItemStack.EMPTY);
            }
        }
    }

    private void addMainItemLevel(int level){
        var main = (IUpgradeableItem) inventory.getItem(0).getItem();
        main.addLevel(inventory.getItem(0),level);
    }

    private void success(){
        cost();
        addMainItemLevel(1);
    }

    private void fail(){
        cost();
        addMainItemLevel(-2);
    }

    private void stop(){
        states = States.STOP;
    }

    private void setClientChance(){
        chance = (int) (getSuccessChance() * 10000);
    }

    private double getSuccessChance(){
        double chance = 0;
        if(!isValidItem()){
            return chance;
        }
        for (int i = 1; i <= 4; i++) {
            if(!inventory.getItem(i).isEmpty()){
                chance += 0.25;
            }
        }
        return chance;
    }

    private boolean canStart(){
        return states == States.START && isValidItem();
    }

    private boolean isValidItem(){
        ItemStack main = inventory.getItem(0);
        if(!(main.getItem() instanceof IUpgradeableItem)){
            return false;
        }
        var item = (IUpgradeableItem) main.getItem();
        if(item.getLevel(main) < item.getMaxLevel()){
            return false;
        }
        for (int i = 1; i <= 4; i++) {
            ItemStack b = inventory.getItem(i);
            if(b.isEmpty()){
                continue;
            }
            if(!b.getItem().equals(main.getItem())){
                return false;
            }
            var bi = (IUpgradeableItem) b.getItem();
            if(bi.getLevel(b) != item.getLevel(main)){
                return false;
            }
        }
        return true;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        chance = tag.getInt("chance");
    }

    @Nonnull
    @Override
    public final CompoundNBT getUpdateTag() {
        CompoundNBT nbt = super.getUpdateTag();
        nbt.putInt("chance",chance);
        return nbt;
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
    public ITextComponent getDisplayName() {
        return EMCWorld.tc("block.emcworld.top_core");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, @Nonnull PlayerInventory p_createMenu_2_, @Nonnull PlayerEntity p_createMenu_3_) {
        return new TopCoreContainer(p_createMenu_1_, p_createMenu_2_, this);
    }

    public void setStates(States starting) {
        this.states = starting;
    }

    public enum States{
        STOP, START
    }
}
