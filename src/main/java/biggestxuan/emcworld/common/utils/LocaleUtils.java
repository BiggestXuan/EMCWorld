package biggestxuan.emcworld.common.utils;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/17
 */

import java.util.Locale;

public class LocaleUtils {
    public static boolean isChina(){
        Locale locale = Locale.getDefault();
        return locale.getCountry().equals("CN");
    }
}
