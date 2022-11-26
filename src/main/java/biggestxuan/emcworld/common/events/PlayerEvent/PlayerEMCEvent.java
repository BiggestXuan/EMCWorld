package biggestxuan.emcworld.common.events.PlayerEvent;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/26
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.recipes.EMCStageLimit;
import moze_intel.projecte.api.event.PlayerAttemptCondenserSetEvent;
import moze_intel.projecte.api.event.PlayerAttemptLearnEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEMCEvent {
    public static final boolean prevent = ConfigManager.FREE_MODE.get();
    @SubscribeEvent
    public static void playerTryToGetKnowledgeEvent(PlayerAttemptLearnEvent event){
        PlayerEntity player = event.getPlayer();
        if(player.level.isClientSide || prevent) return;
        for(EMCStageLimit recipe:player.level.getRecipeManager().getAllRecipesFor(EMCStageLimit.EMCStageLimitType.INSTANCE)){
            if(recipe.getItem().equals(event.getSourceInfo().getItem()) && !GameStageManager.hasStage(player,recipe.getStage())){
                event.setCanceled(true);
            }
        }
    }
    @SubscribeEvent
    public static void playerSetCondenserEvent(PlayerAttemptCondenserSetEvent event){
        PlayerEntity player = event.getPlayer();
        if(player.level.isClientSide || prevent) return;
        for(EMCStageLimit recipe:player.level.getRecipeManager().getAllRecipesFor(EMCStageLimit.EMCStageLimitType.INSTANCE)){
            if(recipe.getItem().equals(event.getSourceInfo().getItem()) && !GameStageManager.hasStage(player,recipe.getStage())){
                event.setCanceled(true);
            }
        }
    }
}
