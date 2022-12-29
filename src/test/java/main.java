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
        System.out.println(getRangeRandom(100,300));
        System.out.println(getRangeRandom(300,100));
        System.out.println(getRangeRandom(-300,100));
        System.out.println(getRangeRandom(100,100));
        System.out.println(getRangeRandom(1100,-555));
    }
}




