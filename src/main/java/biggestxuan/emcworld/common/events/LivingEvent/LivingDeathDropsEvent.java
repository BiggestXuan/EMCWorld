package biggestxuan.emcworld.common.events.LivingEvent;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/17
 */

import L_Ender.cataclysm.init.ModItems;
import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class LivingDeathDropsEvent {
    @SubscribeEvent
    public static void livingDeathDrop(LivingDropsEvent event){
        LivingEntity entity = event.getEntityLiving();
        if(entity.level.isClientSide) return;
        DamageSource source = event.getSource();
        ResourceLocation rl = entity.getLootTable();
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
        if(rl.equals(new ResourceLocation("dungeomsmod","entites/voidmaster"))){
            addDrops(entity,ModItems.VOID_CORE.get());
            event.setCanceled(true);
        }
    }

    private static void addDrops(LivingEntity entity,Item item){
        addDrops(entity,item,1);
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
