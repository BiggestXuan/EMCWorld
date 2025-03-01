package biggestxuan.emcworld.common.raid.effects;

import biggestxuan.emcworld.common.raid.RaidEffect;
import biggestxuan.emcworld.common.utils.MathUtils;
import hellfirepvp.astralsorcery.common.lib.ConstellationsAS;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.raid.Raid;

import javax.annotation.Nonnull;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/12
 */
public class Mineralis extends RaidEffect {
    double chance = hasAlcara ? 0.015F : 0.15;
    public Mineralis(@Nonnull Raid raid) {
        super(raid, "mineralis", ConstellationsAS.mineralis);
    }

    @Override
    public void onSpawnIllager(LivingEntity livingEntity) {
        if(MathUtils.isRandom(chance)){
            livingEntity.setItemSlot(EquipmentSlotType.HEAD, new ItemStack(Items.DIAMOND_HELMET));
        }
        if(MathUtils.isRandom(chance)){
            livingEntity.setItemSlot(EquipmentSlotType.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE));
        }
        if(MathUtils.isRandom(chance)){
            livingEntity.setItemSlot(EquipmentSlotType.LEGS, new ItemStack(Items.DIAMOND_LEGGINGS));
        }
        if(MathUtils.isRandom(chance)){
            livingEntity.setItemSlot(EquipmentSlotType.FEET, new ItemStack(Items.DIAMOND_BOOTS));
        }
    }

    @Override
    public void onIllagerDeath(LivingEntity livingEntity, DamageSource source) {
        PlayerEntity player = getKilledPlayer(source);
        int time = hasAlcara ? 12 : 120;
        if(player != null && MathUtils.isRandom(chance)){
            player.addEffect(new EffectInstance(Effects.DIG_SPEED,time,0));
        }
    }
}
