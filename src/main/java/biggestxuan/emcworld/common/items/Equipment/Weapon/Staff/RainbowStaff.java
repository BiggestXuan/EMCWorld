package biggestxuan.emcworld.common.items.Equipment.Weapon.Staff;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/17
 */

import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.item.IEMCInfuserItem;
import biggestxuan.emcworld.api.item.INeedLevelItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class RainbowStaff extends StaffItem implements IEMCInfuserItem,INeedLevelItem {
    public RainbowStaff() {
        super(EMCWorldAPI.getInstance().getStaffTier("diamond"));
    }

    @Override
    public int getUseLevel(ItemStack stack) {
        return 20;
    }

    @Override
    public long getMaxInfuser(ItemStack stack) {
        return 3000000;
    }

    @Override
    public double getCriticalChance(ItemStack stack) {
        return tier.getCriticalChance() * getPrefixCommonRate(stack) + (getInfuser(stack) >= Math.E ? Math.log(getInfuser(stack))/35d : 0);
    }

    @Override
    public double getCriticalRate(ItemStack stack) {
        return tier.getCriticalRate() * getPrefixCommonRate(stack) + (getInfuser(stack) >= Math.E ? Math.log(getInfuser(stack))/30d : 0);
    }

    @Override
    public void spawnManaBurst(PlayerEntity player, double speed){
        super.spawnManaBurst(player,speed);
        this.cost(player.getMainHandItem());
    }

    @Override
    public float getBaseDamage(ItemStack stack){
        float baseDamage = (float) (27.5F * getPrefixCommonRate(stack));
        float damage =  getInfuser(stack) >= Math.E ? baseDamage + (float) (Math.log(getInfuser(stack)*0.65)) : baseDamage;
        return damage + getLevel(stack) * 0.06f * damage;
    }

    @Override
    protected double getManaBurstSpeed(ItemStack stack){
        return getInfuser(stack) >= Math.E ? 6 + (float) (Math.log(getInfuser(stack)*0.8)) : 6;
    }

    @Override
    protected int getColor(){
        List<Integer> list = Arrays.asList(0x796653,0x818181,0xc0c0c0,0xdbaf2d,0x74c2b2,0x71615b);
        return list.get((int)(list.size() * Math.random()));
    }
}
