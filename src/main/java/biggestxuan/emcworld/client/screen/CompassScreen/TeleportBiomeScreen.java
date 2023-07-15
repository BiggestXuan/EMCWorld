package biggestxuan.emcworld.client.screen.CompassScreen;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/05
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.client.screen.BaseEMCConfirmScreen;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.network.PacketHandler;
import biggestxuan.emcworld.common.network.toServer.CostEMCPacket;
import biggestxuan.emcworld.common.utils.MathUtils;
import com.chaosthedude.naturescompass.NaturesCompass;
import com.chaosthedude.naturescompass.items.NaturesCompassItem;
import com.chaosthedude.naturescompass.network.TeleportPacket;
import com.chaosthedude.naturescompass.util.CompassState;
import com.chaosthedude.naturescompass.util.ItemUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
@EMCWorldSince("0.9.0")
public class TeleportBiomeScreen extends BaseEMCConfirmScreen {
    public TeleportBiomeScreen() {
        super(getTeleportCost());
    }

    @Override
    protected Button addConfirmButton() {
        Button button =  new Button((width / 2) - 125,height * 3 / 4,buttonLength,buttonHeight, EMCWorld.tc("screen.emc.confirm"), c->{
            PacketHandler.sendToServer(new CostEMCPacket(getTeleportCost(),2));
            Minecraft.getInstance().setScreen(null);
            NaturesCompass.network.sendToServer(new TeleportPacket());
        });
        if(EMCHelper.getPlayerEMC(Minecraft.getInstance().player) < getTeleportCost()){
            button.active = false;
        }
        return button;
    }

    @OnlyIn(Dist.CLIENT)
    private static long getTeleportCost(){
        ClientPlayerEntity player = Minecraft.getInstance().player;
        MathUtils.Position pos1 = new MathUtils.Position(player.blockPosition(),player.level.dimension().location());
        MathUtils.Position pos2 = pos1;
        ItemStack stack = ItemUtils.getHeldNatureCompass(player);
        if(!stack.isEmpty()){
            NaturesCompassItem c = (NaturesCompassItem)stack.getItem();
            if (c.getState(stack) == CompassState.FOUND) {
                int x = c.getFoundBiomeX(stack);
                int z = c.getFoundBiomeZ(stack);
                pos2 = new MathUtils.Position(new BlockPos(x,player.blockPosition().getY(),z),player.level.dimension().location());
            }
        }
        return MathUtils.getTPEMCCost(player,pos1,pos2);
    }
}
