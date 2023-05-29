package biggestxuan.emcworld.common.utils;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/19
 */

import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Calendar;

public class CalendarUtils {

    public static final CalendarUtils INSTANCE = new CalendarUtils();

    Calendar calendar = Calendar.getInstance();
    protected int year = calendar.get(Calendar.YEAR);
    protected int month = calendar.get(Calendar.MONTH)+1;
    protected int day = calendar.get(Calendar.DAY_OF_MONTH);

    public boolean isNewYear(){
        return month == 1 && day == 1;
    }

    public boolean isSpringFestival(){
        if(ConfigManager.SpringFestival.get()){
            return year == 2023 && month == 1 && day == 22 ||
                    (year == 2024 && month == 2 && day == 10) ||
                    (year == 2025 && month == 1 && day == 29) ||
                    (year == 2026 && month == 2 && day == 17) ||
                    (year == 2027 && month == 2 && day == 6);
        }
        return false;
    }

    public boolean isLanternFestival(){
        if(ConfigManager.LanternFestival.get()){
            return year == 2023 && month == 2 && day == 5 ||
                    (year == 2024 && month == 2 && day == 24) ||
                    (year == 2025 && month == 2 && day == 12) ||
                    (year == 2026 && month == 3 && day == 3) ||
                    (year == 2027 && month == 2 && day == 20);
        }
        return false;
    }

    public boolean isAprilFoolsDay(){
        if(ConfigManager.FoolsDay.get()){
            return month == 4 && day == 1;
        }
        return false;
    }

    public boolean isWorkersDay(){
        return month == 5 && day == 1;
    }

    public boolean isDragonBoatFestival(){
        if(ConfigManager.DragonBoatFestival.get()){
            return year == 2023 && month == 6 && day == 22 ||
                    (year == 2024 && month == 6 && day == 10) ||
                    (year == 2025 && month == 5 && day == 31) ||
                    (year == 2026 && month == 6 && day == 19) ||
                    (year == 2027 && month == 6 && day == 9);
        }
        return false;
    }

    public int getHour(){
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public NationalUtils isNationalDay(){
        return new NationalUtils(month == 10 && day == 1,year-1949);
    }

    public boolean isMidAutumnFestival(){
        if(ConfigManager.MidAutumnFestival.get()){
            return year == 2023 && month == 9 && day == 29 ||
                    (year == 2024 && month == 9 && day == 17) ||
                    (year == 2025 && month == 10 && day == 6) ||
                    (year == 2026 && month == 9 && day == 25) ||
                    (year == 2027 && month == 9 && day == 15);
        }
        return false;
    }

    public boolean isChristmasDay(){
        return month == 12 && day == 25;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getMinute() {
        return calendar.get(Calendar.MINUTE);
    }

    public int getSecond() {
        return calendar.get(Calendar.SECOND);
    }

    public static class NationalUtils{
        private final boolean isNationalDay;
        private final int year;

        public NationalUtils(boolean isNationalDay,int year){
            this.isNationalDay = isNationalDay;
            this.year = year;
        }

        public int getYear() {
            return year;
        }

        public boolean isNationalDay() {
            return isNationalDay;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public String getNowTimeWelcome(){
        String s;
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if(player != null && new BirthdayUtils(player).HappyBirthday()){
            return "Happy Birthday";
        }
        int hour = getHour();
        if(hour >= 6 && hour < 12){
            s = "Good Morning";
        }else if(hour >= 12 && hour < 18){
            s = "Good Afternoon";//sdxhop
        } else if(hour >= 18 && hour <= 23){
            s = "Good Evening";
        }else{
            s = "Have A Good Dream";
        }
        return s;
    }
}
