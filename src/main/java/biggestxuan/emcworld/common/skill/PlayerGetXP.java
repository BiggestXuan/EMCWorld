package biggestxuan.emcworld.common.skill;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/26
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.event.PlayerModifyEMCEvent;
import biggestxuan.emcworld.api.event.PlayerGetXPEvent;
import biggestxuan.emcworld.common.compact.CraftTweaker.CrTConfig;
import biggestxuan.emcworld.common.traits.ITrait;
import biggestxuan.emcworld.common.traits.TraitType;
import biggestxuan.emcworld.common.traits.TraitUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerGetXP {

    @SubscribeEvent
    public static void playerCostEMCEvent(PlayerModifyEMCEvent event){
        PlayerEntity player = event.getPlayer();
        long cost = event.getEMC();
        IPlayerSkillCapability cap = player.getCapability(EMCWorldCapability.PLAYER_LEVEL).orElseThrow(NullPointerException::new);
        if(cap.getLevel() < cap.getMaxLevel() && cost < 0){
            int base = Math.negateExact((int) Math.round(600 * Math.pow(2, CrTConfig.getWorldDifficulty()/2)));
            int xp = (int) (cost / base); //cost,base are negative.
            if(-cost < -base){
                cap.setCostEMC(-cost);
                if(cap.getCostEMC() >= -base){
                    xp = 1;
                    cap.setCostEMC(cap.getCostEMC()+base);
                }
            }
            PlayerGetXPEvent postEvent = new PlayerGetXPEvent(player,xp);
            MinecraftForge.EVENT_BUS.post(postEvent);
            int value = postEvent.getAmount();
            cap.addXP(value);
        }
        ItemStack stack = player.getMainHandItem();
        for(ITrait trait : TraitUtils.getStackTraits(stack)){
            if(trait.getTraitType() != TraitType.ARMOR){
                trait.onEMCModify(player,event, stack);
            }
        }
        for(ItemStack s : player.inventory.armor){
            for(ITrait trait : TraitUtils.getStackTraits(s)){
                if(trait.getTraitType() == TraitType.ARMOR){
                    trait.onEMCModify(player,event,s);
                }
            }
        }
    }

    @SubscribeEvent
    public static void playerGetXpEvent(PlayerGetXPEvent event){
        PlayerEntity player = event.getPlayer();
        IPlayerSkillCapability cap = player.getCapability(EMCWorldCapability.PLAYER_LEVEL).orElseThrow(NullPointerException::new);
        if(cap.getSkills()[20] !=0){
            event.setAmount((int) (event.getAmount() * (1+cap.getSkills()[20]/10000d)));
        }
        if(cap.getProfession() == 4 && cap.getModify() == 2 && cap.getSkills()[36] != 0){
            event.setAmount(event.getAmount() * 2);
        }
    }
}
