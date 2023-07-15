package biggestxuan.emcworld.common.events.LivingEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/17
 */

import L_Ender.cataclysm.init.ModItems;
import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.events.PlayerEvent.PlayerTickEvent;
import biggestxuan.emcworld.common.registry.EWDamageSource;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.RaidUtils;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.raid.Raid;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.champions.common.capability.ChampionCapability;

import java.util.List;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class LivingDeathDropsEvent {
    @SubscribeEvent
    public static void livingDeathDrop(LivingDropsEvent event){
        LivingEntity entity = event.getEntityLiving();
        if(entity.level.isClientSide) return;
        DamageSource source = event.getSource();
        ResourceLocation rl = entity.getLootTable();
        if(entity instanceof AbstractRaiderEntity){
            AbstractRaiderEntity entity1 = (AbstractRaiderEntity) entity;
            Raid raid = RaidUtils.getRaid(entity1);
            if(raid != null && (source.getDirectEntity() instanceof PlayerEntity || source instanceof EWDamageSource) && !raid.isLoss()){
                entity1.getCapability(EMCWorldCapability.ENTITY_UTIL).ifPresent(cap -> {
                    //EMCWorld.LOGGER.info("test2 successful!");
                    if(cap.isRaidEntity()){
                        //EMCWorld.LOGGER.info("test3 successful!");
                        List<PlayerTickEvent.RaidInfo> info = RaidUtils.getRaidAllPlayers(raid);
                        if(info.size() >= 1){
                            PlayerEntity player = info.get(0).getPlayer();
                            double chance = RaidUtils.getIllagerShardDropRate(entity1,player);
                            if(MathUtils.isRandom(chance)){
                                addDrops(entity1,EWItems.ILLAGER_SHARD.get(),1);
                            }
                        }
                    }
                });
            }
        }
        if(rl.equals(new ResourceLocation("divinerpg","entities/desert_crawler"))){
            event.setCanceled(true);
        }
        if(rl.equals(new ResourceLocation("cataclysm","entities/ignis"))){
            int base = 6;
            if(MathUtils.isRandom(0.5)) base++;
            if(MathUtils.isRandom(0.5)) base++;
            if(source.getEntity() instanceof PlayerEntity){
                PlayerEntity player = (PlayerEntity) source.getEntity();
                for (int i = 0; i < EnchantmentHelper.getMobLooting(player); i++) {
                    base++;
                }
            }
            for (int i = 0; i < base; i++) {
                if(entity.level == null){
                    break;
                }
                addDrops(entity,ModItems.IGNITIUM_INGOT.get());
            }
            if(MathUtils.isRandom(0.1 * MathUtils.difficultyLoss())){
                addDrops(entity,getRandomArmor());
            }
            addDrops(entity,ModItems.BULWARK_OF_THE_FLAME.get());
        }
        if(rl.equals(EMCWorld.rl("entities/tulye")) && MathUtils.isRandom(0.33)){
            addDrops(entity,EWItems.SCROLL_TULYE.get());
        }
        if(rl.equals(new ResourceLocation("cataclysm","entities/ender_golem")) && MathUtils.isRandom(0.5)){
            addDrops(entity,ModItems.ENDERITE_INGOT.get());
        }
        if(rl.equals(new ResourceLocation("cataclysm","entities/ender_guardian"))){
            addDrops(entity,ModItems.ENDERITE_INGOT.get(),3);
            if(MathUtils.isRandom(0.5)){
                addDrops(entity,ModItems.ENDERITE_INGOT.get());
            }
        }
        ChampionCapability.getCapability(entity).ifPresent(iChampion -> iChampion.getServer().getRank().ifPresent(rank -> {
            int tier = rank.getTier();
            switch (tier){
                case 1:
                    addDrops(entity,EWItems.BIG_EMC_GEM,MathUtils.getRangeRandom(1,32));
                    break;
                case 2:
                    addDrops(entity,EWItems.BIGGEST_EMC_GEM,MathUtils.getRangeRandom(1,24));
                    break;
                case 3:
                    addDrops(entity,EWItems.BIGGEST_EMC_GEM,MathUtils.getRangeRandom(1,64));
                    addDrops(entity,EWItems.BIGGEST_EMC_GEM,MathUtils.getRangeRandom(1,32));
                    if(MathUtils.isMaxDifficulty()){
                        addDrops(entity,EWItems.ADVANCED_EMC_GEM,MathUtils.getRangeRandom(1,8));
                    }
                    break;
                case 4:
                    addDrops(entity,EWItems.ADVANCED_EMC_GEM,MathUtils.getRangeRandom(1,24));
                    break;
                case 5:
                    addDrops(entity,EWItems.ADVANCED_EMC_GEM,MathUtils.getRangeRandom(1, (int) Math.round(20 * ConfigManager.DIFFICULTY.get())));
                    break;
                case 6:
                    addDrops(entity,EWItems.SUPER_EMC_GEM,MathUtils.getRangeRandom(1, (int) Math.round(8 * ConfigManager.DIFFICULTY.get())));
                    if(MathUtils.isMaxDifficulty()){
                        addDrops(entity,EWItems.SUPER_EMC_GEM,MathUtils.getRangeRandom(1,5));
                    }
                    break;
                case 7:
                    addDrops(entity,EWItems.EPIC_EMC_GEM,MathUtils.getRangeRandom(1,12));
                    if(MathUtils.isMaxDifficulty()){
                        addDrops(entity,EWItems.SUPER_EMC_GEM,MathUtils.getRangeRandom(1,8));
                        addDrops(entity,EWItems.EPIC_EMC_GEM,MathUtils.getRangeRandom(0,3));
                    }
                    break;
                case 8:
                    addDrops(entity,EWItems.EPIC_EMC_GEM,MathUtils.getRangeRandom(1,16));
                    if(MathUtils.isMaxDifficulty()){
                        addDrops(entity,EWItems.EPIC_EMC_GEM,MathUtils.getRangeRandom(1,4));
                        if(MathUtils.isRandom(0.15)){
                            addDrops(entity,EWItems.INFINITY_EMC_GEM,MathUtils.getRangeRandom(1,4));
                        }
                    }
                    break;
            }
        }));
        if(rl.equals(new ResourceLocation("dungeonsmod","entites/voidmaster"))){
            addDrops(entity,ModItems.VOID_CORE.get());
            event.setCanceled(true);
        }
    }

    private static void addDrops(LivingEntity entity,Item item){
        addDrops(entity,item,1);
    }

    private static void addDrops(LivingEntity entity, RegistryObject<Item> item, int count){
        addDrops(entity,item.get(),count);
    }

    private static void addDrops(LivingEntity entity,Item item,int count){
        addDrops(entity,new ItemStack(item,count));
    }

    private static void addDrops(LivingEntity entity,ItemStack stack){
        entity.level.addFreshEntity(new ItemEntity(entity.level,entity.getX(),entity.getY(),entity.getZ(),stack));
    }

    private static ItemStack getRandomArmor(){
        double random = Math.random();
        Item item;
        if(random <0.25){
            item = ModItems.IGNITIUM_HELMET.get();
        }else if(random <0.5){
            item = ModItems.IGNITIUM_CHESTPLATE.get();
        }else if(random <0.75){
            item = ModItems.IGNITIUM_LEGGINGS.get();
        }else item = ModItems.IGNITIUM_BOOTS.get();
        return new ItemStack(item);
    }
}
