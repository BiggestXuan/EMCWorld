package biggestxuan.emcworld.common.registry;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/26
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.blocks.AdvancedUpdateBlock.AdvancedUpdateBlock;
import biggestxuan.emcworld.common.blocks.*;
import biggestxuan.emcworld.common.blocks.GemstoneBlock.GemstoneBlock;
import biggestxuan.emcworld.common.blocks.InfuserBlock.InfuserBlock;
import biggestxuan.emcworld.common.blocks.Ores.EWDirtOre;
import biggestxuan.emcworld.common.blocks.Ores.EWStoneOre;
import biggestxuan.emcworld.common.blocks.SteelFurnace.SteelFurnaceCore;
import biggestxuan.emcworld.common.blocks.VisConversionBlock.VisConversionBlock;
import biggestxuan.emcworld.common.blocks.WeaponUpgradeBlock.WeaponUpgradeBlock;
import biggestxuan.emcworld.common.compact.Botania.BotaniaFlowers.TileEMCFlower;
import biggestxuan.emcworld.common.compact.Botania.FlowerBlock;
import mekanism.api.tier.ITier;
import mekanism.common.block.attribute.AttributeTier;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.item.block.ItemBlockInductionCell;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registries.MekanismBlockTypes;
import mekanism.common.tile.multiblock.TileEntityInductionCell;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Function;
import java.util.function.Supplier;

import static vazkii.botania.common.block.ModBlocks.register;

@SuppressWarnings("unused")
public class EWBlocks {
        public static final BlockDeferredRegister B = new BlockDeferredRegister(EMCWorld.MODID);
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EMCWorld.MODID);
        public static final RegistryObject<Block> HARDCORE_STONE = BLOCKS.register("hardcore_stone",() -> new EWStoneOre(3,100.0F));
        public static final RegistryObject<Block> CONTROL_UPDATE_CORE = BLOCKS.register("control_update_core",UpdateBlock::new);
        public static final RegistryObject<Block> INFUSER_BLOCK_CORE = BLOCKS.register("infuser_core", InfuserBlock::new);
        public static final RegistryObject<Block> STEEL_FURNACE_BRICK = BLOCKS.register("steel_furnace_brick",() -> new EWBlock(5.5F));
        public static final RegistryObject<Block> STEEL_FURNACE_CORE = BLOCKS.register("steel_furnace_core", SteelFurnaceCore::new);
        public static final RegistryObject<Block> WEAPON_UPGRADE_CORE = BLOCKS.register("weapon_upgrade_core", WeaponUpgradeBlock::new);
        public static final RegistryObject<Block> ADVANCED_UPDATE_CORE = BLOCKS.register("advanced_update_core", AdvancedUpdateBlock::new);
        public static final RegistryObject<Block> PROFESSION_CORE = BLOCKS.register("profession_core", ProfessionalBlock::new);
        public static final RegistryObject<Block> GEMSTONE_CORE = BLOCKS.register("gemstone_core", GemstoneBlock::new);
        public static final RegistryObject<Block> EMC_ORE = BLOCKS.register("emc_ore",() -> new EWStoneOre(2,3F));
        public static final RegistryObject<Block> RICH_EMC_ORE = BLOCKS.register("rich_emc_ore",() -> new EWStoneOre(3,7F));
        public static final RegistryObject<Block> NETHER_EMC_ORE = BLOCKS.register("nether_emc_ore", () -> new EWStoneOre(2,5F));
        public static final RegistryObject<Block> END_EMC_ORE = BLOCKS.register("end_emc_ore", () -> new EWStoneOre(3,7F));
        public static final RegistryObject<Block> END_RICH_EMC_ORE = BLOCKS.register("end_rich_emc_ore", () -> new EWStoneOre(5,10F));
        public static final RegistryObject<Block> CHLOROPHYTE_ORE = BLOCKS.register("chlorophyte_ore",() -> new EWDirtOre(3,3.5F));
        public static final RegistryObject<Block> VIS_CONVERSION_CORE = BLOCKS.register("vis_conversion_core",VisConversionBlock::new);
        public static final RegistryObject<Block> ORICHALCOS_ORE = BLOCKS.register("orichalcos_ore",() -> new EWStoneOre(4,16.0F));
        public static final RegistryObject<Block> COLD_ORE = BLOCKS.register("cold_ore", () -> new EWStoneOre(1,2.5F));
        public static final RegistryObject<Block> NICKEL_ORE = BLOCKS.register("nickel_ore",() -> new EWStoneOre(1,6.0F));
        public static final RegistryObject<Block> ALUMINUM_ORE = BLOCKS.register("aluminum_ore",() -> new EWStoneOre(1,6.0F));
        public static final RegistryObject<Block> SILVER_ORE = BLOCKS.register("silver_ore",() -> new EWStoneOre(1,6.0F));
        public static final RegistryObject<Block> SUNLIT_ORE = BLOCKS.register("sunlit_ore",() -> new EWStoneOre(2,7.5F));
        public static final RegistryObject<Block> DRYSTONE_ORE = BLOCKS.register("drystone_ore",() -> new EWStoneOre(3,15.0F));
        public static final RegistryObject<Block> AQUAMARINE_ORE = BLOCKS.register("aquamarine_ore",() -> new EWDirtOre(1,3.0F));
        public static final RegistryObject<Block> INDIUM_ORE = BLOCKS.register("indium_ore",() -> new EWStoneOre(4,15.0F));
        public static final RegistryObject<Block> MAGNESIUM_ORE = BLOCKS.register("magnesium_ore",() -> new EWStoneOre(3,6F));
        public static final RegistryObject<Block> TITANIUM_ORE = BLOCKS.register("titanium_ore",() -> new EWDirtOre(5,22.5F));

        public static final RegistryObject<Block> UPDATE_BASE_PURPLE = BLOCKS.register("update_base_purple",() -> new EWUpdateBlock(1,1,1,1));
        public static final RegistryObject<Block> UPDATE_BASE_BX_PURPLE = BLOCKS.register("update_base_bx_purple",() -> new EWUpdateBlock(2,1,1,1));
        public static final RegistryObject<Block> UPDATE_BASE_BLUE = BLOCKS.register("update_base_blue",() -> new EWUpdateBlock(3,1,1,1));
        public static final RegistryObject<Block> UPDATE_BASE_CYAN = BLOCKS.register("update_base_cyan",() -> new EWUpdateBlock(4,1,1,1));
        public static final RegistryObject<Block> UPDATE_BASE_GREEN = BLOCKS.register("update_base_green",() -> new EWUpdateBlock(5,1,1,1));
        public static final RegistryObject<Block> UPDATE_BASE_YELLOW = BLOCKS.register("update_base_yellow",() -> new EWUpdateBlock(6,1,1,1));
        public static final RegistryObject<Block> UPDATE_BASE_ORANGE = BLOCKS.register("update_base_orange",() -> new EWUpdateBlock(7,1,1,1));
        public static final RegistryObject<Block> UPDATE_BASE_RED = BLOCKS.register("update_base_red",() -> new EWUpdateBlock(8,1,1,1));

        public static final RegistryObject<Block> UPDATE_ADDON_PURPLE = BLOCKS.register("update_addon_purple",() -> new EWUpdateBlock(1,1.002,1.001,1.001));
        public static final RegistryObject<Block> UPDATE_ADDON_BX_PURPLE = BLOCKS.register("update_addon_bx_purple",() -> new EWUpdateBlock(2,1.005,1.002,1.002));
        public static final RegistryObject<Block> UPDATE_ADDON_BLUE = BLOCKS.register("update_addon_blue",() -> new EWUpdateBlock(3,1.009,1.003,1.003));
        public static final RegistryObject<Block> UPDATE_ADDON_CYAN = BLOCKS.register("update_addon_cyan",() -> new EWUpdateBlock(4,1.014,1.004,1.007));
        public static final RegistryObject<Block> UPDATE_ADDON_GREEN = BLOCKS.register("update_addon_green",() -> new EWUpdateBlock(5,1.02,1.006,1.01));
        public static final RegistryObject<Block> UPDATE_ADDON_YELLOW = BLOCKS.register("update_addon_yellow",() -> new EWUpdateBlock(6,1.027,1.01,1.015));
        public static final RegistryObject<Block> UPDATE_ADDON_ORANGE = BLOCKS.register("update_addon_orange",() -> new EWUpdateBlock(7,1.035,1.015,1.02));
        public static final RegistryObject<Block> UPDATE_ADDON_RED = BLOCKS.register("update_addon_red",() -> new EWUpdateBlock(8,1.045,1.02,1.03));

        public static final RegistryObject<Block> UPDATE_TIME_PURPLE = BLOCKS.register("update_time_purple",() -> new EWUpdateBlock(1,1,0.996,1.001));
        public static final RegistryObject<Block> UPDATE_TIME_BX_PURPLE = BLOCKS.register("update_time_bx_purple",() -> new EWUpdateBlock(2,1,0.992,1.002));
        public static final RegistryObject<Block> UPDATE_TIME_BLUE = BLOCKS.register("update_time_blue",() -> new EWUpdateBlock(3,1,0.987,1.003));
        public static final RegistryObject<Block> UPDATE_TIME_CYAN = BLOCKS.register("update_time_cyan",() -> new EWUpdateBlock(4,1,0.982,1.004));
        public static final RegistryObject<Block> UPDATE_TIME_GREEN = BLOCKS.register("update_time_green",() -> new EWUpdateBlock(5,1,0.976,1.006));
        public static final RegistryObject<Block> UPDATE_TIME_YELLOW = BLOCKS.register("update_time_yellow",() -> new EWUpdateBlock(6,1,0.968,1.008));
        public static final RegistryObject<Block> UPDATE_TIME_ORANGE = BLOCKS.register("update_time_orange",() -> new EWUpdateBlock(7,1,0.955,1.01));
        public static final RegistryObject<Block> UPDATE_TIME_RED = BLOCKS.register("update_time_red",() -> new EWUpdateBlock(8,1,0.94,1.013));

        public static final RegistryObject<Block> UPDATE_COST_PURPLE = BLOCKS.register("update_cost_purple",() -> new EWUpdateBlock(1,1,1.003,0.998));
        public static final RegistryObject<Block> UPDATE_COST_BX_PURPLE = BLOCKS.register("update_cost_bx_purple",() -> new EWUpdateBlock(2,1,1.006,0.996));
        public static final RegistryObject<Block> UPDATE_COST_BLUE = BLOCKS.register("update_cost_blue",() -> new EWUpdateBlock(3,1,1.01,0.993));
        public static final RegistryObject<Block> UPDATE_COST_CYAN = BLOCKS.register("update_cost_cyan",() -> new EWUpdateBlock(4,1,1.015,0.989));
        public static final RegistryObject<Block> UPDATE_COST_GREEN = BLOCKS.register("update_cost_green",() -> new EWUpdateBlock(5,1,1.02,0.984));
        public static final RegistryObject<Block> UPDATE_COST_YELLOW = BLOCKS.register("update_cost_yellow",() -> new EWUpdateBlock(6,1,1.025,0.978));
        public static final RegistryObject<Block> UPDATE_COST_ORANGE = BLOCKS.register("update_cost_orange",() -> new EWUpdateBlock(7,1,1.03,0.971));
        public static final RegistryObject<Block> UPDATE_COST_RED = BLOCKS.register("update_cost_red",() -> new EWUpdateBlock(8,1,1.04,0.962));

        public static final Block EMC_FLOWER = new FlowerBlock(4.5f, TileEMCFlower::new);

        public static final RegistryObject<Block> TEST_BLOCK = BLOCKS.register("test_block", TestBlock::new);
        public static final BlockRegistryObject<BlockTile<TileEntityInductionCell, BlockTypeTile<TileEntityInductionCell>>, ItemBlockInductionCell> EMC_CELL = registerInductionCell(MekanismBlockTypes.ULTIMATE_INDUCTION_CELL);;

        private static BlockRegistryObject<BlockTile<TileEntityInductionCell, BlockTypeTile<TileEntityInductionCell>>, ItemBlockInductionCell> registerInductionCell(BlockTypeTile<TileEntityInductionCell> type) {
                return registerTieredBlock(((AttributeTier)type.get(AttributeTier.class)).getTier(), "_induction_cell", () -> new BlockTile(type), ItemBlockInductionCell::new);
        }
        private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(ITier tier, String suffix, Supplier<? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
                return B.register(tier.getBaseTier().getLowerName() + suffix, blockSupplier, itemCreator);
        }

        public static void botaniaInit(RegistryEvent.Register<Block> event){
                IForgeRegistry<Block> e = event.getRegistry();
                register(e,EMCWorld.rl("emc_flower"),EMC_FLOWER);
        }
}
