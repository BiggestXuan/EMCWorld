package biggestxuan.emcworld.common.items.Equipment.Weapon.LuckyItem;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/27
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.base.BaseDifficultyItem;

public class LuckyItem extends BaseDifficultyItem implements ILuckyItem {
    private final float lucky;

    public LuckyItem(float lucky,float difficulty){
        super(difficulty,false,EMCWorld.tc("tooltip.emcworld.lucky_item",String.format("%.2f",lucky)));
        this.lucky = lucky;
    }

    @Override
    public float getLucky() {
        return lucky;
    }
}
