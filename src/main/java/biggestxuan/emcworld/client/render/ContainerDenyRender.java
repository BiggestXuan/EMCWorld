package biggestxuan.emcworld.client.render;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/22
 */

import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.config.ConfigManager;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import noobanidus.mods.lootr.api.tile.ILootTile;

import javax.annotation.Nonnull;

public class ContainerDenyRender extends TileEntityRenderer<LockableTileEntity> {
    public ContainerDenyRender(TileEntityRendererDispatcher p_i226006_1_) {
        super(p_i226006_1_);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void render(LockableTileEntity p_225616_1_, float p_225616_2_, @Nonnull MatrixStack p_225616_3_,@Nonnull IRenderTypeBuffer p_225616_4_, int p_225616_5_, int p_225616_6_) {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        ClientWorld world = Minecraft.getInstance().level;
        BlockPos pos = p_225616_1_.getBlockPos();
        if(p_225616_1_ instanceof ILootTile && !ConfigManager.FREE_MODE.get()){
            ILootTile tile = (ILootTile) p_225616_1_;
            ResourceLocation rl = tile.getTable();
            if(rl != null && (rl.getNamespace().contains("illager") || rl.getPath().contains("illager"))){
                if(player != null && !GameStageManager.hasStage(player,"three")){
                    if(world != null && world.getDayTime() % 40 == 0){
                        world.addParticle(ParticleTypes.BARRIER, pos.getX() + 0.5D, pos.getY() + 1.5D, pos.getZ()+ 0.5D, 0.0D, 0.0D, 0.0D);
                    }
                }
            }
        }
    }
}
