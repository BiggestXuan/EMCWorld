package biggestxuan.emcworld.common.items.Equipment.Weapon.Sword;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/13
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.equipment.weapon.BaseWeaponItem;
import biggestxuan.emcworld.api.item.equipment.weapon.IRangeAttackWeapon;
import biggestxuan.emcworld.api.item.IUpgradeableMaterial;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ChaosSword extends BaseWeaponItem implements IRangeAttackWeapon, IUpgradeableMaterial {
    public ChaosSword() {
        super(new IItemTier() {
            @Override
            public int getUses() {
                return 2705;
            }

            @Override
            public float getSpeed() {
                return 1;
            }

            @Override
            public float getAttackDamageBonus() {
                return 1.25f;
            }

            @Override
            public int getLevel() {
                return 1;
            }

            @Override
            public int getEnchantmentValue() {
                return 15;
            }

            @Override
            @Nonnull
            public Ingredient getRepairIngredient() {
                return Ingredient.of(Items.NETHERITE_INGOT);
            }
        }, 11, -2f);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, @Nonnull ITooltipFlag p_77624_4_) {
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.chaos_sword"));
    }

    @Override
    public double getAttackRange(ItemStack stack) {
        return 1.25d;
    }

    @Override
    public int getWeight(ItemStack stack){
        return 120;
    }
}
