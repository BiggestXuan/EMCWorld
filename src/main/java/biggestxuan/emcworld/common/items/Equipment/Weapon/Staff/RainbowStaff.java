package biggestxuan.emcworld.common.items.Equipment.Weapon.Staff;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/17
 */

import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.item.IEMCInfuserItem;
import biggestxuan.emcworld.api.item.INeedLevelItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.IRainbowEquipment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class RainbowStaff extends StaffItem implements IRainbowEquipment {
    public RainbowStaff() {
        super(EMCWorldAPI.getInstance().getStaffTier("diamond"));
    }

    @Override
    public double getCriticalChance(ItemStack stack) {
        return super.getCriticalChance(stack) + getRainbowCriticalChance(stack);
    }

    @Override
    public double getCriticalRate(ItemStack stack) {
        return super.getCriticalRate(stack) + getRainbowCriticalRate(stack);
    }

    @Override
    public void spawnManaBurst(PlayerEntity player, double speed){
        super.spawnManaBurst(player,speed);
        this.cost(player.getMainHandItem());
    }

    @Override
    public float getBaseDamage(ItemStack stack){
        float baseDamage = (float) (22.5F * getPrefixCommonRate(stack));
        return getRainbowDamage(stack,baseDamage+ getLevel(stack) * 0.06f * baseDamage);
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
