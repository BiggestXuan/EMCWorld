package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.api.item.IEMCInfuserItem;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import divinerpg.items.base.ItemMod;
import divinerpg.items.vanilla.ItemSerenadeOfHealth;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/31
 */

@Mixin(ItemSerenadeOfHealth.class)
public abstract class ItemSerenadeOfHealthMixin extends ItemMod implements IEMCInfuserItem, IPrefixItem, IUpgradeableItem {
    public ItemSerenadeOfHealthMixin(String name) {
        super(name);
    }

    /**
     * @author Biggest_Xuan
     * @reason .
     */
    @Override
    @Overwrite
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand){
        if(!world.isClientSide){
            ItemStack stack = player.getItemInHand(hand);
            if(player.getHealth() < player.getMaxHealth()){
                if(getInfuser(stack) >= getMaxInfuser(stack) / 10){
                    cost(stack);
                }else{
                    stack.hurtAndBreak(1, player, (p_220009_1_) -> {
                        p_220009_1_.broadcastBreakEvent(player.getUsedItemHand());
                    });
                }
                player.getCooldowns().addCooldown(this,300);
                player.heal(player.getMaxHealth()*getHealRate(stack));
                return new ActionResult(ActionResultType.SUCCESS, stack);
            }
        }
        return super.use(world, player, hand);
    }

    private float getHealRate(ItemStack stack){
        float base = 0.5F;
        base += (getPrefix(stack).getLevel()-4)*0.02F;
        base += getLevel(stack) * 0.03F;
        return base;
    }

    @Override
    public double costRate(ItemStack stack){
        return 0.05;
    }

    @Override
    public long getMaxInfuser(ItemStack stack) {
        return 1000000;
    }

    @Override
    public int getMaxLevel() {
        return 10;
    }
}
