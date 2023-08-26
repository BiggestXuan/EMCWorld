package biggestxuan.emcworld.common.utils;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/26
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.api.item.ICostEMCItem;
import biggestxuan.emcworld.api.item.IEMCRepairableItem;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.api.item.equipment.IGemInlaidItem;
import biggestxuan.emcworld.api.item.equipment.bow.IUpgradeBow;
import biggestxuan.emcworld.api.item.equipment.weapon.IAdditionsDamageWeapon;
import biggestxuan.emcworld.api.item.equipment.weapon.ICriticalWeapon;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.compact.CraftTweaker.CrTConfig;
import biggestxuan.emcworld.common.compact.FTBQuests.QuestReward;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.compact.Projecte.EMCGemsMapping;
import biggestxuan.emcworld.common.compact.ScalingHealth.DifficultyHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Dagger.DaggerItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Staff.StaffItem;
import biggestxuan.emcworld.common.registry.EWDamageSource;
import biggestxuan.emcworld.common.registry.EWEnchantments;
import biggestxuan.emcworld.common.registry.EWModules;
import biggestxuan.emcworld.common.utils.Sponsors.Sponsors;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import dev.ftb.mods.ftbquests.quest.reward.CustomReward;
import mekanism.common.content.gear.Module;
import mekanism.common.item.gear.ItemMekaSuitArmor;
import net.blay09.mods.waystones.api.IWaystone;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.openzen.zencode.java.ZenCodeType;
import vazkii.botania.common.item.ItemKeepIvy;

import javax.annotation.Nullable;
import java.util.*;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.math")
@SuppressWarnings("unused")
public final class MathUtils {

    @ZenCodeType.Method
    public static double Random(){
        return Math.random();
    }

    @ZenCodeType.Method
    public static boolean isRandom(double chance){
        return Random() <= chance;
    }

    @ZenCodeType.Method
    public static int getRandom(int max){
        return new Random().nextInt(max);
    }

    @ZenCodeType.Method
    public static int getRangeRandom(int min,int max){
        if(min == max) return min;
        Random random = new Random();
        int value = max - min;
        if(value < 0){
            return -(random.nextInt(-value + 1) + min);
        }
        return random.nextInt(max - min + 1) + min;
    }

    @ZenCodeType.Method
    public static long getEMCWhenUseGem(long baseEMC){
        double diff = CrTConfig.getWorldDifficulty();
        double rate;
        if(diff<=1) rate=1;
        else{
            rate = -0.3*diff+1.3;
        }
        double rand = 0.2*Random()+0.9;
        return Long.parseLong(String.valueOf(Math.round(rate*baseEMC*rand)));
    }

    @ZenCodeType.Method
    public static int getDifficultyMulti(int base){
        double diff = CrTConfig.getWorldDifficulty();
        double multi = -1.6 * diff + 5.8;
        return Integer.parseInt(String.valueOf(Math.round(multi*base)));
    }

    public static double getRangePlayerAverageIndex(Entity entity,int range){
        BlockPos pos = entity.blockPosition();
        MinecraftServer server = entity.getServer();
        if(server != null){
            List<? extends PlayerEntity> players = server.getPlayerList().getPlayers();
            double playerAmount = server.getPlayerCount();
            if(playerAmount == 0) return 0;
            double indexAmount = 0;
            for(PlayerEntity player : players){
                for(DifficultySetting setting : DifficultySetting.values()){
                    if(GameStageManager.hasStage(player, setting.getGameStage())){
                        indexAmount += setting.getIndex();
                        break;
                    }
                }
            }
            return indexAmount / playerAmount;
        }
        return 0;
    }

    public static int[] StringArray2IntArray(String[] array){
        int[] a = new int[array.length];
        for (int i = 0; i < array.length ; i++) {
            a[i] = isNum(array[i]) ? Integer.parseInt(array[i]) : 0;
        }
        return a;
    }

    public static String thousandSign(long text){
        return thousandSign(String.valueOf(text));
    }

    public static String thousandSign(String text){
        int len = text.length();
        ArrayList<String> stringContainer = new ArrayList<>();
        while(len>3){
            stringContainer.add(text.substring(len-3,len));
            len-=3;
        }
        stringContainer.add(text.substring(0,len));
        StringBuilder buffer = new StringBuilder();
        for(int i = stringContainer.size()-1;i>=0;i--){
            buffer.append(stringContainer.get(i)).append(",");
        }
        buffer.deleteCharAt(buffer.length()-1);
        return buffer.toString();
    }

    public static float getAdditionDamage(Entity attacker, LivingEntity target, float damage){
        double difficulty = DifficultyHelper.getLivingDifficulty(target);
        return (float) (damage * (difficulty / 120f * ConfigManager.DIFFICULTY.get()));
    }

    public static <T> List<T> copyList(List<T> list){
        return new ArrayList<>(list);
    }

    public static String longSign(long value){
        if(value < 0){
            return "-" + thousandSign(Math.negateExact(value));
        }
        return "+" + thousandSign(value);
    }

    public static boolean isNum(String s){
        if (s == null) {
            return false;
        }
        try{
            int i = Integer.parseInt(s);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    @ZenCodeType.Method
    public static String format(String text){
        if(text.length() <= 6 || !ConfigManager.FORMAT.get()){
            return thousandSign(text);
        }
        if(ConfigManager.FORMAT.get()){
            return KMT(text);
        }
        return text;
    }

    public static String KMT(String text){
        if(text.length() <= 6){
            return format(text);
        }
        String flag = getFlag(text);
        return formatAfter(text)+flag;
    }

    public static int getInfinityCardCost(){
        return (int) (4000 * ConfigManager.DIFFICULTY.get() / 3);
    }

    public static float getBowAdditionDamage(ItemStack stack){
        Item item = stack.getItem();
        if(item instanceof IUpgradeBow && item instanceof IPrefixItem && item instanceof BowItem){
            IUpgradeBow i1 = (IUpgradeBow) item;
            IPrefixItem i2 = (IPrefixItem) item;
            return i1.getLevel(stack) * 1.25F + (i2.getPrefix(stack).getLevel() - 4) * 0.5F;
        }
        return 0f;
    }

    public static long min(long a,long b,long c){
        if(a == b && b == c) return a;
        a = Math.min(a,b);
        a = Math.min(a,c);
        return a;
    }

    public static long max(long a,long b,long c){
        if(a == b && b == c) return a;
        a = Math.max(a,b);
        a = Math.max(a,c);
        return a;
    }


    public static float getBowLossTime(ItemStack stack){
        Item item = stack.getItem();
        if(item instanceof IUpgradeBow && item instanceof IPrefixItem && item instanceof BowItem){
            IUpgradeBow i1 = (IUpgradeBow) item;
            IPrefixItem i2 = (IPrefixItem) item;
            int prefixLevel = i2.getPrefix(stack).getLevel();
            return (float) (Math.pow(0.98d,i1.getLevel(stack)) * (prefixLevel >= 4 ? 1 - (0.01f * prefixLevel) : 1 + (0.025f * (4 - prefixLevel))));
        }
        return 0f;
    }

    public static String format(double value){
        return format(String.valueOf((long) value));
    }

    public static <T> String format(T value){
        return format(String.valueOf(value));
    }

    public static String doubleFormat(double v){
        return v < 1000d ? String.format("%.1f",v) : format(v);
    }

    public static long doubleToLong(double value){
        return Long.parseLong(String.valueOf(Math.round(value)));
    }

    @ZenCodeType.Method
    public static double difficultyLoss(){
        return CrTConfig.getWorldDifficulty();
    }

    public static long getPlayerDeathBaseCost(PlayerEntity player) {
        if(!ConfigManager.EMC_DEATH.get()){
            return 0L;
        }
        long costEMC = 0;
        for (DifficultySetting obj : DifficultySetting.values()) {
            if (GameStageManager.hasStage(player, obj.getGameStage())) {
                PlayerInventory playerInventory = player.inventory;
                double amt = 0;
                for (ItemStack item : playerInventory.items) {
                    double rate = 1.0d;
                    if(ItemKeepIvy.hasIvy(item)){
                        rate = 0d;
                    }
                    if(item.getItem() instanceof ICostEMCItem){
                        rate = ((ICostEMCItem) item.getItem()).getEMCCostRate();
                    }
                    amt += (rate * 64 / item.getMaxStackSize()) * item.getCount();
                }
                costEMC = MathUtils.doubleToLong(amt * obj.getDeathBase() * MathUtils.difficultyLoss());
                break;
            }
        }
        return costEMC;
    }

    public static double getCommonBaseCost(PlayerEntity player){
        for(DifficultySetting obj : DifficultySetting.values()){
            if(GameStageManager.hasStage(player,obj.getGameStage())){
                return obj.getCommonBase();
            }
        }
        return 0d;
    }

    public static double getGodWeaponAddition(ItemStack stack,double base){
        if(stack.getItem() instanceof IUpgradeableItem){
            IUpgradeableItem item = (IUpgradeableItem) stack.getItem();
            int level = item.getLevel(stack);
            int c = level - 22;
            return base * (1 + (c << 3) / 95d);
        }
        return 0d;
    }

    public static double getAttackBaseCost(PlayerEntity player){
        if(!ConfigManager.EMC_ATTACK.get()){
            return 0;
        }
        for(DifficultySetting obj:DifficultySetting.values()){
            if(GameStageManager.hasStage(player, obj.getGameStage())){
                return obj.getAttackBase();
            }
        }
        return 0;
    }

    public static double getChestBaseCost(PlayerEntity player, Container container){
        double baseCost = 0;
        if(!ConfigManager.EMC_CONTAINER.get()){
            return 0;
        }
        for(DifficultySetting obj:DifficultySetting.values()){
            if(GameStageManager.hasStage(player,obj.getGameStage())){
                double rate;
                for(ItemStack itemStack:container.getItems()){
                    rate = 1.0d;
                    if(itemStack == ItemStack.EMPTY) continue;
                    if(itemStack.getItem() instanceof ICostEMCItem){
                        rate = ((ICostEMCItem) itemStack.getItem()).getEMCCostRate();
                    }
                    baseCost += rate * itemStack.getCount() * 64 / itemStack.getMaxStackSize();
                }
                for(ItemStack itemStack:player.inventory.items){
                    rate = 1.0d;
                    if(itemStack == ItemStack.EMPTY) continue;
                    if(itemStack.getItem() instanceof ICostEMCItem){
                        rate = ((ICostEMCItem) itemStack.getItem()).getEMCCostRate();
                    }
                    baseCost -= rate * itemStack.getCount() * 64 / itemStack.getMaxStackSize();
                }
                baseCost *= obj.getChestBase();
                break;
            }
        }
        return Math.max(baseCost,0);
    }
    public static double getUseHoeBaseCost(PlayerEntity player){
        return getCommonBaseCost(player) * 3.5d;
    }
    public static double getPickUpItemBaseCost(PlayerEntity player){
        return getCommonBaseCost(player) * 0.5d;
    }
    public static double getFillBucketBaseCost(PlayerEntity player){
        return getCommonBaseCost(player) * 4.0d;
    }
    public static double getWakeUpBaseCost(PlayerEntity player){
        return getCommonBaseCost(player) * 30.0d * 5;
    }
    public static double getCraftBaseCost(PlayerEntity player){
        return getCommonBaseCost(player) * 0.75d;
    }

    public static double getBreakBlockCost(PlayerEntity player){
        if(!GameStageManager.hasStage(player,"two")){
            return 0;
        }
        return getCommonBaseCost(player) * 0.2d;
    }
    public static double getQuestCompletedRewardBase(String stage, CustomReward customReward){
        if(!ConfigManager.EMC_QUEST.get()){
            return 0;
        }
        double base = 0d;
        for(DifficultySetting obj:DifficultySetting.values()){
            if(stage.equals(obj.getGameStage().toLowerCase())){
                for(QuestReward reward: QuestReward.values()){
                    if(customReward.hasTag(reward.getTag())){
                        base += reward.getBaseEMC();
                        break;
                    }
                }
                base *= obj.getFtbBase();
                break;
            }
        }
        return base;
    }

    public static long getEatFoodBase(PlayerEntity player, Food food){
        for(DifficultySetting setting : DifficultySetting.values()){
            if(GameStageManager.hasStage(player,setting.getGameStage())){
                double c = food.getNutrition() + food.getSaturationModifier() * 10;
                return (long) (getCommonBaseCost(player) * c * 0.05);
            }
        }
        return 0L;
    }

    public static QuestInfo getQuestCompletedRewardBase(PlayerEntity player, CustomReward customReward){
        double base = 0d;
        String diff = "";
        for(DifficultySetting obj:DifficultySetting.values()){
            if(GameStageManager.hasStage(player,obj.getGameStage())){
                for(QuestReward reward: QuestReward.values()){
                    diff = reward.getTag();
                    if(customReward.hasTag(reward.getTag())){
                        base += reward.getBaseEMC();
                        break;
                    }
                }
                base *= obj.getFtbBase();
                break;
            }
        }
        return new QuestInfo((long) base,diff);
    }
    public static boolean isMaxDifficulty(){
        return CrTConfig.getWorldDifficulty() == 3.0d;
    }
    public static String modulus(double input) {
        return Math.round(input * 100) + "%";
    }
    public static int getMaxAmount(long input1,long input2){
        int amt = 0;
        while(input1 >= input2){
            amt++;
            input1 -= input2;
            if(amt >=64) break;
        }
        return amt;
    }
    @ZenCodeType.Method
    public static List<Long> getEMCGemBase(){
        List<Long> out = new ArrayList<>();
        for(EMCGemsMapping obj : EMCGemsMapping.values()){
            out.add(obj.getBaseEMC());
        }
        return out;
    }

    public static int getAdditionAmount(double chance){
        int amt = 1;
        amt += (int) chance -1;
        if(isRandom(chance - (int) chance)){
            amt++;
        }
        return amt;
    }
    public static int getNeedXPToLevel(int level){
        if(level >=100) return 0;
        return (int) Math.round(Math.pow(1.15,level) * 100);
    }

    public static IPlayerSkillCapability getPlayerLevelCapability(PlayerEntity player){
        return EMCWorldAPI.getInstance().getPlayerSkillCapability(player);
    }

    public static boolean isTwoLivingDistance(World world1,World world2,Vector3d pos1,Vector3d pos2, double distance){
        if(world1.equals(world2)){
            return Math.sqrt(Math.pow(pos2.x-pos1.x,2)+Math.pow(pos2.y-pos1.y,2)+Math.pow(pos2.z-pos1.z,2)) < distance;
        }
        else return false;
    }

    public static boolean isTwoLivingDistance(World world1,World world2,Vector3d pos1,BlockPos pos2, double distance){
        if(world1.equals(world2)){
            return Math.sqrt(Math.pow(pos2.getX()-pos1.x,2)+Math.pow(pos2.getY()-pos1.y,2)+Math.pow(pos2.getZ()-pos1.z,2)) < distance;
        }
        else return false;
    }

    public static boolean isTwoLivingDistance(LivingEntity player1, LivingEntity player2, double distance){
        return isTwoLivingDistance(player1.getCommandSenderWorld(),player2.getCommandSenderWorld(),player1.position(),player2.position(),distance);
    }

    public static int range(int num,int min,int max){
        if(num < min){
            return min;
        }
        return Math.min(num, max);
    }

    public static AxisAlignedBB expandAABB(BlockPos pos,int range){
        return new AxisAlignedBB(new BlockPos(pos.getX()-range,pos.getY()-range,pos.getZ()-range),new BlockPos(pos.getX()+range,pos.getY()+range,pos.getZ()+range));
    }

    public static AxisAlignedBB expandAABB(Vector3d v ,int range){
        return expandAABB(new BlockPos(v),range);
    }

    @Nullable
    public static Sponsors getSponsor(PlayerEntity player){
        Sponsors sponsor;
        if(!player.getCapability(EMCWorldCapability.UTIL).isPresent()){
            sponsor = null;
        }else{
            IUtilCapability cap = EMCWorldAPI.getInstance().getUtilCapability(player);
            sponsor = new Sponsors(player.getScoreboardName(),player.getUUID(),cap.getLevel());
        }
        return sponsor;
    }

    public static double log(double base,double value){
        return Math.log(value) / Math.log(base);
    }

    public static double getDistance(BlockPos pos1,BlockPos pos2){
        return (long) Math.sqrt(Math.pow(pos1.getX()-pos2.getX(),2)+Math.pow(pos1.getY()-pos2.getY(),2)+Math.pow(pos1.getZ()-pos2.getZ(),2));
    }

    public static int getTPEMCCost(PlayerEntity player,Position start,Position end){
        if(!ConfigManager.EMC_TELEPORT.get()){
            return 0;
        }
        if(end == null){
            return 0;
        }
        BlockPos pos1 = start.pos;
        BlockPos pos2 = end.pos;
        if(Math.abs(pos1.getX()-pos2.getX()) >= EMCWorld.HOMO || Math.abs(pos1.getZ()-pos2.getZ()) >= EMCWorld.HOMO){
            return Integer.MAX_VALUE;
        }
        long distance = (long) getDistance(pos1,pos2);
        double emc = 0;
        distance = Math.max(0L,distance-128L);
        emc += distance;
        if(!start.world.equals(end.world)){
            emc *= 3;
        }
        emc *= ConfigManager.DIFFICULTY.get();
        emc = emc >= 1.04 ? log(1.02,emc) : emc;
        for(DifficultySetting diff : DifficultySetting.values()){
            if(GameStageManager.hasStage(player,diff.getGameStage())){
                emc *= diff.getCommonBase();
                break;
            }
        }
        return emc >= Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) emc / 18;
    }

    public static int getTPEMCCost(PlayerEntity player, IWaystone start,IWaystone end){
        if(end == null){
            return 0;
        }
        return getTPEMCCost(player,new Position(start.getPos(),start.getDimension().location()),new Position(end.getPos(),end.getDimension().location()));
    }

    private static String getFlag(String value){
        int c = value.length();
        String o = "";
        if(c <= 6 && c >= 4){
            o = "K";
        }else if(c <= 9 && c >= 7){
            o = "M";
        }else if(c <= 12 && c >= 10){
            o = "G";
        }else if(c <= 15 && c >= 13){
            o = "T";
        }else if(c <= 18 && c >= 16)o = "P";
        return o;
    }

    private static String formatAfter(String value){
        int c = value.length() - 1;
        if(c < 3) return value;
        if(c % 3 == 0){
            return value.charAt(0)+"."+value.substring(1,3);
        }
        if(c % 3 == 1){
            return value.substring(0,2)+"."+value.substring(2,4);
        }
        return value.substring(0,3)+"."+value.substring(3,5);
    }

    public static long getMirrorTPCost(PlayerEntity player, World origin, World target, double x, double y, double z){
        MathUtils.Position pos1 = new MathUtils.Position(new BlockPos(player.position()),origin.dimension().location());
        MathUtils.Position pos2 = new MathUtils.Position(new BlockPos(x,y,z),target.dimension().location());
        return getTPEMCCost(player,pos1,pos2);
    }

    public final static class Position{
        private final BlockPos pos;
        private final ResourceLocation world;

        public Position(BlockPos pos,ResourceLocation world){
            this.pos = pos;
            this.world = world;
        }
    }

    public final static class QuestInfo{
        private final long emc;
        private final String difficulty;

        public QuestInfo(long emc,String difficulty){
            this.emc = emc;
            this.difficulty = difficulty;
        }

        public long getEmc() {
            return emc;
        }

        public String getDifficulty() {
            return difficulty;
        }
    }

    public static boolean canAbsorbHurt(PlayerEntity player, DamageSource source){
        boolean flag = true;
        if(EWDamageSource.isReallyDamage(source) || source.equals(DamageSource.OUT_OF_WORLD)){
            return false;
        }
        NonNullList<ItemStack> armors = player.inventory.armor;
        for(ItemStack stack : armors){
            if(stack.getItem() instanceof ItemMekaSuitArmor && !stack.isEmpty()){
                ItemMekaSuitArmor armor = (ItemMekaSuitArmor) stack.getItem();
                if(armor.getModules(stack).size() == 0){
                    return false;
                }
                if(!containerModule(armor,stack,EMCWorld.rl("energy_protect_module"))){
                    flag = false;
                }
            }else{
                flag = false;
            }
        }
        return flag;
    }

    public static List<LivingEntity> getNearLiving(LivingEntity living,int distance,boolean includePlayer){
        AxisAlignedBB aabb = expandAABB(living.blockPosition(),distance);
        List<LivingEntity> list = new ArrayList<>();
        if(living.level.isClientSide) return list;
        for(LivingEntity l : living.level.getLoadedEntitiesOfClass(LivingEntity.class,aabb)){
            if(l instanceof PlayerEntity && !includePlayer){
                continue;
            }
            list.add(l);
        }
        return list;
    }

    public static long C(int n,int m) {
        long a = 1;
        long b = 1;
        m = Math.min(m, (n - m));
        for (long i = m; i > 0; i--) {
            a *= n;
            b *= i;
            n--;
        }
        return a / b;
    }

    public static List<List<Integer>> permute(List<Integer> nums, int count) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.size() == 0 || count > nums.size()) {
            return res;
        }
        List<Integer> temp = new ArrayList<>();
        backtrack(res, nums, temp, count);
        return res;
    }

    public static void backtrack(List<List<Integer>> res, List<Integer> nums, List<Integer> temp, int count) {
        if (temp.size() == count) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int num : nums) {
            if (temp.contains(num)) {
                continue;
            }
            if (temp.size() > 0 && num <= temp.get(temp.size() - 1)) {
                continue;
            }
            temp.add(num);
            backtrack(res, nums, temp, count);
            temp.remove(temp.size() - 1);
        }
    }

    public static Ingredient mergeIngredient(Ingredient ... ingredients){
        return mergeIngredient(List.of(ingredients));
    }

    public static Ingredient mergeIngredient(List<Ingredient> l){
        List<ItemStack> list = new ArrayList<>();
        for(Ingredient i : l){
            list.addAll(Arrays.asList(i.getItems()));
        }
        return Ingredient.of(list.stream());
    }

    @EMCWorldSince("1.0.3")
    public static String getDoubleFormat(double d){
        return String.format("%.2f",d);
    }

    @EMCWorldSince("1.0.2")
    public static float getMusicShooterDamage(float base,ItemStack stack){
        Item item = stack.getItem();
        if(!(item instanceof IUpgradeableItem) || !(item instanceof IPrefixItem)){
            return base;
        }
        IUpgradeableItem u = (IUpgradeableItem) item;
        IPrefixItem p = (IPrefixItem) item;
        base += base * 0.05 * u.getLevel(stack);
        int level = p.getPrefix(stack).getLevel();
        base += level <= 3 ? base * -0.12 * level : base * 0.04 * level;
        return base;
    }

    @EMCWorldSince("1.0.2")
    public static double getWeaponDPS(ItemStack stack,PlayerEntity player){
        Item item = stack.getItem();
        if(!(item instanceof TieredItem)){
            return -1;
        }
        double base = 0;
        boolean flag = false;
        if(item instanceof StaffItem){
            flag = true;
            base = ((StaffItem) item).getBaseDamage(stack);
        }else{
            if(item instanceof IAdditionsDamageWeapon){
                flag = true;
                base = ((IAdditionsDamageWeapon) item).getAdditionsDamage(player,stack).total();
            }
        }
        if(!flag){
            return -1;
        }
        return -1;//todo
    }

    @EMCWorldSince("1.0.3")
    public static List<TranslationTextComponent> getWeaponDisplayWillCost(PlayerEntity player, ItemStack stack, boolean isShift){
        Item i = stack.getItem();
        var list = new ArrayList<TranslationTextComponent>();
        double cost = getAttackBaseCost(player) * ConfigManager.DIFFICULTY.get();
        if(stack.getItem() instanceof ICostEMCItem){
            if(stack.getItem() instanceof StaffItem){
                cost *= ((StaffItem) stack.getItem()).getCostRate(stack,player);
            }else{
                cost *= ((ICostEMCItem) stack.getItem()).costEMCWhenAttackActually(stack);
            }
        }
        double t = 0;
        var damage = i instanceof StaffItem ? ((StaffItem) i).getManaBurstBaseDamage(stack,player):SkillUtils.getPlayerAttackDamage(player,stack);
        DamageUtils u = new DamageUtils(0);
        if(damage.getBaseDamage() == 0){
            return list;
        }
        if(stack.getItem() instanceof ICriticalWeapon){
            var item = (ICriticalWeapon) stack.getItem();
            double chance = item.getActCriticalChance(stack);
            double rate = item.getActCriticalRate(stack);
            t = damage.total() * chance * rate;
            u = damage.copy().append(t);
        }
        if(isShift){
            //list.add(EMCWorld.tc("tooltip.cost.line1"));
            list.add(EMCWorld.tc("tooltip.cost.line2",getDoubleFormat(damage.getBaseDamage()),format(damage.getBaseDamage()*cost)+" EMC"));
            if(damage.getAddDamage().size() > 0){
                double d = damage.get(1);
                if(i instanceof IGemInlaidItem){
                    if(((IGemInlaidItem) i).getGemIndex(stack) != 0){
                        d += damage.get(2);
                    }
                }
                list.add(EMCWorld.tc("tooltip.cost.line3",getDoubleFormat(d),format(d*cost)+" EMC"));
            }
            if(u.getBaseDamage() != 0){
                list.add(EMCWorld.tc("tooltip.cost.line5",getDoubleFormat(t),format(t * cost)+" EMC"));
            }
        }
        list.add(EMCWorld.tc("tooltip.cost.line0",format(cost * damage.append(t).total())+" EMC"));
        return list;
    }

    @Deprecated
    public static String t(BlockPos pos){
        return pos.getX()+","+pos.getY()+","+pos.getZ();
    }

    @EMCWorldSince("1.0.0")
    private static boolean containerModule(ItemMekaSuitArmor armor,ItemStack stack,ResourceLocation rl){
        if(armor.getModules(stack).size() == 0) return false;
        for(Module<?> module : armor.getModules(stack)){
            if(module.getData().getRegistryName().equals(rl)){
                return true;
            }
        }
        return false;
    }

    @EMCWorldSince("1.0.3")
    public static long getEMCRepairCost(ItemStack stack){
        Item item = stack.getItem();
        if(item instanceof IEMCRepairableItem){
            return ((IEMCRepairableItem) item).getTickCost(stack);
        }
        int level = EnchantmentHelper.getItemEnchantmentLevel(EWEnchantments.EMC_REPAIR.get(),stack);
        if(level >= 1){
            return 1080 + (-250L * level);
        }
        return -1;
    }
}
