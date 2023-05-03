package biggestxuan.emcworld.common.blocks.StarPedestal;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/04
 */

import biggestxuan.emcworld.api.block.BaseContainerTileEntity;
import biggestxuan.emcworld.api.item.equipment.IStarItem;
import biggestxuan.emcworld.common.registry.EWTileEntityTypes;
import hellfirepvp.astralsorcery.common.crafting.recipe.altar.ActiveSimpleAltarRecipe;
import hellfirepvp.astralsorcery.common.crafting.recipe.altar.effect.*;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class StarPedestalTileEntity extends BaseContainerTileEntity implements ITickableTileEntity {
    private final Inventory inventory = new Inventory(1);
    private int craftLevel;
    private boolean isCrafting;
    private STATE state = STATE.STOP;
    private int tick = 0;

    public StarPedestalTileEntity(){
        super(EWTileEntityTypes.starPedestalTileEntity.get());
    }

    @Override
    public void tick(){
        if(!level.isClientSide){
            StarPedestalMultiBlock mb = new StarPedestalMultiBlock(this);
            craftLevel = mb.getLevel();
            level.sendBlockUpdated(getBlockPos(),getBlockState(),getBlockState(),Constants.BlockFlags.BLOCK_UPDATE);
            if(state == STATE.START){
                List<List<ItemStack>> inv = mb.getInventory();
                StarPedestalRecipe recipe = null;
                for(StarPedestalRecipe r : StarPedestalRecipe.values()){
                    if(equals(inv,r.getInputs())){
                        recipe = r;
                    }
                }
                if(canStart(recipe)){
                    if(tick >= 60){
                        if(recipe.getMode() == StarPedestalRecipe.MODE.MAX_STAR){
                            addMaxStar();
                        }else addStar();
                        mb.costInventory(recipe.getLevel());
                        stop();
                    }
                    tick++;
                }else{
                    stop();
                }
            }
        }
        else{
            renderLight();
        }
        isCrafting = state == STATE.START;
    }

    private boolean canStart(StarPedestalRecipe recipe){
        StarPedestalMultiBlock mb = new StarPedestalMultiBlock(this);
        ItemStack s = inventory.getItem(0);
        if(!(s.getItem() instanceof IStarItem) || recipe == null){
            return false;
        }
        IStarItem item = (IStarItem) s.getItem();
        final boolean base = craftLevel >= recipe.getLevel() && mb.getStar() != null && mb.getStar().getSimpleName().equals(recipe.getStar().getSimpleName());
        if(recipe.getMode() == StarPedestalRecipe.MODE.MAX_STAR){
            return base && item.getMaxStar(s) == recipe.getRequireStar() && item.getMaxStar(s) < IStarItem.getDifficultyMaxStar();
        }else{
            return base && item.getStar(s) == recipe.getRequireStar() && item.getStar(s) < item.getMaxStar(s);
        }
    }

    private void stop(){
        state = STATE.STOP;
        tick = 0;
    }

    private void addMaxStar(){
        IStarItem item = (IStarItem) inventory.getItem(0).getItem();
        item.addMaxStar(inventory.getItem(0));
    }

    private void addStar(){
        IStarItem item = (IStarItem) inventory.getItem(0).getItem();
        item.addStar(inventory.getItem(0));
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
        CompoundNBT compoundNBT = super.getUpdateTag();
        compoundNBT.putBoolean("flag", isCrafting);
        compoundNBT.putInt("level", craftLevel);
        return compoundNBT;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        isCrafting = tag.getBoolean("flag");
        craftLevel = tag.getInt("level");
    }

    public int getCraftLevel() {
        return craftLevel;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void load(@Nonnull BlockState p_230337_1_, @Nonnull CompoundNBT p_230337_2_){
        super.load(p_230337_1_,p_230337_2_);
        inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item")));
        tick = p_230337_2_.getInt("tick");
        isCrafting = p_230337_2_.getBoolean("flag");
    }

    @Nonnull
    @Override
    public CompoundNBT save(@Nonnull CompoundNBT p_189515_1_){
        p_189515_1_.put("item",this.inventory.getItem(0).serializeNBT());
        p_189515_1_.putInt("tick",tick);
        p_189515_1_.putBoolean("flag",isCrafting);
        super.save(p_189515_1_);
        return p_189515_1_;
    }

    @OnlyIn(Dist.CLIENT)
    private void renderLight(){
        FakeAltar altar = new FakeAltar();
        List<AltarRecipeEffect> effects = new ArrayList<>();
        effects.add(new BuiltInEffectConstellationFinish());
        effects.add(new BuiltInEffectTraitRelayHighlight());
        effects.add(new EffectAltarDefaultSparkle());
        effects.add(new BuiltInEffectAttunementSparkle());
        effects.add(new BuiltInEffectTraitFocusCircle());
        effects.add(new BuiltInEffectConstellationLines());
        altar.setPosition(this.getBlockPos());
        if(isCrafting){
            effects.forEach(e->{
                e.onTick(altar, ActiveSimpleAltarRecipe.CraftingState.ACTIVE);
                e.onTick(altar, ActiveSimpleAltarRecipe.CraftingState.WAITING);
            });
        }
    }

    private static boolean equals(List<List<ItemStack>> inv,List<List<ItemStack>> recipe){
        boolean flag = true;
        for (int i = 0; i < 4; i++) {
            List<ItemStack> s1 = copy(inv.get(i));
            List<ItemStack> s2 = copy(recipe.get(i));
            if(!equalSet(s1,s2)){
                flag = false;
            }
        }
        return flag;
    }

    private static boolean equalSet(List<ItemStack> s1,List<ItemStack> s2){
        boolean flag = true;
        for(ItemStack t1 : s2){
            if(t1.equals(ItemStack.EMPTY) || t1.getItem().equals(Items.AIR)){
                continue;
            }
            if(!hasItem(s1,t1)){
                flag = false;
            }
        }
        return flag;
    }

    private static boolean hasItem(List<ItemStack> h,ItemStack stack){
        for(ItemStack c : h){
            if(c.getItem().equals(stack.getItem())){
                return true;
            }
        }
        return false;
    }

    public void start(){
        state = STATE.START;
        new StarPedestalMultiBlock(this).getInventory().forEach(c->{
            c.forEach(k->{
                //System.out.println(k.toString());
            });
        });
    }

    private static <T> List<ItemStack> copy(List<ItemStack> set){
        List<ItemStack> s = new ArrayList<>();
        set.forEach(k->s.add(k.copy()));
        return s;
    }

    public enum STATE{
        START,STOP
    }
}
