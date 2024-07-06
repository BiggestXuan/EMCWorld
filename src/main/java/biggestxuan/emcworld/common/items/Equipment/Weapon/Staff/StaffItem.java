package biggestxuan.emcworld.common.items.Equipment.Weapon.Staff;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/21
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.api.item.IEMCInfuserItem;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.equipment.staff.IStaffTier;
import biggestxuan.emcworld.api.item.equipment.weapon.ICriticalWeapon;
import biggestxuan.emcworld.api.item.equipment.weapon.IUpgradeableWeapon;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import biggestxuan.emcworld.common.registry.EWDamageSource;
import biggestxuan.emcworld.common.utils.DamageUtils;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.Message;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.mehvahdjukaar.dummmmmmy.setup.Registry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.common.entity.EntityManaBurst;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class StaffItem extends TieredItem implements IUpgradeableWeapon, ILensEffect, ICriticalWeapon, IPrefixItem {
    protected final IStaffTier tier;
    private final ImmutableMultimap<Attribute, AttributeModifier> defaultModifiers;

    @Override
    public double getEMCCostRate() {
        return 1d;
    }

    public StaffItem(IStaffTier p_i48459_1_) {
        super(p_i48459_1_,new Properties().tab(EWCreativeTabs.EW_EQUIPMENT_TAB));
        this.tier = p_i48459_1_;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", tier.getSpeed(), AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    protected double getPrefixCommonRate(ItemStack stack){
        double b = 1;
        Prefix prefix = getPrefix(stack);
        if(prefix == Prefix.NULL) return b;
        if(prefix.getLevel() <= 3){
            b = 1 - 0.13 * (4 - prefix.getLevel());
        }else{
            b = 0.025 * (prefix.getLevel()-4) + 1;
        }
        return b;
    }

    @Override
    public int getWeightRequired(ItemStack stack){
        return (int) (IUpgradeableWeapon.super.getWeightRequired(stack) * 5.5);
    }

    public double getCostRate(ItemStack stack,PlayerEntity player){
        double costRate = costEMCWhenAttackActually(stack);
        IPlayerSkillCapability cap = EMCWorldAPI.getInstance().getPlayerSkillCapability(player);
        if(cap.getModify() == 2 && cap.getProfession() == 3 && cap.getSkills()[36] != 0){
            double r = cap.getSkills()[36] /10000f;
            costRate *= 1-r;
        }
        return costRate;
    }

    public void spawnManaBurst(PlayerEntity player,double speed){
        EntityManaBurst burst = new EntityManaBurst(player);
        ItemStack stack = player.getMainHandItem();

        if(!(stack.getItem() instanceof StaffItem)){
            return;
        }

        double s = getManaBurstSpeed(stack) * speed;
        burst.setColor(getColor());
        burst.setMana(100);
        burst.setStartingMana(100);
        burst.setGravity(0);
        burst.setMinManaLoss(40);
        burst.setManaLossPerTick(1.5F);
        burst.setSourceLens(stack.copy());
        burst.setBurstMotion(burst.getDeltaMovement().x*s,burst.getDeltaMovement().y*s,burst.getDeltaMovement().z*s);
        player.level.addFreshEntity(burst);
    }

    public double costEMCWhenAttack(ItemStack stack) {
        return 1.5 / getPrefixCommonRate(stack);
    }

    public float getBaseDamage(ItemStack stack){
        return (float) (tier.getAttackDamageBonus() * getPrefixCommonRate(stack) + tier.getAttackDamageBonus() * 0.13 * getLevel(stack));
    }

    public DamageUtils getManaBurstBaseDamage(ItemStack stack,Entity entity){
        DamageUtils base = DamageUtils.of(getBaseDamage(stack));
        if(entity instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) entity;
            IPlayerSkillCapability cap = EMCWorldAPI.getInstance().getPlayerSkillCapability(player);
            if(cap.getProfession() == 3){
                double skillRate = Math.pow(1+(cap.getSkills()[0]/10000f),cap.getLevel());
                double b = base.total();
                base.append(b*skillRate-b);
            }
        }
        return base;
    }

    private float getManaBurstDamage(ItemStack stack,Entity entity){
        float damage = (float) getManaBurstBaseDamage(stack,entity).total();
        double chance = getActCriticalChance(stack);
        if(entity instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) entity;
            IPlayerSkillCapability cap = EMCWorldAPI.getInstance().getPlayerSkillCapability(player);
            if(cap.getProfession() == 3){
                if(cap.getSkills()[12] != 0){
                    damage += cap.getSkills()[12]/10000f;
                }
                if(cap.getSkills()[24] != 0){
                    chance += cap.getSkills()[24]/10000f;
                }
                if(cap.getSkills()[28] != 0){
                    damage += cap.getSkills()[28]/10000f;
                }
            }
            IUtilCapability util = EMCWorldAPI.getInstance().getUtilCapability(player);
            if(util.isRaid()){
                damage *= util.getRaidRate();
            }
        }
        if(MathUtils.isRandom(chance)){
            damage *= getActCriticalRate(stack);
        }
        return damage;
    }

    protected int lv(ItemStack stack){
        return IUpgradeableWeapon.super.getWeightRequired(stack);
    }

    @Override
    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_){
        if(p_77659_2_.level.isClientSide) return ActionResult.fail(new ItemStack(this));
        if(p_77659_3_ == Hand.OFF_HAND){
            return ActionResult.fail(p_77659_2_.getOffhandItem());
        }
        IPlayerSkillCapability cap = EMCWorldAPI.getInstance().getPlayerSkillCapability(p_77659_2_);
        ItemStack staff = p_77659_2_.getMainHandItem();
        this.spawnManaBurst(p_77659_2_,1);
        if(staff.getItem() instanceof IEMCInfuserItem){
            IEMCInfuserItem godStaff = (IEMCInfuserItem) staff.getItem();
            godStaff.cost(staff);
        }
        this.setDamage(staff,getDamage(staff)+1);
        if(!(cap.getModify() == 1 && cap.getProfession() == 3 && cap.getSkills()[40] != 0 && cap.getSkills()[41] != 0)){
            p_77659_2_.getCooldowns().addCooldown(this,(int) (20/(tier.getSpeed()+4)));
        }
        if(staff.getDamageValue() > staff.getMaxDamage()){
            staff.shrink(1);
        }
        return ActionResult.success(staff);
    }

    protected double getManaBurstSpeed(ItemStack stack){
        return tier.getBurstSpeed();
    }

    protected int getColor(){
        return tier.getColor();
    }

    @Override
    public double getCriticalChance(ItemStack stack) {
        return tier.getCriticalChance() * getPrefixCommonRate(stack);
    }

    @Override
    public double getCriticalRate(ItemStack stack) {
        return tier.getCriticalRate() * getPrefixCommonRate(stack);
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
        Entity thrower = entity.getOwner();
        for (LivingEntity living : getCanAttackEntity(burst,stack)) {
            if (living.hurtTime == 0 && !living.isInvisible()) {
                if (!burst.isFake() && !entity.level.isClientSide) {
                    DamageSource source = thrower instanceof PlayerEntity ? new EWDamageSource((PlayerEntity) thrower).REALLY_PLAYER : EWDamageSource.REALLY;
                    float damage = getManaBurstDamage(stack,thrower);
                    if(thrower instanceof PlayerEntity){
                        PlayerEntity player = (PlayerEntity) thrower;
                        long cost = (long) (MathUtils.getAttackBaseCost(player) * MathUtils.difficultyLoss() * getCostRate(stack,player) * getManaBurstDamage(stack,player));
                        if(living.getType().equals(Registry.TARGET_DUMMY.get())){
                            cost = 0;
                        }
                        if(EMCHelper.getPlayerEMC(player) >= cost){
                            EMCHelper.modifyPlayerEMC(player,new EMCSource.AttackEMCSource(Math.negateExact(cost),player,living,damage,null),true);
                        }else{
                            damage = 0;
                            Message.sendMessage(player, EMCWorld.tc("message.evt.attackcancel",MathUtils.format(cost)));
                        }
                    }
                    /**living.hurtTime += 5;
                    living.invulnerableTime += 5;*/
                    if(damage == 0){
                        continue;
                    }
                    living.hurt(source, damage);
                }
            }
        }
    }

    private List<LivingEntity> getCanAttackEntity(IManaBurst burst,ItemStack stack){
        ThrowableEntity entity = burst.entity();
        Entity attacker = entity.getOwner();
        AxisAlignedBB axis = MathUtils.expandAABB(entity.position(),1);
        List<LivingEntity> entities = entity.level.getEntitiesOfClass(LivingEntity.class, axis);
        List<LivingEntity> out = new ArrayList<>();
        StaffAttackMode mode = getMode(stack);
        entities.forEach((e)->{
            if(!e.equals(attacker)){
                switch (mode){
                    case NONE:
                        break;
                    case ALL:
                        out.add(e);
                        break;
                    case ONLY_MOB:
                        if(e instanceof MonsterEntity) out.add(e);
                        break;
                    case ONLY_ANIMAL:
                        if(e instanceof AnimalEntity) out.add(e);
                        break;
                    case ONLY_PLAYER:
                        if(e instanceof PlayerEntity) out.add(e);
                        break;
                    case EXCEPT_PLAYER:
                        if(! (e instanceof PlayerEntity)) out.add(e);
                        break;
                }
            }
        });
        return out;
    }

    @Override
    public void inventoryTick(@Nonnull ItemStack p_77663_1_,@Nonnull World p_77663_2_, @Nonnull Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        if(p_77663_2_.isClientSide) return;
        if(p_77663_1_.getTag() == null || p_77663_1_.getTag().get("staff_attack_mode") == null){
            p_77663_1_.getOrCreateTag().putInt("staff_attack_mode",1);
        }
    }

    public static StaffAttackMode getMode(ItemStack stack){
        for(StaffAttackMode mode : StaffAttackMode.values()){
            if(mode.index == stack.getOrCreateTag().getInt("staff_attack_mode")){
                return mode;
            }
        }
        return StaffAttackMode.NONE;
    }

    public static void switchStaffAttackMode(ItemStack stack){
        if(stack.getItem() instanceof StaffItem){
            int index = stack.getOrCreateTag().getInt("staff_attack_mode");
            if(index >= StaffAttackMode.values().length-1){
                stack.getOrCreateTag().putInt("staff_attack_mode",0);
            }else{
                stack.getOrCreateTag().putInt("staff_attack_mode",index+1);
            }
        }
    }

    @Override
    public boolean doParticles(IManaBurst burst, ItemStack stack) {
        return true;
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

    @Override
    public long EMCModifySecond(ItemStack stack) {
        return 0;
    }

    @Override
    public int getMaxLevel() {
        return (int) ((tier.getLevel() + 1) * ConfigManager.DIFFICULTY.get() * 0.75d);
    }

    public enum StaffAttackMode{
        NONE(0,"none"),
        ALL(1,"all"),
        ONLY_MOB(2,"mob"),
        ONLY_ANIMAL(3,"animal"),
        EXCEPT_PLAYER(4,"except_player"),
        ONLY_PLAYER(5,"only_player")
        ;

        private final int index;
        private final String name;

        StaffAttackMode(int index,String name){
            this.index = index;
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public String getName() {
            return EMCWorld.tc("attack_range_"+name).getString();
        }

    }
}
