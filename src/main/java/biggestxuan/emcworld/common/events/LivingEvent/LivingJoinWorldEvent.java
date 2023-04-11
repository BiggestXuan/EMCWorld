package biggestxuan.emcworld.common.events.LivingEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/07
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.entity.Player.Tulye;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SkullItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import twilightforest.entity.boss.LichEntity;

import java.util.List;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class LivingJoinWorldEvent {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void livingJoinWorldEvent(EntityJoinWorldEvent event){
        if(event.getEntity().level.isClientSide || event.getEntity() == null) return;
        if(event.getEntity() instanceof Tulye){
            Tulye e = (Tulye) event.getEntity();
            e.addEffect(new EffectInstance(Effects.INVISIBILITY,600,0));
        }
        if(event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof LichEntity)){
            LivingEntity livingEntity = (LivingEntity) event.getEntity();
            if(livingEntity.getLootTable().getNamespace().equals("divinerpg") && livingEntity.level.dimension().equals(World.OVERWORLD)){
                AxisAlignedBB aabb = MathUtils.expandAABB(livingEntity.position(),64);
                List<PlayerEntity> players = livingEntity.level.getLoadedEntitiesOfClass(PlayerEntity.class,aabb);
                for(PlayerEntity p : players){
                    if(GameStageManager.hasStage(p,"two")){
                        return;
                    }
                }
                event.setCanceled(true);
            }
            ItemStack headItem = livingEntity.getItemBySlot(EquipmentSlotType.HEAD);
            if(headItem.getItem() instanceof SkullItem){
                livingEntity.setItemSlot(EquipmentSlotType.HEAD,ItemStack.EMPTY);
            }
        }
    }
}
