package biggestxuan.emcworld.common.blocks;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/06
 */

import biggestxuan.emcworld.common.blocks.tile.InfuserBlockTileEntity;
import com.kwpugh.gobber2.init.BlockInit;
import hellfirepvp.astralsorcery.common.lib.BlocksAS;
import mekanism.common.registries.MekanismBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import quek.undergarden.registry.UGBlocks;
import vazkii.botania.common.block.ModBlocks;
import wayoftime.bloodmagic.common.block.BloodMagicBlocks;

public final class InfuserMultiBlock {
    private int level;
    private final World world;
    private final BlockPos pos;

    public InfuserMultiBlock(InfuserBlockTileEntity entity){
        this.world = entity.getLevel();
        this.pos = entity.getPos();
        this.level = 1;
    }

    public int getLevel() {
        if(level2()){
            level = 2;
        }
        if(level3() && level == 2){
            level = 3;
        }
        if(level4() && level == 3){
            level = 4;
        }
        return level;
    }

    private boolean level2(){
        boolean i = isBlock(0, -1, 0, Blocks.BEACON) || isBlock(0, 2, 0, ModBlocks.naturaPylon);
        int[] c = new int[]{-1,0,1};
        for(int t : c){
            for(int j :c){
                if(t == 0 && j == 0){
                    continue;
                }
                if(!isBlock(t,-1,j, MekanismBlocks.STEEL_CASING.getBlock().getBlock())){
                    i = false;
                }
            }
        }
        if(round(2, BlocksAS.BLACK_MARBLE_ARCH)){
            i = false;
        }
        int[] a = new int[]{-2,2};
        int[] h = new int[]{0,1};
        for(int o : a){
            for(int p : a){
                if(!isBlock(o,-1,p, BloodMagicBlocks.MASTER_RITUAL_STONE.get())){
                    i = false;
                }
            }
        }
        if(round(3, BlocksAS.MARBLE_RUNED)){
            i = false;
        }
        for(int q : h){
            for(int w : a){
                for(int e : a){
                    if(!isBlock(w,q,e, BlocksAS.INFUSED_WOOD_COLUMN.getBlock())){
                        i = false;
                    }
                }
            }
        }
        for(int k : a){
            for(int u :a){
                if(!isBlock(k,2,u,BlocksAS.CELESTIAL_COLLECTOR_CRYSTAL.getBlock())){
                    i = false;
                }
            }
        }
        return i;
    }

    private boolean level3(){
        boolean i = true;
        int[] a = new int[]{3,-3};
        int[] h = new int[]{0,1};
        for(int p : a){
            for(int k : a){
                if(!isBlock(p,-1,k, UGBlocks.FORGOTTEN_BLOCK.get())){
                    i = false;
                }
                if(!isBlock(p,2,k,Blocks.LODESTONE)){
                    i = false;
                }
                if(!isBlock(p,3,k, de.ellpeck.naturesaura.blocks.ModBlocks.FIELD_CREATOR)){
                    i = false;
                }
            }
        }
        if(round(4, UGBlocks.SHIVERSTONE_BRICKS.get())){
            i = false;
        }
        if(!isBlock(0,1,0,BlocksAS.RITUAL_PEDESTAL)){
            i = false;
        }
        for(int o : h){
            for(int l : a){
                for(int u : a){
                    if(!isBlock(l,o,u,BlocksAS.MARBLE_PILLAR)){
                        i = false;
                    }
                }
            }
        }
        return i;
    }

    private boolean level4(){
        boolean i = true;
        int[] a = new int[]{4,-4};
        int[] a1= new int[]{5,-5};
        for(int u : a){
            for(int m : a){
                if(!isBlock(u,-1,m, L_Ender.cataclysm.init.ModBlocks.CHISELED_END_STONE_BRICKS.get())){
                    i = false;
                }
                if(!isBlock(u,0,m, Blocks.NETHERITE_BLOCK)){
                    i = false;
                }
            }
        }
        for(int f : a1){
            for(int k :a1){
                if(!isBlock(f,-1,k, com.thevortex.allthemodium.init.ModBlocks.UNOBTAINIUMBLOCK)){
                    i = false;
                }
                for (int j = 0; j < 4; j++) {
                    if(!isBlock(f,j,k, L_Ender.cataclysm.init.ModBlocks.END_STONE_PILLAR.get())){
                        i = false;
                    }
                }
                if(!isBlock(f,4,k, BlockInit.GOBBER2_BLOCK_END.get())){
                    i = false;
                }
            }
        }
        if(round(5, Blocks.END_STONE_BRICKS)){
            i = false;
        }
        return i;
    }

    private boolean isBlock(int x, int y, int z, Block block){
        return world.getBlockState(new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z)).getBlock().equals(block);
    }

    private boolean round(int index, Block block){
        int n = Math.negateExact(index);
        int a = index - 1;
        int y = -1;
        return istoBlock(index, y, Math.negateExact(a), index, y, a, block) ||
                istoBlock(Math.negateExact(a), y, n, a, y, n, block) ||
                istoBlock(n, y, Math.negateExact(a), n, y, a, block) ||
                istoBlock(Math.negateExact(a), y, index, a, y, index, block);
    }

    private boolean istoBlock(int x,int y,int z,int x1,int y1,int z1,Block block){
        for (int i = x; i < x1+1; i++) {
            for (int j = y; j < y1+1; j++) {
                for (int k = z; k < z1+1; k++) {
                    if(!isBlock(i,j,k,block)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
