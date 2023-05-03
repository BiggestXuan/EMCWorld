package biggestxuan.emcworld.common.blocks.WeaponUpgradeBlock;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/27
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.block.BaseContainerTileEntity;
import biggestxuan.emcworld.api.event.PlayerUpgradeItemEvent;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.api.item.IUpgradeableMaterial;
import biggestxuan.emcworld.api.item.equipment.IEMCGodWeaponLevel;
import biggestxuan.emcworld.common.compact.CraftTweaker.CrTConfig;
import biggestxuan.emcworld.common.items.Equipment.Scroll.BiggestXuanScroll;
import biggestxuan.emcworld.common.items.Equipment.Weapon.LuckyItem.ILuckyItem;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.registry.EWTileEntityTypes;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.Message;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class WeaponUpgradeBlockTileEntity extends BaseContainerTileEntity implements ITickableTileEntity, INamedContainerProvider{
    private int chance;
    private States states;
    private final BlockPos pos = this.getBlockPos();
    private final Inventory inventory = new Inventory(6);
    public IIntArray data;
    public PlayerEntity lastClick = null;
    public int lastChance;

    public WeaponUpgradeBlockTileEntity() {
        super(EWTileEntityTypes.WeaponUpgradeBlockTileEntity.get());
        this.data = new IIntArray() {
            @Override
            public int get(int p_221476_1_) {
                return WeaponUpgradeBlockTileEntity.this.chance;
            }

            @Override
            public void set(int p_221477_1_, int p_221477_2_) {
                WeaponUpgradeBlockTileEntity.this.chance = p_221477_2_;
            }

            @Override
            public int getCount() {
                return 1;
            }
        };
        this.states = States.STOP;
    }

    @Override
    public void load(@Nonnull BlockState p_230337_1_, @Nonnull CompoundNBT p_230337_2_){
        super.load(p_230337_1_,p_230337_2_);
        this.chance = p_230337_2_.getInt("chance");
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item")));
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item1")));
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item2")));
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item3")));
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item4")));
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item5")));
    }

    @Nonnull
    @Override
    public CompoundNBT save(@Nonnull CompoundNBT p_189515_1_){
        p_189515_1_.putInt("chance",this.chance);
        p_189515_1_.put("item",this.inventory.getItem(0).copy().serializeNBT());
        p_189515_1_.put("item1",this.inventory.getItem(1).copy().serializeNBT());
        p_189515_1_.put("item2",this.inventory.getItem(2).copy().serializeNBT());
        p_189515_1_.put("item3",this.inventory.getItem(3).copy().serializeNBT());
        p_189515_1_.put("item4",this.inventory.getItem(4).copy().serializeNBT());
        p_189515_1_.put("item5",this.inventory.getItem(5).copy().serializeNBT());
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
        if(states.equals(States.STARTING) && canStart(this)){
            boolean isSucceed = true;
            boolean isBreak = false;
            lastChance = getChance(this.inventory);
            PlayerUpgradeItemEvent.Pre event = new PlayerUpgradeItemEvent.Pre(lastClick,this.inventory.getItem(0),lastChance/10000d,getBreakChance()/10000d,this);
            MinecraftForge.EVENT_BUS.post(event);
            lastChance = (int) (event.getSuccessChance() * 10000);
            double breakChance = event.getBreakChance();
            if(isSuccess(lastChance)){
                success(this);
            }else{
                isSucceed = false;
                if(MathUtils.isRandom(breakChance)){
                    breakWeapon();
                    isBreak = true;
                }
                fail(this);
            }
            MinecraftForge.EVENT_BUS.post(new PlayerUpgradeItemEvent.After(lastClick,this.inventory.getItem(0),lastChance/10000d,breakChance,isSucceed,isBreak,this));
        }
        states = States.STOP;
        this.data.set(0,getChance(this.inventory));
    }

    private boolean canStart(WeaponUpgradeBlockTileEntity entity){
        ItemStack stack = entity.getInventory().getItem(0);
        Inventory inventory = entity.getInventory();
        if(stack.getItem() instanceof IUpgradeableItem){
            IUpgradeableItem item = (IUpgradeableItem) stack.getItem();
            return item.getLevel(stack) < item.getMaxLevel() && (getChance(inventory) > 0 && getChance(inventory) < 114513);
        }
        return false;
    }

    private double getBreakChance(){
        double chance = 0;
        for (int i = 1; i < 5; i++) {
            if(this.inventory.getItem(i).getItem() instanceof IUpgradeableMaterial){
                IUpgradeableMaterial material = (IUpgradeableMaterial) this.inventory.getItem(i).getItem();
                chance += material.breakWeaponRate();
            }
        }
        return chance;
    }

    private void giveLuckyGem(WeaponUpgradeBlockTileEntity entity){
        World world = entity.getLevel();
        BlockPos pos = entity.getBlockPos();
        for (int i = 0; i < getGemAmount(getWeight(entity.getInventory())); i++) {
            if(world == null) return;
            world.addFreshEntity(new ItemEntity(world,pos.getX(),pos.getY()+1,pos.getZ(),new ItemStack(EWItems.LUCKY_GEM_BLUE.get())));
        }
    }

    private void breakWeapon(){
        IUpgradeableItem item = (IUpgradeableItem) this.inventory.getItem(0).getItem();
        item.setLevel(this.inventory.getItem(0),0);
    }

    private void sendMessage(){
        if(this.level == null || this.level.getServer() == null || lastClick == null) return;
        Message.sendMessageToAllPlayer(this.level.getServer(), (TranslationTextComponent) EMCWorld.tc("message.emcworld.weapon_success",lastClick.getScoreboardName(),String.format("%.2f",lastChance/100f)+"%").append(this.inventory.getItem(0).getDisplayName()));
    }

    private boolean isSendMessage(ItemStack stack){
        if(stack.getItem() instanceof IUpgradeableItem && stack.getItem() instanceof IEMCGodWeaponLevel){
            IUpgradeableItem weapon = (IUpgradeableItem) stack.getItem();
            int level = weapon.getLevel(stack);
            int maxLevel = weapon.getMaxLevel();
            return level >= maxLevel - 2;
        }
        return false;
    }

    private int getGemAmount(int weight){
        return weight / 30;
    }

    private void fail(WeaponUpgradeBlockTileEntity tileEntity){
        giveLuckyGem(tileEntity);
        costMaterial(tileEntity);
        ItemStack weapon = tileEntity.getInventory().getItem(0);
        if(weapon.equals(ItemStack.EMPTY)){
            return;
        }
        IUpgradeableItem weapon1 = (IUpgradeableItem) weapon.getItem();
        if(weapon1.getLevel(weapon) > weapon1.getMaxLevel()/3){
            weapon1.lossLevel(weapon,1);
        }
    }

    private boolean hasBXScroll(){
        Inventory inventory = this.inventory;
        for (int i = 1; i < 6; i++) {
            if(inventory.getItem(i).getItem().equals(EWItems.SCROLL_BX.get())){
                return true;
            }
        }
        return false;
    }

    private void success(WeaponUpgradeBlockTileEntity tileEntity){
        costMaterial(tileEntity);
        ItemStack weapon = tileEntity.getInventory().getItem(0);
        IUpgradeableItem weapon1 = (IUpgradeableItem) weapon.getItem();
        weapon1.addLevel(weapon,1);
        if(hasBXScroll() && MathUtils.isRandom(BiggestXuanScroll.chance)){
            weapon.shrink(1);
        }
        else if(isSendMessage(weapon)){
            sendMessage();
        }
        //tileEntity.level.addParticle(ParticleTypes.HAPPY_VILLAGER,pos.getX(),pos.getY()+1,pos.getZ(),10,10,10);
    }

    private void costMaterial(WeaponUpgradeBlockTileEntity tileEntity){
        Inventory inventory = tileEntity.inventory;
        for (int i = 1; i < 6; i++) {
            if(!inventory.getItem(i).equals(ItemStack.EMPTY)){
                inventory.getItem(i).shrink(1);
            }
        }
    }

    private float getDifficultyChance(){
        double diff = CrTConfig.getWorldDifficulty();
        float chance = 1f;
        if(diff < 1){
            chance *= 0.8f;
        }
        if(diff <= 1.75){
            chance *= 0.9f;
        }
        if(diff <= 2.25){
            chance *= 0.93f;
        }
        if(diff <= 2.75){
            chance *= 0.95f;
        }
        return chance;
    }

    private static boolean isSuccess(int chance){
        return MathUtils.isRandom(chance/10000.0d);
    }

    private int getChance(Inventory inventory){
        int weight,requireWeight;
        Item mainItem = inventory.getItem(0).getItem();
        if(mainItem instanceof IUpgradeableItem){
            IUpgradeableItem weapon = (IUpgradeableItem) mainItem;
            if(weapon.getMaxLevel() <= weapon.getLevel(inventory.getItem(0))){
                return 1919810;
            }
            requireWeight = weapon.getWeightRequired(inventory.getItem(0));
            weight = getWeight(inventory);
            ItemStack lucky = inventory.getItem(5);
            if(lucky.getItem() instanceof ILuckyItem){
                ILuckyItem luckyItem = (ILuckyItem) lucky.getItem();
                weight *= luckyItem.getLucky();
            }
            weight *= getDifficultyChance();
            return Math.min((int) (10000.0d * weight / requireWeight),10000);
        }
        else return 114514;
    }

    private int getWeight(Inventory inventory){
        int weight = 0;
        for (int i = 1; i < 5; i++) {
            Item item = inventory.getItem(i).getItem();
            if(item instanceof IUpgradeableMaterial){
                IUpgradeableMaterial material = (IUpgradeableMaterial) item;
                weight += material.getWeight(inventory.getItem(i));
            }
        }
        return weight;
    }

    public void setStates(States states){
        this.states = states;
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return EMCWorld.tc("gui.emcworld.weapon_upgrade_core");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, @Nonnull PlayerInventory p_createMenu_2_, @Nonnull PlayerEntity p_createMenu_3_) {
        return new WeaponUpgradeContainer(p_createMenu_1_, p_createMenu_2_, this, this.data);
    }

    public enum States{
        STOP,STARTING
    }
}
