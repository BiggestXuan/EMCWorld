package biggestxuan.emcworld.api.item.equipment.tier;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/19
 */

import biggestxuan.emcworld.common.registry.EWItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public enum EWAtmTier implements IItemTier {
    ATM(10000,20,150,6),
    VIBRANIUM(50000,30,300,7),
    UNOBTAINIUM(100000,60,700,10)
    ;

    final int use;
    final int speed;
    final float damage;
    final int level;

    EWAtmTier(int use,int speed,float damage,int level){
        this.use = use;
        this.speed = speed;
        this.damage = damage;
        this.level = level;
    }

    @Override
    public int getUses() {
        return this.use;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.damage;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getEnchantmentValue() {
        return 20;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(new ItemStack(EWItems.GREEN_MATTER.get()));
    }
}
