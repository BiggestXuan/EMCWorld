package biggestxuan.emcworld.common.compact.Projecte;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/14
 */

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class KnowledgeHelper {
    public static void addPlayerKnowledge(PlayerEntity player, ItemStack item){
        EMCHelper.getPlayerIKP(player).addKnowledge(item);
        EMCHelper.flushPlayer(player);
    }
}
