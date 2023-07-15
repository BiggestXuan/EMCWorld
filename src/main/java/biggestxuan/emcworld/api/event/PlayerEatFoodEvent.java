package biggestxuan.emcworld.api.event;

import biggestxuan.emcworld.api.EMCWorldSince;
import net.minecraft.entity.player.PlayerEntity;
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

    public PlayerEatFoodEvent(PlayerEntity player,int level,float saturation) {
        super(player);
        this.level = level;
        this.saturation = saturation;
    }

    public int getLevel() {
        return level;
    }

    public float getSaturation() {
        return saturation;
    }
}
