package biggestxuan.emcworld.common.compact.AstralSorcery.Perk;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/09
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import hellfirepvp.astralsorcery.common.perk.type.PerkAttributeType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class ArmorPerk extends PerkAttributeType {
    public ArmorPerk() {
        super(EMCWorld.rl("astral_tool_perk"));
    }

    @Override
    protected void attachListeners(IEventBus eventBus) {
        eventBus.addListener(this::livingHurtEvent);
    }

    private void livingHurtEvent(LivingHurtEvent event){
        LivingEntity entity = event.getEntityLiving();
        float damage = event.getAmount();
        if(entity instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) entity;
            if(EMCHelper.getPlayerEMC(player) > Math.pow(2,damage)){
                damage *= 0.75f;
            }
        }
        event.setAmount(damage);
    }
}
