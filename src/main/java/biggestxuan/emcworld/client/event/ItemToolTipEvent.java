package biggestxuan.emcworld.client.event;

/**
 * EMC WORLD MOD
 * @Author Biggest_Xuan
 * 2022/10/13
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.item.*;
import biggestxuan.emcworld.api.item.equipment.IAttackSpeedItem;
import biggestxuan.emcworld.api.item.equipment.armor.IReachArmor;
import biggestxuan.emcworld.api.item.equipment.armor.ISpeedArmor;
import biggestxuan.emcworld.api.item.equipment.armor.IUpgradeableArmor;
import biggestxuan.emcworld.api.item.equipment.weapon.*;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.items.Equipment.BaseWeaponGemItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Staff.StaffItem;
import biggestxuan.emcworld.common.recipes.EMCStageLimit;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.Sponsors.Sponsors;
import mekanism.common.item.gear.ItemHazmatSuitArmor;
import mekanism.common.registries.MekanismItems;
import moze_intel.projecte.utils.EMCHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.Style;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(
        modid = EMCWorld.MODID,
        bus = Mod.EventBusSubscriber.Bus.FORGE,
        value = {Dist.CLIENT}
)
public class ItemToolTipEvent {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void tooltipEvent(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        if(event.getPlayer() == null) return;
        final Item[] radiationItem = new Item[]{
                MekanismItems.PLUTONIUM_PELLET.getItem(),MekanismItems.ANTIMATTER_PELLET.getItem(),MekanismItems.POLONIUM_PELLET.getItem()
        };
        if(stack.equals(ItemStack.EMPTY)) return;
        if(stack.getItem() instanceof IPlayerDifficultyItem){
            IPlayerDifficultyItem item_3 = (IPlayerDifficultyItem) stack.getItem();
            double diff = item_3.requireDifficulty();
            if(Minecraft.getInstance().player == null) return;
            double player_diff = Minecraft.getInstance().player.getCapability(EMCWorldCapability.UTIL).orElseThrow(NullPointerException::new).getDifficulty();
            if(player_diff < diff){
                event.getToolTip().add(EMCWorld.tc("message.all_diff",diff));
                return;
            }
        }
        if(stack.getItem() instanceof IDifficultyItem){
            IDifficultyItem item_2 = (IDifficultyItem) stack.getItem();
            if(ConfigManager.DIFFICULTY.get() < item_2.getDifficulty()){
                event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.difficulty_cant_use",item_2.getDifficulty()));
                return;
            }
        }
        if(stack.getItem() instanceof INeedLevelItem){
            INeedLevelItem item_1 = (INeedLevelItem) stack.getItem();
            if(Minecraft.getInstance().player == null) return;
            ClientPlayerEntity player = Minecraft.getInstance().player;
            int playerLevel = player.getCapability(EMCWorldCapability.PLAYER_LEVEL).orElseThrow(NullPointerException::new).getLevel();
            int itemLevel = item_1.getUseLevel(stack);
            if(playerLevel < itemLevel && !player.isCreative() && itemLevel >= 1){
                event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.need_level_to_use",itemLevel));
                return;
            }
        }
        long value;
        String stage = "";
        value = EMCHelper.getEmcValue(stack);
        boolean isTrans = true;
        boolean free = ConfigManager.FREE_MODE.get();
        if(value > 0L){
            RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
            for(EMCStageLimit recipe:manager.getAllRecipesFor(EMCStageLimit.EMCStageLimitType.INSTANCE)){
                if(recipe.getItem().equals(stack.getItem())){
                    if(GameStageManager.hasStage(event.getPlayer(),recipe.getStage())){
                        break;
                    }
                    isTrans = false;
                    stage = recipe.getStage();
                }
            }
            IFormattableTextComponent normal = EMCWorld.tc("tooltip.emcworld.emc",MathUtils.format(value));
            IFormattableTextComponent stack_tip = EMCWorld.tc("tooltip.emcworld.emc_stack",MathUtils.format(value * stack.getCount()));
            if(Screen.hasShiftDown()){
                normal = EMCWorld.tc("tooltip.emcworld.emc",MathUtils.thousandSign(value));
                stack_tip = EMCWorld.tc("tooltip.emcworld.emc_stack",MathUtils.thousandSign(value * stack.getCount()));
            }
            if(!(isTrans || free)){
                normal.setStyle(Style.EMPTY.setStrikethrough(true));
                //stack_tip.setStyle(Style.EMPTY.setStrikethrough(true));
            }
            event.getToolTip().add(normal);
            if(stack.getCount() > 1){
                event.getToolTip().add(stack_tip);
            }
        }
        if(stack.getItem() instanceof StaffItem){
            StaffItem i_s = (StaffItem) stack.getItem();
            event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.staff_damage",String.format("%.2f",i_s.getBaseDamage(stack))));
        }
        if(stack.getItem() instanceof BaseEMCGodWeapon){
            BaseEMCGodWeapon i_q = (BaseEMCGodWeapon) stack.getItem();
            if(i_q.getGemType(stack) == 0){
                event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.gem_null"));
            }else{
                event.getToolTip().add(getGemName(i_q.getGemType(stack)));
            }
        }
        if(stack.getItem() instanceof IAdditionsDamageWeapon){
            IAdditionsDamageWeapon item = (IAdditionsDamageWeapon) stack.getItem();
            event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.weapon_god_addition_damage",String.format("%.2f",item.getAdditionsDamage(stack))));
        }
        if(stack.getItem() instanceof IRangeAttackWeapon){
            IRangeAttackWeapon item1 = (IRangeAttackWeapon) stack.getItem();
            event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.weapon_god_attack_range",String.format("%.1f",item1.getAttackRange(stack))));
        }
        if(stack.getItem() instanceof IUpgradeableArmor){
            IUpgradeableArmor item_1_1 = (IUpgradeableArmor) stack.getItem();
            event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.armor_god_hurt",String.format("%.2f",(1-item_1_1.hurtRate(stack))*100)).append("%"));
            event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.armor_god_health",String.format("%.2f",item_1_1.extraHealth(stack))));
        }
        if(stack.getItem() instanceof ICostEMCItem){
            ICostEMCItem item2 = (ICostEMCItem) stack.getItem();
            double cost = item2.costEMCWhenAttack(stack);
            if(cost > 1){
                event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.weapon_god_emc_cost_addon",String.format("%.2f",(cost-1)*100)+"%"));
            }
            if(cost < 1){
                event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.weapon_god_emc_cost",String.format("%.2f",(1-cost)*100)+"%"));
            }
        }
        if(stack.getItem() instanceof ISpeedArmor){
            ISpeedArmor item_2_5 = (ISpeedArmor) stack.getItem();
            event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.armor_god_speed",String.format("%.2f",item_2_5.getSpeed(stack))));
        }
        if(stack.getItem() instanceof IReachArmor){
            IReachArmor item_2_6 = (IReachArmor) stack.getItem();
            event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.armor_reach_distance",String.format("%.2f",item_2_6.getReachDistance(stack))));
        }
        if(stack.getItem() instanceof ICriticalWeapon){
            ICriticalWeapon ww = (ICriticalWeapon) stack.getItem();
            event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.critical_chance",String.format("%.2f",ww.getActCriticalChance(stack)*100)).append("%"));
            event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.critical_rate",String.format("%.2f",ww.getActCriticalRate(stack)*100)).append("%"));
        }
        if(stack.getItem() instanceof IAttackSpeedItem){
            IAttackSpeedItem si = (IAttackSpeedItem) stack.getItem();
            double rate = si.getAttackSpeed(stack);
            if(rate > 1){
                event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.attack_speed_add",String.format("%.2f",(rate-1)*100)).append("%"));
            }
            if(rate < 1){
                event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.attack_speed_loss",String.format("%.2f",(1-rate)*100)).append("%"));
            }
        }
        if(stack.getItem() instanceof ISecondEMCItem){
            ISecondEMCItem item3 = (ISecondEMCItem) stack.getItem();
            event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.weapon_god_emc_second", MathUtils.format(item3.EMCModifySecond(stack))));
        }
        if(stack.getItem() instanceof BaseEMCGodWeapon){
            BaseEMCGodWeapon w = (BaseEMCGodWeapon) stack.getItem();
            event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.killCount",MathUtils.format(w.getKillCount(stack))));
        }
        if(stack.getItem() instanceof IRangeAttackWeapon){
            IRangeAttackWeapon a_w = (IRangeAttackWeapon) stack.getItem();
            event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.attack_range").append(EMCWorld.tc(a_w.getAttackMode(stack).getName())));
        }
        if(stack.getItem() instanceof IEMCInfuserItem){
            IEMCInfuserItem ii_i = (IEMCInfuserItem) stack.getItem();
            event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.emc_power",String.format("%.1f",ii_i.getInfuserRate(stack)*100)).append("%"));
        }
        for(Item item: radiationItem){
            if(stack.getItem().getItem().equals(item)){
                event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.radiation_item"));
            }
        }
        if(stack.getItem() instanceof ItemHazmatSuitArmor || stack.getItem().equals(MekanismItems.MODULE_RADIATION_SHIELDING.getItem().getItem())){
            event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.unclear_limit"));
        }
        if(stack.getItem() instanceof ISponsorItem){
            ISponsorItem item4 = (ISponsorItem) stack.getItem();
            Sponsors sp = item4.getSponsor();
            int level = 0;
            String name;
            if(sp == null){
                name = "";
            }else{
                level = sp.getSponsorLevel();
                name = sp.getPlayerName();
            }
            event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.sponsoritem",name).setStyle(Style.EMPTY.withItalic(true).withColor(getColor(level))));
            ClientPlayerEntity player = Minecraft.getInstance().player;
            if(player == null) return;
            if(new Sponsors(player.getScoreboardName(),player.getUUID(), EMCWorldAPI.getInstance().getUtilCapability(player).getLevel()).equals(sp)){
                event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.sponsoract"));
            }
        }
        if(!(isTrans || free) && stage.equals("disabled")){
            event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.emc_disable"));
            return;
        }
        if(!(isTrans || free) && !stage.equals("")){
            event.getToolTip().add(EMCWorld.tc("tooltip.emcworld.emc_locked",stage));
        }
    }

    @Nonnull
    private static Color getColor(int level){
        switch (level){
            case 2:
                return Color.fromRgb(0x00aaaa);
            case 3:
                return Color.fromRgb(0xffaa00);
            default:
                return Color.fromRgb(0x00aa00);
        }
    }

    @Nonnull
    private static IFormattableTextComponent getGemName(int type){
        String pre = "item."+EMCWorld.MODID+".";
        String n = "";
        String a = "_gemstone";
        switch (type){
            case 1:
                n = "blood";
                break;
            case 2:
                n = "nature";
                break;
            case 3:
                n = "lake";
                break;
            case 4:
                n = "abyss";
        }
        return EMCWorld.tc(pre+n+a).setStyle(Style.EMPTY.withColor(Color.fromRgb(BaseWeaponGemItem.gem.valueOf(n.toUpperCase()).getColor())));
    }
}
