package biggestxuan.emcworld.common.traits.traits;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.trait.AbstractTrait;
import biggestxuan.emcworld.api.trait.TraitType;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.entity.LivingEntity;
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
public class GoldTrait extends AbstractTrait {
    public GoldTrait() {
        super(EMCWorld.rl("gold"),0xffc00a);
    }

    @Override
    public Item item() {
        return Items.GOLD_INGOT;
    }

    @Override
    public float onAttackEntity(PlayerEntity player, LivingEntity living, float damage, ItemStack stack) {
        if(type == TraitType.TOOL){
            return MathUtils.isRandom(0.5) ? damage * 2 : damage / 2;
        }
        return damage;
    }

    @Override
    public float onHurt(PlayerEntity player, DamageSource source, float damage, ItemStack stack) {
        if(type == TraitType.ARMOR){
            return MathUtils.isRandom(0.5) ? damage * 2 : damage / 2;
        }
        return damage;
    }
}
