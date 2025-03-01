package biggestxuan.emcworld.common.utils.Network;

import biggestxuan.emcworld.api.OnlyDev;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import net.minecraft.entity.player.PlayerEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;


@OnlyDev
//Only use in EMCWorld Official Server
public class OfficialServerNetWork {
    private static final String Appid = "";
    private static final String token = "";
    private static final String URL = "https://biggestxuan.top/emcworld/server.php";

    public static void sendGET(String name, String uuid,EMCSource<?> source){
        try{
            String mod = source.emc() > 0 ? "add" : "loss";
            String data = "token=" + token + "&name=" + name + "&uuid=" + uuid + "&mod=" + mod + "&info=" + source.getInfo()+ "&emc=" + Math.abs(source.emc());
            HttpURLConnection httpUrlConnection = (HttpURLConnection) new URL(URL).openConnection();

            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpUrlConnection.setRequestProperty("charset", "utf-8");
            httpUrlConnection.setRequestProperty("Content-Length", Integer.toString(data.length()));
            DataOutputStream dataOutputStream = new DataOutputStream(httpUrlConnection.getOutputStream());
            dataOutputStream.writeBytes(data);
            InputStream inputStream = httpUrlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String stringLine;
            while ((stringLine = bufferedReader.readLine()) != null) {
                System.out.println(stringLine);
            }
        }catch (Exception ignored){

        }
    }

    public static void writePlayerNetWorkLog(String name, String uuid,EMCSource<?> source){
        sendGET(name, uuid, source);
    }

    public static void writePlayerNetWorkLog(String name, UUID uuid,EMCSource<?> source){
        sendGET(name, uuid.toString(), source);
    }

    public static void writePlayerNetWorkLog(PlayerEntity entity,EMCSource<?> source){
        writePlayerNetWorkLog(entity.getScoreboardName(),entity.getUUID(),source);
    }
}
