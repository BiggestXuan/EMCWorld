package biggestxuan.emcworld.common.impl;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/25
 */

import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.api.item.equipment.tier.EWAtmTier;
import biggestxuan.emcworld.api.item.equipment.tier.EWGodWeaponTier;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;

public class EMCWorldAPI implements biggestxuan.emcworld.api.EMCWorldAPI {
    @Override
    public IUtilCapability getUtilCapability(PlayerEntity player) {
        return player.getCapability(EMCWorldCapability.UTIL).orElseThrow(NullPointerException::new);
    }

    @Override
    public IPlayerSkillCapability getPlayerSkillCapability(PlayerEntity player) {
        return player.getCapability(EMCWorldCapability.PLAYER_LEVEL).orElseThrow(NullPointerException::new);
    }

    @Override
    public IItemTier getGodWeaponTier(){
        return EWGodWeaponTier.INSTANCE;
    }

    @Override
    public IItemTier getATMTier(){
        return EWAtmTier.ATM;
    }

    @Override
    public IItemTier getVibraniumTier(){
        return EWAtmTier.VIBRANIUM;
    }

    @Override
    public IItemTier getUnobtainiumTier(){
        return EWAtmTier.UNOBTAINIUM;
    }
}
