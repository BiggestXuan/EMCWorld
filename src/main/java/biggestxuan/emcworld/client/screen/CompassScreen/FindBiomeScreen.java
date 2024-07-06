package biggestxuan.emcworld.client.screen.CompassScreen;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/05
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.client.screen.BaseEMCConfirmScreen;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.network.PacketHandler;
import biggestxuan.emcworld.common.network.toServer.CostEMCPacket;
import biggestxuan.emcworld.common.utils.DifficultySetting;
import com.chaosthedude.naturescompass.NaturesCompass;
import com.chaosthedude.naturescompass.network.CompassSearchPacket;
import com.chaosthedude.naturescompass.util.BiomeUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@EMCWorldSince("0.9.0")
@OnlyIn(Dist.CLIENT)
public class FindBiomeScreen extends BaseEMCConfirmScreen {
    private final Biome list;
    public FindBiomeScreen(Biome list) {
        super(getEMCCost());
        this.list = list;
    }

    @OnlyIn(Dist.CLIENT)
    private static long getEMCCost(){
        if(!ConfigManager.EMC_LOCATE.get()){
            return 0L;
        }
        ClientPlayerEntity player = Minecraft.getInstance().player;
        for(DifficultySetting setting : DifficultySetting.values()){
            if(GameStageManager.hasStage(player,setting.getGameStage())){
                return (long) (setting.getCommonBase() * 255L * ConfigManager.DIFFICULTY.get());
            }
        }
        return 0L;
    }

    @Override
    protected Button addConfirmButton() {
        Button button =  new Button((width / 2) - 125,height * 3 / 4,buttonLength,buttonHeight, EMCWorld.tc("screen.emc.confirm"),c->{
            NaturesCompass.network.sendToServer(new CompassSearchPacket(BiomeUtils.getKeyForBiome(getMinecraft().level, list), Minecraft.getInstance().player.blockPosition()));
            PacketHandler.sendToServer(new CostEMCPacket(getEMCCost(),1));
            Minecraft.getInstance().setScreen(null);
        });
        if(EMCHelper.getPlayerEMC(Minecraft.getInstance().player) < getEMCCost()){
            button.active = false;
        }
        return button;
    }
}
