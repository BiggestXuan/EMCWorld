package biggestxuan.emcworld.common.events.PlayerEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/25
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.IFakeEMCItem;
import biggestxuan.emcworld.api.trait.ITrait;
import biggestxuan.emcworld.api.trait.TraitType;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.traits.TraitUtils;
import moze_intel.projecte.api.ItemInfo;
import moze_intel.projecte.api.event.PlayerAttemptLearnEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerGetKnowledgeEvent {
    @SubscribeEvent
    public static void playerGetKnowledgeEvent(PlayerAttemptLearnEvent event){
        ItemInfo info = event.getSourceInfo();
        PlayerEntity player = event.getPlayer();
        ItemStack stack = player.getMainHandItem();
        for(ITrait trait : TraitUtils.getStackTraits(stack)){
            if(trait.getTraitType() != TraitType.ARMOR){
                trait.onGetKnowledge(player,stack, event);
            }
        }
        for(ItemStack s : player.inventory.armor){
            for(ITrait trait : TraitUtils.getStackTraits(s)){
                if(trait.getTraitType() == TraitType.ARMOR){
                    trait.onGetKnowledge(player,s,event);
                }
            }
        }
        if(info.getItem().equals(EWItems.VOUCHER.get())){
            event.setCanceled(true);
        }
        if(info.getItem() instanceof IFakeEMCItem){
            IFakeEMCItem item = (IFakeEMCItem) info.getItem();
            item.doSomething(event.getPlayer(),info.createStack());
            event.setCanceled(true);
        }

    }
}
