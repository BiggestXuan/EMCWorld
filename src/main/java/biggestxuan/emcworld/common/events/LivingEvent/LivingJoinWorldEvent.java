package biggestxuan.emcworld.common.events.LivingEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/07
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.Curios.PlayerCuriosUtils;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.entity.Player.Tulye;
import biggestxuan.emcworld.common.utils.MathUtils;
import com.github.alexthe666.rats.server.entity.RatsEntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SkullItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
/*import top.theillusivec4.champions.Champions;
import top.theillusivec4.champions.api.IChampionsApi;
import top.theillusivec4.champions.common.capability.ChampionCapability;
import top.theillusivec4.champions.common.rank.Rank;*/
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
        if(ConfigManager.DIFFICULTY.get() == 0.5 && MathUtils.isRandom(0.95) && !(event.getEntity() instanceof PlayerEntity)){
            event.setCanceled(true);
            return;
        }
        if(event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof LichEntity)){
            LivingEntity livingEntity = (LivingEntity) event.getEntity();
           /* ChampionCapability.getCapability(livingEntity).ifPresent(c -> {
                var api = c.getServer();
                if(api.getRank().isEmpty() || api.getRank().get().getTier() == 0){
                    event.setCanceled(true);
                }
            });*/
            if(livingEntity.getLootTable().getNamespace().equals("divinerpg")){
                if(!canSpawnDivingRPGMob(livingEntity)){
                    event.setCanceled(true);
                    return;
                }
            }
            ItemStack headItem = livingEntity.getItemBySlot(EquipmentSlotType.HEAD);
            if(headItem.getItem() instanceof SkullItem){
                livingEntity.setItemSlot(EquipmentSlotType.HEAD,ItemStack.EMPTY);
            }
            try{
                /*ChampionCapability.getCapability(livingEntity).ifPresent(cap -> {
                    double level = MathUtils.getRangePlayerAverageIndex(livingEntity,64);
                    if(level <= 1.25){
                        cap.getServer().setRank(new Rank());
                    }
                });*/
            }catch (RuntimeException e){
                EMCWorld.LOGGER.fatal("Oh...This is all Biggest_Xuan's fault");
            }
        }
    }

    private static boolean canSpawnDivingRPGMob(Entity entity){
        AxisAlignedBB aabb = MathUtils.expandAABB(entity.position(),128);
        List<PlayerEntity> players = entity.level.getLoadedEntitiesOfClass(PlayerEntity.class,aabb);
        for(PlayerEntity p : players){
            if(!GameStageManager.hasStage(p,"two") && entity.level.dimension().equals(World.OVERWORLD)){
                return false;
            }
            if(PlayerCuriosUtils.hasExorcismCandle(p)){
                if(entity.getType().equals(RatsEntityRegistry.PLAGUE_DOCTOR)){
                    return false;
                }
                return false;
            }
        }
        return true;
    }
}
