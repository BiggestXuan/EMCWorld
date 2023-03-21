import biggestxuan.emcworld.common.utils.CalendarUtils;
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
        /*for (int i = 0; i < 100; i++) {
            System.out.println(MathUtils.getRangeRandom(1,10));
        }*/
        /*double i = 1;
        while (i < 1.1){
            System.out.println(i+":"+(MathUtils.format((long)(MathUtils.log(i,3000)*1800000.0))));
            i += 0.01;
        }*/
        System.out.println(CalendarUtils.INSTANCE.getHour());
    }
}




