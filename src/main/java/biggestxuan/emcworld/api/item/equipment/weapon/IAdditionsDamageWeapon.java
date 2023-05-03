package biggestxuan.emcworld.api.item.equipment.weapon;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/30
 */

import biggestxuan.emcworld.common.utils.DamageUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface IAdditionsDamageWeapon {
    DamageUtils getAdditionsDamage(PlayerEntity player, ItemStack stack);
}
