package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/28
 */

import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.item.IItemTier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import quek.undergarden.registry.UGItemTiers;

@Mixin(UGItemTiers.class)
public abstract class UGTierMixin implements IItemTier {
    @Shadow(remap = false)
    @Final
    private float damage;

    @Shadow(remap = false)
    @Final
    private int level;

    @Shadow(remap = false)
    @Final
    private int enchantmentValue;

    @Override
    public float getAttackDamageBonus(){
        return ConfigManager.FREE_MODE.get() ? damage : damage * level + enchantmentValue / 3.5f;
    }
}
