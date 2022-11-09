package biggestxuan.emcworld.common.events.PlayerEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/03
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.client.Message;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.CalendarUtils;
import biggestxuan.emcworld.common.utils.Sponsors.ModPackHelper;
import biggestxuan.emcworld.common.utils.Sponsors.Sponsors;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.Difficulty;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

import static biggestxuan.emcworld.EMCWorld.tc;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerLoggedEvent {
    @SubscribeEvent
    public static void playerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event){
        PlayerEntity player = event.getPlayer();
        String name = player.getName().getString();
        UUID uuid = player.getUUID();
        LazyOptional<IUtilCapability> sponsorCap = player.getCapability(EMCWorldCapability.UTIL);
        if(!GameStageManager.hasStage(player,"Start")){
            GameStageManager.addStage(player,"Start");
            GameStageManager.syncStage(player);
        }
        ModPackHelper.packInfo info = ModPackHelper.getPackInfo();
        for(Sponsors sp:info.getSponsors()){
            if(name.equals(sp.getPlayerName()) && uuid.equals(sp.getPlayerUUID())){
                sponsorCap.ifPresent((cap) -> {
                    cap.setLevel(sp.getSponsorLevel());
                });
                break;
            }
            sponsorCap.ifPresent((cap)-> {
                cap.setLevel(0);
            });
        }
        IUtilCapability c = sponsorCap.orElseThrow(NullPointerException::new);
        int log = c.getLogAmount();
        c.setLogAmount(log+1);
        CalendarUtils instance = CalendarUtils.INSTANCE;
        int year = instance.getYear();
        if(instance.isNewYear()){
            Message.sendMessage(player,tc("message.festival.spring"));
            if(c.getF1() != year){
                player.addItem(new ItemStack(EWItems.YEARCAKE.get(),8));
            }
            c.setF1(year);
        }
        if(instance.isLanternFestival()){
            Message.sendMessage(player,tc("message.festival.lantern"));
            if(c.getF2() != year){
                player.addItem(new ItemStack(EWItems.TANGYUAN.get(),8));
            }
            c.setF2(year);
        }
        if(instance.isDragonBoatFestival()){
            Message.sendMessage(player,tc("message.festival.dragon_boat"));
            if(c.getF3() != year){
                player.addItem(new ItemStack(EWItems.ZONGZI.get(),8));
            }
            c.setF3(year);
        }
        if(instance.isMidAutumnFestival()){
            Message.sendMessage(player,tc("message.festival.mid_autumn"));
            if(c.getF4() != year){
                player.addItem(new ItemStack(EWItems.MOONCAKE.get(),8));
            }
            c.setF4(year);
        }
        if(instance.isNewYear()){
            Message.sendMessage(player,tc("message.festival.new_year"));
        }
        if(instance.isWorkersDay()){
            Message.sendMessage(player,tc("message.festival.workers"));
        }
        if(instance.isNationalDay().isNationalDay()){
            Message.sendMessage(player,tc("message.festival.national"));
        }
        if(instance.isChristmasDay()){
            Message.sendMessage(player,tc("message.festival.christmas"));
        }
        if(info.getVersion() == 0){
            Message.sendMessage(player,tc("message.update_fail"));
        }else if(info.getVersion() > EMCWorld.ModPackVersion){
            Message.sendMessage(player, tc("message.update_need",info.getVersionName()));
        }else{
            Message.sendMessage(player,tc("message.update_none"));
        }
        sponsorCap.ifPresent((cap) -> {
            int sponsorLevel = cap.getLevel();
            if(instance.isAprilFoolsDay()){
                Message.sendMessage(player, tc("message.welcome.dev",name));
                return;
            }
            switch (sponsorLevel){
                case 1:
                    Message.sendMessage(player, tc("message.welcome.normal",name));
                    break;
                case 2:
                    Message.sendMessageToThisWorldPlayer(player, tc("message.welcome.advanced",name));
                    break;
                case 3:
                    Message.sendMessageToAllPlayer(player, tc("message.welcome.highest",name));
                    break;
                case 5:
                    Message.sendMessageToAllPlayer(player, tc("message.welcome.dev",name));
                    break;
                default:
                    Message.sendMessage(player, tc("message.welcome.default",name));
            }
        });
        MinecraftServer server = player.getServer();
        if(server == null) return;
        server.setDifficulty(Difficulty.HARD,true);
        server.setDifficultyLocked(true);
    }
}
