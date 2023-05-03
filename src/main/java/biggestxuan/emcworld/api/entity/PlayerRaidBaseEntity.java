package biggestxuan.emcworld.api.entity;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/17
 */

import biggestxuan.emcworld.EMCWorld;
import com.teammetallurgy.atum.entity.ITexture;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public abstract class PlayerRaidBaseEntity extends AbstractRaiderEntity implements ITexture {

    private final String name;

    public PlayerRaidBaseEntity(EntityType<? extends AbstractRaiderEntity> entityType, World world, String name) {
        super(entityType, world);
        super.registerGoals();
        this.name = name;
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this,1d,true));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, AbstractRaiderEntity.class)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute create(){
        return createMobAttributes().add(Attributes.MAX_HEALTH,100F).add(Attributes.ATTACK_DAMAGE,14D).add(Attributes.MOVEMENT_SPEED,0.3D)
                .add(Attributes.FOLLOW_RANGE,30.0).add(Attributes.ARMOR,20F);
    }

    @Override
    public void applyRaidBuffs(int p_213660_1_, boolean p_213660_2_) {
    }

    @Nonnull
    @Override
    public SoundEvent getCelebrateSound() {
        return SoundEvents.PILLAGER_CELEBRATE;
    }

    @Override
    public String getTexture() {
        return EMCWorld.rl("textures/entity/"+name+".png").toString();
    }

}
