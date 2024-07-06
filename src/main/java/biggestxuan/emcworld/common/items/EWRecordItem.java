package biggestxuan.emcworld.common.items;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.IFormattableTextComponent;

import java.util.function.Supplier;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/08/21
 */

@EMCWorldSince("1.0.3")
public class EWRecordItem extends MusicDiscItem {
    private final boolean isGeng;
    public EWRecordItem(Supplier<SoundEvent> event,boolean isGeng) {
        super(1, event,new Properties().tab(EWCreativeTabs.EW_CREATIVE_TAB));
        this.isGeng = isGeng;
    }

    @Override
    public IFormattableTextComponent getDisplayName() {
        return EMCWorld.tc(this.getDescriptionId());
    }

    @Override
    public ActionResultType useOn(ItemUseContext p_195939_1_) {
        if(!ConfigManager.GENG.get() && this.isGeng){
            return ActionResultType.FAIL;
        }
        return super.useOn(p_195939_1_);
    }
}
