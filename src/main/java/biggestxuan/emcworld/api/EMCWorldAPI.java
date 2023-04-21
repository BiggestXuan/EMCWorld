package biggestxuan.emcworld.api;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/6
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.api.item.equipment.dagger.IDaggerTier;
import biggestxuan.emcworld.api.item.equipment.gun.IGunTier;
import biggestxuan.emcworld.api.item.equipment.staff.IStaffTier;
import biggestxuan.emcworld.api.item.equipment.warhammer.IWarHammerTier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public interface EMCWorldAPI{
    String MODID = EMCWorld.MODID;
    int version = 2;

    LazyValue<EMCWorldAPI> INSTANCE = new LazyValue<>(()->{
        try{
            return (EMCWorldAPI) Class.forName("biggestxuan.emcworld.common.impl.EMCWorldAPI").newInstance();
        }catch (ReflectiveOperationException e){
            EMCWorld.LOGGER.error("Can not get EMCWorld API!");
            return new EMCWorldAPI() {};
        }
    });

    static EMCWorldAPI getInstance(){
        return INSTANCE.get();
    }

    default IUtilCapability getUtilCapability(PlayerEntity player){
        return null;
    }

    default IPlayerSkillCapability getPlayerSkillCapability(PlayerEntity player){
        return null;
    }

    default IItemTier getGodWeaponTier(){
        return DUMMY_TIER;
    }

    default IItemTier getATMTier(){
        return DUMMY_TIER;
    }

    default IItemTier getVibraniumTier(){
        return DUMMY_TIER;
    }

    default IItemTier getUnobtainiumTier(){
        return DUMMY_TIER;
    }

    default IStaffTier getStaffTier(String name){
        return DUMMY_TIER;
    }

    default IDaggerTier getDaggerTier(String name){
        return null;
    }

    default IWarHammerTier getWarHammerTier(String name){
        return null;
    }

    default IGunTier getGunTier(String name){
        return null;
    }

    IStaffTier DUMMY_TIER = new IStaffTier() {
        @Override
        public double getCriticalRate() {
            return 0;
        }

        @Override
        public double getCriticalChance() {
            return 0;
        }

        @Override
        public int getColor() {
            return 0;
        }

        @Override
        public double getBurstSpeed() {
            return 0;
        }

        @Override
        public int getUses() {
            return 0;
        }

        @Override
        public float getSpeed() {
            return 0;
        }

        @Override
        public float getAttackDamageBonus() {
            return 0;
        }

        @Override
        public int getLevel() {
            return 0;
        }

        @Override
        public int getEnchantmentValue() {
            return 0;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return null;
        }
    };
}
