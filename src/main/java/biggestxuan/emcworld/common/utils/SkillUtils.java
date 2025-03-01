package biggestxuan.emcworld.common.utils;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/05/02
 */

import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.equipment.weapon.IAdditionsDamageWeapon;
import biggestxuan.emcworld.api.item.equipment.weapon.IRangeAttackWeapon;
import biggestxuan.emcworld.common.items.ModPack.EndLight;
import biggestxuan.emcworld.common.registry.EWEffects;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TieredItem;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SkillUtils {
    @Nullable
    public static Double getGunRate(PlayerEntity player){
        var cap = EMCWorldAPI.getInstance().getPlayerSkillCapability(player);
        if(cap.getProfession() == 6 && cap.getMaxLevel() >= 80){
            return cap.getSkills()[28]/10000d;
        }
        return null;
    }

    public static DamageUtils getPlayerAttackDamage(PlayerEntity player,ItemStack stack){
        IPlayerSkillCapability cap = EMCWorldAPI.getInstance().getPlayerSkillCapability(player);
        IUtilCapability util = EMCWorldAPI.getInstance().getUtilCapability(player);
        if(!(stack.getItem() instanceof IAdditionsDamageWeapon)){
            return DamageUtils.of(0);
        }
        IAdditionsDamageWeapon weapon = (IAdditionsDamageWeapon) stack.getItem();
        DamageUtils utils = weapon.getAdditionsDamage(player,stack);
        double damage = utils.total();
        if(stack.getItem() instanceof SwordItem){
            SwordItem sword = (SwordItem) stack.getItem();
            var d = sword.getDamage();
            IPrefixItem i = (IPrefixItem) sword;
            if(i.getPrefix(stack).getLevel() != 0){
                d = 0;
            }
            d += EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS,stack) * 0.75;
            EffectInstance instance = player.getEffect(Effects.DAMAGE_BOOST);
            if(instance != null) {
                d += (instance.getAmplifier()+1) * 1.25;
            }
            utils.set(d+utils.total()+1);
            damage += sword.getDamage();
        } else{
            if(stack.getItem() instanceof TieredItem){
                TieredItem item = (TieredItem) stack.getItem();
                utils.set(item.getTier().getAttackDamageBonus()+1+utils.total());
                damage += item.getTier().getAttackDamageBonus();
            }
        }
        int level = cap.getLevel();
        int[] skills = cap.getSkills();
        double c = damage;
        damage *= EndLight.getAttackTimeDamageBoost(player);
        if(cap.getProfession() == 1){
            damage *= Math.pow(1+skills[0]/10000f,level);
            int modify = cap.getModify();
            if(modify == 1){
                if(util.getTimer() >0){
                    damage *= 1+(cap.getSkills()[33]/10000f);
                }
            }
            if(skills[24] != 0){
                float base = 1 - skills[24] /10000f;
                damage *= base;
            }
            if(skills[28] != 0){
                damage += skills[28] /10000f;
            }
        }
        if(cap.getProfession() == 5){
            damage *= Math.pow(1+skills[0]/10000f,level);
        }
        damage -= c;
        return utils.append(damage);
    }

    public static DamageUtils getPlayerAttackRange(PlayerEntity player, ItemStack stack){
        if(!(stack.getItem() instanceof IRangeAttackWeapon)){
            return DamageUtils.of(0);
        }
        IRangeAttackWeapon weapon = (IRangeAttackWeapon) stack.getItem();
        DamageUtils range = weapon.getAttackRange(player,stack);
        double effect = 0;
        EffectInstance instance = player.getEffect(EWEffects.ATTACK_RANGE.get());
        if(instance != null){
            int level = instance.getAmplifier();
            effect = range.total() * 0.1*(level+1);
        }
        List<Double> list = new ArrayList<>();
        list.add(effect);
        return range.append(list);
    }

    public static void addEffect(LivingEntity living, Effect effect,int time,int level){
        living.addEffect(getEffect(effect,time,level));
    }

    public static EffectInstance getEffect(Effect effect,int time,int level){
        return new EffectInstance(effect,time <= 0 ? 1 : time, level <= -1 ? 0 : level);
    }
}
