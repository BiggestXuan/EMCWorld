package biggestxuan.emcworld.common.raid.effects;

import biggestxuan.emcworld.api.item.equipment.armor.IEMCShieldArmor;
import biggestxuan.emcworld.common.raid.RaidEffect;
import biggestxuan.emcworld.common.utils.MathUtils;
import hellfirepvp.astralsorcery.common.lib.ConstellationsAS;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.raid.Raid;

import javax.annotation.Nonnull;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/12
 */
public class Evorsio extends RaidEffect {
    public Evorsio(@Nonnull Raid raid) {
        super(raid,"evorsio", ConstellationsAS.evorsio);
    }

    @Override
    public float onPlayerEMCShiedCost(PlayerEntity player, DamageSource source, float amount) {
        float rate = hasAlcara ? 1.015F : 1.15F;
        return amount * rate;
    }

    @Override
    public void onIllagerDeath(LivingEntity livingEntity, DamageSource source) {
        float rate = hasAlcara ? 0.02F : 0.2F;
        double chance = hasAlcara ? 0.2D : 0.02D;
        PlayerEntity player = getKilledPlayer(source);
        if(player != null && MathUtils.isRandom(chance)){
            for(ItemStack stack : player.inventory.armor){
                if(stack.getItem() instanceof IEMCShieldArmor){
                    IEMCShieldArmor armor = (IEMCShieldArmor) stack.getItem();
                    armor.modifyShield(stack,(armor.getMaxShield(stack) - armor.getShield(stack)) * rate);
                }
            }
        }
    }
}
