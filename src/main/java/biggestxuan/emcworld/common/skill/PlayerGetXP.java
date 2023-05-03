package biggestxuan.emcworld.common.skill;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/26
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.event.PlayerCostEMCEvent;
import biggestxuan.emcworld.api.event.PlayerGetXPEvent;
import biggestxuan.emcworld.common.compact.CraftTweaker.CrTConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerGetXP {

    @SubscribeEvent
    public static void playerCostEMCEvent(PlayerCostEMCEvent event){
        PlayerEntity player = event.getPlayer();
        long cost = event.getEMC();
        IPlayerSkillCapability cap = player.getCapability(EMCWorldCapability.PLAYER_LEVEL).orElseThrow(NullPointerException::new);
        if(cap.getLevel() < cap.getMaxLevel()){
            int base = Math.negateExact((int) Math.round(600 * Math.pow(2, CrTConfig.getWorldDifficulty()/2)));
            PlayerGetXPEvent postEvent = new PlayerGetXPEvent(player,(int) (cost / base));
            MinecraftForge.EVENT_BUS.post(postEvent);
            int value = postEvent.getAmount();
            cap.addXP(value);
        }
    }

    @SubscribeEvent
    public static void playerGetXpEvent(PlayerGetXPEvent event){
        PlayerEntity player = event.getPlayer();
        IPlayerSkillCapability cap = player.getCapability(EMCWorldCapability.PLAYER_LEVEL).orElseThrow(NullPointerException::new);
        if(cap.getSkills()[20] !=0){
            event.setAmount((int) (event.getAmount() * (1+cap.getSkills()[20]/10000d)));
        }
    }
}
