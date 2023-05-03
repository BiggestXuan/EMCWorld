package biggestxuan.emcworld.common.items.Equipment;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/11
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.items.EWItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;

import javax.annotation.Nonnull;

public class BaseWeaponGemItem extends EWItem{
    private final int index;
    private final int color;

    public BaseWeaponGemItem(gem gem){
        this.index = gem.index;
        this.color = gem.color;
    }

    @Override
    public double getEMCCostRate(){
        return 0;
    }

    public int getIndex() {
        return index;
    }

    @Nonnull
    @Override
    public ITextComponent getName(@Nonnull ItemStack p_200295_1_) {
        String name = this.toString();
        return EMCWorld.tc("item.emcworld."+name).setStyle(Style.EMPTY.withColor(Color.fromRgb(color)));
    }

    public enum gem{
        BLOOD(10,0xbc3b29),
        NATURE(20,0x55c024),
        LAKE(30,0x2470c0),
        ABYSS(40,0x8f24c0)
        ;

        private final int index;
        private final int color;

        gem(int index,int color){
            this.index = index;
            this.color = color;
        }

        public int getColor(){
            return this.color;
        }
    }
}
