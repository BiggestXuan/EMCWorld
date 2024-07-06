package biggestxuan.emcworld.common.blocks;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/19
 */

import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.registry.EWBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MultiBlock {
    public static boolean SteelFurnaceCanCraft(World world,BlockPos corePos){
        int x = corePos.getX();
        int y = corePos.getY();
        int z = corePos.getZ();
        for (int i = x-2; i < x+3; i++) {
            for (int j = z-2; j < z+3; j++) {
                if(i == x && j == z) continue;
                if(!world.getBlockState(new BlockPos(i,y,j)).equals(EWBlocks.STEEL_FURNACE_BRICK.get().defaultBlockState())){
                    return false;
                }
                int y1 = y+4;
                if(i >= x-1 && i<= x+2 && j >= z-1 && j <= z+2) continue;
                if(!world.getBlockState(new BlockPos(i,y1,j)).equals(EWBlocks.STEEL_FURNACE_BRICK.get().defaultBlockState())){
                    return false;
                }
            }
        }
        int[] s1 = new int[]{-2,2};
        int[] s2 = new int[]{-2,2};
        for (int i = 1; i < 4; i++) {
            for(int j:s1){
                for(int k:s2){
                    if(!world.getBlockState(new BlockPos(x+j,y+i,z+k)).equals(EWBlocks.STEEL_FURNACE_BRICK.get().defaultBlockState())){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean isAttachLevel(World world,BlockPos pos,int level){
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        for (int i = x-level; i < x+level+1; i++) {
            for (int j = z-level; j < z+level+1; j++) {
                Block block = world.getBlockState(new BlockPos(i,y-level,j)).getBlock();
                if(block instanceof EWUpdateBlock){
                    EWUpdateBlock b = (EWUpdateBlock) block;
                    int l = b.getLevel();
                    if(l < level){
                        return false;
                    }
                }
                else return false;
            }
        }
        return true;
    }

    public static UpdateMath getUpdateInfo(World world,BlockPos pos){
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        int level = getMultiLevel(world,pos);
        double time = 1d;
        double cost = 1d;
        double addon = 1d;
        long t = (long) ConfigManager.SUNDRY_CRAFT_CD.get();
        if(world.getBlockState(pos).equals(EWBlocks.CONTROL_UPDATE_CORE.get().defaultBlockState())){
            return new UpdateMath(1,ConfigManager.SUNDRY_CRAFT_CD.get(),1,0);
        }
        for (int i = 1; i < level+1; i++) {
            for (int j = x-i; j < x+i+1; j++) {
                for (int k = z-i; k < z+i+1; k++) {
                    Block block = world.getBlockState(new BlockPos(j,y-i,k)).getBlock();
                    if(block instanceof EWUpdateBlock){
                        EWUpdateBlock b = (EWUpdateBlock) block;
                        time *= b.getTime();
                        cost *= b.getCost();
                        addon *= b.getAddon();
                    }
                }
            }
        }
        t *= time;
        return new UpdateMath(cost,t,addon,level);
    }

    public static int getMultiLevel(World world ,BlockPos pos){
        int l = 0;
        for (int i = 1; i < 9; i++) {
            if(isAttachLevel(world,pos,i)){
                l ++ ;
            }else break;
        }
        return l;
    }

    public static class UpdateMath{
        private final double cost;
        private final long time;
        private final double addon;
        private final int level;

        public UpdateMath(double cost, long time, double addon, int level){
            this.cost = cost;
            this.time = time;
            this.addon = addon;
            this.level = level;
        }

        public UpdateMath(){
            this.cost = 0d;
            this.time = 0L;
            this.addon = 0d;
            this.level = 0;
        }

        public double getCost(){
            return this.cost;
        }

        public long getTime(){
            return this.time;
        }

        public double getAddon(){
            return this.addon;
        }

        public int getLevel(){
            return this.level;
        }
    }
}
