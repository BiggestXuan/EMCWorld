package biggestxuan.emcworld.client.screen;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.config.ClientConfigManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ConfirmOpenLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/09/03
 */

@EMCWorldSince("1.0.4")
public abstract class EMCWorldHelperGUI extends Screen implements IHelperScreen{

    protected EMCWorldHelperGUI(ITextComponent p_i51108_1_) {
        super(p_i51108_1_);
    }

    @Override
    public void init(){
        super.init();
        if(ClientConfigManager.RENDER_GUI_HELPER.get()){
            this.addButton(getButton());
        }
    }

    protected int[] getHelperPos(){
        return new int[]{10,20};
    }

    protected Button getButton(){
        return new Button(getHelperPos()[0], getHelperPos()[1], 20, 20, EMCWorld.tc("?"), p_onPress_1_ -> {
            Minecraft.getInstance().setScreen(new ConfirmOpenLinkScreen(this::consume,getHelperLink(),true));
        });
    }

    private void consume(boolean i){
        Minecraft.getInstance().setScreen(this);
        if(i){
            Util.getPlatform().openUri(getHelperLink());
        }
    }
}
