package biggestxuan.emcworld.common.items.Equipment.Weapon.Gun;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/04
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.ICostEMCItem;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.ISponsorItem;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.api.item.equipment.gun.IGunTier;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.Message;
import biggestxuan.emcworld.common.utils.Sponsors.Sponsors;
import divinerpg.entities.projectile.EntityShooterBullet;
import divinerpg.enums.BulletType;
import divinerpg.registries.EntityRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class GunItem extends TieredItem implements ISponsorItem, IPrefixItem, IUpgradeableItem, ICostEMCItem {
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

        EntityShooterBullet bullet = new EntityShooterBullet(EntityRegistry.SHOOTER_BULLET,player,world, BulletType.MAELSTROM_SHOT){
            public void onHitEntity(EntityRayTraceResult result) {
                float damage = damage(stack);
                long cost = (long) (MathUtils.getAttackBaseCost(player) * damage * ConfigManager.DIFFICULTY.get() * costEMCWhenAttack(stack));
                if (result.getEntity() != null) {
                    Entity entity = result.getEntity();
                    if(entity instanceof LivingEntity){
                        if(EMCHelper.getPlayerEMC(player) >= cost){
                            entity.hurt(DamageSource.thrown(this, this.getOwner()), damage);
                            EMCHelper.modifyPlayerEMC(player,new EMCSource.AttackEMCSource(Math.negateExact(cost),player,(LivingEntity) entity,damage,null),true);
                        }else{
                            Message.sendMessage(player, EMCWorld.tc("message.evt.attackcancel",cost));
                        }
                    }
                }
                if (!this.level.isClientSide) {
                    this.kill();
                }
            }
        };
        bullet.setNoGravity(true);
        bullet.getCapability(EMCWorldCapability.ENTITY_UTIL).ifPresent(c -> c.setProjectileCostRate(0));
        bullet.shootFromRotation(player, player.xRot, player.yRot, 1F, 6.5F,(float) (6.5F * (1-accuracy(stack,player))));
        world.addFreshEntity(bullet);
        player.getCooldowns().addCooldown(this,cd(stack));
        return ActionResult.success(stack);
    }

    @Override
    public double costEMCWhenAttack(ItemStack stack) {
        double base = 1d;
        if(getPrefix(stack) != Prefix.NULL){
            int level = getPrefix(stack).getLevel();
            base += level < 4 ? 0.1 * (4 - level) : -0.025 * level;
        }
        base += 0.03 * getLevel(stack);
        return base;
    }

    public float damage(ItemStack stack){
        float base = tier.getAttackDamageBonus() * 1.25F;
        base += base * 0.1 * getLevel(stack);
        if(getPrefix(stack) != Prefix.NULL){
            base += getPrefix(stack).getLevel() < 4 ? base * -0.1 * (4 - getPrefix(stack).getLevel()) : base * 0.03 * (getPrefix(stack).getLevel() - 4);
        }
        return base;
    }

    public double accuracy(ItemStack stack,PlayerEntity player){
        double base = tier.accuracy(stack);
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
        return Math.min(1,base);
    }

    public int cd(ItemStack stack){
        int cd = tier.cd(stack);
        cd -= cd * 0.02 * getLevel(stack);
        if(getPrefix(stack) != Prefix.NULL){
            cd -= getPrefix(stack).getLevel() < 4 ? cd * - 0.1 * (4 - getPrefix(stack).getLevel()) : cd * 0.005 * (getPrefix(stack).getLevel() - 4);
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
}
