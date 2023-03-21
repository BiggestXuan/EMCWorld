package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/08
 */

import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.equipment.bow.IUpgradeBow;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BowItem.class)
public abstract class BowItemMixin extends ShootableItem implements IPrefixItem, IUpgradeBow {
    public BowItemMixin(Properties p_i50040_1_) {
        super(p_i50040_1_);
    }

    @Override
    public int getMaxLevel() {
        return (int) (9 * ConfigManager.DIFFICULTY.get() / 3);
    }

    @Override
    public float getAdditionDamage(ItemStack stack) {
        return MathUtils.getBowAdditionDamage(stack);
    }

    @Override
    public float lossBowTime(ItemStack stack) {
        return MathUtils.getBowLossTime(stack);
    }

    /**
     * @author Biggest_Xuan
     * @reason .
     */
    //TODO
    @Override
    @Overwrite
    public int getUseDuration(ItemStack p_77626_1_) {
        return (int) (72000 * lossBowTime(p_77626_1_));
    }
}
