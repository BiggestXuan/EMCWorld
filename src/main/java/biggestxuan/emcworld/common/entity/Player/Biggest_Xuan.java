package biggestxuan.emcworld.common.entity.Player;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/01
 */

import biggestxuan.emcworld.api.entity.PlayerRaidBaseEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class Biggest_Xuan extends PlayerRaidBaseEntity {
    public Biggest_Xuan(EntityType<? extends PlayerRaidBaseEntity> entityType, World world) {
        super(entityType,world,"biggest_xuan");
    }
}
