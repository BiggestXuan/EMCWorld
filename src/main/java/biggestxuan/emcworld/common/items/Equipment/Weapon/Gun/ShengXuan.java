package biggestxuan.emcworld.common.items.Equipment.Weapon.Gun;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.item.equipment.gun.BaseEMCGodGun;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/02
 */

@EMCWorldSince("0.9.0")
public class ShengXuan extends BaseEMCGodGun{
    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ActionResult<ItemStack> result = super.use(world, player, hand);
        if(!player.level.isClientSide){
            ItemStack stack = player.getItemInHand(hand);
            CompoundNBT nbt = stack.getOrCreateTag();
            int index = nbt.getInt("god_gun");
            nbt.putInt("god_gun",index == 0 ? 1 : 0);
            return ActionResult.success(stack);
        }
        return result;
    }
}
