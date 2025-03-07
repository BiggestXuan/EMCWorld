package biggestxuan.emcworld.common.events.PlayerEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/28
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.api.event.PlayerEMCShiedCostEvent;
import biggestxuan.emcworld.api.item.equipment.armor.IEMCShieldArmor;
import biggestxuan.emcworld.common.compact.Curios.PlayerCuriosUtils;
import biggestxuan.emcworld.common.registry.EWDamageSource;
import biggestxuan.emcworld.common.utils.MathUtils;
import com.google.common.util.concurrent.AtomicDouble;
import mekanism.api.Coord4D;
import mekanism.api.MekanismAPI;
import mekanism.api.radiation.IRadiationManager;
import mekanism.common.lib.radiation.RadiationManager;
import mekanism.common.registries.MekanismDamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
//import top.theillusivec4.champions.common.capability.ChampionCapability;

import java.util.ArrayList;
import java.util.List;

import static biggestxuan.emcworld.common.events.PlayerEvent.PlayerHurtEvent.getPlayerHurtRate;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID)
public class PlayerWillHurtEvent {
    @SubscribeEvent
    public static void LivingAttackEvent(LivingAttackEvent event){
        LivingEntity entity = event.getEntityLiving();
        if(entity.level.isClientSide || !(entity instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity) entity;
        if(player.isDeadOrDying()){
            return;
        }
        IPlayerSkillCapability cap = EMCWorldAPI.getInstance().getPlayerSkillCapability(player);
        IUtilCapability util = EMCWorldAPI.getInstance().getUtilCapability(player);
        int[] skills = cap.getSkills();
        if(cap.getProfession() == 4){
            double chance = skills[0]/10000d;
            if(cap.getModify() == 2){
                int time = util.getTimer();
                if(time > 0){
                    chance *= skills[33]/10000d;
                }
                if(cap.getSkills()[40] != 0){
                    chance *= 2;
                }
            }
            if(MathUtils.isRandom(chance)){
                event.setCanceled(true);
                float rate = skills[28]/10000f;
                player.heal(player.getMaxHealth() * rate);
                if(cap.getModify() == 1 && skills[36] != 0){
                    double c = skills[36]/10000d;
                    if(MathUtils.isRandom(c)){
                        util.setTimer(EMCWorld.HOMO);
                    }
                }
            }
        }
        if(MathUtils.canAbsorbHurt(player,event.getSource())){
            event.setCanceled(true);
            return;
        }
        if(costEMCShield(player,event.getAmount(),event.getSource())){
            event.setCanceled(true);
        }
        if(event.getSource().equals(MekanismDamageSource.RADIATION)){
            float amount = event.getAmount();
            if(util.getShield() >= amount * 2){
                if(costEMCShield(player,amount * 2,event.getSource())){
                    event.setCanceled(true);
                }
                IRadiationManager manager = RadiationManager.INSTANCE;
                if(util.getShield() * 100 >= manager.getRadiationLevel(new Coord4D(player)) && costEMCShield(player,amount * 10,event.getSource())){
                    RadiationManager.INSTANCE.removeRadiationSource(new Coord4D(player));
                }
            }
        }
    }

    private static boolean costEMCShield(PlayerEntity player, float amount, DamageSource source){
        PlayerEMCShiedCostEvent event = new PlayerEMCShiedCostEvent(player,amount,source);
        MinecraftForge.EVENT_BUS.post(event);
        if(source instanceof EWDamageSource || source.equals(DamageSource.OUT_OF_WORLD) || player.isCreative()){
            return false;
        }
        if(player.hurtTime > 0){
            return true;
        }
        amount = event.getAmt();
        if(MathUtils.isMaxDifficulty()) amount *= 1.05f;
        if(source.getDirectEntity() instanceof ProjectileEntity){
            amount = MathUtils.getAdditionDamage(source.getDirectEntity(),player,amount);
        }
        try{
            IPlayerSkillCapability cap = EMCWorldAPI.getInstance().getPlayerSkillCapability(player);
            if(cap.getProfession() == 2){
                amount *= (1-getPlayerHurtRate(player));
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
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
        ItemStack stack1 = PlayerCuriosUtils.getPlayerEMCShield(player);
        if(!stack1.equals(ItemStack.EMPTY) && stack1.getItem() instanceof IEMCShieldArmor){
            IEMCShieldArmor shieldArmor = (IEMCShieldArmor) stack1.getItem();
            shield += shieldArmor.getShield(stack1);
            maxShield += shieldArmor.getMaxShield(stack1);
        }
        AtomicDouble rate = new AtomicDouble(1);
        if(shield < amount) return false;
        if(source.getDirectEntity() instanceof LivingEntity && !(source.getDirectEntity() instanceof PlayerEntity)){
            LivingEntity living = (LivingEntity) source.getDirectEntity();
            /*ChampionCapability.getCapability(living).ifPresent(iChampion -> iChampion.getServer().getAffixes().forEach(affix -> {
                if(affix.getIdentifier().equals("shield_flaming")){
                    iChampion.getServer().getRank().ifPresent(rank -> rate.set(1 + rank.getTier() / 3f));
                }
            }));*/
        }
        amount *= rate.get();
        if(maxShield <= 0){
            return false;
        }
        for (ItemStack stack : armors) {
            IEMCShieldArmor armor = (IEMCShieldArmor) armors.get(0).getItem();
            armor.modifyShield(stack, negateExact(amount * armor.getMaxShield(stack) / maxShield));
        }
        if(!stack1.equals(ItemStack.EMPTY)){
            IEMCShieldArmor armor = (IEMCShieldArmor) stack1.getItem();
            armor.modifyShield(stack1,negateExact(amount * armor.getMaxShield(stack1) / maxShield));
        }
        player.playSound(SoundEvents.ANVIL_BREAK,1,1);
        player.hurtTime += 15;
        return true;
    }

    private static float negateExact(float value){
        return -value;
    }
}
