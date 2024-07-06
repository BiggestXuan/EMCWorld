package biggestxuan.emcworld.common.compact.Hwyla;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/16
 */

import biggestxuan.emcworld.common.blocks.*;
import dev.latvian.mods.projectex.block.CollectorBlock;
import mcp.mobius.waila.api.IRegistrar;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.TooltipPosition;
import mcp.mobius.waila.api.WailaPlugin;

@WailaPlugin
public class EMCWorldHwylaPlugin implements IWailaPlugin {

    @Override
    public void register(IRegistrar iRegistrar) {
        iRegistrar.registerComponentProvider(DataProvider.INSTANCE, TooltipPosition.BODY, AdvancedUpdateBlock.class);
        iRegistrar.registerComponentProvider(DataProvider.INSTANCE, TooltipPosition.BODY, ProfessionalBlock.class);
        iRegistrar.registerComponentProvider(DataProvider.INSTANCE, TooltipPosition.BODY, UpdateBlock.class);
        iRegistrar.registerComponentProvider(DataProvider.INSTANCE, TooltipPosition.BODY, CollectorBlock.class);
        iRegistrar.registerComponentProvider(DataProvider.INSTANCE, TooltipPosition.BODY, InfuserBlock.class);
        iRegistrar.registerComponentProvider(DataProvider.INSTANCE, TooltipPosition.BODY, EMCOreCoreBlock.class);
        //iRegistrar.registerComponentProvider(DataProvider.INSTANCE, TooltipPosition.BODY, VisConversionBlock.class);
    }
}
