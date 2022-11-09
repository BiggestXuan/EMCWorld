package biggestxuan.emcworld.api.item;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/25
 */

import biggestxuan.emcworld.common.utils.Sponsors.Sponsors;

import javax.annotation.Nullable;

public interface ISponsorItem {

    @Nullable
    default Sponsors getSponsor(){
        return null;
    };
}
