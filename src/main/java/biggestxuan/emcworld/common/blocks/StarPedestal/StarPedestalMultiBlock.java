package biggestxuan.emcworld.common.blocks.StarPedestal;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/05
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.registry.EWStarlight;
import com.blakebr0.extendedcrafting.tileentity.PedestalTileEntity;
import de.ellpeck.naturesaura.blocks.ModBlocks;
import de.ellpeck.naturesaura.blocks.tiles.TileEntityWoodStand;
import hellfirepvp.astralsorcery.common.constellation.IConstellation;
import hellfirepvp.astralsorcery.common.lib.BlocksAS;
import hellfirepvp.astralsorcery.common.tile.TileCollectorCrystal;
import hellfirepvp.astralsorcery.common.tile.TileInfuser;
import moze_intel.projecte.gameObjs.registries.PEBlocks;
import moze_intel.projecte.gameObjs.tiles.DMPedestalTile;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import quek.undergarden.registry.UGBlocks;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class StarPedestalMultiBlock{
    private final StarPedestalTileEntity entity;
    private final BlockPos pos;
    private final World world;
    private final List<List<ItemStack>> inventory;

    public StarPedestalMultiBlock(StarPedestalTileEntity tile){
        entity = tile;
        pos = tile.getBlockPos();
        world = tile.getLevel();
        inventory = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            inventory.add(new ArrayList<>());
        }
    }

    private boolean level4(){
        boolean flag = level3();
        List<BlockPos> pos = new ArrayList<>();
        add(pos,9);
        for(BlockPos p : pos){
            for (int i = 0; i < 3; i++) {
                if(!isBlock(p,PEBlocks.RED_MATTER.getBlock(), i)){
                    flag = false;
                }
            }
            if(!isBlock(p,BlocksAS.INFUSER.getBlock(),3)){
                flag = false;
            }
        }
        return flag;
    }

    private boolean level3(){
        boolean flag = level2();
        List<BlockPos> pos = new ArrayList<>();
        for(int i : new int[]{-6,6}){
            for(int j : new int[]{3,-3}){
                pos.add(new BlockPos(i,0,j));
            }
        }
        for(int i : new int[]{3,-3}){
            for(int j : new int[]{6,-6}){
                pos.add(new BlockPos(i,0,j));
            }
        }
        for(BlockPos p:pos){
            for (int i = 0; i < 2; i++) {
                if(!isBlock(p, UGBlocks.CLOGGRUM_BLOCK.get(),i)){
                    flag = false;
                }
            }
            if(!isBlock(p, PEBlocks.DARK_MATTER_PEDESTAL.getBlock(),2)){
                flag = false;
            }
        }
        return flag;
    }

    private boolean level2(){
        boolean flag = level1();
        List<BlockPos> pos = new ArrayList<>();
        add(pos,6);
        for(int i : new int[]{3,-3}){
            for(int j : new int[]{3,-3}){
                pos.add(new BlockPos(i,0,j));
            }
        }
        for(BlockPos p : pos){
            if(!isBlock(p, Blocks.NETHERITE_BLOCK,0)){
                flag = false;
            }
            if(!isBlock(p, com.blakebr0.extendedcrafting.init.ModBlocks.PEDESTAL.get(), 1)){
                flag = false;
            }
        }
        return flag;
    }

    private boolean level1(){
        boolean flag = true;
        List<BlockPos> pos = new ArrayList<>();
        add(pos,3);
        for(BlockPos p : pos){
            if(!isBlock(p, BlocksAS.STARMETAL,-1)){
                flag = false;
            }
            if(!isBlock(p, ModBlocks.WOOD_STAND,0)){
                flag = false;
            }
        }
        return flag;
    }

    @Nullable
    public IConstellation getStar(){
        TileEntity entity = world.getBlockEntity(new BlockPos(this.pos.getX(),this.pos.getY()+2,this.pos.getZ()));
        if(entity instanceof TileCollectorCrystal){
            TileCollectorCrystal c = (TileCollectorCrystal) entity;
            return c.getAttunedConstellation();
        }
        return null;
    }

    private static List<BlockPos> add(List<BlockPos> list, int d){
        int d_ = Math.negateExact(d);
        list.add(new BlockPos(d_,0,0));
        list.add(new BlockPos(d,0,0));
        list.add(new BlockPos(0,0,d));
        list.add(new BlockPos(0,0,d_));
        return list;
    }

    private static List<BlockPos> add(List<BlockPos> list, int d,int y){
        int d_ = Math.negateExact(d);
        list.add(new BlockPos(d_,y,0));
        list.add(new BlockPos(d,y,0));
        list.add(new BlockPos(0,y,d));
        list.add(new BlockPos(0,y,d_));
        return list;
    }

    private boolean isBlock(BlockPos p, Block block,int y){
        return world.getBlockState(new BlockPos(pos.getX() + p.getX(), pos.getY() + p.getY() + y, pos.getZ() + p.getZ())).getBlock().equals(block);
    }

    public void costInventory(int level){
        for(BlockPos p : add(new ArrayList<>(),3)){
            costTileItem(p);
        }
        if(level >= 2){
            for(BlockPos p : add(new ArrayList<>(),6,1)){
                costTileItem(p);
            }
            for(int i : new int[]{3,-3}){
                for(int j : new int[]{3,-3}){
                    costTileItem(new BlockPos(i,1,j));
                }
            }
        }
        if(level >= 3){
            for(int i : new int[]{-6,6}){
                for(int j : new int[]{3,-3}){
                    costTileItem(new BlockPos(i,2,j));
                }
            }
            for(int i : new int[]{3,-3}){
                for(int j : new int[]{6,-6}){
                    costTileItem(new BlockPos(i,2,j));
                }
            }
        }
        if(level >= 4){
            for(BlockPos p : add(new ArrayList<>(),9,3)){
                costTileItem(p);
            }
        }
    }

    public List<List<ItemStack>> getInventory(){
        List<ItemStack> s1 = new ArrayList<>();
        List<ItemStack> s2 = new ArrayList<>();
        List<ItemStack> s3 = new ArrayList<>();
        List<ItemStack> s4 = new ArrayList<>();
        for(BlockPos p : add(new ArrayList<>(),3)){
            s1.add(getTileItem(p));
        }
        for(BlockPos p : add(new ArrayList<>(),6,1)){
            s2.add(getTileItem(p));
        }
        for(int i : new int[]{3,-3}){
            for(int j : new int[]{3,-3}){
                s2.add(getTileItem(new BlockPos(i,1,j)));
            }
        }
        for(int i : new int[]{-6,6}){
            for(int j : new int[]{3,-3}){
                s3.add(getTileItem(new BlockPos(i,2,j)));
            }
        }
        for(int i : new int[]{3,-3}){
            for(int j : new int[]{6,-6}){
                s3.add(getTileItem(new BlockPos(i,2,j)));
            }
        }
        for(BlockPos p : add(new ArrayList<>(),9,3)){
            s4.add(getTileItem(p));
        }
        inventory.set(0,s1);
        inventory.set(1,s2);
        inventory.set(2,s3);
        inventory.set(3,s4);
        return inventory;
    }

    public int getLevel(){
        int level = 0;
        if(level1()) level++;
        if(level2()) level++;
        if(level3()) level++;
        if(level4()) level++;
        return level;
    }

    private ItemStack getTileItem(BlockPos p){
        TileEntity tile = world.getBlockEntity(new BlockPos(pos.getX()+p.getX(),pos.getY()+p.getY(),pos.getZ()+p.getZ()));
        if(tile == null) return ItemStack.EMPTY;
        //System.out.println(tile);
        if(tile instanceof TileEntityWoodStand){
            return ((TileEntityWoodStand) tile).items.getStackInSlot(0);
        }
        if(tile instanceof PedestalTileEntity){
            return ((PedestalTileEntity) tile).getInventory().getStackInSlot(0);
        }
        if(tile instanceof DMPedestalTile){
            return ((DMPedestalTile) tile).getInventory().getStackInSlot(0);
        }
        if(tile instanceof TileInfuser){
            return ((TileInfuser) tile).getItemInput();
        }
        return ItemStack.EMPTY;
    }

    private void costTileItem(BlockPos p){
        ItemStack s = EMCWorld.getItem("minecraft:air");
        TileEntity tile = world.getBlockEntity(new BlockPos(pos.getX()+p.getX(),pos.getY()+p.getY(),pos.getZ()+p.getZ()));
        if(tile == null) return;
        if(tile instanceof TileEntityWoodStand){
            ((TileEntityWoodStand) tile).items.setStackInSlot(0,s);
        }
        if(tile instanceof PedestalTileEntity){
            ((PedestalTileEntity) tile).getInventory().setStackInSlot(0,s);
        }
        if(tile instanceof DMPedestalTile){
            ((DMPedestalTile) tile).getInventory().setStackInSlot(0,s);
        }
        if(tile instanceof TileInfuser){
            ((TileInfuser) tile).setItemInput(s);
        }
    }
}