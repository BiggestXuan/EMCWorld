package biggestxuan.emcworld.common.exception;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/16
 */

public class EMCWorldNotFinalException extends Exception{
    public EMCWorldNotFinalException(){
        super("EMCWorld is not completed, only sponsors above level 2 can join world.");
    }
}
