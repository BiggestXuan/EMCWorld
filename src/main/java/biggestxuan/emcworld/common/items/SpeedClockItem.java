package biggestxuan.emcworld.common.items;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.ISponsorItem;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.api.item.equipment.IStarItem;
import biggestxuan.emcworld.client.screen.OpenScreen;
import biggestxuan.emcworld.client.screen.SpeedClockScreen;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.Sponsors.Sponsors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/30
 */

@EMCWorldSince("1.0.3")
public class SpeedClockItem extends EWItem implements ISponsorItem, IUpgradeableItem, IPrefixItem, IStarItem {

    @Override
    public int getMaxLevel() {
        return 30;
    }

    @Nullable
    @Override
    public Sponsors getSponsor() {
        return Sponsors.all.yuluo_1.getSponsors();
    }

    @Override
    public void inventoryTick(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        if(!p_77663_1_.hasTag()){
            CompoundNBT nbt = p_77663_1_.getOrCreateTag();
            nbt.putInt("emc_speed",0);
        }
    }

    @Override
    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
        if(p_77659_1_.isClientSide && p_77659_2_.isShiftKeyDown()){
            DistExecutor.safeCallWhenOn(Dist.CLIENT,() -> () -> new OpenScreen(new SpeedClockScreen()));
        }
        return super.use(p_77659_1_, p_77659_2_, p_77659_3_);
    }

    @Override
    @Nonnull
    public ActionResultType useOn(ItemUseContext context) {
        if(context.getLevel().isClientSide){
            return ActionResultType.FAIL;
        }
        ItemStack stack = context.getItemInHand();
        World world = context.getLevel();
        TileEntity tile = world.getBlockEntity(context.getClickedPos());
        if(tile instanceof ITickableTileEntity){
            int speed = getSpeedRate(stack);
            new Do((ITickableTileEntity) tile,speed).start();
            context.getPlayer().getCooldowns().addCooldown(this,getCoolDown(stack));
        }
        return super.useOn(context);
    }

    public int getCoolDown(ItemStack stack){
        int level = getLevel(stack);
        int star = getStar(stack);
        int prefix = getPrefix(stack).getLevel();
        int base = 1200;
        base *= 1 + 0.05 * level;
        base /= 1 + 0.07 * star;
        assert level <= 10;
        base *= 1 + ((10 - level)* 0.1);
        return base;
    }

    public long getEMCCost(ItemStack stack,int rate){
        return (long) (-1000L * (rate == -1 ? getMaxSpeedRate(stack) : rate) * ConfigManager.DIFFICULTY.get());
    }

    public int getMaxSpeedRate(ItemStack stack){
        int level = getLevel(stack);
        int star = getStar(stack);
        Prefix prefix = getPrefix(stack);
        return getMaxSpeedRate(level,prefix,star);
    }

    public static int getMaxSpeedRate(int level,Prefix prefix,int star){
        int base = 20;
        base *= Math.pow(1.3,level);
        base *= 1 - ((10 - prefix.getLevel()) * 0.08);
        base *= 2.5 * star;
        base /= ConfigManager.DIFFICULTY.get();
        return base;
    }

    public int getSpeedRate(ItemStack stack){
        CompoundNBT nbt = stack.getOrCreateTag();
        return nbt.getInt("emc_speed");
    }

    private static class Do extends Thread{
        private final ITickableTileEntity tile;
        private final int count;

        Do(ITickableTileEntity tile,int count){
            this.tile = tile;
            this.count = count;
        }

        @Override
        public void run(){
            for (int i = 0; i < count; i++) {
                tile.tick();
            }
        }
    }
}
