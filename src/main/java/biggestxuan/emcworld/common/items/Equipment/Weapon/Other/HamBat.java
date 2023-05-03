package biggestxuan.emcworld.common.items.Equipment.Weapon.Other;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/30
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.INeedLevelItem;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.equipment.weapon.IAdditionsDamageWeapon;
import biggestxuan.emcworld.api.item.equipment.weapon.IRangeAttackWeapon;
import biggestxuan.emcworld.common.items.EWItem;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import biggestxuan.emcworld.common.utils.DamageUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class HamBat extends EWItem implements IAdditionsDamageWeapon, INeedLevelItem, IRangeAttackWeapon {
    public HamBat(){
        super(new Properties().tab(EWCreativeTabs.EW_EQUIPMENT_TAB).stacksTo(1));
    }

    private int getFresh(ItemStack stack){
        return stack.getOrCreateTag().getInt("fresh");
    }

    private void setFresh(ItemStack stack,int fresh){
        stack.getOrCreateTag().putInt("fresh",fresh);
    }

    private void waste(ItemStack stack){
        setFresh(stack,getFresh(stack)-1);
    }

    @Override
    public int getUseLevel(ItemStack stack) {
        return 30;
    }

    @Override
    public DamageUtils getAdditionsDamage(PlayerEntity player,ItemStack stack) {
        return DamageUtils.of(getFresh(stack) >= 100 ? 0.3F * getFresh(stack) - 10 : 0.1F * getFresh(stack) + 10);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, @Nonnull ITooltipFlag p_77624_4_) {
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.ham_bat_fresh",getFresh(p_77624_1_)/2).append(" %"));
    }

    @Override
    public void inventoryTick(@Nonnull ItemStack p_77663_1_, @Nonnull World p_77663_2_, @Nonnull Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        if(p_77663_2_.isClientSide) return;
        if(p_77663_1_.getTag() == null || p_77663_1_.getTag().get("fresh") == null){
            p_77663_1_.getOrCreateTag().putInt("fresh",200);
        }
        if(p_77663_2_.getDayTime() % 500 == 0){
            waste(p_77663_1_);
        }
        if(p_77663_1_.getTag().getInt("fresh") == 0){
            p_77663_1_.shrink(1);
        }
    }

    @Override
    public DamageUtils getAttackRange(PlayerEntity player,ItemStack stack) {
        return DamageUtils.of(3.5D * getFresh(stack) / 200);
    }
}
