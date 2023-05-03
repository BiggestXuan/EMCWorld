package biggestxuan.emcworld.common.utils.EMCLog;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/07
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.exception.EMCWorldCommonException;
import biggestxuan.emcworld.common.utils.CalendarUtils;
import codechicken.lib.util.ServerUtils;
import net.minecraft.entity.player.PlayerEntity;

import java.io.*;
import java.nio.charset.StandardCharsets;

// Only server side!
public class EMCWriter {
    private static final File serverDiv = new File(ServerUtils.getSaveDirectory(),"EMCWorldLog");

    public static void Init(){
        if(!serverDiv.exists()){
            serverDiv.mkdirs();
        }
    }

    public static void WriteEMCLog(PlayerEntity player,EMCSource<?> source) {
        try{
            if(player == null || source.emc == 0 || source.amt == -1){
                return;
            }
            File file = getPlayerFile(player);
            FileWriter writer = new FileWriter(file,StandardCharsets.UTF_8,true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(getTime()+" "+source.getInfo()+"\n");
            bufferedWriter.close();
        }catch (IOException e){
            EMCWorld.LOGGER.error("Can not write emc log!");
        }
    }

    private static File getPlayerFile(PlayerEntity player){
        try{
            File f = new File(serverDiv,player.getScoreboardName()+"_"+player.getStringUUID()+".log");
            if(!f.exists()){
                f.createNewFile();
            }
            return f;
        }catch (IOException e){
            EMCWorld.LOGGER.error("Can not create player's emc log file!");
        }
        throw new EMCWorldCommonException();
    }

    private static String getTime(){
        CalendarUtils calendar = new CalendarUtils();
        int year = calendar.getYear();
        int month = calendar.getMonth();
        int day = calendar.getDay();
        String hour = calendar.getHour() <= 9 ? "0"+calendar.getHour() : ""+calendar.getHour();
        String mi = calendar.getMinute() <= 9 ? "0"+calendar.getMinute() : ""+calendar.getMinute();
        String se = calendar.getSecond() <= 9 ? "0"+calendar.getSecond() : ""+calendar.getSecond();
        return "["+year+"/"+month+"/"+day+" "+hour+":"+mi+":"+se+"]";
    }
}
