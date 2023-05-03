package biggestxuan.emcworld.api.entity;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/01
 */

import biggestxuan.emcworld.EMCWorld;
import com.teammetallurgy.atum.entity.ITexture;
import com.teammetallurgy.atum.entity.stone.StoneBaseEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

public abstract class PlayerBaseEntity extends StoneBaseEntity implements ITexture {
    private final String name;

    public PlayerBaseEntity(EntityType<? extends StoneBaseEntity> entityType, World world, String name) {
        super(entityType, world);
        this.name = name;
    }

    @Override
    public String getTexture() {
        return EMCWorld.rl("textures/entity/"+name+".png").toString();
    }

    public static AttributeModifierMap.MutableAttribute create(){
        return createMobAttributes().add(Attributes.MAX_HEALTH,30F).add(Attributes.ATTACK_DAMAGE,8D).add(Attributes.MOVEMENT_SPEED,0.3D)
                .add(Attributes.FOLLOW_RANGE,30.0).add(Attributes.ARMOR,3F);
    }
}
