package biggestxuan.emcworld.common.utils;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/09/11
 */

@EMCWorldSince("1.0.4")
public class PlayTimeUtils {
    public static int getPlayerPlayTime(PlayerEntity player){
        try{
            var util = EMCWorldAPI.getInstance().getUtilCapability(player);
            return util.getPlayTime();
        }catch (NullPointerException ignored){

        }
        return 0;
    }

    public static boolean shouldSendMessage(PlayerEntity player){
        return ConfigManager.SUNDRY_REST_MESSAGE.get() && getPlayerPlayTime(player) % 7200 == 0;
    }

    public static void sendRestMessage(PlayerEntity player){
        Message.sendMessage(player,getMessage(player).setStyle(Style.EMPTY.withColor(TextFormatting.AQUA)));
    }

    @Nonnull
    private static IFormattableTextComponent getMessage(PlayerEntity player){
        int min = 0;
        int max = 11;
        int index = MathUtils.getRangeRandom(min,max);
        return EMCWorld.tc("message.emcworld.rest_base",getPlayerPlayTime(player) / 3600).append(EMCWorld.tc("message.emcworld.rest_"+index));
    }
}
