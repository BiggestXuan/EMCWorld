package biggestxuan.emcworld.common.utils.Network;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/17
 */

import biggestxuan.emcworld.common.utils.Sponsors.ModPackHelper;
import biggestxuan.emcworld.common.utils.Sponsors.Sponsors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

public class Network {
    public static StringBuilder getNetwork(){
        try{
            URL url = new URL("https://biggestxuan.top/emcworld/main.txt");
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder out = new StringBuilder();
            int count = 0;
            while (bf.readLine() != null) {
                String line = bf.readLine();
                if(count <2){
                    count++;
                    out.append(line);
                    continue;
                }
                out.append(line);
            }
            bf.close();
            return out;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        for(Sponsors sp: ModPackHelper.getPackInfo().getSponsors()){
            System.out.println(sp);
        }
        System.out.println(ModPackHelper.getPackInfo().getVersionName());
        System.out.println(UUID.randomUUID());
    }
}
