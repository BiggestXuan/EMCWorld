package biggestxuan.emcworld.api.item.equipment.armor;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/24
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.api.item.IUpgradeableMaterial;
import biggestxuan.emcworld.api.item.equipment.IGemInlaidItem;
import biggestxuan.emcworld.api.item.equipment.IStarItem;
import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseEMCGodArmorItem extends BaseArmorItem implements IUpgradeableMaterial,IUpgradeableArmor,ISpeedArmor,IReachArmor,IEMCShieldArmor, IPrefixItem, IStarItem,IHealBoostArmor, IGemInlaidItem {
    protected final int index;
    public BaseEMCGodArmorItem(IArmorMaterial p_i48534_1_, int p_i48534_2_) {
        super(p_i48534_1_, getType(p_i48534_2_));
        index = p_i48534_2_;
    }

    protected double getPrefixCommonRate(ItemStack stack){
        double b;
        Prefix prefix = getPrefix(stack);
        if(prefix == Prefix.NULL) return 1;
        if(prefix.getLevel() <= 3){
            b = 1 - (4 - prefix.getLevel()) * 0.1;
        }else{
            b = (prefix.getLevel()-4) * 0.03 + 1;
        }
        return b;
    }

    public abstract double healBoostRate(ItemStack stack);

    @Override
    public double getHealBoostRate(ItemStack stack) {
        double d = healBoostRate(stack);
        switch (getGemType(stack)){
            case 1:
                d *= 0.85;
                break;
            case 2:
                d *= 1.15;
                break;
            case 3:
                d *= 1.025;
                break;
            case 4:
                d *= 0.975;
                break;
        }
        return d * getBuffer(stack);
    }

    @Override
    public double getEMCCostRate() {
        return 0d;
    }

    @Override
    public boolean canBeHurtBy(@Nonnull DamageSource p_234685_1_){
        return false;
    }

    @Override
    public double costEMCWhenAttack(ItemStack stack) {
        return 1;
    }

    @Override
    public long getTickCost(ItemStack stack) {
        double d = (long) Math.pow(2,getLevel(stack));
        switch (getGemType(stack)){
            case 1:
                d *= 1.15;
                break;
            case 2:
                d *= 0.75;
                break;
            case 3:
                d *= 1.025;
                break;
            case 4:
                d *= 1.075;
                break;
        }
        return (long) d;
    }

    @Override
    public abstract long EMCModifySecond(ItemStack stack);

    @Override
    public int getMaxLevel() {
        double diff = ConfigManager.DIFFICULTY.get();
        if(diff <1) return 6;
        if(diff <2) return 8;
        if(diff <3) return 10;
        return 12;
    }

    @Override
    public abstract float getSpeed(ItemStack stack);

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, @Nonnull ITooltipFlag p_77624_4_) {
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.weapon_god"));
    }

    @Override
    public int getWeight(ItemStack stack){
        if(stack.getItem() instanceof IUpgradeableItem) {
            IUpgradeableItem item = (IUpgradeableItem) stack.getItem();
            int l = item.getLevel(stack);
            int weight = 10;
            for (int i = 0; i < l; i++) {
                weight = (int) (1.7f * weight);
            }
            return weight;
        }
        return 0;
    }

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return false;
    }

    @Override
    public float extraHealth(ItemStack stack){
        double d = health(stack);
        switch (getGemType(stack)){
            case 1:
                d *= 1.1;
                break;
            case 2:
                d *= 0.95;
                break;
            case 3:
                d *= 1.025;
                break;
            case 4:
                d *= 1.03;
                break;
        }
        return (float) (d * getBuffer(stack));
    }

    @Override
    public double hurtRate(ItemStack stack){
        double d = hurt(stack);
        switch (getGemType(stack)){
            case 1:
                d *= 0.95;
                break;
            case 2:
                d *= 1.025;
                break;
            case 3:
                d *= 0.975;
                break;
            case 4:
                d *= 0.985;
                break;
        }
        return d / getBuffer(stack);
    }

    @Override
    public float maxShield(ItemStack stack) {
        double d = shield(stack);
        switch (getGemType(stack)){
            case 1:
                d *= 1.25;
                break;
            case 2:
                d *= 0.65;
                break;
            case 3:
                d *= 1.05;
                break;
            case 4:
                d *= 1.125;
                break;
        }
        return (float) (d * getBuffer(stack));
    }

    @Override
    public float shieldSpeed(ItemStack stack) {
        double d = shield_speed(stack);
        switch (getGemType(stack)){
            case 1:
                d *= 1.125;
                break;
            case 2:
                d *= 0.875;
                break;
            case 3:
                d *= 0.95;
                break;
            case 4:
                d *= 1.075;
                break;
        }
        return (float) (d * getBuffer(stack));
    }

    public abstract float shield(ItemStack stack);

    public abstract float shield_speed(ItemStack stack);

    public abstract float health(ItemStack stack);

    public abstract double hurt(ItemStack stack);

    public static EquipmentSlotType getType(int index){
        switch (index){
            case 1:
                return EquipmentSlotType.HEAD;
            case 2:
                return EquipmentSlotType.CHEST;
            case 3:
                return EquipmentSlotType.LEGS;
            default:
                return EquipmentSlotType.FEET;
        }
    }

    protected float getShieldRate(){
        switch (index){
            case 1:
                return 0.7f;
            case 2:
                return 1.5f;
            case 3:
                return 1.25f;
            case 4:
                return 0.55f;
        }
        return 1f;
    }
}
