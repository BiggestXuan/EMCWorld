package biggestxuan.emcworld.common.traits.traits;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.trait.AbstractTrait;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/11/20
 */

@EMCWorldSince("1.1.0")
public class IronTrait extends AbstractTrait {
    public IronTrait() {
        super(EMCWorld.rl("iron"),0xcbd4dc);
    }

    @Override
    public Item item() {
        return Items.IRON_INGOT;
    }

    @Override
    public void afterBreak(PlayerEntity player, BlockState state, ItemStack stack) {
        summonIron(player,level);
    }

    @Override
    public void onHitEntity(PlayerEntity player, LivingEntity living, ItemStack stack) {
        summonIron(player,level);
    }

    @Override
    public float onHurt(PlayerEntity player, DamageSource source, float damage, ItemStack stack) {
        summonIron(player,level);
        return super.onHurt(player, source, damage, stack);
    }

    private void summonIron(Entity entity,int level){
        ItemStack stack = new ItemStack(MathUtils.isRandom(level / 13D) ? Items.IRON_INGOT : Items.IRON_NUGGET);
        if(MathUtils.isRandom(level / 30D)){
            entity.level.addFreshEntity(new ItemEntity(entity.level,entity.getX(),entity.getY()+1,entity.getZ(),stack));
        }
    }
}
