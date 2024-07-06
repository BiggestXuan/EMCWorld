package biggestxuan.emcworld.common.mixin;

import io.github.noeppi_noeppi.libx.inventory.BaseItemStackHandler;
import io.github.noeppi_noeppi.libx.mod.ModX;
import io.github.noeppi_noeppi.libx.mod.registration.BlockTE;
import mythicbotany.rune.BlockRuneHolder;
import mythicbotany.rune.TileRuneHolder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/06/18
 */

@Mixin(BlockRuneHolder.class)
public abstract class BlockRuneHolderMixin<T extends TileRuneHolder> extends BlockTE<T> {
    public BlockRuneHolderMixin(ModX mod, Class<T> teClass, Properties properties) {
        super(mod, teClass, properties);
    }

    @Redirect(
            method = "use",
            at = @At(value = "INVOKE",target = "Lio/github/noeppi_noeppi/libx/inventory/BaseItemStackHandler;isItemValid(ILnet/minecraft/item/ItemStack;)Z",remap = false)
    )
    public boolean _redirect(BaseItemStackHandler instance, int slot, ItemStack stack){
        return !stack.getItem().equals(Items.AIR);
    }
}
