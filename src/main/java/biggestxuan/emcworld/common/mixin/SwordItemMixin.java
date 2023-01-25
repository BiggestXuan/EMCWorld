package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/18
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.IUpgradeableMaterial;
import biggestxuan.emcworld.api.item.equipment.weapon.IUpgradeableWeapon;
import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Mixin;

import javax.annotation.Nonnull;

@Mixin(SwordItem.class)
public abstract class SwordItemMixin extends TieredItem implements IUpgradeableWeapon, IUpgradeableMaterial {
    private final IItemTier tier = ((SwordItem) (Object) this).getTier();

    private final float damage = tier.getAttackDamageBonus();

    public SwordItemMixin(IItemTier p_i48459_1_, Properties p_i48459_2_) {
        super(p_i48459_1_, p_i48459_2_);
    }

    @Nonnull
    @Override
    public ITextComponent getName(@Nonnull ItemStack p_200295_1_) {
        int level = getLevel(p_200295_1_);
        String name = this.toString();
        ResourceLocation rl = getRegistryName();
        if(rl == null || getMaxLevel() == 0){
            return super.getName(p_200295_1_);
        }
        return EMCWorld.tc("item."+rl.getNamespace()+"."+name).append(" (+"+level+")");
    }

    @Override
    public int getWeightRequired(ItemStack stack){
        int l = getLevel(stack);
        int weight = 10;
        for (int i = 0; i < l; i++) {
            weight = (int) (1.65f * weight);
        }
        return weight * 2;
    }

    @Override
    public double costEMCWhenAttack(ItemStack stack) {
        return 1;
    }

    @Override
    public long getTickCost(ItemStack stack) {
        return 0;
    }

    @Override
    public long EMCModifySecond(ItemStack stack) {
        return 0;
    }

    @Override
    public int getMaxLevel() {
        return (int) ((tier.getLevel() + 1) * ConfigManager.DIFFICULTY.get() * 0.65d);
    }

    @Override
    public float getAdditionsDamage(ItemStack stack) {
        return getLevel(stack) == 0 ? 0 : (float) (tier.getAttackDamageBonus() * 0.1 * getLevel(stack));
    }

    @Override
    public double getAttackRange(ItemStack stack) {
        return getLevel(stack) == 0 ? 0 : 0.1 * tier.getLevel() + (damage/45) * getLevel(stack);
    }
}
