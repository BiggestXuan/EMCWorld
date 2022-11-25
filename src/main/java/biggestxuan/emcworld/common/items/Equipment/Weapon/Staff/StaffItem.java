package biggestxuan.emcworld.common.items.Equipment.Weapon.Staff;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/21
 */

import biggestxuan.emcworld.api.item.INeedLevelItem;
import biggestxuan.emcworld.api.item.equipment.staff.IStaffTier;
import biggestxuan.emcworld.api.item.equipment.weapon.ICriticalWeapon;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import biggestxuan.emcworld.common.registry.EWDamageSource;
import biggestxuan.emcworld.common.utils.MathUtils;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.common.entity.EntityManaBurst;

import javax.annotation.Nonnull;
import java.util.List;

public class StaffItem extends TieredItem implements ILensEffect, ICriticalWeapon, INeedLevelItem {
    protected final IStaffTier tier;
    private final ImmutableMultimap<Attribute, AttributeModifier> defaultModifiers;

    public StaffItem(IStaffTier p_i48459_1_) {
        super(p_i48459_1_,new Properties().tab(EWCreativeTabs.EW_EQUIPMENT_TAB));
        this.tier = p_i48459_1_;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", tier.getSpeed(), AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    public void spawnManaBurst(PlayerEntity player){
        EntityManaBurst burst = new EntityManaBurst(player);
        ItemStack stack = player.getMainHandItem();
        if(!(stack.getItem() instanceof StaffItem)){
            return;
        }
        long cost = (long) (MathUtils.getAttackBaseCost(player) * MathUtils.difficultyLoss() * getManaBurstDamage(stack) * costEMCWhenAttack(stack));
        double s = getManaBurstSpeed(stack);
        burst.setColor(getColor());
        burst.setMana(100);
        burst.setStartingMana(100);
        burst.setGravity(0);
        burst.setMinManaLoss(40);
        burst.setManaLossPerTick(1.5F);
        burst.setSourceLens(stack.copy());
        burst.setBurstMotion(burst.getDeltaMovement().x*s,burst.getDeltaMovement().y*s,burst.getDeltaMovement().z*s);
        if(EMCHelper.getPlayerEMC(player) >= cost){
            EMCHelper.modifyPlayerEMC(player,Math.negateExact(cost),true);
            player.level.addFreshEntity(burst);
        }
    }

    protected double costEMCWhenAttack(ItemStack stack) {
        return 1d;
    }

    public float getBaseDamage(ItemStack stack){
        return tier.getAttackDamageBonus();
    }

    private float getManaBurstDamage(ItemStack stack){
        float damage = tier.getAttackDamageBonus();
        if(MathUtils.isRandom(getActCriticalChance(stack))){
            damage *= getActCriticalRate(stack);
        }
        return damage;
    }

    protected double getManaBurstSpeed(ItemStack stack){
        return tier.getBurstSpeed();
    }

    private int getColor(){
        return tier.getColor();
    }

    @Override
    public double getCriticalChance(ItemStack stack) {
        return tier.getCriticalChance();
    }

    @Override
    public double getCriticalRate(ItemStack stack) {
        return tier.getCriticalRate();
    }

    @Override
    public void apply(ItemStack stack, BurstProperties props) {

    }

    @Override
    public boolean collideBurst(IManaBurst burst, RayTraceResult pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        return dead;
    }

    @Override
    public void updateBurst(@Nonnull IManaBurst burst, ItemStack stack) {
        ThrowableEntity entity = burst.entity();
        AxisAlignedBB axis = new AxisAlignedBB(entity.getX(), entity.getY(), entity.getZ(), entity.getX()+1, entity.getY()+1, entity.getZ()+1);
        List<LivingEntity> entities = entity.level.getEntitiesOfClass(LivingEntity.class, axis);
        Entity thrower = entity.getOwner();
        for (LivingEntity living : entities) {
            if (living.equals(thrower)) {
                continue;
            }
            if (living.hurtTime == 0) {
                int cost = 100 / 3;
                int mana = burst.getMana();
                if (mana >= cost) {
                    burst.setMana(mana - cost);
                    float damage = getManaBurstDamage(stack);
                    if (!burst.isFake() && !entity.level.isClientSide) {
                        DamageSource source = EWDamageSource.REALLY;
                        living.hurt(source, damage);
                        entity.remove();
                        break;
                    }
                }
            }
        }
    }

    @Override
    public boolean doParticles(IManaBurst burst, ItemStack stack) {
        return true;
    }

    @Override
    public int getUseLevel(ItemStack stack){
        return tier.getUseLevel();
    }

    @Nonnull
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@Nonnull EquipmentSlotType p_111205_1_) {
        return p_111205_1_ == EquipmentSlotType.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(p_111205_1_);
    }

    @Override
    public boolean canAttackBlock(BlockState p_195938_1_, World p_195938_2_, BlockPos p_195938_3_, PlayerEntity p_195938_4_) {
        return false;
    }

    @Override
    public boolean hurtEnemy(ItemStack p_77644_1_, LivingEntity p_77644_2_, LivingEntity p_77644_3_) {
        return false;
    }
}
