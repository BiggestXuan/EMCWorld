package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/27
 */

import biggestxuan.emcworld.common.config.ConfigManager;
import fr.factionbedrock.aerialhell.Item.Tools.AerialHellSwordItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AerialHellSwordItem.class)
public abstract class AerialHellSwordItemMixin extends SwordItem {
    public AerialHellSwordItemMixin(IItemTier p_i48460_1_, int p_i48460_2_, float p_i48460_3_, Properties p_i48460_4_) {
        super(p_i48460_1_, p_i48460_2_, p_i48460_3_, p_i48460_4_);
    }

    @Override
    public float getDamage() {
        return ConfigManager.FREE_MODE.get() ? super.getDamage() :(0.75F * getTier().getLevel() + 1) * getTier().getAttackDamageBonus();
    }
}
