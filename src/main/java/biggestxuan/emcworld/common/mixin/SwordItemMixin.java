package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/18
 */

import biggestxuan.emcworld.api.item.IEMCGod;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.equipment.IAttackSpeedItem;
import biggestxuan.emcworld.api.item.equipment.weapon.IAdditionsDamageWeapon;
import biggestxuan.emcworld.api.item.equipment.weapon.IRangeAttackWeapon;
import biggestxuan.emcworld.api.item.equipment.weapon.IUpgradeableWeapon;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.items.Equipment.Weapon.IRainbowEquipment;
import biggestxuan.emcworld.common.utils.DamageUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SwordItem.class)
public abstract class SwordItemMixin extends TieredItem implements IAdditionsDamageWeapon, IRangeAttackWeapon,IUpgradeableWeapon, IPrefixItem, IAttackSpeedItem {
    private final IItemTier tier = getTier();

    private final float damage = tier.getAttackDamageBonus();

    public SwordItemMixin(IItemTier p_i48459_1_, Properties p_i48459_2_) {
        super(p_i48459_1_, p_i48459_2_);
    }

    @Override
    public double costEMCWhenAttack(ItemStack stack) {
        int prefix_level = getPrefix(stack).getLevel();
        return prefix_level >= 7 ? 1 - (prefix_level - 6) * 0.1 : 1;
    }

    @Override
    public long EMCModifySecond(ItemStack stack) {
        return 0;
    }

    @Override
    public int getMaxLevel() {
        if(tier.equals(ItemTier.NETHERITE)){
            return (int) (14 * ConfigManager.DIFFICULTY.get() / 3);
        }
        return (int) ((tier.getLevel() + 1) * ConfigManager.DIFFICULTY.get() * 0.65d);
    }

    @Override
    public DamageUtils getAdditionsDamage(PlayerEntity player,ItemStack stack) {
        return DamageUtils.of(getLevel(stack) == 0 ? getPrefixDamage(stack) : (float) (tier.getAttackDamageBonus() * 0.1 * getLevel(stack) + getPrefixDamage(stack)));
    }

    @Override
    public DamageUtils getAttackRange(PlayerEntity player,ItemStack stack) {
        float level = getLevel(stack) * getInfuserRate(stack);
        double d = Math.max(0,getLevel(stack) == 0 ? 0 + getPrefixRange(stack) : (0.075 * tier.getLevel() + (damage/65) * level / 3)) + getPrefixRange(stack);
        return DamageUtils.of(d);
    }

    @Override
    public double getAttackSpeed(ItemStack stack){
        Prefix prefix = getPrefix(stack);
        if(prefix == Prefix.NULL){
            return 1;
        }
        return prefix.getLevel() >= 4 ? (prefix.getLevel() - 4) * 0.02 + 1 : (prefix.getLevel() - 4) * 0.05 + 1;
    }

    private static float getInfuserRate(ItemStack stack){
        Item item = stack.getItem();
        if(item instanceof IRainbowEquipment && !(item instanceof IEMCGod)){
            IRainbowEquipment equipment = (IRainbowEquipment) item;
            long infuser = equipment.getInfuser(stack);
            return infuser >= Math.E ? (float) (Math.log(infuser*0.65) /3) : 0;
        }
        return 0;
    }

    private float getPrefixDamage(ItemStack stack){
        SwordItem sword = (SwordItem) (Object) this;
        Prefix prefix = getPrefix(stack);
        float base = sword.getDamage() + getInfuserRate(stack);
        return prefix.getLevel() <= 3 ? prefix.getLevel() == 0 ? 0 : (1 + -0.1f * (4 - prefix.getLevel())) * base : (1 + 0.1f * (prefix.getLevel()-4)) * base;
    }

    private double getPrefixRange(ItemStack stack){
        Prefix prefix = getPrefix(stack);
        return prefix.getLevel() <= 3 ? prefix.getLevel() == 0 ? 0 : -0.1f * (4 - prefix.getLevel()) * tier.getLevel() : 0.02f * (prefix.getLevel()-4) * tier.getLevel() / 3;
    }
}
