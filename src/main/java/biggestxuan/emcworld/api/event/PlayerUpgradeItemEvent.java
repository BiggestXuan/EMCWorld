package biggestxuan.emcworld.api.event;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/04
 */

import biggestxuan.emcworld.common.blocks.WeaponUpgradeBlock.WeaponUpgradeBlockTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerUpgradeItemEvent extends PlayerEvent {
    private final ItemStack item;
    private final WeaponUpgradeBlockTileEntity tile;
    protected double successChance;
    protected double breakChance;

    public PlayerUpgradeItemEvent(PlayerEntity player,ItemStack item,double successChance,double breakChance,WeaponUpgradeBlockTileEntity tile){
        super(player);
        this.item = item;
        this.successChance = successChance;
        this.breakChance = breakChance;
        this.tile = tile;
    }

    public WeaponUpgradeBlockTileEntity getTile(){
        return tile;
    }

    public double getBreakChance() {
        return breakChance;
    }

    public ItemStack getItem() {
        return item;
    }

    public double getSuccessChance() {
        return successChance;
    }

    public static class Pre extends PlayerUpgradeItemEvent{

        public Pre(PlayerEntity player, ItemStack item, double successChance, double breakChance,WeaponUpgradeBlockTileEntity tile) {
            super(player, item, successChance, breakChance,tile);
        }

        public void setSuccessChance(double chance){
            this.successChance = chance;
        }

        public void setBreakChance(double chance){
            this.breakChance = chance;
        }
    }

    public static class After extends PlayerUpgradeItemEvent{
        private final boolean isSucceed;
        private final boolean isBreakItem;

        public After(PlayerEntity player, ItemStack item, double successChance, double breakChance, boolean isBreakItem, boolean isSucceed,WeaponUpgradeBlockTileEntity tile) {
            super(player, item, successChance, breakChance,tile);
            this.isBreakItem = isBreakItem;
            this.isSucceed = isSucceed;
        }

        public boolean isBreakItem() {
            return isBreakItem;
        }

        public boolean isSucceed(){
            return isSucceed;
        }
    }
}
