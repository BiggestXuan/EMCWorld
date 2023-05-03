package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/15
 */

import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import top.theillusivec4.champions.common.ChampionEventsHandler;

@Mixin(ChampionEventsHandler.class)
public abstract class ChampionEventsHandlerMixin {
    /***
     * @author Biggest_Xuan
     * @reason cancel this event.
     */
    @SubscribeEvent
    @Overwrite(remap = false)
    public void onLivingDrops(LivingDropsEvent evt) {

    }
}
