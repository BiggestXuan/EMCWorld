package biggestxuan.emcworld.common.items;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/20
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.IEMCInfuserItem;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.api.item.equipment.IStarItem;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class EMCChargeItem extends EWItem implements IEMCInfuserItem, IUpgradeableItem, IStarItem, IPrefixItem {
    public EMCChargeItem(){
        super(new Properties().tab(EWCreativeTabs.EW_EQUIPMENT_TAB).stacksTo(1).setNoRepair());
    }

    @Override
    public void inventoryTick(@Nonnull ItemStack p_77663_1_, @Nonnull World p_77663_2_, @Nonnull Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        if(p_77663_3_.level.isClientSide) return;
        CompoundNBT nbt = p_77663_1_.getOrCreateTag();
        if(!nbt.contains("mode")){
            nbt.putBoolean("mode",false);
        }
        if(p_77663_3_ instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) p_77663_3_;
            for(ItemStack stack : getAllItems(player)){
                if(stack.getItem() instanceof IEMCInfuserItem && !stack.getItem().equals(this) && nbt.getBoolean("mode")){
                    IEMCInfuserItem item = (IEMCInfuserItem) stack.getItem();
                    long used = item.getUse(stack);
                    if(used <= 0){
                        continue;
                    }
                    long cost = MathUtils.min(used,this.getInfuser(p_77663_1_), (long) (this.getMaxInfuser(p_77663_1_)*0.01D));
                    this.addInfuser(p_77663_1_,Math.negateExact(cost));
                    item.addInfuser(stack,cost);
                }
            }
        }
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, @Nonnull List<ITextComponent> p_77624_3_,@Nonnull ITooltipFlag p_77624_4_) {
        super.appendHoverText(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        if(p_77624_1_.getOrCreateTag().getBoolean("mode")){
            p_77624_3_.add(EMCWorld.tc("message.emc_charge.on"));
        }else{
            p_77624_3_.add(EMCWorld.tc("message.emc_charge.off"));
        }
    }

    @Override
    public int getMaxLevel() {
        return (int) (100 * ConfigManager.DIFFICULTY.get() / 3.0D);
    }

    @Override
    public long getMaxInfuser(ItemStack stack) {
        int level = getPrefix(stack).getLevel();
        double rate = level >= 4 ? 1 + (level - 4) * 0.02 : 1 - (4 - level) * 0.06;
        return (long) (1L * Integer.MAX_VALUE * getLevel(stack) * getBuffer(stack) * rate);
    }

    @Override
    public int getWeightRequired(ItemStack stack){
        int l = getLevel(stack);
        return (int) (100 + 625 * l / getBuffer(stack));
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> use(@Nonnull World p_77659_1_, @Nonnull PlayerEntity p_77659_2_, @Nonnull Hand p_77659_3_) {
        ItemStack stack = p_77659_2_.getItemInHand(p_77659_3_);
        if(p_77659_2_.level.isClientSide) return ActionResult.fail(stack);
        CompoundNBT nbt = stack.getOrCreateTag();
        nbt.putBoolean("mode", !nbt.getBoolean("mode"));
        return ActionResult.success(stack);
    }

    @Override
    public boolean isFoil(@Nonnull ItemStack p_77636_1_) {
        return p_77636_1_.getOrCreateTag().getBoolean("mode");
    }

    private static NonNullList<ItemStack> getAllItems(PlayerEntity player){
        NonNullList<ItemStack> stacks = NonNullList.create();
        stacks.addAll(player.inventory.armor);
        stacks.addAll(player.inventory.items);
        stacks.addAll(player.inventory.offhand);
        Optional<SlotResult> result = CuriosApi.getCuriosHelper().findFirstCurio(player, EWItems.EMC_SHIELD_SUPPLY.get());
        result.ifPresent(s -> stacks.add(s.getStack()));
        return stacks;
    }
}
