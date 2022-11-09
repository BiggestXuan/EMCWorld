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
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
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
                entity.level.addFreshEntity(new ItemEntity(entity.level,x,y,z,new ItemStack(ModItems.IGNITIUM_INGOT.get())));
            }
            if(MathUtils.isRandom(0.1 * MathUtils.difficultyLoss())){
                entity.level.addFreshEntity(new ItemEntity(entity.level,x,y,z,getRandomArmor()));
            }
            entity.level.addFreshEntity(new ItemEntity(entity.level,x,y,z,new ItemStack(ModItems.BULWARK_OF_THE_FLAME.get())));
        }
        if(rl.equals(EMCWorld.rl("entities/tulye")) && MathUtils.isRandom(0.33)){
            entity.level.addFreshEntity(new ItemEntity(entity.level,x,y,z,new ItemStack(EWItems.SCROLL_TULYE.get())));
        }
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
