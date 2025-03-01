package biggestxuan.emcworld.common.villager;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/18
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.registry.EWVillagers;
import biggestxuan.emcworld.common.utils.VillagerTradeBuilder;
import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import moze_intel.projecte.gameObjs.registries.PEItems;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID)
public class EMCVillagerProfession extends VillagerProfession {
    public EMCVillagerProfession() {
        super("emc_villager", EWVillagers.TRANS_TABLE.get(),ImmutableSet.of(),ImmutableSet.of(),SoundEvents.VILLAGER_WORK_LIBRARIAN);
    }

    @SubscribeEvent
    public static void Trades(VillagerTradesEvent event){
        Int2ObjectMap<List<VillagerTrades.ITrade>> trades = event.getTrades();
        if(event.getType().equals(VillagerProfession.ARMORER)){
            trades.get(2).add(new VillagerTradeBuilder(new ItemStack(Items.EMERALD),new ItemStack(Items.COAL,32),8).get());
        }
        if(event.getType().equals(VillagerProfession.TOOLSMITH)){
            trades.get(2).add(new VillagerTradeBuilder(new ItemStack(Items.IRON_INGOT,8),new ItemStack(Items.COAL,32),8).get());
        }
        if(event.getType().equals(VillagerProfession.CLERIC)){
            trades.get(2).add(new VillagerTradeBuilder(new ItemStack(Items.GOLD_INGOT,1),new ItemStack(Items.COAL,32),8).get());
        }
        if(event.getType().equals(EWVillagers.EMC_VILLAGER.get())){
            trades.get(1).add(new VillagerTradeBuilder(new ItemStack(EWItems.BIG_EMC_GEM.get(),3),new ItemStack(Items.EMERALD),8).get());
            trades.get(1).add(new VillagerTradeBuilder(new ItemStack(Items.EMERALD,3),new ItemStack(EWItems.MONEY.get()),8).get());
            trades.get(1).add(new VillagerTradeBuilder(new ItemStack(EWItems.MONEY.get(),7),new ItemStack(Items.EMERALD),8).get());
            trades.get(1).add(new VillagerTradeBuilder(new ItemStack(Items.EMERALD,5),new ItemStack(EWItems.EMC_CHECK.get()),6).get());
            trades.get(1).add(new VillagerTradeBuilder(new ItemStack(Items.EMERALD,2),new ItemStack(EWItems.SCROLL_WHITE.get()),10).get());
            trades.get(2).add(new VillagerTradeBuilder(new ItemStack(EWItems.BIG_EMC_GEM.get(),64),new ItemStack(PEItems.DARK_MATTER),5).get());
            trades.get(2).add(new VillagerTradeBuilder(new ItemStack(Items.EMERALD,3),new ItemStack(EWItems.SCROLL_WHITE.get(),2),7).get());
            trades.get(2).add(new VillagerTradeBuilder(new ItemStack(PEItems.DARK_MATTER,1),new ItemStack(Items.EMERALD,12),12).get());
            trades.get(2).add(new VillagerTradeBuilder(new ItemStack(EWItems.HARDCORE_STONE.get(),16),new ItemStack(Items.EMERALD,1),24).get());
            trades.get(3).add(new VillagerTradeBuilder(new ItemStack(PEItems.DARK_MATTER,7),new ItemStack(PEItems.RED_MATTER,1),4).get());
            trades.get(3).add(new VillagerTradeBuilder(new ItemStack(PEItems.RED_MATTER,1),new ItemStack(EWItems.BASE_EMC_STORED_TOTEM.get(),1),2).get());
            trades.get(3).add(new VillagerTradeBuilder(new ItemStack(EWItems.ILLAGER_GEM.get(),1),new ItemStack(EWItems.SCROLL_GREEN.get(),1),8).get());
            trades.get(3).add(new VillagerTradeBuilder(new ItemStack(PEItems.DARK_MATTER,1),new ItemStack(EWItems.SCROLL_GREEN.get(),1),5).get());
            trades.get(4).add(new VillagerTradeBuilder(new ItemStack(PEItems.RED_MATTER,6),new ItemStack(EWItems.MAGENTA_MATTER.get(),1),1).get());
            trades.get(4).add(new VillagerTradeBuilder(new ItemStack(PEItems.RED_MATTER,1),new ItemStack(EWItems.SCROLL_GREEN.get(),3),4).get());
            trades.get(4).add(new VillagerTradeBuilder(new ItemStack(EWItems.ADVANCED_EMC_GEM.get(),16),new ItemStack(EWItems.FIRE_SWORD.get(),1),1).get());
            trades.get(4).add(new VillagerTradeBuilder(new ItemStack(EWItems.ADVANCED_EMC_GEM.get(),16),new ItemStack(EWItems.ICE_SWORD.get(),1),1).get());
            trades.get(4).add(new VillagerTradeBuilder(new ItemStack(EWItems.ADVANCED_EMC_GEM.get(),16),new ItemStack(EWItems.NATURE_SWORD.get(),1),1).get());
            trades.get(4).add(new VillagerTradeBuilder(new ItemStack(EWItems.ADVANCED_EMC_GEM.get(),16),new ItemStack(EWItems.NIGHT_LIGHT.get(),1),1).get());
            trades.get(4).add(new VillagerTradeBuilder(new ItemStack(EWItems.ADVANCED_EMC_GEM.get(),16),new ItemStack(EWItems.RED_GREEN_DAGGER.get(),1),1).get());
            trades.get(4).add(new VillagerTradeBuilder(new ItemStack(EWItems.ADVANCED_EMC_GEM.get(),16),new ItemStack(EWItems.CREATION.get(),1),1).get());
            trades.get(4).add(new VillagerTradeBuilder(new ItemStack(EWItems.ADVANCED_EMC_GEM.get(),16),new ItemStack(EWItems.SUPER_STAR.get(),1),1).get());
            trades.get(4).add(new VillagerTradeBuilder(new ItemStack(EWItems.ADVANCED_EMC_GEM.get(),16),new ItemStack(EWItems.PURPLE_STAFF.get(),1),1).get());
            trades.get(4).add(new VillagerTradeBuilder(new ItemStack(EWItems.ADVANCED_EMC_GEM.get(),16),new ItemStack(EWItems.NATURE_STAFF.get(),1),1).get());

            trades.get(5).add(new VillagerTradeBuilder(new ItemStack(PEItems.RED_MATTER,1),new ItemStack(Items.EMERALD,64),12).get());
            trades.get(5).add(new VillagerTradeBuilder(new ItemStack(EWItems.MAGENTA_MATTER.get(),1),new ItemStack(EWItems.PREFIX_SCROLL.get(),1),1).get());
            trades.get(5).add(new VillagerTradeBuilder(new ItemStack(EWItems.MAGENTA_MATTER.get(),1),new ItemStack(EWItems.SCROLL_BLUE.get(),2),4).get());
            trades.get(5).add(new VillagerTradeBuilder(EMCWorld.getItem("botania:dice"),new ItemStack(EWItems.SCROLL_BLUE.get(),3),3).get());
            trades.get(5).add(new VillagerTradeBuilder(new ItemStack(EWItems.ILLAGER_GEM.get(),3),new ItemStack(EWItems.PREFIX_SCROLL.get(),1),1).get());
            trades.get(5).add(new VillagerTradeBuilder(new ItemStack(EWItems.PINK_MATTER.get(),1),new ItemStack(EWItems.SCROLL_BLUE.get(),8),3).get());
            trades.get(5).add(new VillagerTradeBuilder(new ItemStack(EWItems.PINK_MATTER.get(),1),new ItemStack(EWItems.SCROLL_PURPLE.get(),2),2).get());
            trades.get(5).add(new VillagerTradeBuilder(new ItemStack(EWItems.PURPLE_MATTER.get(),1),new ItemStack(EWItems.SCROLL_BASE_EMC.get(),2),2).get());
            trades.get(5).add(new VillagerTradeBuilder(new ItemStack(EWItems.PURPLE_MATTER.get(),1),new ItemStack(EWItems.SCROLL_RED.get(),1),1).get());
            trades.get(5).add(new VillagerTradeBuilder(new ItemStack(EWItems.VIOLET_MATTER.get(),1),new ItemStack(EWItems.SCROLL_RED.get(),4),1).get());
            if(ConfigManager.DIFFICULTY.get() == 3){
                trades.get(5).add(new VillagerTradeBuilder(new ItemStack(EWItems.ILLAGER_SHARD.get(),1),new ItemStack(EWItems.SCROLL_GOLD.get(),10),1).get());
            }
        }
    }
}
