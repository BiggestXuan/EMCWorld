package biggestxuan.emcworld.common.entity.Player;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/01
 */

import biggestxuan.emcworld.api.entity.PlayerBaseEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class MCyunxi extends PlayerBaseEntity {
    public MCyunxi(EntityType<? extends PlayerBaseEntity> entityType, World world) {
        super(entityType,world,"mcyunxi");
    }
}
