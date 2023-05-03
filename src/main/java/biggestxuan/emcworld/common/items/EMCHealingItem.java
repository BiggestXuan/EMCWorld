package biggestxuan.emcworld.common.items;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/28
 */

import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import biggestxuan.emcworld.common.registry.EWItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.silentchaos512.scalinghealth.ScalingHealth;
import net.silentchaos512.scalinghealth.init.ModPotions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class EMCHealingItem extends EWItem implements IUpgradeableItem, IPrefixItem {
    final int healSpeed = 700;

    public EMCHealingItem() {
        super(new Properties().stacksTo(1).defaultDurability(8).tab(EWCreativeTabs.EW_EQUIPMENT_TAB).fireResistant());
    }

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return false;
    }

    @Override
    public boolean isValidRepairItem(@Nonnull ItemStack p_82789_1_, @Nonnull ItemStack p_82789_2_) {
        return p_82789_2_.getItem().equals(EWItems.BIGGEST_EMC_GEM.get());
    }

    @Override
    public boolean isFoil(ItemStack p_77636_1_) {
        return true;
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 100;
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.getHealth() < player.getMaxHealth() && !player.hasEffect(ModPotions.BANDAGED.get()) && stack.getDamageValue() < stack.getMaxDamage()) {
            player.startUsingItem(hand);
            return ActionResult.success(stack);
        } else {
            return ActionResult.fail(stack);
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entityLiving) {
        if (!world.isClientSide) {
            if (entityLiving instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity)entityLiving;
                stack.setDamageValue(stack.getDamageValue()+1);
                player.awardStat(Stats.ITEM_USED.get(this));
                player.getCapability(EMCWorldCapability.UTIL).ifPresent(cap -> {
                    cap.setHealTick(healSpeed);
                    cap.setHealPreTick(getTickHeal(player,stack));
                });
                player.getCooldowns().addCooldown(stack.getItem(), (int) (healSpeed * 1.05));
            }
        }

        return stack;
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        if (count % 10 == 0) {
            player.playSound(SoundEvents.ARMOR_EQUIP_LEATHER, 1.25F, (float)(1.1D + 0.05D * ScalingHealth.random.nextGaussian()));
        }

    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new TranslationTextComponent("item.scalinghealth.healing_item.value", (int)(getHealRate(stack) * 100.0F), healSpeed / 20));
        tooltip.add(new TranslationTextComponent("item.scalinghealth.healing_item.howToUse", 5));
    }

    @Override
    public int getWeightRequired(ItemStack stack) {
        return 3 << getLevel(stack) + 2;
    }

    private float getTickHeal(PlayerEntity player,ItemStack stack){
        return (float) (player.getMaxHealth() * getHealRate(stack) / healSpeed);
    }

    private double getHealRate(ItemStack stack){
        return (getLevel(stack) + 1 + getPrefix(stack).getLevel() * 0.1) * 0.2 + 0.8d;
    }

    @Override
    public int getMaxLevel() {
        return (int) (12 * ConfigManager.DIFFICULTY.get() / 3);
    }
}
