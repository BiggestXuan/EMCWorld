package biggestxuan.emcworld.common.events.LivingEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/25
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.equipment.ISuckerItem;
import biggestxuan.emcworld.api.item.equipment.armor.IHealBoostArmor;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.trait.ITrait;
import biggestxuan.emcworld.api.trait.TraitType;
import biggestxuan.emcworld.common.raid.RaidEffectExecutor;
import biggestxuan.emcworld.common.traits.TraitUtils;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.raid.Raid;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class LivingHealEvent {
    @SubscribeEvent
    public static void livingHealEvent(net.minecraftforge.event.entity.living.LivingHealEvent event){
        LivingEntity livingEntity = event.getEntityLiving();
        World world = livingEntity.level;
        float amount = event.getAmount();
        if(livingEntity.getCommandSenderWorld().isClientSide) return;
        ServerWorld world1 = (ServerWorld) world;
        if(livingEntity instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) livingEntity;
            ItemStack mainStack = player.getMainHandItem();
            ItemStack offStack = player.getOffhandItem();
            BlockPos pos = player.blockPosition();
            if(mainStack.getItem() instanceof ISuckerItem || offStack.getItem() instanceof ISuckerItem){
                event.setAmount(amount);
                return;
            }
            if(world1.isRaided(pos)){
                Raid raid = world1.getRaidAt(pos);
                if(raid != null){
                    amount = new RaidEffectExecutor(raid).onPlayerHeal(player,amount);
                }
            }
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
            double rate = 1;

            for(ITrait t : TraitUtils.getStackTraits(mainStack)){
                if(t.getTraitType() == TraitType.TOOL){
                    amount = t.onHeal(player,amount,mainStack);
                }
            }
            for(ItemStack s : player.inventory.armor){
                if(s.getItem() instanceof IHealBoostArmor){
                    IHealBoostArmor armor = (IHealBoostArmor) s.getItem();
                    rate += armor.getHealBoostRate(s)-1;
                }
                for(ITrait t : TraitUtils.getStackTraits(s)){
                    if(t.getTraitType() == TraitType.ARMOR){
                        amount = t.onHeal(player,amount,s);
                    }
                }
            }
            amount *= Math.max(0,rate);

        }
        else{
            amount *= 1.33f;
        }
        event.setAmount(amount);
    }
}
