package biggestxuan.emcworld.common.impl;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/25
 */

import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.api.item.equipment.staff.IStaffTier;
import biggestxuan.emcworld.api.item.equipment.warhammer.IWarHammerTier;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Tier.EWAtmTier;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Tier.EWGodWeaponTier;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Staff.StaffTier;
import biggestxuan.emcworld.common.items.Equipment.Weapon.WarHammer.WarHammerTier;
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

    @Override
    public IStaffTier getStaffTier(String name){
        return StaffTier.valueOf(name.toUpperCase());
    }

    @Override
    public IWarHammerTier getWarHammerTier(String name){
        return WarHammerTier.valueOf(name.toUpperCase());
    }
}
