//import cn.hutool.core.date.ChineseDate;

import biggestxuan.emcworld.common.entity.Player.Biggest_Xuan;
import biggestxuan.emcworld.common.registry.EWEntities;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.Network.OfficialServerNetWork;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.Date;
import java.util.UUID;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/10
 */

public class main {
    public static void main(String[] args) {
        /**for (int i = 0; i < 25; i++) {
            *//**double d = (i*Math.PI/180);
            System.out.println("level"+i+"\t"+((((Math.pow(1.2,i)-Math.tan(d)))))*5);*//**

        }
        int weight = 23;
        for (int k = 0; k < 24; k++) {
            weight *= 1.15d;
            System.out.println(weight);
        }*/
        //System.out.println(UUID.randomUUID());
        /**for (int i = 0; i < 100; i++) {
            System.out.println(MathUtils.getRangeRandom(1,10));
        }*/
        /**double i = 1;
        while (i < 1.1){
            System.out.println(i+":"+(MathUtils.format((long)(MathUtils.log(i,3000)*1800000.0))));
            i += 0.01;
        }*/
        /*ChineseDate d = new ChineseDate(2023,5,5);
        Date dd = d.getGregorianDate();
        System.out.println(d.getGregorianYear());
        System.out.println(d.getGregorianMonth());
        System.out.println(d.getGregorianDay());
        System.out.println((long) Math.abs(UUID.fromString("29328b6c-6f03-4fba-9436-678b696e8aeb").hashCode()));
*/
        for (int i = 0; i <= 29; i++) {
            System.out.println(i+","+MathUtils.getEMCGodRequireWeight(i));
        }
        //OfficialServerNetWork.sendGET("test","66666");
    }
}




