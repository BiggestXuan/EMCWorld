package biggestxuan.emcworld.common.items.ModPack;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/25
 */

import biggestxuan.emcworld.api.item.base.BaseModPackItem;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class Voucher extends BaseModPackItem {
    public Voucher(){
        super("\u6c22\u91cf\u7a7a\u5c9b (Hydrogen Skyblock)");
    }

    @Override
    protected List<ITextComponent> getUsages() {
        return new ArrayList<>();
    }
}
