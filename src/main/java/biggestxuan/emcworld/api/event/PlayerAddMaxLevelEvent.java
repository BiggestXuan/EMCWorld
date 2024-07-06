package biggestxuan.emcworld.api.event;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/07
 */

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * This event fired when player use item to add max professional level.
 * It is not cancelable.
 */

public class PlayerAddMaxLevelEvent extends PlayerEvent {

    private final int originalLevel;
    private final int nowLevel;

    public PlayerAddMaxLevelEvent(PlayerEntity player,int originalLevel,int nowLevel){
        super(player);
        this.originalLevel = originalLevel;
        this.nowLevel = nowLevel;
    }

    public int getNowLevel() {
        return nowLevel;
    }

    public int getOriginalLevel(){
        return originalLevel;
    }
}
