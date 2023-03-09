package biggestxuan.emcworld.common.utils.Sponsors;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/18
 */

import biggestxuan.emcworld.common.utils.Network.Network;

import java.util.*;

public class ModPackHelper {
    protected static List<String> getInfo(){
        StringBuilder builder = Network.getNetwork();
        if(builder == null){
            return new ArrayList<>();
        }
        String info = Objects.requireNonNull(builder.toString().trim());
        return new ArrayList<>(Arrays.asList(info.split(";")));
    }

    public static packInfo getPackInfo(){
        List<Sponsors> sponsorsList = new ArrayList<>();
        int version = 0;
        String versionName = "0.0";
        int i = 0;
        for(String info:getInfo()){
            i++;
            if(i == 1){
                version = Integer.parseInt(info);
                continue;
            }
            if(i == 2){
                versionName = info;
                continue;
            }
            int index = info.indexOf(",");
            String name = info.substring(0,index);
            int afterIndex = info.lastIndexOf(",");
            UUID uuid = UUID.fromString(info.substring(index+1,afterIndex));
            int level = Integer.parseInt(info.substring(afterIndex+1));
            sponsorsList.add(new Sponsors(name,uuid,level));
        }
        return new packInfo(version,versionName,sponsorsList);
    }

    public static class packInfo{
        private final int version;
        private final String versionName;
        private final List<Sponsors> sponsors;

        packInfo(int version,String versionName,List<Sponsors> sponsors){
            this.version = version;
            this.versionName = versionName;
            this.sponsors = sponsors;
        }

        public List<Sponsors> getSponsors() {
            return sponsors;
        }

        public int getVersion() {
            return version;
        }

        public String getVersionName() {
            return versionName;
        }
    }
}
