package biggestxuan.emcworld.common.raid.effects;

import biggestxuan.emcworld.common.raid.RaidEffect;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.MathUtils;
import hellfirepvp.astralsorcery.common.lib.ConstellationsAS;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
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
public class Discidia extends RaidEffect {
    public Discidia(@Nonnull Raid raid) {
        super(raid, "discidia", ConstellationsAS.discidia);
    }

    @Override
    public float onIllagerAttack(LivingEntity attacker, Entity target, float amount) {
        return amount * (hasAlcara ? 1.01F : 1.1F);
    }

    @Override
    public void onIllagerDeath(LivingEntity livingEntity, DamageSource source) {
        ItemStack stack = ItemStack.EMPTY;
        double chance = (hasAlcara ? 0.001 : 0.01) * waves;
        if(MathUtils.isRandom(chance)){
            if(MathUtils.isRandom(chance)){
                if(MathUtils.isRandom(chance)){
                    stack = new ItemStack(EWItems.SCROLL_GOLD.get(),1);
                }else{
                    stack = new ItemStack(EWItems.SCROLL_PURPLE.get(),2);
                }
            }else{
                stack = new ItemStack(EWItems.SCROLL_BLUE.get(),3);
            }
        }
        if(!stack.isEmpty()){
            BlockPos pos = livingEntity.blockPosition();
            world.addFreshEntity(new ItemEntity(world,pos.getX(),pos.getY(),pos.getZ(),stack));
        }
    }
}
