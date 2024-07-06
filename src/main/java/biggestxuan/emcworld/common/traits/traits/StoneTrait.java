package biggestxuan.emcworld.common.traits.traits;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.trait.AbstractTrait;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.SkillUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effects;

import static biggestxuan.emcworld.api.trait.TraitType.TOOL;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/11/20
 */

@EMCWorldSince("1.1.0")
public class StoneTrait extends AbstractTrait {
    public StoneTrait() {
        super(EMCWorld.rl("stone"),0xb2b5b8);
    }

    @Override
    public Item item() {
        return Items.STONE;
    }

    @Override
    public void onArmorTick(PlayerEntity player, ItemStack stack) {
        SkillUtils.addEffect(player, Effects.MOVEMENT_SLOWDOWN,10,0);
    }

    @Override
    public float onAttackEntity(PlayerEntity player, LivingEntity living, float damage, ItemStack stack) {
        if(type == TOOL){
            if(MathUtils.isRandom(0.3 - level * 0.02)){
                return 0;
            }
            return damage * (1 + level * 0.005F);
        }
        return damage;
    }
}
