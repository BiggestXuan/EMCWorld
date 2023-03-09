package biggestxuan.emcworld.common.utils.Sponsors;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/17
 */

import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.common.utils.EMCWorldPlayer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class Sponsors extends EMCWorldPlayer {
    public static final Sponsors Biggest_Xuan = new Sponsors("Biggest_Xuan","29328b6c-6f03-4fba-9436-678b696e8aeb",5);
    public static final Sponsors DCTOR_0415 = new Sponsors("Dctor_0415","4b2a8226-3c3a-4d3d-a26d-68b577ae1463",5);
    public static final Sponsors sdxhop = new Sponsors("sdxhop","4f25e2d3-2cd4-45ce-b83d-8a965fe2137b",5);
    public static final Sponsors _Btmy_ = new Sponsors("_Btmy_","ec54ae0b-a148-4408-8ff8-0661ab44fed0",5);
    public static final Sponsors dytlj7788 = new Sponsors("dytlj7788","e690ea7a-8fce-4049-80dd-07158cd6a348",5);
    public static final Sponsors Chara_SS = new Sponsors("Chara_SS","1738cb1b-ea69-4e0f-8678-688aea7e8d1b",5);
    public static final Sponsors xiangshushumiao = new Sponsors("xiangshushumiao","19cd7e09-e249-4b92-b35a-770b3399a302",4);
    public static final Sponsors MCyunxi = new Sponsors("MCyunxi","eb91acd8-a70e-4b1d-b1c4-34fc4c8af495",3);
    public static final Sponsors HIEHEIHEICAT = new Sponsors("HIEHEIHEICAT","28f6f584-5d9c-45ba-b919-a8ebabf53477",3);
    public static final Sponsors ABunana = new Sponsors("Abunana","6105dab8-94a6-440f-b797-00d046587eda",1);
    private final int index;
    public final static int maxIndex = 6;

    public Sponsors(String name,UUID uuid,int index){
        super(name,uuid);
        this.index = index;
    }

    public Sponsors(String name,String uuid,int index){
        this(name,UUID.fromString(uuid),index);
    }

    public Sponsors(PlayerEntity player){
        this(player.getScoreboardName(),player.getUUID(),EMCWorldAPI.getInstance().getUtilCapability(player).getLevel());
    }

    public int getIndex(){
        return this.index;
    }

    public List<Integer> canSwitchIndex(){
        List<Integer> list = new ArrayList<>();
        if(index <= 5){
            for (int i = 0; i <= index ; i++) {
                if(i == 3 && index == 4) continue;
                list.add(i);
            }
        }
        if(index > 5){
            for (int i = 0; i <= 4; i++) {
                list.add(i);
            }
            list.add(index);
        }
        return list;
    }

    public static String getSponsorName(int index){
        String n = "message.sponsor.";
        switch (index){
            case 0:
                n += "none";
                break;
            case 1:
                n += "sponsor";
                break;
            case 2:
                n += "senior_sponsor";
                break;
            case 3:
                n += "top_sponsor";
                break;
            case 4:
                n += "mascot";
                break;
            case 5:
                n += "dev";
                break;
            default:
                n += "only";
        }
        return I18n.get(n);
    }

    @Override
    public String toString(){
        return playerName.toUpperCase(Locale.ROOT)+"(\""+playerName+"\",\""+uuid+"\",,"+"),";
    }

    @Override
    public boolean equals(Object e){
        if(e instanceof Sponsors){
            Sponsors object = (Sponsors) e;
            return object.getIndex() == this.index && object.getPlayerName().equals(this.getPlayerName()) && object.getPlayerUUID().equals(this.getPlayerUUID());
        }
        return false;
    }
}
