package biggestxuan.emcworld.common.compact.CraftTweaker;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/22
 */

import biggestxuan.emcworld.common.utils.CalendarUtils;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.Calendar")
public class CrTCalendar {
    @ZenCodeType.Method
    public static boolean isSpringFestival(){
        return CalendarUtils.INSTANCE.isSpringFestival();
    }

    @ZenCodeType.Method
    public static boolean isLanternFestival(){
        return CalendarUtils.INSTANCE.isLanternFestival();
    }

    @ZenCodeType.Method
    public static boolean isDragonBoatFestival(){
        return CalendarUtils.INSTANCE.isDragonBoatFestival();
    }

    @ZenCodeType.Method
    public static boolean isMidAutumnFestival(){
        return CalendarUtils.INSTANCE.isMidAutumnFestival();
    }
}
