package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/15
 */

import biggestxuan.emcworld.common.utils.MathUtils;
import com.refinedmods.refinedstorage.api.network.IWirelessTransmitter;
import com.refinedmods.refinedstorage.apiimpl.network.node.NetworkNode;
import com.refinedmods.refinedstorage.apiimpl.network.node.WirelessTransmitterNetworkNode;
import com.refinedmods.refinedstorage.inventory.item.UpgradeItemHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.co.hexeption.rsinfinitybooster.utils.CardUtil;

@Mixin(value = WirelessTransmitterNetworkNode.class,priority = 980)
public abstract class WirelessTransmitterNetworkNodeMixin extends NetworkNode implements IWirelessTransmitter {
    @Shadow(remap = false)
    @Final
    private UpgradeItemHandler upgrades;

    protected WirelessTransmitterNetworkNodeMixin(World world, BlockPos pos) {
        super(world, pos);
    }

    @Inject(method = "getEnergyUsage", at = @At("HEAD"), cancellable = true,remap = false)
    public void cost(CallbackInfoReturnable<Integer> cir){
        if (CardUtil.isBothCards(upgrades)) {
            cir.setReturnValue(m(MathUtils.getInfinityCardCost() * 3));
        } else if (CardUtil.isInfinityCard(upgrades)) {
            cir.setReturnValue(m(MathUtils.getInfinityCardCost()));
        } else if (CardUtil.isDimensionCard(upgrades)) {
            cir.setReturnValue(m(MathUtils.getInfinityCardCost() * 2));
        }
    }

    private static int m(int value){
        return Math.min(24000,value);
    }
}
