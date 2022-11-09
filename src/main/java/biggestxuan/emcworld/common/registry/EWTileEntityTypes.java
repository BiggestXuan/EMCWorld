package biggestxuan.emcworld.common.registry;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/20
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.blocks.AdvancedUpdateBlock.AdvancedUpdateTileEntity;
import biggestxuan.emcworld.common.blocks.InfuserBlock.InfuserBlockTileEntity;
import biggestxuan.emcworld.common.blocks.SteelFurnace.SteelFurnaceTileEntity;
import biggestxuan.emcworld.common.blocks.VisConversionBlock.VisConversionTileEntity;
import biggestxuan.emcworld.common.blocks.WeaponUpgradeBlock.WeaponUpgradeBlockTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;


public class EWTileEntityTypes {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, EMCWorld.MODID);

    public static RegistryObject<TileEntityType<AdvancedUpdateTileEntity>> AdvancedUpdateTileEntity = TILE_ENTITIES.register
            ("advanced_update_tileentity", () -> TileEntityType.Builder.of(AdvancedUpdateTileEntity::new,
                    EWBlocks.ADVANCED_UPDATE_CORE.get()).build(null));
    public static RegistryObject<TileEntityType<InfuserBlockTileEntity>> InfuserBlockTileEntity = TILE_ENTITIES.register
            ("infuser_tileentity", () -> TileEntityType.Builder.of(InfuserBlockTileEntity::new,
                    EWBlocks.INFUSER_BLOCK_CORE.get()).build(null));
    public static RegistryObject<TileEntityType<WeaponUpgradeBlockTileEntity>> WeaponUpgradeBlockTileEntity = TILE_ENTITIES.register
            ("weapon_upgrade_tileentity", () -> TileEntityType.Builder.of(WeaponUpgradeBlockTileEntity::new,
                    EWBlocks.WEAPON_UPGRADE_CORE.get()).build(null));
    public static RegistryObject<TileEntityType<VisConversionTileEntity>> VisConversionTileEntity = TILE_ENTITIES.register
            ("vis_conversion_tileentity", () -> TileEntityType.Builder.of(VisConversionTileEntity::new,
                    EWBlocks.VIS_CONVERSION_CORE.get()).build(null));
    public static RegistryObject<TileEntityType<SteelFurnaceTileEntity>> SteelFurnaceTileEntity = TILE_ENTITIES.register
            ("steel_furnace_tileentity", () -> TileEntityType.Builder.of(SteelFurnaceTileEntity::new,
                    EWBlocks.STEEL_FURNACE_CORE.get()).build(null));
}
