package biggestxuan.emcworld.common.events.PlayerEvent;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/28
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.equipment.armor.IEMCShieldArmor;
import biggestxuan.emcworld.common.compact.Curios.PlayerCurios;
import biggestxuan.emcworld.common.registry.EWDamageSource;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID)
public class PlayerWillHurtEvent {
    @SubscribeEvent
    public static void LivingAttackEvent(LivingAttackEvent event){
        LivingEntity entity = event.getEntityLiving();
        if(entity.level.isClientSide || !(entity instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity) entity;
        if(costEMCShield(player,event.getAmount(),event.getSource())){
            event.setCanceled(true);
        }
    }

    private static boolean costEMCShield(PlayerEntity player, float amount, DamageSource source){
        if(source instanceof EWDamageSource || source.equals(DamageSource.OUT_OF_WORLD) || player.isCreative()){
            return false;
        }
        if(MathUtils.isMaxDifficulty()) amount *= 1.167f;
        float shield = 0f;
        float maxShield = 0f;
        List<ItemStack> armors = new ArrayList<>();
        for(ItemStack stack :player.inventory.armor){
            if(stack.getItem() instanceof IEMCShieldArmor){
                armors.add(stack);
                IEMCShieldArmor armor = (IEMCShieldArmor) stack.getItem();
                shield += armor.getShield(stack);
                maxShield += armor.getMaxShield(stack);
            }
        }
        ItemStack stack1 = PlayerCurios.getPlayerEMCShield(player);
        if(!stack1.equals(ItemStack.EMPTY) && stack1.getItem() instanceof IEMCShieldArmor){
            IEMCShieldArmor shieldArmor = (IEMCShieldArmor) stack1.getItem();
            shield += shieldArmor.getShield(stack1);
            maxShield += shieldArmor.getMaxShield(stack1);
        }
        if(shield < amount) return false;
        for (ItemStack stack : armors) {
            IEMCShieldArmor armor = (IEMCShieldArmor) armors.get(0).getItem();
            armor.modifyShield(stack, negateExact(amount * armor.getMaxShield(stack) / maxShield));
        }
        if(!stack1.equals(ItemStack.EMPTY)){
            IEMCShieldArmor armor = (IEMCShieldArmor) stack1.getItem();
            armor.modifyShield(stack1,negateExact(amount * armor.getMaxShield(stack1) / maxShield));
        }
        return true;
    }

    private static float negateExact(float value){
        return 0 - value;
    }
}
