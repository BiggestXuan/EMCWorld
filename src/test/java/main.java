import java.util.UUID;

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
        for (int i = 0; i < 25; i++) {
            double a = Math.pow(1.4,i)*500000*0.0005;
            System.out.println(a);
            System.out.println(Math.log(a));
            System.out.println("----");
        }
    }
}




