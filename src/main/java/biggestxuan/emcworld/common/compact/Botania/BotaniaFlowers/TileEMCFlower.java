package biggestxuan.emcworld.common.compact.Botania.BotaniaFlowers;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/21
 */

import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.registry.EWTileEntityTypes;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.TileEntityGeneratingFlower;

import java.util.List;

public class TileEMCFlower extends TileEntityGeneratingFlower {
    private static final int MAX_MANA = 1000000;
    private static final int RANGE = 3;
    private static final int[] VALUE = new int[]{1200,12000,800000};

    public TileEMCFlower() {
        super(EWTileEntityTypes.EMC_FLOWER.get());
    }

    @Override
    public void tickFlower(){
        super.tickFlower();

        if(getLevel() == null || getLevel().isClientSide || getMana() >= getMaxMana()){
            return;
        }

        if(getLevel().getDayTime() % 40 != 0){
            return;
        }

        AxisAlignedBB aabb = MathUtils.expandAABB(this.worldPosition,RANGE);
        List<? extends ItemEntity> items = getLevel().getLoadedEntitiesOfClass(ItemEntity.class,aabb);
        for(ItemEntity i :items){
            ItemStack item = i.getItem();
            if(item.getItem().equals(EWItems.SMALL_EMC_GEM.get())){
                if(canAddMana(VALUE[0])){
                    item.shrink(1);
                    addMana(VALUE[0]);
                }
                sync();
                return;
            }
            if(item.getItem().equals(EWItems.BIG_EMC_GEM.get())){
                if(canAddMana(VALUE[1])){
                    item.shrink(1);
                    addMana(VALUE[1]);
                }
                sync();
                return;
            }
            if(item.getItem().equals(EWItems.BIGGEST_EMC_GEM.get())){
                if(canAddMana(VALUE[2])){
                    item.shrink(1);
                    addMana(VALUE[2]);
                }
                sync();
                return;
            }
        }
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(getEffectivePos(),RANGE);
    }

    @Override
    public int getMaxMana(){
        return MAX_MANA;
    }

    @Override
    public int getColor(){
        return 0xc3c17c;
    }

    private boolean canAddMana(int mana){
        return getMana() + mana < getMaxMana();
    }
}
