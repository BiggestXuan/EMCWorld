package biggestxuan.emcworld.common.compact.Projecte;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/26
 */

import biggestxuan.emcworld.common.config.ConfigManager;
import dev.latvian.mods.projectex.Matter;

public class ModifyCollector {
    public static void init(){
        Matter.BASIC.collectorOutput = g(5);
        Matter.DARK.collectorOutput = g(7);
        Matter.RED.collectorOutput = g(9);
        Matter.MAGENTA.collectorOutput = g(11);
        Matter.PINK.collectorOutput = g(12);
        Matter.PURPLE.collectorOutput = g(13);
        Matter.VIOLET.collectorOutput = g(14);
        Matter.BLUE.collectorOutput = g(17);
        Matter.CYAN.collectorOutput = g(20);
        Matter.GREEN.collectorOutput = g(21);
        Matter.LIME.collectorOutput = g(23);
        Matter.YELLOW.collectorOutput = g(26);
        Matter.ORANGE.collectorOutput = g(28);
        Matter.WHITE.collectorOutput = g(30);
        Matter.FINAL.collectorOutput = 0;
        Matter.FADING.collectorOutput = 0;
    }

    private static long g(int b){
        long y = 1L << b;
        if(ConfigManager.DIFFICULTY.get() >= 1){
            y *= ConfigManager.DIFFICULTY.get() / 3;
        }else{
            y *= Math.pow(2,((1-ConfigManager.DIFFICULTY.get())*10)+1);
        }
        return y;
    }
}
