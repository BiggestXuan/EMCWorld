package biggestxuan.emcworld.api.event;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/26
 */

import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * This event fired when player cost emc.
 * Such as attacking mob,hurt damage,use hoe etc.
 * Use transmutation table don't fire this event.
 * This event is cancelable.
 */
@Cancelable
public class PlayerCostEMCEvent extends PlayerEvent {
    private EMCSource<?> source;

    public PlayerCostEMCEvent(PlayerEntity player,EMCSource<?> source){
        super(player);
        this.source = source;
    }

    public void setEMC(long emc){
        source.setEmc(emc);
    }

    public long getEMC(){
        return this.source.emc();
    }

    public void setSource(EMCSource<?> source){
        this.source = source;
    }

    public EMCSource<?> getSource() {
        return source;
    }
}
