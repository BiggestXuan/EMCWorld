package biggestxuan.emcworld.common.raid.effects;

import biggestxuan.emcworld.common.raid.RaidEffect;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.MathUtils;
import hellfirepvp.astralsorcery.common.lib.ConstellationsAS;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.raid.Raid;

import javax.annotation.Nonnull;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/12
 */
public class Vorux extends RaidEffect {
    float rate = hasAlcara ? 0.025F : 0.25F;
    public Vorux(@Nonnull Raid raid) {
        super(raid, "vorux", ConstellationsAS.vorux);
    }

    @Override
    public float onPlayerHurt(PlayerEntity player, DamageSource source, float amount) {
        return amount * (1 + rate);
    }

    @Override
    public float onPlayerAttack(PlayerEntity player, Entity target, float amount) {
        return amount * (1 + rate);
    }

    @Override
    public void onIllagerDeath(LivingEntity livingEntity, DamageSource source) {
        ItemStack stack = ItemStack.EMPTY;
        if(MathUtils.isRandom(rate)){
            if(waves <= 6){
                stack = new ItemStack(EWItems.LUCKY_GEM_BLUE.get(),6);
            }else if(waves <= 12){
                stack = new ItemStack(EWItems.LUCKY_GEM_RED.get(),4);
            }else if(waves <= 16){
                stack = new ItemStack(EWItems.LUCKY_GEM_PURPLE.get(),2);
            }else{
                stack = new ItemStack(EWItems.LUCKY_GEM_GOLD.get(),1);
            }
        }
        if(!stack.isEmpty()){
            BlockPos pos = livingEntity.blockPosition();
            world.addFreshEntity(new ItemEntity(world,pos.getX(),pos.getY(),pos.getZ(),stack));
        }
    }
}
