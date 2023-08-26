package biggestxuan.emcworld.api.event;

import biggestxuan.emcworld.api.EMCWorldSince;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/02
 */

@EMCWorldSince("0.9.0")
public class PlayerEatFoodEvent extends PlayerEvent {
    private final int level;
    private final float saturation;
    private final Food food;

    public PlayerEatFoodEvent(PlayerEntity player,int level,float saturation,Food food) {
        super(player);
        this.level = level;
        this.saturation = saturation;
        this.food = food;
    }

    public int getLevel() {
        return level;
    }

    public float getSaturation() {
        return saturation;
    }

    public Food getFood() {
        return food;
    }
}
