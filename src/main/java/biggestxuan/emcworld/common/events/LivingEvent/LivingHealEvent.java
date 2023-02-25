package biggestxuan.emcworld.common.events.LivingEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/25
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.equipment.armor.IHealBoostArmor;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class LivingHealEvent {
    @SubscribeEvent
    public static void livingHealEvent(net.minecraftforge.event.entity.living.LivingHealEvent event){
        LivingEntity livingEntity = event.getEntityLiving();
        float amount = event.getAmount();
        if(livingEntity.getCommandSenderWorld().isClientSide) return;
        if(livingEntity instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) livingEntity;
            IPlayerSkillCapability cap = player.getCapability(EMCWorldCapability.PLAYER_LEVEL).orElseThrow(NullPointerException::new);
            int[] skill = cap.getSkills();
            if(MathUtils.isMaxDifficulty()){
                amount *= 0.67f;
            }
            if(cap.getProfession() == 2){
                if(skill[24] !=0){
                    amount *= 1+skill[24]/10000f;
                }
            }
            List<? extends PlayerEntity> allPlayer = player.getCommandSenderWorld().players();
            for(PlayerEntity player1 : allPlayer){
                IPlayerSkillCapability cap1 = player1.getCapability(EMCWorldCapability.PLAYER_LEVEL).orElseThrow(NullPointerException::new);
                if(cap1.getProfession() == 2 && cap1.getSkills()[36] !=0 && cap1.getModify() == 2 && MathUtils.isTwoLivingDistance(player,player1,5.0d)){
                    amount *= 1+(cap1.getSkills()[36]/10000f * cap1.getSkills()[24]/10000f);
                    break;
                }
            }
            for(ItemStack s : player.inventory.armor){
                if(s.getItem() instanceof IHealBoostArmor){
                    IHealBoostArmor armor = (IHealBoostArmor) s.getItem();
                    amount *= armor.getHealBoostRate(s);
                }
            }
        }
        else{
            amount *= 1.33f;
        }
        event.setAmount(amount);
    }
}
