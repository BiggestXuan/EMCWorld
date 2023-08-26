package biggestxuan.emcworld.common.config;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/03
 */

import biggestxuan.emcworld.EMCWorld;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus= Mod.EventBusSubscriber.Bus.MOD)
public class ClientConfigManager {
    public static ForgeConfigSpec CLIENT_CONFIG ;
    //public static ForgeConfigSpec.BooleanValue LIVE_MODE;
    public static ForgeConfigSpec.BooleanValue RENDER_GUI_HELPER;
    public static ForgeConfigSpec.BooleanValue PCL_WARNING;
    public static ForgeConfigSpec.BooleanValue ADD_MOD_WARNING;

    static{
        ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        BUILDER.push("General");
        //LIVE_MODE = BUILDER.comment("Disable all client message").define("Enable Live Mode",false);
        RENDER_GUI_HELPER = BUILDER.define("render_gui_helper",true);
        PCL_WARNING = BUILDER.define("pcl_warning",true);
        ADD_MOD_WARNING = BUILDER.define("add_mod_warning",true);
        BUILDER.pop();

        CLIENT_CONFIG = BUILDER.build();
    }
}
