package biggestxuan.emcworld.common.compact.Projecte;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/26
 */

import biggestxuan.emcworld.common.config.ConfigManager;
import dev.latvian.mods.projectex.Matter;

public class ModifyCollector {
    public static void init(){
        Matter.BASIC.collectorOutput = g(3);
        Matter.DARK.collectorOutput = g(4);
        Matter.RED.collectorOutput = g(5);
        Matter.MAGENTA.collectorOutput = g(6);
        Matter.PINK.collectorOutput = g(7);
        Matter.PURPLE.collectorOutput = g(8);
        Matter.VIOLET.collectorOutput = g(9);
        Matter.BLUE.collectorOutput = g(11);
        Matter.CYAN.collectorOutput = g(13);
        Matter.GREEN.collectorOutput = g(15);
        Matter.LIME.collectorOutput = g(17);
        Matter.YELLOW.collectorOutput = g(19);
        Matter.ORANGE.collectorOutput = g(22);
        Matter.WHITE.collectorOutput = g(25);
        Matter.FINAL.collectorOutput = 0;
        Matter.FADING.collectorOutput = 0;
    }

    private static long g(int b){
        long y = 3L << b;
        if(ConfigManager.DIFFICULTY.get() >= 1){
            y *= ConfigManager.DIFFICULTY.get() / 3;
        }else{
            y *= Math.pow(2,((1-ConfigManager.DIFFICULTY.get())*10)+1);
        }
        return y;
    }
}
