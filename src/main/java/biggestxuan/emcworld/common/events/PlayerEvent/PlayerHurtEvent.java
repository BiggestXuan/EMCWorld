package biggestxuan.emcworld.common.events.PlayerEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/03
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.equipment.armor.IUpgradeableArmor;
import biggestxuan.emcworld.client.Message;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.entity.Player.Dctor_0415;
import biggestxuan.emcworld.common.entity.Player.Tulye;
import biggestxuan.emcworld.api.entity.PlayerRaidBaseEntity;
import biggestxuan.emcworld.common.registry.EWEffects;
import biggestxuan.emcworld.common.utils.DifficultySetting;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vazkii.botania.common.brew.ModPotions;

import java.util.List;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerHurtEvent {
    @SubscribeEvent
    public static void playerHurtEvent(LivingHurtEvent event){
        DamageSource source = event.getSource();
        if(event.getEntityLiving() instanceof PlayerEntity && !source.equals(DamageSource.OUT_OF_WORLD)){
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();
            if(player.getCommandSenderWorld().isClientSide) return;
            if(source.getEntity() instanceof PlayerRaidBaseEntity){
                PlayerRaidBaseEntity entity = (PlayerRaidBaseEntity) source.getEntity();
                if(entity instanceof Tulye){
                    player.addEffect(new EffectInstance(ModPotions.bloodthrst,1200,2));
                    if(!player.hasEffect(ModPotions.bloodthrst)){
                        Message.sendMessage(player,EMCWorld.tc("message.hurt.tulye"));
                    }
                }
                if(entity instanceof Dctor_0415){
                    player.addEffect(new EffectInstance(Effects.SLOW_FALLING,40,1));
                }
            }
            float amount = event.getAmount();
            if(MathUtils.isMaxDifficulty()){
                amount *= 1.33f;
            }
            IPlayerSkillCapability cap = player.getCapability(EMCWorldCapability.PLAYER_LEVEL).orElseThrow(NullPointerException::new);
            IUtilCapability util = player.getCapability(EMCWorldCapability.UTIL).orElseThrow(NullPointerException::new);
            int level = cap.getLevel();
            int modify = cap.getModify();
            int[] skill = cap.getSkills();
            if(cap.getProfession() == 1){
                if(cap.getModify() == 1){
                    if(util.getTimer() > 0){
                        amount *= 1 + cap.getSkills()[34]/10000f;
                    }
                }
            }
            if(cap.getProfession() == 2){
                amount *= Math.pow(1-skill[0]/10000f,level);
                if(skill[8] !=0){
                    if(MathUtils.isRandom(skill[8]/10000d)){
                        amount = 0;
                    }
                }
                if(skill[12] !=0){
                    if(MathUtils.isRandom(skill[12]/10000d)){
                        player.heal(amount);
                        amount = 0;
                    }
                }
                if(skill[28] !=0){
                    amount = Math.max(0,amount-skill[28]/10000f);
                }
                if(modify == 1 && skill[36] !=0 && skill[37] !=0){
                    if(source.isMagic() || source.isExplosion() || source.isFire()){
                        event.setCanceled(true);
                    }
                    amount *= 1-skill[36]/10000f;
                }
                if(modify == 1 && util.getTimer() >0){
                    amount = 0f;
                }
            }
            if(cap.getProfession() == 3){
                if(cap.getSkills()[8] != 0){
                    double chance = cap.getSkills()[8]/10000f;
                    if(MathUtils.isRandom(chance)){
                        player.addEffect(new EffectInstance(EWEffects.MAGIC_PROTECT.get(),600,1));
                    }
                }
                if(cap.getModify() == 2 && cap.getSkills()[40] != 0 && cap.getSkills()[41] != 0){
                    if(source.isMagic()){
                        amount = 0;
                        event.setCanceled(true);
                    }
                }
            }
            float armorRate = 0f;
            for(ItemStack stack :player.inventory.armor){
                if(stack.getItem() instanceof IUpgradeableArmor){
                    IUpgradeableArmor armor = (IUpgradeableArmor) stack.getItem();
                    armorRate += 1-armor.hurtRate(stack);
                }
            }
            armorRate /= 4f;
            amount *= (1-armorRate);
            List<? extends PlayerEntity> allPlayer = player.getCommandSenderWorld().players();
            for(PlayerEntity player1 : allPlayer){
                IPlayerSkillCapability cap1 = player1.getCapability(EMCWorldCapability.PLAYER_LEVEL).orElseThrow(NullPointerException::new);
                if(cap1.getProfession() == 2 && cap1.getSkills()[40] !=0 && cap1.getModify() == 2 && MathUtils.isTwoLivingDistance(player,player1,5.0d)){
                    amount *= 1- (cap1.getSkills()[40]/10000f * (Math.pow(1-cap1.getSkills()[0]/10000f,level)));
                    break;
                }
            }
            for(DifficultySetting obj:DifficultySetting.values()){
                if(GameStageManager.hasStage(player, obj.getGameStage())){
                    long costEMC =MathUtils.doubleToLong(obj.getHurtBase() * amount * MathUtils.difficultyLoss());
                    if(event.getAmount() >= player.getHealth()){
                        break;
                    }
                    if(EMCHelper.getPlayerEMC(player)>costEMC){
                        EMCHelper.modifyPlayerEMC(player,Math.negateExact(costEMC),true);
                    }
                    else{
                        event.setAmount(player.getMaxHealth()*1000.0f);
                        //Message.sendMessage(player, EMCWorld.tc("message.evt.hurtcancel",MathUtils.format(String.valueOf(costEMC))));
                        return;
                    }
                    break;
                }
            }
            event.setAmount(amount);
            //EMCWorld.LOGGER.info("Source"+source.toString()+",Amount"+amount);
        }
    }
}
