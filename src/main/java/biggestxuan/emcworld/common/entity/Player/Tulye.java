package biggestxuan.emcworld.common.entity.Player;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/01
 */

import biggestxuan.emcworld.api.entity.PlayerRaidBaseEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

public class Tulye extends PlayerRaidBaseEntity {
    public Tulye(EntityType<? extends PlayerRaidBaseEntity> entityType, World world) {
        super(entityType,world,"tulye");
    }

    public static AttributeModifierMap.MutableAttribute create(){
        return createMobAttributes().add(Attributes.MAX_HEALTH,100F).add(Attributes.ATTACK_DAMAGE,12D).add(Attributes.MOVEMENT_SPEED,0.7D)
                .add(Attributes.FOLLOW_RANGE,30.0).add(Attributes.ARMOR,20F);
    }
}
