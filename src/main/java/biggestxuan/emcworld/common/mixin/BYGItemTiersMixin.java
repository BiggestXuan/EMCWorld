package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/24
 */

import biggestxuan.emcworld.common.config.ConfigManager;
import corgiaoc.byg.common.properties.items.itemtiers.BYGItemTiers;
import net.minecraft.item.IItemTier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BYGItemTiers.class)
public abstract class BYGItemTiersMixin implements IItemTier {

    @Shadow(remap = false)
    @Final
    private float attackDamage;

    /**
     * @author Biggest_Xuan
     * @reason Add 50% damage
     */
    @Override
    @Overwrite(remap = false)
    public float getAttackDamageBonus() {
        return ConfigManager.FREE_MODE.get() ? this.attackDamage : this.attackDamage * 1.5f;
    }
}
