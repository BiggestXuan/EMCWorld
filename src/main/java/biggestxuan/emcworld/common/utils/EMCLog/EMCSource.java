package biggestxuan.emcworld.common.utils.EMCLog;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/07
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.items.LotteryItem;
import biggestxuan.emcworld.common.utils.Lottery.Lottery;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.NonNullList;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class EMCSource<T> {
    protected long emc;
    private final BlockPos pos;
    private final RegistryKey<World> dim;
    private final T target;
    protected final double amt;

    public EMCSource(long emc,BlockPos pos,RegistryKey<World> dim,T target,double amt){
        this.emc = emc;
        this.pos = pos;
        this.dim = dim;
        this.target = target;
        this.amt = amt;
    }

    public long emc(){
        return emc;
    }

    public void setEmc(long emc){
        this.emc = emc;
    }

    public BlockPos getPos() {
        return pos;
    }

    public T getTarget() {
        return target;
    }

    public ResourceLocation getDim() {
        return dim.location();
    }

    public String getEMC(){
        return MathUtils.longSign(emc);
    }

    public String getInfo() {
        if (getPet() != null) {
            return EMCWorld.tc("emcsource.title_pet", getPet().getDisplayName().getString(), pos.toString(), getDim().toString()).getString() + " ";
        }
        return EMCWorld.tc("emcsource.title", pos.toString(), getDim().toString()).getString() + " ";
    }

    @Nullable
    public LivingEntity getPet(){
        return null;
    }

    public EMCSource(long emc, Entity entity, T target, double amt){
        this(emc,new BlockPos(entity.position()),entity.level.dimension(),target,amt);
    }

    public static class AttackEMCSource extends EMCSource<LivingEntity>{
        private final LivingEntity living;
        private final LivingEntity pet;
        public AttackEMCSource(long emc, PlayerEntity player, @Nonnull LivingEntity living, double amount, @Nullable LivingEntity pet) {
            super(emc,player,living,amount);
            this.living = living;
            this.pet = pet;
        }

        @Override
        public LivingEntity getPet(){
            return pet;
        }

        @Override
        public String getInfo() {
            return super.getInfo()+EMCWorld.tc("emcsource.attack",living.getDisplayName().getString(),amt,getEMC()).getString();
        }
    }

    public static class WakeUpEMCSource extends EMCSource<Object>{
        public WakeUpEMCSource(long emc, PlayerEntity player, Object target, double amt) {
            super(emc, player, target, amt);
        }

        @Override
        public String getInfo() {
            return super.getInfo()+EMCWorld.tc("emcsource.wake",getEMC()).getString();
        }
    }

    public static class UseHoeEMCSource extends EMCSource<ItemStack>{
        private final ItemStack stack;
        public UseHoeEMCSource(long emc, PlayerEntity player, ItemStack target, double amt) {
            super(emc, player, target, amt);
            stack = target;
        }

        @Override
        public String getInfo() {
            return super.getInfo()+EMCWorld.tc("emcsource.hoe",stack.toString(),getEMC()).getString();
        }
    }

    public static class OpenContainerEMCSource extends EMCSource<Container>{
        private final Container container;

        public OpenContainerEMCSource(long emc, PlayerEntity player, Container target, double amt) {
            super(emc, player, target, amt);
            container = target;
        }

        @Override
        public String getInfo() {
            return super.getInfo()+EMCWorld.tc("emcsource.container",getItems(),getEMC()).getString();
        }

        private String getItems(){
            StringBuilder n = new StringBuilder();
            for(ItemStack stack : container.getItems()){
                n.append(stack.toString()).append(",");
            }
            return n.toString();
        }
    }

    public static class FillBucketEMCSource extends EMCSource<ItemStack>{
        private final ItemStack stack;
        public FillBucketEMCSource(long emc, PlayerEntity player, ItemStack target, double amt) {
            super(emc, player, target, amt);
            stack = target;
        }

        @Override
        public String getInfo() {
            return super.getInfo()+EMCWorld.tc("emcsource.bucket",getEMC()).getString();
        }
    }

    public static class CraftItemEMCSource extends EMCSource<ItemStack>{
        private final ItemStack stack;

        public CraftItemEMCSource(long emc, PlayerEntity player, ItemStack target, double amt) {
            super(emc, player, target, amt);
            stack = target;
        }

        @Override
        public String getInfo() {
            return super.getInfo()+EMCWorld.tc("emcsource.craft",stack.toString(),getEMC()).getString();
        }
    }

    public static class BreakBlockEMCSource extends EMCSource<BlockState>{
        private final BlockState blockState;

        public BreakBlockEMCSource(long emc, PlayerEntity player, BlockState target, double amt) {
            super(emc, player, target, amt);
            blockState = target;
        }

        @Override
        public String getInfo() {
            return super.getInfo()+EMCWorld.tc("emcsource.break",blockState.toString(),getEMC()).getString();
        }
    }

    public static class DeathEMCSource extends EMCSource<Object>{

        public DeathEMCSource(long emc, PlayerEntity player, Object target, double amt) {
            super(emc, player, target, amt);
        }

        @Override
        public String getInfo() {
            return super.getInfo()+EMCWorld.tc("emcsource.death",getEMC()).getString();
        }
    }

    public static class HurtEMCSource extends EMCSource<Entity>{
        private final Entity entity;
        private final LivingEntity pet;

        public HurtEMCSource(long emc, PlayerEntity player, Entity target, double amt,@Nullable LivingEntity pet) {
            super(emc, player, target, amt);
            entity = target;
            this.pet = pet;
        }

        @Override
        public LivingEntity getPet(){
            return pet;
        }

        @Override
        public String getInfo() {
            return super.getInfo()+EMCWorld.tc("emcsource.hurt",entity == null ? "null" : entity.getName().getString(),amt,getEMC()).getString();
        }
    }

    public static class PickItemEMCSource extends EMCSource<ItemStack>{
        private final ItemStack entity;

        public PickItemEMCSource(long emc, PlayerEntity player, ItemStack target, double amt) {
            super(emc, player, target, amt);
            entity = target;
        }

        @Override
        public String getInfo() {
            ResourceLocation rl = entity.getItem().getItem().getRegistryName();
            return super.getInfo()+EMCWorld.tc("emcsource.pick",rl == null ? "null" : rl.toString()+"x"+getTarget().getCount(),getEMC()).getString();
        }
    }

    public static class QuestCompletedEMCSource extends EMCSource<Object>{
        private final String stage;
        private final String difficulty;

        public QuestCompletedEMCSource(long emc, PlayerEntity player, Object target, double amt,String stage,String difficulty) {
            super(emc, player, target, amt);
            this.stage = stage;
            this.difficulty = difficulty;
        }

        @Override
        public String getInfo() {
            return super.getInfo()+EMCWorld.tc("emcsource.quest",stage,difficulty,getEMC()).getString();
        }
    }

    public static class ProduceItemEMCSource extends EMCSource<List<ItemStack>>{
        private final List<ItemStack> list;

        public ProduceItemEMCSource(long emc, PlayerEntity player, List<ItemStack> target, double amt) {
            super(emc, player, target, amt);
            list = target;
        }

        @Override
        public String getInfo() {
            return super.getInfo()+EMCWorld.tc("emcsource.produce",getItems(),getEMC()).getString();
        }

        public String getItems(){
            String s = "";
            for(ItemStack stack : list){
                s += stack.toString() + ",";
            }
            return s;
        }
    }

    public static class UseEMCGemEMCSource extends EMCSource<ItemStack>{
        private final ItemStack stack;

        public UseEMCGemEMCSource(long emc, PlayerEntity player, ItemStack target, double amt) {
            super(emc, player, target, amt);
            stack = target;
        }

        @Override
        public String getInfo() {
            return super.getInfo()+EMCWorld.tc("emcsource.gem",stack.toString(),getEMC()).getString();
        }
    }

    public static class RepairItemEMCSource extends EMCSource<ItemStack>{
        private final ItemStack stack;

        public RepairItemEMCSource(long emc, PlayerEntity player, ItemStack target, double amt) {
            super(emc, player, target, amt);
            stack = target;
        }

        @Override
        public String getInfo() {
            return super.getInfo()+EMCWorld.tc("emcsource.repair",stack.toString(),getEMC()).getString();
        }
    }

    public static class VisCoreEMCSource extends EMCSource<BlockPos> {
        private final BlockPos visPos;
        public VisCoreEMCSource(long emc, PlayerEntity player, BlockPos target, double amt) {
            super(emc, player, target, amt);
            visPos = target;
        }

        @Override
        public String getInfo() {
            return super.getInfo()+EMCWorld.tc("emcsource.repair",visPos.toString(),getEMC()).getString();
        }
    }

    public static class TeleportEMCSource extends EMCSource<BlockPos>{
        private final BlockPos pos;
        private final long distance;

        public TeleportEMCSource(long emc, PlayerEntity player, BlockPos target, double amt,long distance) {
            super(emc, player, target, amt);
            pos = target;
            this.distance = distance;
        }

        public TeleportEMCSource(long emc, PlayerEntity player) {
            this(emc,player, null,0,0);
        }

        @Override
        public String getInfo() {
            return super.getInfo() + ((pos == null) ? EMCWorld.tc("emcsource.tp_null",getEMC()).getString() : EMCWorld.tc("emcsource.tp",pos.toString(),distance,getEMC()).getString());
        }
    }

    public static class LocateEMCSource extends EMCSource<Object>{

        public LocateEMCSource(long emc, PlayerEntity player, Object target, double amt) {
            super(emc, player, target, amt);
        }

        @Override
        public String getInfo() {
            return super.getInfo()+EMCWorld.tc("emcsource.locate",getEMC()).getString();
        }
    }

    public static class EffectEMCSource extends EMCSource<EffectInstance>{
        private final EffectInstance effect;

        public EffectEMCSource(long emc, PlayerEntity player, EffectInstance target, double amt) {
            super(emc, player, target, amt);
            effect = target;
        }

        @Override
        public String getInfo() {
            return super.getInfo()+EMCWorld.tc("emcsource.effect",effect.getEffect().getDisplayName().getString(),effect.getAmplifier()+1,getEMC()).getString();
        }
    }

    public static class CommandEMCSource extends EMCSource<Object>{

        public CommandEMCSource(long emc, PlayerEntity player, Object target, double amt) {
            super(emc, player, target, amt);
        }

        @Override
        public String getInfo() {
            return super.getInfo()+EMCWorld.tc("emcsource.command",getEMC()).getString();
        }
    }

    public static class LotteryEMCSource extends EMCSource<Lottery>{
        private final Lottery lottery;

        public LotteryEMCSource(long emc, PlayerEntity player, Lottery target) {
            super(emc, player, target, 0);
            lottery = target;
        }

        @Override
        public String getInfo(){
            List<Integer> list = lottery.getNum();
            list.addAll(lottery.getAdd());
            return super.getInfo()+EMCWorld.tc("emcsource.lottery",getEMC(), LotteryItem.getString(list),lottery.getRate()).getString();
        }
    }

    @EMCWorldSince("0.9.0")
    public static class FishEMCSource extends EMCSource<NonNullList<ItemStack>>{
        public FishEMCSource(long emc, PlayerEntity player, NonNullList<ItemStack> target) {
            super(emc, player, target,0);
        }

        @Override
        public String getInfo() {
            return super.getInfo()+EMCWorld.tc("emcsource.fish",getTarget().toString(),getEMC()).getString();
        }
    }

    @EMCWorldSince("0.9.0")
    public static class ExceptionAppleEMCSource extends EMCSource<Object>{
        public ExceptionAppleEMCSource(long emc,PlayerEntity player){
            super(emc,player,null,0);
        }

        @Override
        public String getInfo(){
            return super.getInfo()+EMCWorld.tc("emcsource.exception_apple",getEMC());
        }
    }

    @EMCWorldSince("1.0.2")
    public static class EMCTransferBloodSource extends EMCSource<Object>{
        public EMCTransferBloodSource(long emc,PlayerEntity player){
            super(emc,player,null,0);
        }

        @Override
        public String getInfo(){
            return super.getInfo()+EMCWorld.tc("emcsource.emc2blood",getEMC());
        }

    }

    @EMCWorldSince("1.0.3")
    public static class EMCPowerFlowerSource extends EMCSource<Object>{

        public EMCPowerFlowerSource(long emc, Entity entity, Object target) {
            super(emc, entity, target, 0);
        }

        @Override
        public String getInfo(){
            return super.getInfo()+EMCWorld.tc("emcsource.powerflower",getEMC()).getString();
        }
    }

    @EMCWorldSince("1.0.4")
    public static class IceCreamSource extends EMCSource<Object>{
        public IceCreamSource(long emc,PlayerEntity player){
            super(emc,player,player,0);
        }

        @Override
        public String getInfo(){
            return super.getInfo()+EMCWorld.tc("emcsource.icecream",getTarget().toString(),getEMC()).getString();
        }
    }

    @EMCWorldSince("1.0.5")
    public static class TulyeShieldSource extends EMCSource<Object>{
        public TulyeShieldSource(long emc,PlayerEntity player){
            super(emc,player,player,0);
        }

        @Override
        public String getInfo(){
            return super.getInfo()+EMCWorld.tc("emcsource.tulye_shield",getTarget().toString(),getEMC()).getString();
        }//todo
    }

    @EMCWorldSince("1.1.0")
    public static class EatSource extends EMCSource<PlayerEntity>{
        public EatSource(long emc,PlayerEntity player){
            super(emc,player,player,0);
        }

        @Override
        public String getInfo(){
            return super.getInfo()+EMCWorld.tc("emcsource.eat",getTarget().getScoreboardName(),getEMC()).getString();
        }
    }
}
