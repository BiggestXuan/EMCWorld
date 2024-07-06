package biggestxuan.emcworld.client.screen.CompassScreen;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/05
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.client.screen.BaseEMCConfirmScreen;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.network.toServer.CostEMCPacket;
import biggestxuan.emcworld.common.network.PacketHandler;
import biggestxuan.emcworld.common.utils.DifficultySetting;
import com.chaosthedude.explorerscompass.gui.StructureSearchList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Objects;

@OnlyIn(Dist.CLIENT)
public class FindScreen extends BaseEMCConfirmScreen {
    private final StructureSearchList list;
    public FindScreen(StructureSearchList list) {
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
            if(list.hasSelection()){
                Objects.requireNonNull(list.getSelected()).searchForBiome();
                PacketHandler.sendToServer(new CostEMCPacket(getEMCCost(),1));
            }
            Minecraft.getInstance().setScreen(null);
        });
        if(EMCHelper.getPlayerEMC(Minecraft.getInstance().player) < getEMCCost()){
            button.active = false;
        }
        return button;
    }
}
