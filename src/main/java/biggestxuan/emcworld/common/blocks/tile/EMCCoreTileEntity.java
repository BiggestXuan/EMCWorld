package biggestxuan.emcworld.common.blocks.tile;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.block.BaseUpgradeTileEntity;
import biggestxuan.emcworld.api.trait.IHasTraitItem;
import biggestxuan.emcworld.common.blocks.container.EMCCoreContainer;
import biggestxuan.emcworld.common.compact.Projecte.EMCGemsMapping;
import biggestxuan.emcworld.common.items.EMCGemItem;
import biggestxuan.emcworld.common.items.EMCWorldTraitCoreItem;
import biggestxuan.emcworld.common.registry.EWTileEntityTypes;
import biggestxuan.emcworld.api.trait.AbstractTrait;
import biggestxuan.emcworld.api.trait.ITrait;
import biggestxuan.emcworld.api.trait.TraitType;
import biggestxuan.emcworld.common.traits.TraitUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicReference;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/11/19
 */

@EMCWorldSince("1.1.0")
public abstract class EMCCoreTileEntity extends BaseUpgradeTileEntity implements ITickableTileEntity, INamedContainerProvider {
    public Inventory inventory;

    public static class Assembler extends EMCCoreTileEntity{
        @Override
        public void work() {
            super.work();
            ItemStack weapon = inventory.getItem(0);
            if(!level.isClientSide && weapon.getItem() instanceof IHasTraitItem){
                int maxTrait = getWeaponMaxTrait();
                for (int i = 1; i <= maxTrait; i++) {
                    if(!inventory.getItem(i).isEmpty()){
                        ItemStack inv = inventory.getItem(i);
                        if(inv.getItem() instanceof EMCWorldTraitCoreItem){
                            ITrait trait = TraitUtils.getTrait(inv,1);
                            if(!trait.isFake()){
                                trait.setTraitType(weapon.getItem() instanceof ArmorItem ? TraitType.ARMOR : TraitType.TOOL);
                                TraitUtils.setTrait(trait,weapon,i);
                                inv.setCount(0);
                                setChanged();
                            }
                        }
                    }
                }
            }
        }

        public Assembler() {
            super(EWTileEntityTypes.emcCoreAssemblerTileEntity.get());
            inventory = new Inventory(6);
        }

        @Nullable
        @Override
        public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
            return new EMCCoreContainer.Assembler(p_createMenu_1_,p_createMenu_2_,this);
        }

        @Override
        protected String getName() {
            return "assembler";
        }


    }

    public static class Puller extends EMCCoreTileEntity{
        @Override
        public void work() {
            super.work();
            ItemStack weapon = inventory.getItem(0);
            if(!level.isClientSide && weapon.getItem() instanceof IHasTraitItem){
                int maxTrait = getWeaponMaxTrait();
                if(canPullerWeapon()){
                    for (int i = 1; i <= maxTrait; i++) {
                        ITrait trait = TraitUtils.getTrait(weapon,i);
                        if(!trait.isFake()){
                            inventory.setItem(i+1,TraitUtils.getTraitCoreItem(trait));
                            TraitUtils.removeTrait(weapon,i);
                            setChanged();
                        }
                    }
                }
            }
        }

        public boolean canPullerWeapon(){
            return true; //todo
        }

        public Puller() {
            super(EWTileEntityTypes.emcCorePullerTileEntity.get());
            inventory = new Inventory(7);
        }

        @Override
        protected String getName() {
            return "puller";
        }

        @Nullable
        @Override
        public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
            return new EMCCoreContainer.Puller(p_createMenu_1_,p_createMenu_2_,this);
        }
    }

    public static class Generator extends EMCCoreTileEntity{
        @Override
        public void work() {
            super.work();
            ItemStack core = inventory.getItem(0);
            if(!level.isClientSide && core.getItem() instanceof EMCWorldTraitCoreItem){
                if(getGenTrait() != AbstractTrait.FAKE_TRAIT){
                    addValue();
                }
            }
        }

        public void addValue(){
            ItemStack stack = inventory.getItem(1);
            if(stack.getItem() instanceof EMCGemItem){
                for(EMCGemsMapping mapping : EMCGemsMapping.values()){
                    if(stack.getItem().equals(mapping.getItem())){
                        double value = mapping.getBaseEMC() / 100D * inventory.getItem(2).getCount() * Math.sqrt(stack.getCount());
                        value *= 1 + upgradeLevel * 0.01;
                        value *= 1 + star * 0.025;
                        value *= 1 + (prefix - 4) * 0.005;
                        if(setCoreTrait(getGenTrait(),(int) value)){
                            stack.setCount(0);
                            inventory.getItem(2).setCount(0);
                        }
                    }
                }
            }
        }

        public boolean setCoreTrait(ITrait trait,int value){
            ItemStack core = inventory.getItem(0);
            ITrait coreTrait = TraitUtils.getTrait(core,1);
            if(coreTrait.getTraitLevel() >= 10){
                return false;
            }
            int level = coreTrait.getTraitLevel();
            if(coreTrait.isFake()){
                coreTrait = trait;
                level = 1;
            }
            if(coreTrait.getRL().equals(trait.getRL())){
                CompoundNBT nbt = core.getOrCreateTag();
                int total = nbt.getInt("core_progress") + value;
                while (true){
                    if(total < getEMCCoreRequirePoint(level)){
                        break;
                    }else {
                        total -= getEMCCoreRequirePoint(level);
                        level++;
                    }
                }
                nbt.putInt("core_progress",total);
                coreTrait.setLevel(Math.min(10,level));
                TraitUtils.setTrait(coreTrait,core,1);
                setChanged();
                return true;
            }
            return false;
        }

        public ITrait getGenTrait(){
            ItemStack stack = inventory.getItem(2);
            AtomicReference<ITrait> trait = new AtomicReference<>(AbstractTrait.FAKE_TRAIT);
            TraitUtils.getAllTraits().forEach(t -> {
                if(t.baseItem().test(stack)){
                    trait.set(t);
                }
            });
            return trait.get();
        }

        public Generator() {
            super(EWTileEntityTypes.emcCoreGeneratorTileEntity.get());
            inventory = new Inventory(3);
        }

        public static int getEMCCoreRequirePoint(int level){
            switch (level){
                case 1:
                    return 25;
                case 2:
                    return 120;
                case 3:
                    return 1500;
                case 4:
                    return 15000;
                case 5:
                    return 80000;
                case 6:
                    return 300000;
                case 7:
                    return 5000000;
                case 8:
                    return 10000000;
                case 9:
                    return 2000000000;
            }
            return 0;
        }

        @Nullable
        @Override
        public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
            return new EMCCoreContainer.Generator(p_createMenu_1_,p_createMenu_2_,this);
        }

        @Override
        protected String getName() {
            return "generator";
        }
    }

    public static class Puncher extends EMCCoreTileEntity{
        @Override
        public void work() {
            super.work();
            ItemStack weapon = inventory.getItem(0);
            if(!level.isClientSide && weapon.getItem() instanceof IHasTraitItem){
                if(canPunch()){
                    IHasTraitItem traitItem = (IHasTraitItem) weapon.getItem();
                    traitItem.setMaxTraits(weapon,getWeaponMaxTrait()+1);
                    setChanged();
                }
            }
        }

        public boolean canPunch(){
            ItemStack weapon = inventory.getItem(0);
            if(weapon.getItem() instanceof IHasTraitItem && !(weapon.getItem() instanceof EMCWorldTraitCoreItem)){
                IHasTraitItem item = (IHasTraitItem) weapon.getItem();
                return !item.isMaxTraits(weapon);
            }
            return false;
        }

        public Puncher() {
            super(EWTileEntityTypes.emcCorePuncherTileEntity.get());
            inventory = new Inventory(3);
        }

        @Override
        protected String getName() {
            return "puncher";
        }

        @Nullable
        @Override
        public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
            return new EMCCoreContainer.Puncher(p_createMenu_1_,p_createMenu_2_,this);
        }
    }

    public EMCCoreTileEntity(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    public NonNullList<ItemStack> getInventory(){
        NonNullList<ItemStack> list = NonNullList.withSize(inventory.getContainerSize(),ItemStack.EMPTY);
        for (int i = 0; i < list.size(); i++) {
            list.set(i,inventory.getItem(i));
        }
        return list;
    }

    @Override
    public void setChanged(){
        super.setChanged();
        inventory.setChanged();
    }

    public void setInventory(NonNullList<ItemStack> inventory){
        for (int i = 0; i < this.inventory.getContainerSize(); i++) {
            this.inventory.setItem(i,inventory.get(i));
        }
    }

    @Override
    public void load(@Nonnull BlockState p_230337_1_, @Nonnull CompoundNBT nbt) {
        super.load(p_230337_1_, nbt);
        NonNullList<ItemStack> list = NonNullList.withSize(inventory.getContainerSize(),ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt,list);
        setInventory(list);
    }

    @Override
    @Nonnull
    public CompoundNBT save(@Nonnull CompoundNBT nbt) {
        super.save(nbt);
        ItemStackHelper.saveAllItems(nbt,getInventory());
        return nbt;
    }

    protected abstract String getName();

    public void work(){

    };

    protected int getWeaponMaxTrait(){
        ItemStack weapon = inventory.getItem(0);
        if(weapon.getItem() instanceof IHasTraitItem){
            IHasTraitItem item = (IHasTraitItem) weapon.getItem();
            return item.getMaxTraits(weapon);
        }
        return 0;
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return EMCWorld.tc("block.emcworld.emc_core_"+getName());
    }
}
