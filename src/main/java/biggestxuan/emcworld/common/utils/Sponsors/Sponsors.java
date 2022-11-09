package biggestxuan.emcworld.common.utils.Sponsors;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/17
 */

import biggestxuan.emcworld.common.utils.EMCWorldPlayer;

import java.util.Objects;
import java.util.UUID;

public class Sponsors extends EMCWorldPlayer {
    private final int SponsorLevel;

    public Sponsors(String name,UUID uuid,int sponsorLevel){
        super(name,uuid);
        this.SponsorLevel = sponsorLevel;
    }

    public Sponsors(String name,String uuid,int sponsorLevel){
        super(name,UUID.fromString(uuid));
        this.SponsorLevel = sponsorLevel;
    }

    public int getSponsorLevel(){
        return this.SponsorLevel;
    }

    @Override
    public String toString(){
        return "Name:"+this.playerName+"\tLevel:"+this.getSponsorLevel();
    }

    @Override
    public boolean equals(Object e){
        if(e instanceof Sponsors){
            Sponsors object = (Sponsors) e;
            return object.getSponsorLevel() == this.SponsorLevel && Objects.equals(object.getPlayerName(), this.getPlayerName()) && object.getPlayerUUID() == this.getPlayerUUID();
        }
        return false;
    }
}
