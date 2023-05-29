package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/05
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.client.screen.CompassScreen.FindScreen;
import biggestxuan.emcworld.client.screen.CompassScreen.TeleportScreen;
import com.chaosthedude.explorerscompass.ExplorersCompass;
import com.chaosthedude.explorerscompass.gui.ExplorersCompassScreen;
import com.chaosthedude.explorerscompass.gui.StructureSearchList;
import com.chaosthedude.explorerscompass.gui.TransparentButton;
import com.chaosthedude.explorerscompass.sorting.ISorting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ExplorersCompassScreen.class)
public abstract class ExplorersCompassScreenMixin extends Screen {
    @Shadow(remap = false)
    private Button startSearchButton;

    @Shadow(remap = false)
    private StructureSearchList selectionList;

    @Shadow(remap = false)
    private Button sortByButton;

    @Shadow(remap = false)
    private ISorting sortingCategory;

    @Shadow(remap = false)
    private Button cancelButton;

    @Shadow(remap = false)
    private Button teleportButton;

    protected ExplorersCompassScreenMixin(ITextComponent p_i51108_1_) {
        super(p_i51108_1_);
    }

    /**
     * @author Biggest_Xuan
     * @reason .
     */
    @Overwrite(remap = false)
    private void setupButtons(){
        buttons.clear();
        startSearchButton = addButton(new TransparentButton(10, 40, 110, 20,EMCWorld.tc("string.explorerscompass.startSearch"), (onPress) -> {
            if (selectionList.hasSelection()) {
                Minecraft.getInstance().setScreen(new FindScreen(selectionList));
            }

        }));
        sortByButton = addButton(new TransparentButton(10, 65, 110, 20, (EMCWorld.tc("string.explorerscompass.sortBy")).append(EMCWorld.tc(": " + this.sortingCategory.getLocalizedName())), (onPress) -> {
            sortingCategory = sortingCategory.next();
            sortByButton.setMessage((EMCWorld.tc("string.explorerscompass.sortBy")).append(EMCWorld.tc(": " + sortingCategory.getLocalizedName())));
            selectionList.refreshList();
        }));
        cancelButton = addButton(new TransparentButton(10, this.height - 30, 110, 20, EMCWorld.tc("gui.cancel"), (onPress) -> {
            minecraft.setScreen(null);
        }));
        teleportButton = this.addButton(new TransparentButton(this.width - 120, 10, 110, 20, EMCWorld.tc("string.explorerscompass.teleport"), (onPress) -> {
            Minecraft.getInstance().setScreen(new TeleportScreen());
        }));
        startSearchButton.active = false;
        teleportButton.visible = ExplorersCompass.canTeleport;
    }
}
