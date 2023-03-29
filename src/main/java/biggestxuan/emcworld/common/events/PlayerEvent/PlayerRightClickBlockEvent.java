package biggestxuan.emcworld.common.events.PlayerEvent;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/06
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.network.PacketHandler;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.Message;
import hellfirepvp.astralsorcery.common.block.tile.BlockAltar;
import hellfirepvp.astralsorcery.common.block.tile.BlockRitualPedestal;
import mythicbotany.alfheim.Alfheim;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;
import noobanidus.mods.lootr.api.tile.ILootTile;
import twilightforest.network.StructureProtectionPacket;
import twilightforest.network.TFPacketHandler;

import java.util.List;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerRightClickBlockEvent {
    @SubscribeEvent
    public static void rightClickBlock(PlayerInteractEvent.RightClickBlock event){
        BlockState state = event.getWorld().getBlockState(event.getPos());
        Block block = state.getBlock();
        ResourceLocation rl = block.getRegistryName();
        if(event.getWorld().isClientSide || rl == null) return;
        if(block instanceof BlockAltar || block instanceof BlockRitualPedestal){
            if(!event.getWorld().dimension().equals(Alfheim.DIMENSION)){
                event.setResult(Event.Result.DENY);
                Message.sendMessage(event.getPlayer(),EMCWorld.tc("message.altar.cancel"));
                event.setCanceled(true);
            }
        }
        if(block instanceof ShulkerBoxBlock || (rl.getNamespace().equals("refinedstorage") && (rl.getPath().equals("destructor") || rl.getPath().equals("constructor")))){
            event.setCanceled(true);
            Message.sendMessage(event.getPlayer(),EMCWorld.tc("message.disable.deny"));
        }
        if(event.getWorld().getBlockEntity(event.getPos()) instanceof ILootTile && !ConfigManager.FREE_MODE.get()){
            ILootTile tile = (ILootTile) event.getWorld().getBlockEntity(event.getPos());
            if(tile != null && tile.getTable() != null && (tile.getTable().getNamespace().contains("illager") || tile.getTable().getPath().contains("illager"))){
                if(!GameStageManager.hasStage(event.getPlayer(),"three")){
                    event.setCanceled(true);
                }
            }
            /*if(event.getPlayer() instanceof ServerPlayerEntity){
                MutableBoundingBox box = new MutableBoundingBox(event.getPos(),event.getPos());
                TFPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()), new StructureProtectionPacket(box));
            }*/
        }
    }

    public static boolean denyPlayerOpenChest(PlayerEntity player, BlockPos pos){
        World world = player.level;
        AxisAlignedBB aabb = MathUtils.expandAABB(pos,32);
        List<? extends AbstractIllagerEntity> list = world.getLoadedEntitiesOfClass(AbstractIllagerEntity.class,aabb);
        return list.size() >= 1 && !GameStageManager.hasStage(player,"three");
    }
}
