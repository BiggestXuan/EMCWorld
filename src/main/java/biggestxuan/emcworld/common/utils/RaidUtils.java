package biggestxuan.emcworld.common.utils;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/25
 */

import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.common.compact.ScalingHealth.DifficultyHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.events.PlayerEvent.PlayerTickEvent;
import biggestxuan.emcworld.common.registry.EWEntities;
import com.legacy.conjurer_illager.registry.IllagerEntityTypes;
import net.minecraft.block.Blocks;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.raid.Raid;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RaidUtils {
    private final BlockPos center;
    private final Raid raid;
    private final World world;
    private List<? super LivingEntity> raidEntities;

    public RaidUtils(@Nonnull Raid raid){
        this.raid = raid;
        this.center = raid.center;
        this.world = raid.getLevel();
        this.raidEntities = getAllEntities();
    }

    private void update(){
        this.raidEntities = getAllEntities();
    }

    private int getAverageYPos(){
        update();
        if(raidEntities.size() >= 4){
            List<Integer> yPosList = new ArrayList<>();
            for(Object living : raidEntities){
                LivingEntity l = (LivingEntity) living;
                yPosList.add(l.blockPosition().getY());
            }
            Collections.sort(yPosList);
            int a = (int) (raidEntities.size() * 0.3d);
            if (a > 0) {
                yPosList.subList(0, a).clear();
                yPosList.subList(yPosList.size()-1-a,yPosList.size()-1).clear();
            }
            int sum = 0;
            for(int i : yPosList){
                sum += i;
            }
            return sum;
        }
        return -1;
    }

    public BlockPos getRandomPos(BlockPos i){
        update();
        BlockPos center = raid.center;
        int a = getAverageYPos();
        int amin = (int) MathUtils.max(1,a-raidEntities.size(),center.getY()-96);
        int amax = (int) MathUtils.min(255,a+raidEntities.size(),center.getY()+96);
        PosRange range = new PosRange(i.getX(),2*center.getX()-i.getX(),i.getY(),2*center.getY()-i.getY(),i.getZ(),2*center.getZ()-i.getZ());
        PosRange range1 = new PosRange(0.5f*(i.getX()+center.getX()),1.5f*i.getX()-0.5f*center.getX(),0.5f*(i.getY()+center.getY()),1.5f*i.getY()-0.5f*center.getY(),0.5f*(i.getZ()+center.getZ()),1.5f*i.getZ()-0.5f*center.getZ());
        BlockPos rand = range.getRandPos();
        BlockPos rand1 = range1.getRandPos();
        if(a >= 1){
            for (int j = 0; j < 5; j++) {
                BlockPos pos1 = getHighestBlock(rand,amin,amax);
                if(pos1 != null){
                    return pos1;
                }
            }
            for (int j = 0; j < 5; j++) {
                BlockPos pos2 = getHighestBlock(rand1,amin,amax);
                if(pos2 != null){
                    return pos2;
                }
            }
        }
        for (int j = 0; j < 5; j++) {
            BlockPos pos1 = getHighestBlock(rand);
            if(pos1 != null){
                return pos1;
            }
        }
        for (int j = 0; j < 5; j++) {
            BlockPos pos2 = getHighestBlock(rand1);
            if(pos2 != null){
                return pos2;
            }
        }
        if(getVillager().size() >= 1){
            VillagerEntity randomVillager = getVillager().get((int) (MathUtils.Random() * getVillager().size()));
            return randomVillager.blockPosition();
        }
        return null;
    }

    public List<VillagerEntity> getVillager() {
        MinecraftServer server = world.getServer();
        List<VillagerEntity> villagers = new ArrayList<>();
        World world = raid.getLevel();
        BlockPos pos = raid.getCenter();
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        AxisAlignedBB aabb = new AxisAlignedBB(x + 96, Math.min(256, y + 96), z + 96, x - 96, Math.max(0, y - 96), z - 96);
        for (VillagerEntity v : world.getLoadedEntitiesOfClass(VillagerEntity.class, aabb)) {
            BlockPos villagerPos = v.blockPosition();
            if (server != null && server.overworld().getRaidAt(villagerPos) != null && server.overworld().getRaidAt(pos).getId() == raid.getId()) {
                villagers.add(v);
            }
        }
        return villagers;
    }

    private BlockPos getHighestBlock(BlockPos pos,int min,int max){
        return getHighestBlock(pos.getX(),pos.getZ(),min,max);
    }

    private BlockPos getHighestBlock(BlockPos pos){
        return getHighestBlock(pos.getX(), pos.getZ(),1,255);
    }

    private BlockPos getHighestBlock(int x,int z,int min,int max){
        for (int i = max; i > min; i--) {
            BlockPos pos = new BlockPos(x,i,z);
            BlockPos next = new BlockPos(x,i-1,z);
            if(isAir(pos) && !isAir(next)){
                return next;
            }
        }
        return null;
    }

    private boolean isAir(BlockPos pos){
        return world.getBlockState(pos).getBlock().equals(Blocks.AIR);
    }

    public static void init(){
        int b = g(1);
        Raid.WaveMember.create("biggest_xuan", EWEntities.biggest_xuan,new int[]{0,0,0,0,0,0,1,0});
        Raid.WaveMember.create("dctor",EWEntities.dctor_0415,new int[]{0,0,0,0,1,0,0,0});
        Raid.WaveMember.create("tulye",EWEntities.tulye,new int[]{0,0,0,0,0,0,0,1});
        Raid.WaveMember.create("illusioner", EntityType.ILLUSIONER,new int[]{0,0,0,1,0,0,b, g(2)});
        //Raid.WaveMember.create("frost", EntityInit.FROSTMANCER,new int[]{0,0,1,1,b,2,g(2),g(3)});
        Raid.WaveMember.create("conjurer", IllagerEntityTypes.CONJURER,new int[]{0,0,0,1,0,0,1,g(1)});
        //Raid.WaveMember.create("ice", SREntities.ICEOLOGER.get(), new int[]{0,0,0,1,1,1,g(2),g(2)});
    }

    private List<? super LivingEntity> getAllEntities(){
        List<? super LivingEntity> list = new ArrayList<>(getVillager());
        for(PlayerTickEvent.RaidInfo info : getRaidAllPlayers(this.raid)){
            list.add(info.getPlayer());
        }
        return list;
    }

    private static int g(int base){
        return MathUtils.isMaxDifficulty() ? (int) (2.5 * base) : base;
    }

    public int getRaiderCount(){
        AxisAlignedBB aabb = MathUtils.expandAABB(center,96);
        List<AbstractRaiderEntity> raiderEntities = world.getLoadedEntitiesOfClass(AbstractRaiderEntity.class,aabb);
        return raiderEntities.size();
    }

    public static double getIllagerShardDropRate(AbstractRaiderEntity entity, PlayerEntity player){
        if(player == null || ConfigManager.DIFFICULTY.get() != 3) return 0d;
        IUtilCapability cap = EMCWorldAPI.getInstance().getUtilCapability(player);
        float all = 0f;
        if(!cap.isRaid()) return all;
        all += Math.pow(1.33,cap.getWave());
        all -= cap.getVillager() >= 5 ? 1.28f * cap.getVillager() : 0;
        World world1 = entity == null ? player.level : entity.level;
        BlockPos pos = player.level instanceof ClientWorld ? player.blockPosition() : entity.blockPosition();
        double diff = player.level instanceof ClientWorld ? cap.getSHDifficulty() : DifficultyHelper.getAreaDifficulty(world1,pos);
        all += 0.05 * (diff - 1000);
        return Math.max(all / 10000d,0f);
    }

    public static int getRaidWave(){
        final int maxWave = 20;
        double diff = ConfigManager.DIFFICULTY.get();
        int waves;
        if(diff <= 1){
            waves = 1;
        }else if(diff <= 2){
            waves = 4;
        }else if(diff <= 2.5){
            waves = 7;
        }else if(diff <3){
            waves = 10;
        }else waves = maxWave;
        return waves;
    }

    private static class PosRange{
        private final int xmin;
        private final int xmax;
        private final int ymin;
        private final int ymax;
        private final int zmin;
        private final int zmax;

        private PosRange(float a,float a1,float a2,float a3,float a4,float a5){
            xmin = (int) a;
            xmax = (int) a1;
            ymin = (int) a2;
            ymax = (int) a3;
            zmin = (int) a4;
            zmax = (int) a5;
        }

        private BlockPos getRandPos(){
            return new BlockPos(MathUtils.getRangeRandom(xmin,xmax),MathUtils.getRangeRandom(ymin,ymax),MathUtils.getRangeRandom(zmin,zmax));
        }
    }

    public static List<PlayerTickEvent.RaidInfo> getRaidAllPlayers(Raid raid){
        List<PlayerTickEvent.RaidInfo> list = new ArrayList<>();
        World world = raid.getLevel();
        for(PlayerEntity p : world.players()){
            BlockPos playerPos = new BlockPos(p.position());
            ServerWorld world1 = p.getServer().overworld();
            if(world1.isRaided(playerPos)){
                Raid raid1 = world1.getRaidAt(playerPos);
                if(raid1.getId() == raid.getId()){
                    list.add(new PlayerTickEvent.RaidInfo(p));
                }
            }
        }
        list.sort((o1, o2) -> o2.getDamage().compareTo(o1.getDamage()));
        return list;
    }

    public static Raid getRaid(Entity entity){
        if(entity == null || entity.level.isClientSide) return null;
        MinecraftServer server = entity.level.getServer();
        ServerWorld world = server.overworld();
        return world.isRaided(entity.blockPosition()) ? world.getRaidAt(entity.blockPosition()) : null;
    }
}
