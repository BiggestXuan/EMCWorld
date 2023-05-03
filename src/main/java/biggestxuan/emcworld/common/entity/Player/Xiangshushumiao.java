package biggestxuan.emcworld.common.entity.Player;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/01
 */

import biggestxuan.emcworld.api.entity.PlayerBaseEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class Xiangshushumiao extends PlayerBaseEntity {
    public Xiangshushumiao(EntityType<? extends PlayerBaseEntity> entityType, World world) {
        super(entityType,world,"xiangshushumiao");
    }

}
