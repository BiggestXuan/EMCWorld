package biggestxuan.emcworld.common.compact.Hwyla;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/16
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.blocks.AdvancedUpdateBlock.AdvancedUpdateBlock;
import biggestxuan.emcworld.common.blocks.EWUpdateBlock;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.common.registry.EWBlocks;
import biggestxuan.emcworld.common.utils.MathUtils;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class DataProvider implements IComponentProvider {
    public static final DataProvider INSTANCE = new DataProvider();

    @Override
    public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
        Block block = accessor.getBlock();
        if(Minecraft.getInstance().player == null) return;
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if(block instanceof AdvancedUpdateBlock || block.equals(EWBlocks.CONTROL_UPDATE_CORE.get())){
            IUtilCapability cap = player.getCapability(EMCWorldCapability.UTIL).orElseThrow(NullPointerException::new);
            tooltip.add(EMCWorld.tc("hwyla.tip.cd", MathUtils.format(cap.getCoolDown()/20)));
        }
        if(accessor.getBlockState().equals(EWBlocks.PROFESSION_CORE.get().defaultBlockState())){
            int[] skills = player.getCapability(EMCWorldCapability.PLAYER_LEVEL).orElseThrow(NullPointerException::new).getSkills();
            tooltip.add(EMCWorld.tc("hwyla.tip.skill_cd1",MathUtils.format(skills[7]/20)));
            tooltip.add(EMCWorld.tc("hwyla.tip.skill_cd2",MathUtils.format(skills[19]/20)));
            tooltip.add(EMCWorld.tc("hwyla.tip.skill_cd3",MathUtils.format(skills[43]/20)));
        }
    }
}
