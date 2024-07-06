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
        super("氢量空岛 (Hydrogen Skyblock)");
    }

    @Override
    protected List<ITextComponent> getUsages() {
        return new ArrayList<>();
    }
}
