package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/24
 */

import biggestxuan.emcworld.common.utils.UnknownPouchUtils;
import moze_intel.projecte.gameObjs.items.AlchemicalBag;
import moze_intel.projecte.gameObjs.items.ItemPE;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(AlchemicalBag.class)
public abstract class AlchemicalBagMixin extends ItemPE {
    @Shadow(remap = false)
    @Final
    public DyeColor color;

    public AlchemicalBagMixin(Properties props) {
        super(props);
    }

    @Inject(method = "use",at = @At("HEAD"),cancellable = true)
    public void addUse(World world, PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult<ItemStack>> cir) {
        if(!player.level.isClientSide && player.isShiftKeyDown()){
            Vector3d pos = player.position();
            List<ItemStack> list = UnknownPouchUtils.receiveItem(player);
            for(ItemStack stack:list){
                player.level.addFreshEntity(new ItemEntity(player.level,pos.x,pos.y,pos.z,stack));
            }
            cir.setReturnValue(ActionResult.pass(new ItemStack(this)));
        }
    }
}
