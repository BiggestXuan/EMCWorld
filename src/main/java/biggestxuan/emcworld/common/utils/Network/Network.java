package biggestxuan.emcworld.common.utils.Network;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/17
 */

import net.minecraft.entity.player.PlayerEntity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Network {
    public static String getInfo(Object param){
        try{
            if(!(param instanceof Integer) && !(param instanceof PlayerEntity)){
                return "";
            }
            String u = "https://biggestxuan.top/emcworld/api.php?";
            if(param instanceof Integer){
                int index = (int) param;
                u += "version="+index;
            }
            if(param instanceof PlayerEntity){
                PlayerEntity player = (PlayerEntity) param;
                u += "name="+player.getScoreboardName()+"&uuid="+player.getStringUUID();
            }
            System.out.println(u);
            URL url = new URL(u);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String s = bf.readLine();
            if(s == null){
                return "0,0,0,0,0";
            }
            System.out.println(s);
            return s;
        }catch (Exception ignored){

        }
        return "0,0,0,0,0";
    }

    public static void main(String[] args) {
        System.out.println(getInfo(2));
    }
}
