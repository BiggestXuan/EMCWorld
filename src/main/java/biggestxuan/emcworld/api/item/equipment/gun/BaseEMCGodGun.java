package biggestxuan.emcworld.api.item.equipment.gun;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.item.IEMCGod;
import biggestxuan.emcworld.api.item.IEMCRepairableItem;
import biggestxuan.emcworld.api.item.equipment.weapon.BaseEMCGodSword;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Gun.GunItem;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/02
 */

@EMCWorldSince("0.9.0")
public abstract class BaseEMCGodGun extends GunItem implements IEMCGod {
    public BaseEMCGodGun() {
        super(EMCWorldAPI.getInstance().getGunTier("god"));
    }

    @Override
    protected IFormattableTextComponent getGunName(ItemStack stack){
        return EMCWorld.tc("item.emcworld."+getRegistryName().getPath());
    }

    @Override
    public long getMaxInfuser(ItemStack stack){
        return (long) ((Math.pow(1.417,getLevel(stack)) * 500000) * getPrefixCommonRate(stack) * getBuffer(stack));
    }

    private double getPrefixCommonRate(ItemStack stack) {
        int level = getPrefix(stack).getLevel();
        return 1 + 0.1 * level;
    }

    @Override
    public double getEMCCostRate() {
        return 0d;
    }

    @Override
    public int getMaxLevel() {
        return BaseEMCGodSword.getWeaponMaxLevel();
    }

    @Override
    public long getTickCost(ItemStack stack) {
        return (long) (40L * (getLevel(stack) + 1) * MathUtils.difficultyLoss());
    }

    @Nonnull
    @Override
    public Rarity getRarity(@Nonnull ItemStack p_77613_1_){
        int level = getLevel(p_77613_1_);
        if(level <= 8) return Rarity.COMMON;
        if(level <= 14) return Rarity.UNCOMMON;
        if(level <= 20) return Rarity.RARE;
        return Rarity.EPIC;
    }

    @Override
    public int getWeightRequired(ItemStack stack){
        return (int) (super.lv(stack) * 3.6f);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ActionResult<ItemStack> result = super.use(world, player, hand);
        if(!player.level.isClientSide){
            ItemStack stack = player.getItemInHand(hand);
            cost(stack);
            return ActionResult.success(stack);
        }
        return result;
    }
}
