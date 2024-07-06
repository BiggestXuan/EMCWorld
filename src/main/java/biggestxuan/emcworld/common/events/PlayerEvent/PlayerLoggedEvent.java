package biggestxuan.emcworld.common.events.PlayerEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/03
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.compact.ScalingHealth.DifficultyHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.network.PacketHandler;
import biggestxuan.emcworld.common.network.toServer.OfflinePacket;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.*;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.Network.Network;
import dev.ftb.mods.ftbquests.FTBQuests;
import dev.ftb.mods.ftbquests.quest.QuestFile;
import dev.ftb.mods.ftbquests.quest.ServerQuestFile;
import dev.ftb.mods.ftbquests.quest.TeamData;
import hellfirepvp.astralsorcery.common.data.research.ResearchManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vazkii.patchouli.common.item.PatchouliItems;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static biggestxuan.emcworld.EMCWorld.tc;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerLoggedEvent {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void playerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event){
        PlayerEntity player = event.getPlayer();
        if(player.level.isClientSide) return;
        MinecraftServer server = player.getServer();
        if(server == null) return;
        if(server.getGameRules().getBoolean(GameRules.RULE_SENDCOMMANDFEEDBACK)){
            server.getCommands().performCommand(server.createCommandSourceStack(),"gamerule sendCommandFeedback false");
        }
        if(!server.getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)){
            server.getCommands().performCommand(server.createCommandSourceStack(),"gamerule keepInventory true");
        }
        String name = player.getName().getString();
        UUID uuid = player.getUUID();
        LazyOptional<IUtilCapability> sponsorCap = player.getCapability(EMCWorldCapability.UTIL);
        sponsorCap.ifPresent(cp -> cp.setOnline(server.usesAuthentication()));
        ResearchManager.setTomeReceived(player);
        IUtilCapability c = sponsorCap.orElseThrow(NullPointerException::new);
        c.clearPlayTime();
        //PlayTimeUtils.sendRestMessage(player);  //test
        int[] level = MathUtils.StringArray2IntArray(Network.getInfo(player).split(","));
        sendHappyBirthday(server,player);
        sponsorCap.ifPresent(sp -> {
            if(server.usesAuthentication()){
                sp.setLevel(level);
            }else{
                sp.setLevel(new int[]{0});
                giveOfflineWarn(player);
            }
        });
        if(c.getDisplayIndex() == 0 && c.getLevel()[0] != 0){
            c.setDisplayIndex(c.getLevel()[c.getLevel().length-1]);
        }
        int log = c.getLogAmount();
        c.setLogAmount(log+1);
        if(!GameStageManager.hasStage(player,"start")){
            GameStageManager.addStage(player,"start");
        }
        if(log + 1 == 1){
            player.inventory.clearContent();
            ItemStack book = new ItemStack(PatchouliItems.book);
            book.getOrCreateTag().putString("patchouli:book","emcworld:guide");
            //player.addItem(book);
            int emc = (int) (90000 / MathUtils.difficultyLoss());
            EMCHelper.modifyPlayerEMC(player,new EMCSource.QuestCompletedEMCSource(emc,player,null,0,"start","start"),false);
            DifficultyHelper.addPlayerDifficulty(player,1.5*ConfigManager.DIFFICULTY.get());
        }
        if(ConfigManager.FREE_MODE.get()){
            TeamData data = ServerQuestFile.INSTANCE.getData(player);
            if(data != null){
                data.setLocked(true);
            }
        }
        CalendarUtils instance = CalendarUtils.INSTANCE;
        int year = instance.getYear();
        if(instance.isSpringFestival()){
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
        String[] ver = Network.getInfo(0).split(",");
        if(getInt(ver[0]) == 0){
            Message.sendMessage(player,tc("message.update_fail"));
        }else if(getInt(ver[0]) > EMCWorld.ModPackVersion){
            Message.sendMessage(player, tc("message.update_need",ver[1]));
        }else{
            Message.sendMessage(player,tc("message.update_none"));
        }
        server.setDifficulty(Difficulty.HARD,true);
        server.setDifficultyLocked(ConfigManager.SUNDRY_LOCK_DIFFICULTY.get());
        if((log+1) % 100 == 0 && ConfigManager.SPONSOR_INFO.get() && c.getLevel()[0] == 0){
            Message.sendMessage(player,EMCWorld.tc("message.log.sponsor",log+1));
        }
        sponsorCap.ifPresent((cap) -> {
            int sponsorLevel = cap.getDisplayIndex();
            if(instance.isAprilFoolsDay()){
                Message.sendMessage(player, tc("message.welcome.dev",name));
                return;
            }
            if(!cap.getOnline()){
                return;
            }
            switch (sponsorLevel){
                case -1:
                    Message.sendMessage(player, tc("message.welcome.spr",name));
                    break;
                case 1:
                    Message.sendMessage(player, tc("message.welcome.normal",name));
                    break;
                case 2:
                    Message.sendMessageToThisWorldPlayer(player, tc("message.welcome.advanced",name));
                    break;
                case 3:
                    Message.sendMessageToAllPlayer(player, tc("message.welcome.highest",name));
                    break;
                case 4:
                    Message.sendMessageToAllPlayer(player, tc("message.welcome.mascot",name));
                    break;
                case 5:
                    Message.sendMessageToAllPlayer(player, tc("message.welcome.dev",name));
                    break;
                case 6:
                    Message.sendMessageToAllPlayer(player,tc("message.welcome.tulye",name));
                    break;
                case 7:
                    Message.sendMessageToAllPlayer(player,tc("message.welcome.cmzx",name));
                    break;
                default:
                    Message.sendMessage(player, tc("message.welcome.default",name));
                    break;
            }
        });
        if(ConfigManager.SUNDRY_ANNOUNCEMENT.get()){
            try{
                String announcement = Network.getInfo(15);
                if(!announcement.contains("null")){
                    String[] ann = announcement.split(";");
                    Message.sendMessage(player,EMCWorld.tc(ann[0]).setStyle(Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,ann[1])).withColor(TextFormatting.AQUA).withBold(true).withItalic(true)));
                }
            }catch (Exception ignored){

            }

        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void playerLoggedInLowEvent(PlayerEvent.PlayerLoggedInEvent event){
        PlayerEntity player = event.getPlayer();
        if(player.level.isClientSide){
            EMCWorld.LOGGER.info("test");
            if(EMCWorld.isOffline){
                PacketHandler.sendToServer(new OfflinePacket());
                giveOfflineWarn(player);
            }
            QuestFile file = FTBQuests.PROXY.getClientQuestFile();
            if(file != null){
                file.getAllTasks().forEach(t -> {
                    EMCWorld.LOGGER.info("1"+t.quest.getTitle().getString());
                    if(t.quest.getDependants().size() >= 1){
                        t.quest.getDependants().forEach(e -> {
                            EMCWorld.LOGGER.info("2"+e.getTitle().getString());
                        });
                    }
                });
            }
            QuestFile file1 = FTBQuests.PROXY.getQuestFile(false);
            try{
                if(file1 != null){
                    file1.getAllTasks().forEach(t -> {
                        EMCWorld.LOGGER.info(t.quest.getTitle().getString());
                        if(t.quest.getDependants().size() >= 1){
                            t.quest.getDependants().forEach(e -> {
                                EMCWorld.LOGGER.info(e.getTitle().getString());
                            });
                        }
                    });
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }

    private static void sendHappyBirthday(MinecraftServer server,PlayerEntity player){
        try{
            IUtilCapability utilCapability = EMCWorldAPI.getInstance().getUtilCapability(player);
            if(!utilCapability.getOnline()) return;
            BirthdayUtils utils = new BirthdayUtils(player);
            List<TranslationTextComponent> list = new ArrayList<>();
            for (int i = 1; i <= 4; i++) {
                list.add(EMCWorld.tc("message.birthday.a"+i));
            }
            if(utils.HappyBirthday()){
                for(TranslationTextComponent text:list){
                    Message.sendMessage(player,text);
                }
            }
        }catch (NullPointerException e){
            EMCWorld.LOGGER.error("Can not get player's capability!");
        }
    }

    private static void giveOfflineWarn(PlayerEntity player){
        player.getCapability(EMCWorldCapability.UTIL).ifPresent(c -> {
            if(!c.getOnline() && ConfigManager.OFFLINE_WARN.get()){
                for (int i = 1; i < 5; i++) {
                    Message.sendMessage(player,EMCWorld.tc("message.offline."+i));
                }
            }
        });
    }

    private static int getInt(String r){
        try{
            return Integer.parseInt(r);
        }catch (Exception e){
            return 0;
        }
    }
}
