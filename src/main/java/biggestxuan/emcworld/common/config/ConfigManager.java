package biggestxuan.emcworld.common.config;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/25
 */


import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.AstralSorcery.EMCConstellationEffects;
import biggestxuan.emcworld.common.compact.AstralSorcery.EMCMantleEffects;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus= Bus.MOD)
public class ConfigManager {
    public static ForgeConfigSpec COMMON_CONFIG ;
    public static ForgeConfigSpec.DoubleValue DIFFICULTY;
    public static ForgeConfigSpec.BooleanValue ADMIN_MENU;
    public static ForgeConfigSpec.BooleanValue FREE_MODE;
    public static ForgeConfigSpec.BooleanValue SpringFestival;
    public static ForgeConfigSpec.BooleanValue LanternFestival;
    public static ForgeConfigSpec.BooleanValue FoolsDay;
    public static ForgeConfigSpec.BooleanValue DragonBoatFestival;
    public static ForgeConfigSpec.BooleanValue MidAutumnFestival;

    static
    {
        ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        BUILDER.push("General");
        DIFFICULTY = BUILDER.comment("Difficulty Setting").defineInRange("World Difficulty",3.0,0.5,3.0);
        ADMIN_MENU = BUILDER.comment("Whether unable EMCWorld Admin Menu").define("Able Admin Menu",false);
        FREE_MODE = BUILDER.comment("Enable free mode to delete all items stage").define("Able Free Mode",false);
        BUILDER.pop();
        BUILDER.push("Festival");
        SpringFestival = BUILDER.comment("Get gifts on certain festivals,only configurable festivals that give gifts").define("SpringFestival",true);
        LanternFestival = BUILDER.define("LanternFestival",true);
        FoolsDay = BUILDER.define("FoolsDay",true);
        DragonBoatFestival = BUILDER.define("DragonBoatFestival",true);
        MidAutumnFestival = BUILDER.define("MidAutumnFestival",true);
        BUILDER.pop();

        EMCConstellationEffects.CONFIG.createEntries(BUILDER);
        EMCMantleEffects.CONFIG.createEntries(BUILDER);

        COMMON_CONFIG = BUILDER.build();
    }
}
