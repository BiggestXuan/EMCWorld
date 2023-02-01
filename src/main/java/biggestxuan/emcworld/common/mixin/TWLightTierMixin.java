package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/28
 */

import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.item.IItemTier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import twilightforest.enums.TwilightItemTier;

@Mixin(TwilightItemTier.class)
public abstract class TWLightTierMixin implements IItemTier {
    @Shadow(remap = false)
    @Final
    private float attackDamage;

    @Shadow(remap = false)
    @Final
    private int harvestLevel;

    @Shadow(remap = false)
    @Final
    private int enchantability;

    @Override
    public float getAttackDamageBonus(){
        return ConfigManager.FREE_MODE.get() ? attackDamage : attackDamage * Math.max(1,harvestLevel-1) * (1+enchantability/15f);
    }
}
