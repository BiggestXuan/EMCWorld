package biggestxuan.emcworld.common.compact.Projecte;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/26
 */

import biggestxuan.emcworld.common.registry.EWItems;
import net.minecraft.item.Item;

 public enum EMCGemsMapping {
     infinity_gem(EWItems.INFINITY_EMC_GEM.get(),100000000000L,"Eight"),
     epic_gem(EWItems.EPIC_EMC_GEM.get(),2000000000L,"Seven"),
     super_gem(EWItems.SUPER_EMC_GEM.get(),50000000,"Six"),
     advanced_gem(EWItems.ADVANCED_EMC_GEM.get(),2000000,"Four"),
     biggest_gem(EWItems.BIGGEST_EMC_GEM.get(),50000,"Three"),
     big_gem(EWItems.BIG_EMC_GEM.get(),1000,"One"),
     small_gem(EWItems.SMALL_EMC_GEM.get(),100,"Start");
    private final Item item;
    private final long baseEMC;
    private final String gameStage;
    private EMCGemsMapping(Item item,long baseEMC,String gameStage){
        this.item=item;
        this.baseEMC=baseEMC;
        this.gameStage=gameStage;
    }
    public Item getItem(){
        return item;
    }
    public long getBaseEMC(){
        return baseEMC;
    }
    public String getGameStage(){
        return gameStage;
    }
}
