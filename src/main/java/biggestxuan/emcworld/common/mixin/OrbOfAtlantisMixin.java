package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/31
 */

import com.mystic.atlantis.items.item.ItemBase;
import com.mystic.atlantis.items.item.OrbOfAtlantis;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(OrbOfAtlantis.class)
public abstract class OrbOfAtlantisMixin extends ItemBase {

    /**
     * @author Biggest_Xuan
     * @reason Fix a bug
     */
    @Overwrite
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        return new ActionResult(ActionResultType.SUCCESS, playerIn.getItemInHand(handIn));
    }
}
