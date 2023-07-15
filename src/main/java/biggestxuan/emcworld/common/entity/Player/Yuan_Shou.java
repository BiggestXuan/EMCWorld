package biggestxuan.emcworld.common.entity.Player;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.events.PlayerEvent.PlayerClickEvent;
import biggestxuan.emcworld.common.registry.EWEntities;
import biggestxuan.emcworld.common.utils.CalendarUtils;
import com.teammetallurgy.atum.entity.ITexture;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/06/25
 */

@EMCWorldSince("0.9.0")
public class Yuan_Shou extends TameableEntity implements ITexture {
    public Yuan_Shou(EntityType<? extends TameableEntity> p_i48574_1_, World p_i48574_2_) {
        super(p_i48574_1_, p_i48574_2_);
    }

    @Override
    public void die(DamageSource p_70645_1_) {
        if(p_70645_1_.getDirectEntity() instanceof ServerPlayerEntity){
            CalendarUtils utils = CalendarUtils.INSTANCE;
            if(utils.getYear() >= 2099){
                EMCHelper.awardAdvancement((ServerPlayerEntity) p_70645_1_.getDirectEntity(),EMCWorld.rl("entity/yuan_shou_kill"));
            }
        }
        super.die(p_70645_1_);
    }

    @Override
    public void tame(PlayerEntity p_193101_1_) {
        if(p_193101_1_ instanceof ServerPlayerEntity){
            ServerPlayerEntity player = (ServerPlayerEntity) p_193101_1_;
            EMCHelper.awardAdvancement(player,EMCWorld.rl("entity/yuan_shou"));
        }
        super.tame(p_193101_1_);
    }

    @Override
    public boolean isFood(ItemStack p_70877_1_) {
        return p_70877_1_.getItem().equals(Items.CAKE);
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        //return EWEntities.yuan_shou.create(p_241840_1_);
        return this;
    }

    @Override
    protected void registerGoals(){
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.25D, Ingredient.of(Items.CAKE), false));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtGoal(this, LivingEntity.class, 6.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
    }

    @Override
    public void tick(){
        super.tick();
        if(!level.isClientSide){
            setBaby(true);
            List<? extends PlayerEntity> list = PlayerClickEvent.getPlayers(this,5);
            list.forEach(p -> p.addEffect(new EffectInstance(Effects.REGENERATION,4,50)));
        }
    }

    @Override
    public boolean isBaby() {
        return true;
    }

    @Override
    public String getTexture() {
        return EMCWorld.rl("textures/entity/yuan_shou.png").toString();
    }

    public static AttributeModifierMap.MutableAttribute create(){
        return createMobAttributes().add(Attributes.MAX_HEALTH,60F).add(Attributes.MOVEMENT_SPEED,0.15D)
                .add(Attributes.FOLLOW_RANGE,30.0);
    }
}
