package biggestxuan.emcworld.common.exception;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/21
 */

public class EMCWorldCommonException extends RuntimeException{
    public EMCWorldCommonException(){
        super("EMCWorld has been caused a exception! Please read console to get more information.");
    }

    public EMCWorldCommonException(String info){
        super(info);
    }
}
