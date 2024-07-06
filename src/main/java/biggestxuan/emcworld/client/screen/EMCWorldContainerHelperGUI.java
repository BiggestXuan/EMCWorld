package biggestxuan.emcworld.client.screen;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.config.ClientConfigManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ConfirmOpenLinkScreen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/08/20
 */

@EMCWorldSince("1.0.3")
@OnlyIn(Dist.CLIENT)
public abstract class EMCWorldContainerHelperGUI<T extends Container> extends ContainerScreen<T> implements IHelperScreen{
    public EMCWorldContainerHelperGUI(T p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
    }

    @Override
    protected void init(){
        super.init();
        if(ClientConfigManager.RENDER_GUI_HELPER.get()){
            this.addButton(getButton());
        }
    }

    protected int[] getHelperPos(){
        return new int[]{0,-22};
    }

    protected Button getButton(){
        int x = (width - getXSize()) / 2;
        int y = (height - getYSize()) / 2;
        return new Button(x+getHelperPos()[0], y+getHelperPos()[1], 20, 20, EMCWorld.tc("?"), p_onPress_1_ -> {
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
