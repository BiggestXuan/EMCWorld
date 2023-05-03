package biggestxuan.emcworld.api.event;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/26
 */

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

/***
 * This event fired when player cost emc.
 * Such as attacking mob,hurt damage,use hoe etc.
 * Use transmutation table don't fire this event.
 * This event is not cancelable.
 */
public class PlayerCostEMCEvent extends PlayerEvent {
    private final long emc;

    public PlayerCostEMCEvent(PlayerEntity player,long emc){
        super(player);
        this.emc = emc;
    }

    public long getEMC(){
        return this.emc;
    }
}
