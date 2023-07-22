package biggestxuan.emcworld.common.traits;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.event.PlayerModifyEMCEvent;
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

@EMCWorldSince("1.1.0")
public abstract class AbstractTrait implements ITrait{
    protected TraitType type;
    protected int level;
    protected final ResourceLocation rl;
    protected final int color;

    public AbstractTrait(String name, TraitType type, int level, int color){
        this.type = type;
        this.level = level;
        this.rl = EMCWorld.rl(name+"_"+type.toString());
        this.color = color;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public TraitType getTraitType() {
        return type;
    }

    @Override
    public String getDescription() {
        return "trait."+rl.getNamespace()+"."+rl.getPath();
    }

    @Override
    public ResourceLocation getName() {
        return this.rl;
    }

    @Override
    public int getTraitLevel() {
        return this.level;
    }

    @Override
    public void onInventoryTick(PlayerEntity player, ItemStack stack) {

    }

    @Override
    public void onArmorTick(PlayerEntity player, ItemStack stack) {

    }

    @Override
    public void beforeBreak(PlayerEntity player, BlockState state, ItemStack stack) {

    }

    @Override
    public void onHitEntity(PlayerEntity player, LivingEntity living, ItemStack stack) {

    }

    @Override
    public float onAttackEntity(PlayerEntity player, LivingEntity living, float damage, ItemStack stack) {
        return damage;
    }

    @Override
    public float onHurt(PlayerEntity player, DamageSource source, float damage, ItemStack stack) {
        return damage;
    }

    @Override
    public float onHeal(PlayerEntity player, float amt, ItemStack stack) {
        return amt;
    }

    @Override
    public void onEat(PlayerEntity player, ItemStack stack) {

    }

    @Override
    public void onUpgradeItem(PlayerEntity player, PlayerUpgradeItemEvent.Pre event, ItemStack stack) {

    }

    @Override
    public void onPrefixFresh(PlayerEntity player, PlayerPrefixFreshEvent event, ItemStack stack) {

    }

    @Override
    public void onEMCModify(PlayerEntity player, PlayerModifyEMCEvent event, ItemStack stack) {

    }

    @Override
    public void onKeyPressed(PlayerEntity player, ItemStack stack) {

    }

    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level){
        this.level = level;
    }

    @Override
    public void setTraitType(TraitType type){
        this.type = type;
    }
}
