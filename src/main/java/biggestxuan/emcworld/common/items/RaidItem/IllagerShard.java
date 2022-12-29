package biggestxuan.emcworld.common.items.RaidItem;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/26
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.item.IPlayerDifficultyItem;
import biggestxuan.emcworld.common.items.EWItem;
import biggestxuan.emcworld.common.utils.RaidUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class IllagerShard extends EWItem implements IPlayerDifficultyItem {
    public IllagerShard(){
        super(3);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if(player == null) return;
        if(EMCWorldAPI.getInstance().getUtilCapability(player).getDifficulty() != 3.0d) return;
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.illager_shard"));
        double chance = RaidUtils.getIllagerShardDropRate(null,player);
        String info = chance*100 < 0.01 ? "<0.01" : String.format("%.2f",chance*100);
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.illager_shard_desc", info).append("%"));
    }

    @Override
    public double requireDifficulty() {
        return 3D;
    }
}
