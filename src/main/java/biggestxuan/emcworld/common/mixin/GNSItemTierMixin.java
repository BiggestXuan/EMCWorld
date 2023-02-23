package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/23
 */

import biggestxuan.emcworld.common.config.ConfigManager;
import com.legacy.goodnightsleep.item.GNSItemTier;
import net.minecraft.item.IItemTier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GNSItemTier.class)
public abstract class GNSItemTierMixin implements IItemTier {

    @Shadow(remap = false)
    @Final
    private float attackDamage;

    @Shadow(remap = false)
    @Final
    private int harvestLevel;

    @Shadow(remap = false)
    @Final
    private int enchantability;

    /**
     * @author Biggest_Xuan
     * @reason NULL
     */
    @Override
    @Overwrite(remap = false)
    public float getAttackDamageBonus(){
        return ConfigManager.FREE_MODE.get() ? attackDamage : attackDamage * Math.max(1,harvestLevel-1) * (1+enchantability/30f);
    }
}
