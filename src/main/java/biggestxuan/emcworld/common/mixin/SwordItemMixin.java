package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/18
 */

import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.equipment.IAttackSpeedItem;
import biggestxuan.emcworld.api.item.equipment.weapon.IAdditionsDamageWeapon;
import biggestxuan.emcworld.api.item.equipment.weapon.IRangeAttackWeapon;
import biggestxuan.emcworld.api.item.equipment.weapon.IUpgradeableWeapon;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.utils.DamageUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TieredItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SwordItem.class)
public abstract class SwordItemMixin extends TieredItem implements IAdditionsDamageWeapon, IRangeAttackWeapon,IUpgradeableWeapon, IPrefixItem, IAttackSpeedItem {
    private final IItemTier tier = ((SwordItem) (Object) this).getTier();

    private final float damage = tier.getAttackDamageBonus();

    public SwordItemMixin(IItemTier p_i48459_1_, Properties p_i48459_2_) {
        super(p_i48459_1_, p_i48459_2_);
    }

    @Override
    public int getWeightRequired(ItemStack stack){
        int l = getLevel(stack);
        int weight = 10;
        for (int i = 0; i < l; i++) {
            weight = (int) (1.83f * weight);
        }
        return weight * 2;
    }

    @Override
    public double costEMCWhenAttack(ItemStack stack) {
        int prefix_level = getPrefix(stack).getLevel();
        return prefix_level >= 7 ? 1 - (prefix_level - 6) * 0.1 : 1;
    }

    @Override
    public long getTickCost(ItemStack stack) {
        return 0;
    }

    @Override
    public long EMCModifySecond(ItemStack stack) {
        return 0;
    }

    @Override
    public int getMaxLevel() {
        return (int) ((tier.getLevel() + 1) * ConfigManager.DIFFICULTY.get() * 0.65d);
    }

    @Override
    public DamageUtils getAdditionsDamage(PlayerEntity player,ItemStack stack) {
        return DamageUtils.of(getLevel(stack) == 0 ? 0 + getPrefixDamage(getPrefix(stack)) : (float) (tier.getAttackDamageBonus() * 0.1 * getLevel(stack) + getPrefixDamage(getPrefix(stack))));
    }

    @Override
    public DamageUtils getAttackRange(PlayerEntity player,ItemStack stack) {
        double d = Math.max(0,getLevel(stack) == 0 ? 0 + getPrefixRange(getPrefix(stack)) : (0.075 * tier.getLevel() + (damage/65) * getLevel(stack) / 3)) + getPrefixRange(getPrefix(stack));
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

    private float getPrefixDamage(Prefix prefix){
        return prefix.getLevel() <= 3 ? prefix.getLevel() == 0 ? 0 : -0.1f * (4 - prefix.getLevel()) * damage : 0.1f * (prefix.getLevel()-4) * damage;
    }

    private double getPrefixRange(Prefix prefix){
        return prefix.getLevel() <= 3 ? prefix.getLevel() == 0 ? 0 : -0.1f * (4 - prefix.getLevel()) * tier.getLevel() : 0.1f * (prefix.getLevel()-4) * tier.getLevel() / 3;
    }
}
