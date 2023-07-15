package biggestxuan.emcworld.common.items.Equipment.Weapon.Gun;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/04
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.item.*;
import biggestxuan.emcworld.api.item.equipment.gun.IGunTier;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.entity.AmmoEntity;
import biggestxuan.emcworld.common.network.PacketHandler;
import biggestxuan.emcworld.common.network.toClient.ChangeRotPacket;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import biggestxuan.emcworld.common.registry.EWEffects;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.Message;
import biggestxuan.emcworld.common.utils.SkillUtils;
import biggestxuan.emcworld.common.utils.Sponsors.Sponsors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.*;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GunItem extends TieredItem implements ISponsorItem, IPrefixItem, IUpgradeableItem, ICostEMCItem , INameItem {
    private final IGunTier tier;
    
    public GunItem(IGunTier tier) {
        super(tier,new Properties().tab(EWCreativeTabs.EW_EQUIPMENT_TAB));
        this.tier = tier;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if(player.level.isClientSide){
            return ActionResult.fail(stack);
        }
        if(!player.isCreative()){
            stack.setDamageValue(stack.getDamageValue() + 1);
            if(stack.getDamageValue() > stack.getMaxDamage()){
                stack.shrink(1);
            }
        }
        var type = getGunType(stack);
        var bullet = getEntity(player,hand,world);
        if(type == GunType.SHOTGUN){
            int count = (int) MathUtils.log(1.5,getLevel(stack)) + 1;
            var d = SkillUtils.getGunRate(player);
            if(d != null){
                count *= 1 + d;
            }
            for (int i = 0; i < count; i++) {
                var cc = getEntity(player,hand,world);
                if(i == 0){
                    cc.shootFromRotation(player,getXRot(stack,player,true), getYRot(stack,player,true), 0F, 12F,1F);
                    world.addFreshEntity(cc);
                }else{
                    cc.shootFromRotation(player,getXRot(stack,player,false), getYRot(stack,player,false), 0F, 12F,1F);
                    world.addFreshEntity(cc);
                }
            }
        }else{
            bullet.shootFromRotation(player,getXRot(stack,player,true), getYRot(stack,player,true), 0F, 12F,1F);
            world.addFreshEntity(bullet);
        }
        bullet.setInvisible(true);
        var cap = EMCWorldAPI.getInstance().getPlayerSkillCapability(player);
        if(cap.getProfession() == 6){
            if(MathUtils.isRandom(cap.getSkills()[12]/10000d)){
                return ActionResult.success(stack);
            }
        }
        player.getCooldowns().addCooldown(this,cd(stack,player));
        return ActionResult.success(stack);
    }

    private AmmoEntity getEntity(PlayerEntity player,Hand hand,World world){
        ItemStack stack = player.getItemInHand(hand);
        var type = getGunType(stack);
        var entity = new AmmoEntity(player,world){
            public void onHitEntity(@Nonnull EntityRayTraceResult result) {
                super.onHitEntity(result);
                player.level.playSound(null,player.getX(),player.getY(),player.getZ(),SoundEvents.ARROW_HIT_PLAYER, SoundCategory.PLAYERS,0.5F,1F);
                float damage = damage(stack);
                long cost = (long) (MathUtils.getAttackBaseCost(player) * damage * ConfigManager.DIFFICULTY.get() * costEMCWhenAttack(stack));
                if (result.getEntity() != null) {
                    Entity entity = result.getEntity();
                    if(entity instanceof LivingEntity && !entity.equals(player)){
                        if(EMCHelper.getPlayerEMC(player) >= cost){
                            entity.hurt(DamageSource.thrown(this, this.getOwner()), damage);
                            EMCHelper.modifyPlayerEMC(player,new EMCSource.AttackEMCSource(Math.negateExact(cost),player,(LivingEntity) entity,damage,null),true);
                            int baseTime = Math.min((int) MathUtils.log(1.7,getLevel(stack)),20);
                            if(type == GunType.SHOTGUN){
                                ((LivingEntity) entity).hurtTime = 0;
                                entity.invulnerableTime = 0;
                            }
                            if(type == GunType.ASSAULT){
                                Double d = SkillUtils.getGunRate(player);
                                if(d != null){
                                    baseTime *= 1 + d;
                                }
                                ((LivingEntity) entity).hurtTime -= baseTime;
                                entity.invulnerableTime -= baseTime;
                            }
                        }else{
                            Message.sendMessage(player, EMCWorld.tc("message.evt.attackcancel",cost));
                        }
                    }
                }
                if (!this.level.isClientSide) {
                    if(result.getEntity() instanceof LivingEntity){
                        if(!canPenetrate(stack,player,(LivingEntity) result.getEntity())){
                            remove();
                        }
                    }else{
                        remove();
                    }
                }
            }
        };
        entity.setNoGravity(true);
        entity.getCapability(EMCWorldCapability.ENTITY_UTIL).ifPresent(c -> c.setProjectileCostRate(0));
        return entity;
    }

    @Override
    public void inventoryTick(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        CompoundNBT nbt = p_77663_1_.getOrCreateTag();
        if(!nbt.contains("gun_type")){
            nbt.putInt("gun_type",1);
        }
        super.inventoryTick(p_77663_1_, p_77663_2_, p_77663_3_, p_77663_4_, p_77663_5_);
    }

    private float getXRot(ItemStack stack, PlayerEntity player, boolean backlash){
        float x = player.xRot;
        float r = (float) (MathUtils.Random() * 15f * (1 - accuracy(stack,player)));
        float c = (float) (MathUtils.Random() * 4f * backlash(stack,player) / 4f);
        float tr = MathUtils.isRandom(0.5) ? r : -r;
        if(backlash){
            PacketHandler.sendToClient(new ChangeRotPacket(checkX(x+(MathUtils.isRandom(0.5)? c : -c)),-100),(ServerPlayerEntity) player);
        }
        return checkX(x+tr);
    }

    private float getYRot(ItemStack stack,PlayerEntity player,boolean backlash){
        float y = player.yRot;
        float r = (float) (MathUtils.Random() * 15f * (1 - accuracy(stack,player)));
        float c = (float) (MathUtils.Random() * 4f * backlash(stack,player) / 4f);
        float tr = MathUtils.isRandom(0.5) ? r : -r;
        if(backlash){
            PacketHandler.sendToClient(new ChangeRotPacket(-100,checkY(y+(MathUtils.isRandom(0.5) ? c : -c))),(ServerPlayerEntity) player);
        }
        return checkY(y+tr);
    }

    private static float checkX(float v){
        if(v < -90){
            return -90F;
        }
        if(v > 90){
            return 90F;
        }
        return v;
    }

    private static float checkY(float v){
        if(v < -360){
            return -360;
        }
        if(v > 360){
            return 360;
        }
        return v;
    }

    private boolean canPenetrate(ItemStack stack,PlayerEntity player,LivingEntity target){
        double p = penetrate(stack,player);
        if(p <= 0) return false;
        p *= (target.getMaxHealth() - target.getHealth()) / target.getMaxHealth();
        return MathUtils.isRandom(p);
    }

    public double penetrate(ItemStack stack,PlayerEntity player){
        GunType type = getGunType(stack);
        double base = tier.getPenetrate();
        base += base * 0.08 * getLevel(stack);
        if(getPrefix(stack) != Prefix.NULL){
            base += getPrefix(stack).getLevel() < 4 ? base * -0.1 * (4 - getPrefix(stack).getLevel()) : base * 0.04 * (getPrefix(stack).getLevel() - 4);
        }
        if(type == GunType.SNIPER){
            base *= 2.5;
        }
        if(type == GunType.SHOTGUN){
            base *= 1.5;
        }
        if(type == GunType.ASSAULT){
            base *= 1.1;
        }
        return Math.min(1,base);
    }

    @Override
    public double costEMCWhenAttack(ItemStack stack) {
        GunType type = getGunType(stack);
        double base = 1d;
        if(getPrefix(stack) != Prefix.NULL){
            int level = getPrefix(stack).getLevel();
            base += level < 4 ? 0.1 * (4 - level) : -0.025 * (level - 4);
        }
        base += 0.03 * getLevel(stack);
        if(type == GunType.SHOTGUN){
            base *= 1.75;
        }
        if(type == GunType.SNIPER){
            base *= 2.1;
        }
        return base;
    }

    public double backlash(ItemStack stack,PlayerEntity player){
        double base = tier.backlash(stack);
        GunType type = getGunType(stack);
        if(getPrefix(stack) != Prefix.NULL){
            int level = getPrefix(stack).getLevel();
            base += level < 4 ? 0.1 * (4 - level) * base : -0.02 * (level - 4) * base;
        }
        base -= 0.06 * base * getLevel(stack);
        if(type == GunType.SHOTGUN){
            base *= 18;
        }
        if(type == GunType.SNIPER){
            base *= 12;
        }
        var cap = EMCWorldAPI.getInstance().getPlayerSkillCapability(player);
        if(cap.getProfession() == 6){
            double r = 1 - cap.getSkills()[24]/10000d;
            base *= r;
        }
        return Math.max(0,base);
    }

    public float damage(ItemStack stack){
        GunType type = getGunType(stack);
        float base = tier.getAttackDamageBonus() / 1.5f;
        base += base * 0.1 * getLevel(stack);
        if(getPrefix(stack) != Prefix.NULL){
            base += getPrefix(stack).getLevel() < 4 ? base * -0.1 * (4 - getPrefix(stack).getLevel()) : base * 0.03 * (getPrefix(stack).getLevel() - 4);
        }
        if(type == GunType.ASSAULT){
            base /= 2.25;
        }
        if(type == GunType.SHOTGUN){
            base /= 1.75;
        }
        if(type == GunType.SNIPER){
            base *= 4;
        }
        return base;
    }

    public double accuracy(ItemStack stack,PlayerEntity player){
        double base = tier.accuracy(stack);
        GunType type = getGunType(stack);
        base *= 1 + (getLevel(stack) * 0.015);
        Prefix prefix = getPrefix(stack);
        if(prefix != Prefix.NULL){
            if(prefix.getLevel() < 4){
                base *= 1 - ((4 - prefix.getLevel()) * 0.05);
            }
            if(prefix.getLevel() > 4){
                base *= 1 + ((prefix.getLevel() - 4) * 0.01);
            }
        }
        if(type == GunType.ASSAULT){
            base *= 1.15;
        }
        if(type == GunType.SHOTGUN){
            base *= 0.25;
        }
        if(type == GunType.SNIPER){
            base *= 3.3;
        }
        if(type == GunType.PISTOL){
            var d = SkillUtils.getGunRate(player);
            if(d != null){
                base *= 1 + d;
            }
        }
        EffectInstance instance = player.getEffect(EWEffects.ACCURACY.get());
        if(instance != null){
            int level = instance.getAmplifier();
            base *= (1+level) * 0.1d;
        }
        IPlayerSkillCapability cap = EMCWorldAPI.getInstance().getPlayerSkillCapability(player);
        if(cap.getProfession() == 6){
            double b = cap.getSkills()[0]/10000d * cap.getLevel();
            base *= 1 + b;
            if(cap.getModify() == 2 && cap.getSkills()[40] != 0){
                base = 1;
            }
        }
        return Math.min(1,base);
    }

    public int cd(ItemStack stack,PlayerEntity player){
        GunType type = getGunType(stack);
        int cd = tier.cd(stack);
        cd -= cd * 0.02 * getLevel(stack);
        if(getPrefix(stack) != Prefix.NULL){
            cd -= getPrefix(stack).getLevel() < 4 ? cd * - 0.1 * (4 - getPrefix(stack).getLevel()) : cd * 0.005 * (getPrefix(stack).getLevel() - 4);
        }
        if(type == GunType.ASSAULT){
            cd /= 2.5;
        }
        if(type == GunType.SHOTGUN){
            cd *= 8;
        }
        if(type == GunType.SNIPER){
            cd *= 15;
            var d = SkillUtils.getGunRate(player);
            if(d != null){
                cd *= 1 - d;
            }
        }
        EffectInstance instance = player.getEffect(EWEffects.ATTACK_SPEED.get());
        if(instance != null){
            int level = Math.min(10,instance.getAmplifier());
            cd *= (10 - level) / 10d;
        }
        var cap = EMCWorldAPI.getInstance().getPlayerSkillCapability(player);
        if(cap.getProfession() == 6 && cap.getModify() == 1){
            var r = cap.getSkills()[40]/10000d;
            cd *= 1 - r;
        }
        return cd;
    }

    @Nullable
    @Override
    public Sponsors getSponsor() {
        return Sponsors.all.CSZXYMZX.getSponsors();
    }

    @Override
    public int getMaxLevel() {
        return (int) (tier.getLevel() * 2.75 * ConfigManager.DIFFICULTY.get() / 3);
    }

    protected IFormattableTextComponent getGunName(ItemStack stack){
        return EMCWorld.tc("item.emcworld.tier."+tier.getName());
    }

    @Override
    public IFormattableTextComponent getKey(ItemStack stack) {
        return getGunName(stack).append(EMCWorld.tc("item.emcworld.gun."+getGunType(stack).name));
    }

    public GunType getGunType(ItemStack stack){
        CompoundNBT c = stack.getOrCreateTag();
        for(GunType t : GunType.values()){
            if(c.getInt("gun_type") == t.index){
                return t;
            }
        }
        return GunType.PISTOL;
    }

    public int lv(ItemStack stack) {
        return IUpgradeableItem.super.getWeightRequired(stack);
    }

    public enum GunType {
        PISTOL(1,"pistol"),
        ASSAULT(2,"assault"),
        SHOTGUN(3,"shotgun"),
        SNIPER(4,"sniper")
        ;
        private final int index;
        private final String name;

        GunType(int index,String name){
           this.index = index;
           this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
