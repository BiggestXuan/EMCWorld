package biggestxuan.emcworld.common.impl;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/25
 */

import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.api.item.equipment.dagger.IDaggerTier;
import biggestxuan.emcworld.api.item.equipment.gun.IGunTier;
import biggestxuan.emcworld.api.item.equipment.staff.IStaffTier;
import biggestxuan.emcworld.api.item.equipment.warhammer.IWarHammerTier;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Dagger.DaggerTier;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Gun.GunTier;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Tier.EWSwordTier;
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
        return EWSwordTier.ATM;
    }

    @Override
    public IItemTier getVibraniumTier(){
        return EWSwordTier.VIBRANIUM;
    }

    @Override
    public IItemTier getUnobtainiumTier(){
        return EWSwordTier.UNOBTAINIUM;
    }

    @Override
    public IGunTier getGunTier(String name) {
        return GunTier.valueOf(name.toUpperCase());
    }

    @Override
    public IStaffTier getStaffTier(String name){
        return StaffTier.valueOf(name.toUpperCase());
    }

    @Override
    public IWarHammerTier getWarHammerTier(String name){
        return WarHammerTier.valueOf(name.toUpperCase());
    }

    @Override
    public IDaggerTier getDaggerTier(String name){
        return DaggerTier.valueOf(name.toUpperCase());
    }
}
