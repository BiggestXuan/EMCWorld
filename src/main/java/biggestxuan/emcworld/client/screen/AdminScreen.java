package biggestxuan.emcworld.client.screen;

/***
 * EMC WORLD MOD
 * @Author Biggest_Xuan
 * 2022/10/10
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.network.toServer.AdminPacket;
import biggestxuan.emcworld.common.network.PacketHandler;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class AdminScreen extends Screen {

    private final List<Button> button = new ArrayList<>();

    public AdminScreen() {
        super(EMCWorld.tc("gui.title.admin"));
    }

    @Override
    protected void init() {
        super.init();
        if(this.minecraft == null) return;
        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        int w = this.width;
        int h = this.height;
        int x = 1;
        for (int i : new int[]{1,3}) {
            for (int j = 1; j < 5; j++) {
                Button b = new Button(i*w/5,j*h/5,98,20,EMCWorld.tc("gui.admin.button_"+i+"_"+j),new AdminButton(x));
                this.button.add(b);
                this.addButton(b);
                x++;
            }
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawCenteredString(matrixStack,this.font,EMCWorld.tc("gui.admin.button_title"),width/2,40,0xeb0505);
        for(Button b:this.button){
            b.renderButton(matrixStack,mouseX,mouseY,partialTicks);
        }
        super.render(matrixStack,mouseX,mouseY,partialTicks);
    }

    static class AdminButton implements Button.IPressable{
        private final int index;

        AdminButton(int index){
            this.index = index;
        }

        @Override
        public void onPress(@Nonnull Button p_onPress_1_) {
            PacketHandler.sendToServer(new AdminPacket(index));
        }
    }

    public static class open{
        public open(){
            Minecraft.getInstance().setScreen(new AdminScreen());
        }
    }
}
