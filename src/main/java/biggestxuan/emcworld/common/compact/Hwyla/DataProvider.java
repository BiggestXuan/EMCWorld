package biggestxuan.emcworld.common.compact.Hwyla;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/16
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.blocks.AdvancedUpdateBlock.AdvancedUpdateBlock;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.common.compact.Projecte.itf.CollectorLifeSpan;
import biggestxuan.emcworld.common.registry.EWBlocks;
import biggestxuan.emcworld.common.utils.MathUtils;
import dev.latvian.mods.projectex.block.CollectorBlock;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.tileentity.TileEntity;
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
        TileEntity tile = accessor.getTileEntity();
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
        if(block instanceof CollectorBlock){
            CollectorBlock block1 = (CollectorBlock) block;
            CollectorLifeSpan lifeSpan = (CollectorLifeSpan) tile;
            String maxEMC = MathUtils.format((lifeSpan.getMaxLifeSpan() - lifeSpan.getLifeSpan()) * block1.matter.collectorOutput / 20);
            tooltip.add(EMCWorld.tc("tooltip.emcworld.collector.lifespan",(lifeSpan.getMaxLifeSpan()-lifeSpan.getLifeSpan())/20,String.format("%.2f",(1-(1d*lifeSpan.getLifeSpan()/lifeSpan.getMaxLifeSpan()))*100)+"%"));
            tooltip.add(EMCWorld.tc("tooltip.emcworld.collector.max",maxEMC));
        }
    }
}
