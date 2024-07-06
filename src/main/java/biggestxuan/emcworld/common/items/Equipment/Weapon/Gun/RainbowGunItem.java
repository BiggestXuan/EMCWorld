package biggestxuan.emcworld.common.items.Equipment.Weapon.Gun;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/21
 */

import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.item.IEMCInfuserItem;
import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class RainbowGunItem extends GunItem implements IEMCInfuserItem {
    public RainbowGunItem() {
        super(EMCWorldAPI.getInstance().getGunTier("rainbow"));
    }

    @Override
    public long getMaxInfuser(ItemStack stack) {
        return (long) (300000L * Math.pow(getLevel(stack),1.1));
    }

    @Override
    public float damage(ItemStack stack){
        return super.damage(stack) + (getInfuser(stack) <= Math.E ? 0 : (float) Math.log(getInfuser(stack) * 0.7));
    }
    @Override
    public int getMaxLevel() {
        return (int) (10 * ConfigManager.DIFFICULTY.get() / 3);
    }

    @Override
    public double accuracy(ItemStack stack, PlayerEntity player){
        return Math.min(1,super.accuracy(stack,player) * (1 + (getInfuser(stack) <= Math.E ? 0 : Math.log(getInfuser(stack) * 0.06F))));
    }
}
