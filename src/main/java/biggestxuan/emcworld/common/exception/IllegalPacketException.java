package biggestxuan.emcworld.common.exception;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/23
 */

public class IllegalPacketException extends Exception {
    public IllegalPacketException(Object message){
        super("Server receive a illegal packet from: "+message.toString());
    }
}
