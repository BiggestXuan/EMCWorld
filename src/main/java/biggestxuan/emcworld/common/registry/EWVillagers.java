package biggestxuan.emcworld.common.registry;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/18
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.villager.EMCVillagerProfession;
import com.google.common.collect.ImmutableSet;
import moze_intel.projecte.gameObjs.registries.PEBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;

public class EWVillagers {
    public static final DeferredRegister<PointOfInterestType> POI = DeferredRegister.create(ForgeRegistries.POI_TYPES, EMCWorld.MODID);
    public static final DeferredRegister<VillagerProfession> PROFESSION = DeferredRegister.create(ForgeRegistries.PROFESSIONS,EMCWorld.MODID);

    public static final RegistryObject<PointOfInterestType> TRANS_TABLE;
    public static final RegistryObject<VillagerProfession> EMC_VILLAGER;

    static {
        TRANS_TABLE = POI.register("transmutation_table",()-> new PointOfInterestType("transmutation_table",getAll(PEBlocks.TRANSMUTATION_TABLE.getBlock()),1,1));
        EMC_VILLAGER = PROFESSION.register("emc_villager", EMCVillagerProfession::new);
    }

    private static Set<BlockState> getAll(Block block){
        return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
    }
}
