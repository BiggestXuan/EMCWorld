import biggestxuan.emcworld.common.utils.MathUtils;

import java.util.UUID;

import static biggestxuan.emcworld.common.utils.MathUtils.getRangeRandom;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/10
 */

public class main {
    public static void main(String[] args) {
        /*for (int i = 0; i < 25; i++) {
            *//*double d = (i*Math.PI/180);
            System.out.println("level"+i+"\t"+((((Math.pow(1.2,i)-Math.tan(d)))))*5);*//*

        }
        int weight = 23;
        for (int k = 0; k < 24; k++) {
            weight *= 1.15d;
            System.out.println(weight);
        }*/
        //System.out.println(UUID.randomUUID());
        for (int i = 0; i < 100; i++) {
            System.out.println(MathUtils.getRangeRandom(1,10));
        }
    }
}




