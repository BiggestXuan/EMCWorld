package biggestxuan.emcworld.common.traits;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.event.PlayerCostEMCEvent;
import biggestxuan.emcworld.api.event.PlayerPrefixFreshEvent;
import biggestxuan.emcworld.api.event.PlayerUpgradeItemEvent;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/13
 */

//Only called for players, not for fake players.
@EMCWorldSince("1.1.0")
public interface ITrait {
    ResourceLocation getName();

    String getDescription();

    TraitType getTraitType();

    void setTraitType(TraitType type);

    void setLevel(int level);

    int getColor();

    //You should use AbstractTrait class.
    int getTraitLevel();

    //Every tick in player's inventory.
    void onInventoryTick(PlayerEntity player, ItemStack stack);

    //Every tick in player's armors.
    void onArmorTick(PlayerEntity player,ItemStack stack);

    //Before a block breaks.
    void beforeBreak(PlayerEntity player, BlockState state,ItemStack stack);

    //When hit a living entity.
    void onHitEntity(PlayerEntity player, LivingEntity living,ItemStack stack);

    //When cause damage on living entity.
    float onAttackEntity(PlayerEntity player, LivingEntity living,float damage,ItemStack stack);

    //When owner hurt.
    float onHurt(PlayerEntity player, DamageSource source,float damage,ItemStack stack);

    //When owner heal.
    float onHeal(PlayerEntity player,float amt,ItemStack stack);

    //When owner eat foods.(Not support tool trait)
    void onEat(PlayerEntity player,ItemStack stack);

    //When owner upgrading an item.
    void onUpgradeItem(PlayerEntity player, PlayerUpgradeItemEvent.Pre event,ItemStack stack);

    //When owner fresh an item prefix.
    void onPrefixFresh(PlayerEntity player, PlayerPrefixFreshEvent event,ItemStack stack);

    //When player get or loss EMC.
    void onEMCModify(PlayerEntity player, PlayerCostEMCEvent event,ItemStack stack);

    //When player press trait key.
    void onKeyPressed(PlayerEntity player,ItemStack stack);

    default int getPriority(){
        return 1000;
    }
}
