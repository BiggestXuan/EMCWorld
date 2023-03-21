package biggestxuan.emcworld.common.blocks.InfuserBlock;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/13
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.block.BaseContainerTileEntity;
import biggestxuan.emcworld.api.item.IEMCInfuserItem;
import biggestxuan.emcworld.common.compact.Projecte.EMCGemsMapping;
import biggestxuan.emcworld.common.items.Curios.NuclearBall;
import biggestxuan.emcworld.common.items.EMCGemItem;
import biggestxuan.emcworld.common.recipes.InfuserRecipe;
import biggestxuan.emcworld.common.registry.EWTileEntityTypes;
import biggestxuan.emcworld.common.utils.MathUtils;
import mekanism.api.Coord4D;
import mekanism.api.MekanismAPI;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class InfuserBlockTileEntity extends BaseContainerTileEntity implements ITickableTileEntity, INamedContainerProvider{
    private final Inventory inventory = new Inventory(7);
    protected IRecipeType<? extends InfuserRecipe> recipeType;
    private int progress;
    private int craftLevel;
    private int maxProgress;
    private int emc;
    private int maxEMC;
    private int radiation;
    private int maxRadiation;

    public IIntArray data;

    @Override
    public void onLoad() {
        super.onLoad();
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

    @Override
    public void load(@Nonnull BlockState p_230337_1_, @Nonnull CompoundNBT p_230337_2_) {
        super.load(p_230337_1_,p_230337_2_);
        this.progress = p_230337_2_.getInt("progress");
        this.emc = p_230337_2_.getInt("emc");
        this.radiation = p_230337_2_.getInt("radiation");
        this.craftLevel = p_230337_2_.getInt("craftLevel");
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item")));
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item1")));
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item2")));
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item3")));
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item4")));
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item5")));
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item6")));
    }

    @Nonnull
    @Override
    public CompoundNBT save(@Nonnull CompoundNBT p_189515_1_){
        p_189515_1_.putInt("progress",this.progress);
        p_189515_1_.putInt("emc",this.emc);
        p_189515_1_.putInt("radiation",this.radiation);
        p_189515_1_.putInt("craftLevel",this.craftLevel);
        p_189515_1_.put("item",this.inventory.getItem(0).copy().serializeNBT());
        p_189515_1_.put("item1",this.inventory.getItem(1).copy().serializeNBT());
        p_189515_1_.put("item2",this.inventory.getItem(2).copy().serializeNBT());
        p_189515_1_.put("item3",this.inventory.getItem(3).copy().serializeNBT());
        p_189515_1_.put("item4",this.inventory.getItem(4).copy().serializeNBT());
        p_189515_1_.put("item5",this.inventory.getItem(5).copy().serializeNBT());
        p_189515_1_.put("item6",this.inventory.getItem(6).copy().serializeNBT());
        super.save(p_189515_1_);
        return p_189515_1_;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public BlockPos getPos() {
        return this.getBlockPos();
    }

    @Override
    public void tick() {
        if(this.level == null || this.level.isClientSide) return;
        firstSet();
        writeData();
        ItemStack mainItem = this.inventory.getItem(2);
        int nowEMC = this.data.get(2);
        for (int i = 0; i < this.inventory.getMaxStackSize(); i++) {
            ItemStack stack = this.inventory.getItem(i);
            if(stack.equals(ItemStack.EMPTY)) continue;
            if(stack.getItem() instanceof NuclearBall){
                ItemStack stack1 = new ItemStack(stack.getItem());
                int rate = getRadiationRate() * 100;
                int level = ((NuclearBall) stack.getItem()).getLevel();
                if(stack1.getDamageValue() == 0 || this.emc < getRadiationCost(level)) continue;
                stack.setDamageValue(stack.getDamageValue() - rate);
                this.emc -= this.getRadiationCost(level) * rate;
            }
            if(stack.getItem() instanceof IEMCInfuserItem){
                IEMCInfuserItem item = (IEMCInfuserItem) stack.getItem();
                long baseEMC = (long) (maxEMC * 0.03);
                if(emc >= baseEMC && item.getMaxInfuser(stack) != item.getInfuser(stack)){
                    emc -= baseEMC;
                    item.addInfuser(stack, baseEMC);
                }
            }
        }
        if(mainItem.getItem() instanceof EMCGemItem){
            for(EMCGemsMapping emcGem : EMCGemsMapping.values()){
                if(mainItem.getItem().equals(emcGem.getItem()) && emcGem.getBaseEMC() < this.maxEMC - this.emc && this.emc < this.maxEMC){
                    this.emc = (int) Math.min(emcGem.getBaseEMC()+nowEMC,this.maxEMC);
                    this.inventory.getItem(2).setCount(mainItem.getCount()-1);
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

    private void craftItem(InfuserBlockTileEntity entity){
        World world = entity.level;
        assert world != null;
        Optional<InfuserRecipe> recipe = world.getRecipeManager().getRecipeFor(InfuserRecipe.Type.INSTANCE,inventory,world);
        Inventory inventory = entity.getInventory();
        int count = 1;
        if(recipe.isPresent()){
            InfuserRecipe r = recipe.get();
            count = r.getResultItem().getCount();
        }
        for (int i = 0; i < entity.getInventory().getContainerSize(); i++) {
            if(i == 2 || i == 6) continue;
            ItemStack cache = inventory.getItem(i);
            cache.shrink(1);
            entity.inventory.setItem(i,cache);
        }
        if(inventory.getItem(6).equals(ItemStack.EMPTY)){
            int finalCount = count;
            recipe.ifPresent(infuserRecipe -> entity.inventory.setItem(6, new ItemStack(infuserRecipe.getResultItem().getItem(), finalCount)));
        }
        else {
            ItemStack c = inventory.getItem(6);
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
        Inventory inventory = entity.getInventory();
        Optional<InfuserRecipe> match = world.getRecipeManager().getRecipeFor(InfuserRecipe.Type.INSTANCE,inventory,world);
        boolean out = match.isPresent() && canOutPut(inventory,match.get().getResultItem()) && this.craftLevel >= match.get().getCraftLevel() && this.emc >= match.get().getCostEMCRate();
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

    private boolean canOutPut(Inventory inventory,ItemStack output){
        return inventory.getItem(6).getMaxStackSize() > inventory.getItem(6).getCount() &&
                inventory.getItem(6).getCount() < 64 &&
                (inventory.getItem(6).getItem().equals(output.getItem()) || inventory.getItem(6).isEmpty());
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
    public Container createMenu(int sycID, PlayerInventory inventory, PlayerEntity player) {
        assert this.level != null;
        return new InfuserContainer(sycID, inventory, this, this.data);
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return EMCWorld.tc("gui.emcworld.infuser_core");
    }

}