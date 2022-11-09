package biggestxuan.emcworld.api.item.equipment.weapon;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/26
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.IUpgradeableMaterial;
import biggestxuan.emcworld.api.item.equipment.tier.EWGodWeaponTier;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.registry.EWDamageSource;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.MathUtils;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.openzen.zencode.java.ZenCodeType;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.common.entity.EntityManaBurst;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.GodWeapon")
public abstract class BaseEMCGodWeapon extends BaseWeaponItem implements IUpgradeableWeapon, IUpgradeableMaterial, ILensEffect {

    protected final float baseDamage;
    private final String name;
    private final int color;

    public BaseEMCGodWeapon(float baseDamage,String name,int color){
        super(EWGodWeaponTier.INSTANCE,(int) baseDamage,-2.4F);
        this.baseDamage = baseDamage;
        this.name = name;
        this.color = color;
    }

    @ZenCodeType.Method
    public static int getWeaponMaxLevel(){
        IUpgradeableWeapon item = (IUpgradeableWeapon) EWItems.FIRE_SWORD.get();
        return item.getMaxLevel();
    }

    @Override
    public int getMaxLevel(){
        double diff = ConfigManager.DIFFICULTY.get();
        if(diff < 1) return 8;
        if(diff < 2) return 14;
        if(diff < 3) return 20;
        else return 24;
    }

    @Override
    public int getUseLevel(ItemStack stack){
        int weaponLevel = getLevel(stack);
        if(weaponLevel >= 22) return 100;
        if(weaponLevel >= 20) return 91;
        if(weaponLevel >= 18) return 81;
        if(weaponLevel >= 16) return 71;
        if(weaponLevel >= 14) return 61;
        if(weaponLevel >= 12) return 51;
        if(weaponLevel >= 10) return 41;
        return 31;
    }

    @Override
    public boolean canBeHurtBy(@Nonnull DamageSource p_234685_1_){
        return false;
    }

    @Override
    public long getTickCost(ItemStack stack){
        return Math.round(80 * MathUtils.difficultyLoss());
    }

    @Override
    public abstract long EMCModifySecond(ItemStack stack);

    @Override
    public int getLevel(ItemStack stack){
        return stack.getOrCreateTag().getInt("level");
    }

    @Override
    public void setLevel(ItemStack stack, int level) {
        stack.getOrCreateTag().putInt("level",level);
    }

    @Override
    public void addLevel(ItemStack stack, int level) {
        setLevel(stack,Math.min(getMaxLevel(),getLevel(stack)+level));
    }

    @Override
    public void lossLevel(ItemStack stack, int level) {
        setLevel(stack,Math.max(0,getLevel(stack)-level));
    }

    @Override
    public abstract float getAdditionsDamage(ItemStack stack);

    @Override
    public abstract double getAttackRange(ItemStack stack);

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, @Nonnull ITooltipFlag p_77624_4_) {
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.weapon_god"));
    }

    @Nonnull
    @Override
    public ITextComponent getName(@Nonnull ItemStack p_200295_1_) {
        int level = getLevel(p_200295_1_);
        return EMCWorld.tc("item.emcworld.god_"+this.name,level);
    }

    private double getManaBurstSpeed(ItemStack stack){
        return (getLevel(stack) - 11) * 0.8d;
    }

    private int getColor(){
        return this.color;
    }

    public void spawnManaBurst(PlayerEntity player){
        EntityManaBurst burst = new EntityManaBurst(player);
        ItemStack stack = player.getMainHandItem();
        if(!(stack.getItem() instanceof BaseEMCGodWeapon)){
            return;
        }
        long cost = (long) ((MathUtils.getAttackBaseCost(player)*MathUtils.difficultyLoss()) * getManaBurstDamage(stack));
        BaseEMCGodWeapon item = (BaseEMCGodWeapon) stack.getItem();
        double s = getManaBurstSpeed(stack);
        burst.setColor(item.getColor());
        burst.setMana(100);
        burst.setStartingMana(100);
        burst.setGravity(0);
        burst.setMinManaLoss(40);
        burst.setManaLossPerTick(3F);
        burst.setSourceLens(stack.copy());
        burst.setBurstMotion(burst.getDeltaMovement().x*s,burst.getDeltaMovement().y*s,burst.getDeltaMovement().z*s);
        if(EMCHelper.getPlayerEMC(player) >= cost){
            EMCHelper.modifyPlayerEMC(player,Math.negateExact(cost),true);
            player.level.addFreshEntity(burst);
        }
    }

    @Override
    public abstract int getEnchantmentValue();

    @Nonnull
    @Override
    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, @Nonnull Hand p_77659_3_){
        ItemStack stack = p_77659_2_.getItemInHand(p_77659_3_);
        if(p_77659_1_.isClientSide) return ActionResult.fail(stack);
        /*if(p_77659_2_.isShiftKeyDown()){
            this.lossLevel(stack,1);
        }else{
            this.addLevel(stack,1);
        }*/
        return ActionResult.success(stack);
    }

    @Nonnull
    @Override
    public Rarity getRarity(@Nonnull ItemStack p_77613_1_){
        int level = getLevel(p_77613_1_);
        if(level <= 8) return Rarity.COMMON;
        if(level <= 14) return Rarity.UNCOMMON;
        if(level <= 20) return Rarity.RARE;
        return Rarity.EPIC;
    }

    private float getManaBurstDamage(ItemStack stack){
        int level = getLevel(stack);
        return 8f + level-11f ;
    }

    @Override
    public abstract double costEMCWhenAttack(ItemStack stack);

    @Override
    public void inventoryTick(@Nonnull ItemStack p_77663_1_, @Nonnull World p_77663_2_, @Nonnull Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        if(getLevel(p_77663_1_) > getMaxLevel()){
            setLevel(p_77663_1_,getMaxLevel());
        }
    }

    @Override
    public void apply(ItemStack stack, BurstProperties props) {}

    @Override
    public boolean collideBurst(IManaBurst burst, RayTraceResult pos, boolean isManaBlock, boolean dead, ItemStack stack){
        return dead;
    }

    @Override
    public void updateBurst(IManaBurst burst, ItemStack stack){
        ThrowableEntity entity = burst.entity();
        AxisAlignedBB axis = new AxisAlignedBB(entity.getX(), entity.getY(), entity.getZ(), entity.getX()+1, entity.getY()+1, entity.getZ()+1);
        List<LivingEntity> entities = entity.level.getEntitiesOfClass(LivingEntity.class, axis);
        Entity thrower = entity.getOwner();
        for (LivingEntity living : entities) {
            if (living == thrower || living instanceof PlayerEntity && thrower instanceof PlayerEntity
                    && !((PlayerEntity) thrower).canHarmPlayer(((PlayerEntity) living))) {
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
    public boolean doParticles(IManaBurst burst, ItemStack stack){
        return true;
    }
}