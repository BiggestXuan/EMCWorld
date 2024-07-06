package biggestxuan.emcworld.common.utils;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/17
 */

import java.util.UUID;

public class EMCWorldPlayer {
    protected String playerName;
    protected UUID uuid;

    protected EMCWorldPlayer(String name,UUID uuid){
        this.playerName = name;
        this.uuid = uuid;
    }

    public String getPlayerName(){
        return this.playerName;
    }

    public UUID getPlayerUUID() {
        return this.uuid;
    }
}
