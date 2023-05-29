package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/23
 */

import biggestxuan.emcworld.common.utils.MathUtils;
import net.blay09.mods.waystones.api.IWaystone;
import net.blay09.mods.waystones.client.gui.screen.WaystoneSelectionScreenBase;
import net.blay09.mods.waystones.client.gui.widget.WaystoneButton;
import net.blay09.mods.waystones.container.WaystoneSelectionContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(WaystoneSelectionScreenBase.class)
public abstract class WayStoneSelectionScreenBaseMixin extends ContainerScreen<WaystoneSelectionContainer> {
    @Shadow(remap = false)
    protected abstract void onWaystoneSelected(IWaystone waystone);

    public WayStoneSelectionScreenBaseMixin(WaystoneSelectionContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
    }

    /**
     * @author Biggest_Xuan
     * @reason xp -> EMC
     */
    @Overwrite(remap = false)
    private WaystoneButton createWaystoneButton(int y, IWaystone waystone){
        IWaystone waystoneFrom = this.menu.getWaystoneFrom();
        PlayerEntity player = Minecraft.getInstance().player;
        int cost = MathUtils.getTPEMCCost(player,waystone,waystoneFrom);
        WaystoneButton btnWaystone = new WaystoneButton(width / 2 - 100, y, waystone, cost, button -> onWaystoneSelected(waystone));
        if (waystoneFrom != null && waystone.getWaystoneUid().equals(waystoneFrom.getWaystoneUid())) {
            btnWaystone.active = false;
        }
        return btnWaystone;
    }
}
