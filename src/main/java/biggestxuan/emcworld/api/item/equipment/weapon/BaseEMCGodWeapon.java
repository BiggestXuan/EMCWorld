package biggestxuan.emcworld.api.item.equipment.weapon;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/26
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.IEMCInfuserItem;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.IUpgradeableMaterial;
import biggestxuan.emcworld.api.item.equipment.IEMCGodWeaponLevel;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Tier.EWGodWeaponTier;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.items.Equipment.BaseWeaponGemItem;
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
public abstract class BaseEMCGodWeapon extends BaseWeaponItem implements IUpgradeableWeapon, IUpgradeableMaterial, ILensEffect, ICriticalWeapon, IEMCInfuserItem, IEMCGodWeaponLevel, IPrefixItem {

    protected final float baseDamage;
    private final int color;

    @Override
    public double getEMCCostRate() {
        return 0d;
    }

    public BaseEMCGodWeapon(float baseDamage,int color){
        super(EWGodWeaponTier.INSTANCE,(int) baseDamage,-2.4F);
        this.baseDamage = baseDamage;
        this.color = color;
    }

    @Override
    public int getWeightRequired(ItemStack stack){
        int l = getLevel(stack);
        int weight = 10;
        for (int i = 0; i < l; i++) {
            weight = (int) (1.42f * weight);
        }
        return weight;
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
    public boolean canBeHurtBy(@Nonnull DamageSource p_234685_1_){
        return false;
    }

    @Override
    public long getTickCost(ItemStack stack){
        return Math.round(80 * MathUtils.difficultyLoss());
    }

    public int getGemIndex(ItemStack stack){
        return stack.getOrCreateTag().getInt("weapon_gem");
    }

    public void setGemIndex(ItemStack stack,int index){
        stack.getOrCreateTag().putInt("weapon_gem",index);
    }

    public int getGemType(ItemStack stack){
        return (getGemIndex(stack) / 10);
    }

    public long getKillCount(ItemStack stack){
        return stack.getOrCreateTag().getLong("kill_count");
    }

    public void addKillCount(ItemStack stack){
        stack.getOrCreateTag().putLong("kill_count",getKillCount(stack)+1);
    }

    public int getColor(ItemStack stack){
        int t = getGemType(stack);
        int c;
        if(t >= 40){
            c = BaseWeaponGemItem.gem.ABYSS.getColor();
        }else if(t >= 30){
            c = BaseWeaponGemItem.gem.LAKE.getColor();
        }else if(t >= 20){
            c = BaseWeaponGemItem.gem.NATURE.getColor();
        }else c = BaseWeaponGemItem.gem.BLOOD.getColor();
        return c;
    }

    public abstract float getBaseDamage(ItemStack stack);

    public abstract double getBaseRange(ItemStack stack);

    public abstract double getBaseEMCWhenAttack(ItemStack stack);

    public abstract long getBaseModifySecond(ItemStack stack);

    public abstract double getBaseCriticalChance(ItemStack stack);

    public abstract double getBaseCriticalRate(ItemStack stack);

    @Override
    public double getCriticalChance(ItemStack stack){
        double b = getBaseCriticalChance(stack);
        long costEMC = getCostEMC(stack);
        if(costEMC >= 1){
            b += Math.log(costEMC)/85;
        }
        return b * getPrefixCommonRate(stack) * 0.8;
    }

    @Override
    public double getCriticalRate(ItemStack stack){
        double b = getBaseCriticalRate(stack);
        long costEMC = getCostEMC(stack);
        if(costEMC >= 1){
            b += Math.log(costEMC)/85;
        }
        return b * getPrefixCommonRate(stack) * 0.8;
    }

    @Override
    public double costEMCWhenAttack(ItemStack stack){
        double b = getBaseEMCWhenAttack(stack);
        if(getGemType(stack) == 1){
            b *= 1.2;
        }
        if(getGemType(stack) == 2){
            b *= 0.9;
        }
        if(getGemType(stack) == 3){
            b *= 1.1;
        }
        return b / getPrefixCommonRate(stack);
    }

    @Override
    public long EMCModifySecond(ItemStack stack){
        long b = getBaseModifySecond(stack);
        if(getGemType(stack) == 4){
            b *= 1.15f;
        }
        return (long) (b * getPrefixCommonRate(stack));
    }

    @Override
    public float getAdditionsDamage(ItemStack stack){
        float b = getBaseDamage(stack) * 0.75f;
        long costEMC = getCostEMC(stack);
        if(costEMC >= 1){
            b *= (1 + Math.log(costEMC)/85);
        }
        if(getGemType(stack) == 1){
            b *= 1.15f;
        }
        if(getGemType(stack) == 2){
            b *= 0.9f;
        }
        if(getGemType(stack) == 4){
            b *= 0.95f;
        }
        return (float) (b * getPrefixCommonRate(stack));
    }

    @Override
    public long getMaxInfuser(ItemStack stack){
        return (long) ((long) (Math.pow(1.417,getLevel(stack)) * 500000) * getPrefixCommonRate(stack));
    }

    @Override
    public double getAttackRange(ItemStack stack){
        double b = getBaseRange(stack);
        if(getGemType(stack) == 3){
            b += 0.5;
        }
        return b * getPrefixCommonRate(stack);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, @Nonnull ITooltipFlag p_77624_4_) {
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.weapon_god"));
    }

    protected double getPrefixCommonRate(ItemStack stack){
        double b;
        Prefix prefix = getPrefix(stack);
        if(prefix == Prefix.NULL) return 1d;
        if(prefix.getLevel() <= 3){
            b = 1 - 0.1 * (4 - prefix.getLevel());
        }else{
            b = 0.02 * (prefix.getLevel()-4) + 1;
        }
        return b;
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
        float damage = 4f + getLevel(stack) -11f;
        damage += getAdditionsDamage(stack);
        if(MathUtils.isRandom(getActCriticalChance(stack))){
            damage *= getActCriticalRate(stack);
        }
        return damage ;
    }

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
            if (living.hurtTime == 0 && !living.isInvisible()) {
                int cost = 100 / 3;
                int mana = burst.getMana();
                if (mana >= cost) {
                    burst.setMana(mana - cost);
                    float damage = (float) (getManaBurstDamage(stack) * (mana - cost) / (mana * ConfigManager.DIFFICULTY.get() * 5));
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