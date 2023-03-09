package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/05
 */

import biggestxuan.emcworld.client.screen.CompassScreen.FindScreen;
import com.chaosthedude.explorerscompass.gui.StructureSearchEntry;
import com.chaosthedude.explorerscompass.gui.StructureSearchList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.list.ExtendedList;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StructureSearchEntry.class)
public abstract class StructureSearchEntryMixin extends ExtendedList.AbstractListEntry<StructureSearchEntry> {
    @Shadow (remap = false)
    @Final
    private StructureSearchList structuresList;

    @Shadow (remap = false)
    private long lastClickTime;

    /**
     * @author Biggest_Xuan
     * @reason .
     */
    @Overwrite (remap = false)
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        StructureSearchEntry entry = (StructureSearchEntry) (Object) this;
        if (button == 0) {
            structuresList.selectStructure(entry);
            if (Util.getMillis() -lastClickTime < 250) {
                Minecraft.getInstance().setScreen(new FindScreen(structuresList));
                return true;
            } else {
                lastClickTime = Util.getMillis();
                return false;
            }
        } else {
            return false;
        }
    }
}
