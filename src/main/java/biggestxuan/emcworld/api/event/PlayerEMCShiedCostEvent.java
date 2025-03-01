package biggestxuan.emcworld.api.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/12
 */
public class PlayerEMCShiedCostEvent extends PlayerEvent {
    private float amt;
    private final DamageSource source;

    public PlayerEMCShiedCostEvent(PlayerEntity player, float amt, DamageSource source) {
        super(player);
        this.amt = amt;
        this.source = source;
    }

    public float getAmt() {
        return this.amt;
    }

    public DamageSource getSource() {
        return this.source;
    }

    public void setAmt(float amt) {
        this.amt = amt;
    }
}
