package biggestxuan.emcworld.api.item.equipment.armor;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/26
 */

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public enum EMCWorldArmorMaterial implements IArmorMaterial {
    FIRE_RED("fire_red",new int[]{800,1200,1000,600},new int[]{4,8,6,3},13,3,0.1f),
    GUARDIAN("guardian",new int[]{1400,1800,1600,1100},new int[]{7,11,9,6},28,6,0.35f);

    EMCWorldArmorMaterial(String name,int[] durability,int[] defense,int enchantment,float toughness,float knockback) {
        this.name = name;
        this.durability = durability;
        this.defense = defense;
        this.enchantment = enchantment;
        this.toughness = toughness;
        this.knockback = knockback;
    }

    private final String name;
    private final int[] durability;
    private final int[] defense;
    private final int enchantment;
    private final float toughness;
    private final float knockback;

    @Override
    public int getDurabilityForSlot(EquipmentSlotType p_200896_1_) {
        return this.durability[p_200896_1_.getIndex()];
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType p_200902_1_) {
        return this.defense[p_200902_1_.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantment;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_GENERIC;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockback;
    }
}
