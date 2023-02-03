package biggestxuan.emcworld.common.blocks.PrefixBlock;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/01
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.block.BaseContainerTileEntity;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.common.exception.EMCWorldCommonException;
import biggestxuan.emcworld.common.items.Equipment.PrefixScroll;
import biggestxuan.emcworld.common.items.Equipment.Scroll.ScrollItem;
import biggestxuan.emcworld.common.registry.EWTileEntityTypes;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.Message;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static biggestxuan.emcworld.common.items.Equipment.PrefixScroll.getTotal;

public class PrefixTileEntity extends BaseContainerTileEntity implements ITickableTileEntity, INamedContainerProvider {
    private final Inventory inventory = new Inventory(2);
    private State state;
    private PlayerEntity lastClick = null;
    public PrefixTileEntity() {
        super(EWTileEntityTypes.PrefixCoreTileEntity.get());
        state = State.STOP;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return EMCWorld.tc("");
    }

    @Override
    public void load(@Nonnull BlockState p_230337_1_, @Nonnull CompoundNBT p_230337_2_){
        super.load(p_230337_1_,p_230337_2_);
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item")));
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item1")));
    }

    @Nonnull
    @Override
    public CompoundNBT save(@Nonnull CompoundNBT p_189515_1_){
        p_189515_1_.put("item",this.inventory.getItem(0).serializeNBT());
        p_189515_1_.put("item1",this.inventory.getItem(1).serializeNBT());
        super.save(p_189515_1_);
        return p_189515_1_;
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new PrefixContainer(p_createMenu_1_,p_createMenu_2_,this);
    }

    @Override
    public void tick() {
        if(canStart()){
            ItemStack left = inventory.getItem(0);
            ItemStack right = inventory.getItem(1);
            if(left.equals(ItemStack.EMPTY) || right.equals(ItemStack.EMPTY)){
                stop();
            }
            if(left.getItem() instanceof ScrollItem && right.getItem() instanceof PrefixScroll){
                ScrollItem left_scroll = (ScrollItem) left.getItem();
                PrefixScroll right_scroll = (PrefixScroll) right.getItem();
                if(right_scroll.getWeight(right) < PrefixScroll.MAX){
                    costLeft();
                    right_scroll.update(right, (int) (left_scroll.getWeight(left) * (1-left_scroll.breakWeaponRate())));
                }
                stop();
            }
            if(left.getItem() instanceof PrefixScroll && right.getItem() instanceof IPrefixItem){
                IPrefixItem r = (IPrefixItem) right.getItem();
                IPrefixItem.Prefix prefix = getPrefix(left);
                if(prefix != IPrefixItem.Prefix.NULL){
                    costLeft();
                    r.setPrefix(right,prefix);
                    if(prefix == IPrefixItem.Prefix.LEGEND || prefix == IPrefixItem.Prefix.MYTH){
                        MinecraftServer server = getLevel().getServer();
                        CompoundNBT nbt = left.getOrCreateTag();
                        String chance = String.format("%.2f",100d*nbt.getInt(prefix.toString())/getTotal(left))+"%";
                        if(server != null && lastClick != null){
                            Message.sendMessageToAllPlayer(server, (TranslationTextComponent) EMCWorld.tc("message.emcworld.weapon_success",lastClick.getScoreboardName(),chance).append(right.getDisplayName()));
                        }
                    }
                }
                stop();
            }
        }
        stop();
    }

    private static IPrefixItem.Prefix getPrefix(ItemStack stack){
        try {
            if(!(stack.getItem() instanceof PrefixScroll)){
                throw new EMCWorldCommonException();
            }
        } catch (EMCWorldCommonException e) {
            e.printStackTrace();
        }
        CompoundNBT nbt = stack.getOrCreateTag();
        double random = MathUtils.Random();
        double k = 0d;
        int total = getTotal(stack);
        List<Integer> weight = new ArrayList<>();
        List<Double> chance = new ArrayList<>();
        for(IPrefixItem.Prefix prefix : IPrefixItem.Prefix.values()){
            if(prefix == IPrefixItem.Prefix.NULL) continue;
            weight.add(nbt.getInt(prefix.toString()));
        }
        for (int i = 0; i < weight.size(); i++) {
            if(i == 0){
                double c = 1.0d * weight.get(0)/total;
                chance.add(c);
                k += c;
                continue;
            }
            double c = 1.0d*weight.get(i)/total;
            chance.add(k+c);
            k += c;
        }
        /*System.out.println("random"+random);
        System.out.println("weight");
        weight.forEach(System.out::println);
        System.out.println("chance");
        chance.forEach(System.out::println);*/
        for (int i = 0; i < chance.size(); i++) {
            if(i == 0){
                if(random < chance.get(0)){
                    return IPrefixItem.getPrefix(1);
                }
                continue;
            }
            if(random >= chance.get(i-1) && random <= chance.get(Math.min(i,chance.size()-1))){
                IPrefixItem.Prefix prefix = IPrefixItem.getPrefix(i+2);
                return prefix.getLevel() > PrefixScroll.getMaxReachPrefix(nbt.getInt("weight")) ? IPrefixItem.getPrefix(prefix.getLevel()-1) : prefix;
            }
        }
        return IPrefixItem.Prefix.NULL;
    }

    public void setPlayer(PlayerEntity player){
        lastClick = player;
    }

    private void costLeft(){
        ItemStack stack = inventory.getItem(0);
        if(stack.getItem() instanceof ScrollItem){
            stack.shrink(1);
        }
        if(stack.getItem() instanceof PrefixScroll){
            if(stack.getDamageValue() >=2){
                stack.shrink(1);
            }else{
                stack.setDamageValue(stack.getDamageValue()+1);
            }
        }
    }
    private boolean canStart(){
        return state == State.START;
    }

    private void stop(){
        state = State.STOP;
    }

    public enum State{
        STOP,START
    }

    public void start(){
        state = State.START;
    }
}
