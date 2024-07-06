package biggestxuan.emcworld.common.registry;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/20
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.blocks.EWBlock;
import biggestxuan.emcworld.common.blocks.tile.*;
import biggestxuan.emcworld.common.compact.Botania.BotaniaFlowers.TileEMCFlower;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;


public class EWTileEntityTypes {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, EMCWorld.MODID);

    public static RegistryObject<TileEntityType<AdvancedUpdateTileEntity>> AdvancedUpdateTileEntity = registry("advanced_update_tileentity",AdvancedUpdateTileEntity::new,EWBlocks.ADVANCED_UPDATE_CORE);
    public static RegistryObject<TileEntityType<InfuserBlockTileEntity>> InfuserBlockTileEntity = registry("infuser_tileentity",InfuserBlockTileEntity::new,EWBlocks.INFUSER_BLOCK_CORE);
    public static RegistryObject<TileEntityType<WeaponUpgradeBlockTileEntity>> WeaponUpgradeBlockTileEntity = registry("weapon_upgrade_tileentity",WeaponUpgradeBlockTileEntity::new,EWBlocks.WEAPON_UPGRADE_CORE);
    public static RegistryObject<TileEntityType<VisConversionTileEntity>> VisConversionTileEntity = registry("vis_conversion_tileentity",VisConversionTileEntity::new,EWBlocks.VIS_CONVERSION_CORE);
    public static RegistryObject<TileEntityType<SteelFurnaceTileEntity>> SteelFurnaceTileEntity = registry("steel_furnace_tileentity",SteelFurnaceTileEntity::new,EWBlocks.STEEL_FURNACE_CORE);
    public static RegistryObject<TileEntityType<GemstoneTileEntity>> GemstoneTileEntity = registry("gemstone_tileentity",GemstoneTileEntity::new,EWBlocks.GEMSTONE_CORE);
    public static RegistryObject<TileEntityType<PrefixTileEntity>> PrefixCoreTileEntity = registry("prefix_core_tileentity",PrefixTileEntity::new,EWBlocks.PREFIX_CORE);
    public static RegistryObject<TileEntityType<StarPedestalTileEntity>> starPedestalTileEntity = registry("star_pedestal_tileentity",StarPedestalTileEntity::new,EWBlocks.STAR_PEDESTAL);
    public static RegistryObject<TileEntityType<SuperEMCTileEntity>> superEMCTileEntity = registry("super_emc_tileentity",SuperEMCTileEntity::new,EWBlocks.EMC_SUPER);
    public static RegistryObject<TileEntityType<TopCoreTileEntity>> topCoreTileEntity = registry("top_core_tileentity",TopCoreTileEntity::new,EWBlocks.TOP_CORE);
    public static RegistryObject<TileEntityType<EMCOreCoreTileEntity>> emc_oreTileEntity = registry("emc_ore_tileentity",EMCOreCoreTileEntity::new,EWBlocks.EMC_ORE_CORE);
    public static RegistryObject<TileEntityType<EMCCoreTileEntity.Assembler>> emcCoreAssemblerTileEntity = registry("emc_core_assembler", EMCCoreTileEntity.Assembler::new,EWBlocks.EMC_CORE_ASSEMBLER);
    public static RegistryObject<TileEntityType<EMCCoreTileEntity.Puller>> emcCorePullerTileEntity = registry("emc_core_puller", EMCCoreTileEntity.Puller::new,EWBlocks.EMC_CORE_PULLER);
    public static RegistryObject<TileEntityType<EMCCoreTileEntity.Generator>> emcCoreGeneratorTileEntity = registry("emc_core_generator", EMCCoreTileEntity.Generator::new,EWBlocks.EMC_CORE_GENERATOR);
    public static RegistryObject<TileEntityType<EMCCoreTileEntity.Puncher>> emcCorePuncherTileEntity = registry("emc_core_puncher", EMCCoreTileEntity.Puncher::new, EWBlocks.EMC_CORE_PUNCHER);

    public static <T extends TileEntity> RegistryObject<TileEntityType<T>> registry(String name, Supplier<T> supplier, RegistryObject<Block> block){
        return TILE_ENTITIES.register(name,() -> TileEntityType.Builder.of(supplier,block.get()).build(null));
    }

    // Botania
    public static RegistryObject<TileEntityType<TileEMCFlower>> EMC_FLOWER = TILE_ENTITIES.register
            ("emc_flower",() -> TileEntityType.Builder.of(TileEMCFlower::new,
                    EWBlocks.EMC_FLOWER).build(null));
}
