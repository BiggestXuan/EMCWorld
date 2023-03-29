package biggestxuan.emcworld.common.utils;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/26
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.api.item.ICostEMCItem;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.api.item.equipment.bow.IUpgradeBow;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.compact.CraftTweaker.CrTConfig;
import biggestxuan.emcworld.common.compact.FTBQuests.QuestReward;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.compact.Projecte.EMCGemsMapping;
import biggestxuan.emcworld.common.compact.ScalingHealth.DifficultyHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.utils.Sponsors.Sponsors;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import dev.ftb.mods.ftbquests.quest.reward.CustomReward;
import net.blay09.mods.waystones.api.IWaystone;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BowItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.openzen.zencode.java.ZenCodeType;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.math")
@SuppressWarnings("unused")
public class MathUtils {

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
        int m1 = min;
        int m2 = max;
        if(m2 < m1){
            m1 = max;
            m2 = min;
        }
        int range = m2 - m1;
        return (int) (m2 - Random() * range);
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
        long costEMC = 0;
        for (DifficultySetting obj : DifficultySetting.values()) {
            if (GameStageManager.hasStage(player, obj.getGameStage())) {
                PlayerInventory playerInventory = player.inventory;
                double amt = 0;
                for (ItemStack item : playerInventory.items) {
                    double rate = 1.0d;
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
        for(DifficultySetting obj:DifficultySetting.values()){
            if(GameStageManager.hasStage(player, obj.getGameStage())){
                return obj.getAttackBase();
            }
        }
        return 0;
    }

    public static double getChestBaseCost(PlayerEntity player, Container container){
        double baseCost = 0;
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

    public static double getQuestCompletedRewardBase(PlayerEntity player, CustomReward customReward){
        double base = 0d;
        for(DifficultySetting obj:DifficultySetting.values()){
            if(GameStageManager.hasStage(player,obj.getGameStage())){
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

    public static int getTPEMCCost(PlayerEntity player,Position start,Position end){
        if(end == null){
            return 0;
        }
        BlockPos pos1 = start.pos;
        BlockPos pos2 = end.pos;
        if(Math.abs(pos1.getX()-pos2.getX()) >= EMCWorld.HOMO || Math.abs(pos1.getZ()-pos2.getZ()) >= EMCWorld.HOMO){
            return Integer.MAX_VALUE;
        }
        long distance = (long) Math.sqrt(Math.pow(pos1.getX()-pos2.getX(),2)+Math.pow(pos1.getY()-pos2.getY(),2)+Math.pow(pos1.getZ()-pos2.getZ(),2));
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
        return emc >= Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) emc / 30;
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

    public static class Position{
        private final BlockPos pos;
        private final ResourceLocation world;

        public Position(BlockPos pos,ResourceLocation world){
            this.pos = pos;
            this.world = world;
        }
    }

    @Deprecated
    public static String t(BlockPos pos){
        return pos.getX()+","+pos.getY()+","+pos.getZ();
    }
}
