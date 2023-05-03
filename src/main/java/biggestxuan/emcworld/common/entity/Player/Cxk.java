package biggestxuan.emcworld.common.entity.Player;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/02
 */

import biggestxuan.emcworld.api.entity.PlayerBaseEntity;
import biggestxuan.emcworld.common.registry.EWSounds;
import com.teammetallurgy.atum.entity.stone.StoneBaseEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class Cxk extends PlayerBaseEntity {
    public Cxk(EntityType<? extends StoneBaseEntity> entityType, World world) {
        super(entityType, world,"cxk");
    }

    @Override
    protected SoundEvent getDeathSound() {
        return EWSounds.CXK_DEATH.get();
    }
}
