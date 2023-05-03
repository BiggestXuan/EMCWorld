package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/19
 */

import biggestxuan.emcworld.api.item.IKillCountItem;
import net.minecraft.item.Item;
import net.minecraft.item.TieredItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TieredItem.class)
public abstract class TieredItemMixin extends Item implements IKillCountItem {
    public TieredItemMixin(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }
}
