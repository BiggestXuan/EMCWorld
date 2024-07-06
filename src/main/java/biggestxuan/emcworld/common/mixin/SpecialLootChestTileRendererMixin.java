package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/22
 */

import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.ChestTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.properties.ChestType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import noobanidus.mods.lootr.api.tile.ILootTile;
import noobanidus.mods.lootr.block.tile.LootrChestTileEntity;
import noobanidus.mods.lootr.client.block.SpecialLootChestTileRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("all")
@Mixin(SpecialLootChestTileRenderer.class)
public abstract class SpecialLootChestTileRendererMixin<T extends LootrChestTileEntity & ILootTile> extends ChestTileEntityRenderer<T> {
    public SpecialLootChestTileRendererMixin(TileEntityRendererDispatcher p_i226008_1_) {
        super(p_i226008_1_);
    }

    @Inject(method = "getMaterial",at = @At(value = "HEAD"),remap = false)
    protected void get(T tile, ChestType type, CallbackInfoReturnable<RenderMaterial> cir){
        if(ConfigManager.FREE_MODE.get()) return;
        ClientPlayerEntity player = Minecraft.getInstance().player;
        ClientWorld world = Minecraft.getInstance().level;
        BlockPos pos = tile.getBlockPos();
        ResourceLocation rl = tile.getTable();
        if(rl != null && (rl.getNamespace().contains("illager") || rl.getPath().contains("illager")) && !GameStageManager.hasStage(player,"three")){
            if(world != null && world.getDayTime() % 40 == 0){
                world.addParticle(ParticleTypes.BARRIER, pos.getX() + 0.5D, pos.getY() + 1.5D, pos.getZ()+ 0.5D, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
