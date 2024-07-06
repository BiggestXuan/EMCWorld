package biggestxuan.emcworld.common.compact.Hwyla;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/16
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.blocks.AdvancedUpdateBlock;
import biggestxuan.emcworld.common.blocks.EMCOreCoreBlock;
import biggestxuan.emcworld.common.blocks.tile.EMCOreCoreTileEntity;
import biggestxuan.emcworld.common.blocks.InfuserBlock;
import biggestxuan.emcworld.common.blocks.tile.InfuserBlockTileEntity;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.common.compact.Projecte.itf.ICollectorLifeSpan;
import biggestxuan.emcworld.common.registry.EWBlocks;
import biggestxuan.emcworld.common.utils.MathUtils;
import dev.latvian.mods.projectex.block.CollectorBlock;
import dev.latvian.mods.projectex.block.entity.CollectorBlockEntity;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import moze_intel.projecte.api.capabilities.tile.IEmcStorage;
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
            ICollectorLifeSpan lifeSpan = (ICollectorLifeSpan) tile;
            String maxEMC = MathUtils.format((lifeSpan.getMaxLifeSpan() - lifeSpan.getLifeSpan()) * block1.matter.collectorOutput / 20);
            tooltip.add(EMCWorld.tc("tooltip.emcworld.collector.lifespan",(lifeSpan.getMaxLifeSpan()-lifeSpan.getLifeSpan())/20,String.format("%.2f",(1-(1d*lifeSpan.getLifeSpan()/lifeSpan.getMaxLifeSpan()))*100)+"%"));
            tooltip.add(EMCWorld.tc("tooltip.emcworld.collector.max",maxEMC));
        }
        if(block instanceof InfuserBlock){
            InfuserBlockTileEntity infuser = (InfuserBlockTileEntity) tile;
            int emc = infuser.getEmc();
            int max = infuser.getMaxEMC();
            int progress = infuser.getProgress();
            int maxProgress = infuser.getMaxProgress() == 0 ? 1 : infuser.getMaxProgress();
            //tooltip.add(EMCWorld.tc("EMC: "+MathUtils.format(emc)+"/"+MathUtils.format(max)+" ("+String.format("%.2f",100d*emc/max)+"%)"));
            tooltip.add(EMCWorld.tc("tooltip.emcworld.infuser_progress",String.format("%.2f",100d*progress/maxProgress)+"%"));
        }
        if(block instanceof EMCOreCoreBlock){
            EMCOreCoreTileEntity infuser = (EMCOreCoreTileEntity) tile;
            long emc = infuser.emc;
            long max = infuser.maxEMC;
            int progress = infuser.progress;
            int maxProgress = infuser.maxProgress == 0 ? 1 : infuser.maxProgress;
            tooltip.add(EMCWorld.tc("tooltip.emcworld.infuser_progress",String.format("%.2f",100d*progress/maxProgress)+"%"));
        }
        if(tile instanceof IEmcStorage && !(tile instanceof CollectorBlockEntity)){
            IEmcStorage storage = (IEmcStorage) tile;
            tooltip.add(EMCWorld.tc("EMC: "+MathUtils.format(storage.getStoredEmc())+"/"+MathUtils.format(storage.getMaximumEmc())+" ("+String.format("%.2f",100d*storage.getStoredEmc()/storage.getMaximumEmc())+"%)"));
        }
    }
}
