package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/24
 */

import L_Ender.cataclysm.items.ModItemTier;
import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.item.IItemTier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ModItemTier.class)
public abstract class ModItemTierMixin implements IItemTier {
    @Shadow(remap = false)
    @Final
    private float attackDamage;

    /**
     * @author Biggest_Xuan
     * @reason Add 100% damage
     */
    @Override
    @Overwrite(remap = false)
    public float getAttackDamageBonus() {
        return ConfigManager.FREE_MODE.get() ? attackDamage : attackDamage * 2f;
    }
}
