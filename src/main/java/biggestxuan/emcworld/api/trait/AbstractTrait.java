package biggestxuan.emcworld.api.trait;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.event.PlayerGetXPEvent;
import biggestxuan.emcworld.api.event.PlayerModifyEMCEvent;
import biggestxuan.emcworld.api.event.PlayerPrefixFreshEvent;
import biggestxuan.emcworld.api.event.PlayerUpgradeItemEvent;
import moze_intel.projecte.api.event.PlayerAttemptLearnEvent;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

@EMCWorldSince("1.1.0")
public abstract class AbstractTrait implements ITrait {
    public static AbstractTrait FAKE_TRAIT = new AbstractTrait("null", TraitType.OTHER,-1,0) {};

    private final String name;
    protected TraitType type;
    protected int level;
    protected final ResourceLocation rl;
    protected final int color;

    public AbstractTrait(ResourceLocation rl,int color){
        this(rl,TraitType.TOOL,1,color);
    }

    public AbstractTrait(ResourceLocation rl,TraitType type,int level,int color){
        this(rl.toString(),type,level,color);
    }

    public AbstractTrait(String name, TraitType type, int level, int color){
        this.type = type;
        this.level = level;
        this.rl = new ResourceLocation(name);
        this.color = color;
        this.name = name;
    }

    public Ingredient baseItem(){
        return Ingredient.of(item());
    };

    public Item item() {
        return Items.AIR;
    }

    @Override
    public boolean isFake(){
        return this == FAKE_TRAIT;
    }

    @Override
    public IFormattableTextComponent getName() {
        return EMCWorld.tc("trait.emcworld."+rl.getPath());
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
    public ResourceLocation getRL() {
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
    public void afterBreak(PlayerEntity player, BlockState state, ItemStack stack) {

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
    public void onKeyPress(PlayerEntity player, ItemStack stack) {

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

    @Override
    public void onProfessionalExp(PlayerEntity player, ItemStack stack, PlayerGetXPEvent event) {

    }

    @Override
    public void onDeath(PlayerEntity player, ItemStack stack) {

    }

    @Override
    public void onPlayerRespawn(PlayerEntity player, ItemStack stack, boolean isEnd) {

    }

    @Override
    public void onGetKnowledge(PlayerEntity player, ItemStack stack, PlayerAttemptLearnEvent event) {

    }

    @Override
    public void onPickUpItem(PlayerEntity player, ItemStack stack, ItemEntity entity) {

    }

    @Override
    public long onQuestComplete(PlayerEntity player, ItemStack stack, long emc) {
        return emc;
    }

    @Override
    public void onGetStage(PlayerEntity player, ItemStack stack, String stage) {

    }

    @Override
    public void onOpenContainer(PlayerEntity player, ItemStack stack, Container container) {

    }

    @Override
    public void onFishing(PlayerEntity player, ItemStack stack, NonNullList<ItemStack> drops) {

    }

    @Override
    public String toString(){
        return "[EMCWorld Trait] Name:"+getName().getString()+",Type:"+type.toString()+", Level:"+level;
    }
}
