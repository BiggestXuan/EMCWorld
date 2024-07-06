package biggestxuan.emcworld.api.trait;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.event.PlayerGetXPEvent;
import biggestxuan.emcworld.api.event.PlayerModifyEMCEvent;
import biggestxuan.emcworld.api.event.PlayerPrefixFreshEvent;
import biggestxuan.emcworld.api.event.PlayerUpgradeItemEvent;
import dev.ftb.mods.ftbquests.events.CustomRewardEvent;
import moze_intel.projecte.api.event.PlayerAttemptLearnEvent;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.IFormattableTextComponent;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/13
 */

//Only called for players, not for fake players.
@EMCWorldSince("1.1.0")
public interface ITrait {
    boolean isFake();

    Ingredient baseItem();

    IFormattableTextComponent getName();

    ResourceLocation getRL();

    String getDescription();

    TraitType getTraitType();

    void setTraitType(TraitType type);

    void setLevel(int level);

    int getColor();

    //You should use AbstractTrait class.
    int getTraitLevel();

    void onInventoryTick(PlayerEntity player, ItemStack stack);

    void onArmorTick(PlayerEntity player,ItemStack stack);

    void afterBreak(PlayerEntity player, BlockState state, ItemStack stack);

    void onHitEntity(PlayerEntity player, LivingEntity living,ItemStack stack);

    float onAttackEntity(PlayerEntity player, LivingEntity living,float damage,ItemStack stack);

    float onHurt(PlayerEntity player, DamageSource source,float damage,ItemStack stack);

    float onHeal(PlayerEntity player,float amt,ItemStack stack);

    void onEat(PlayerEntity player,ItemStack stack);

    void onUpgradeItem(PlayerEntity player, PlayerUpgradeItemEvent.Pre event,ItemStack stack);

    void onPrefixFresh(PlayerEntity player, PlayerPrefixFreshEvent event,ItemStack stack);

    void onEMCModify(PlayerEntity player, PlayerModifyEMCEvent event, ItemStack stack);

    void onKeyPress(PlayerEntity player, ItemStack stack);

    void onProfessionalExp(PlayerEntity player, ItemStack stack, PlayerGetXPEvent event);

    void onDeath(PlayerEntity player,ItemStack stack);

    void onPlayerRespawn(PlayerEntity player,ItemStack stack,boolean isEnd);

    void onGetKnowledge(PlayerEntity player, ItemStack stack, PlayerAttemptLearnEvent event);

    void onPickUpItem(PlayerEntity player, ItemStack stack, ItemEntity entity);

    long onQuestComplete(PlayerEntity player, ItemStack stack, long emc);

    void onGetStage(PlayerEntity player,ItemStack stack,String stage);

    void onOpenContainer(PlayerEntity player, ItemStack stack, Container container);

    void onFishing(PlayerEntity player, ItemStack stack, NonNullList<ItemStack> drops);

    default int getPriority(){
        return 1000;
    }

    String toString();
}
