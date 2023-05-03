package biggestxuan.emcworld.api.event;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/26
 */

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

/***
 * This event fired when player get professional exp.
 * You can modify exp amount.
 * This event is not cancelable.
 */
public class PlayerGetXPEvent extends PlayerEvent {
    private int amount;

    public PlayerGetXPEvent(PlayerEntity player,int amount){
        super(player);
        this.amount = amount;
    }

    public int getAmount(){
        return this.amount;
    }

    public void setAmount(int amt){
        this.amount = amt;
    }
}
