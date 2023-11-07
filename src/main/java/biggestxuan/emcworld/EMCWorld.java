package biggestxuan.emcworld;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/24
 */

import biggestxuan.emcworld.client.key.*;
import biggestxuan.emcworld.client.render.ContainerDenyRender;
import biggestxuan.emcworld.client.render.StarPedestalRender;
import biggestxuan.emcworld.client.screen.*;
import biggestxuan.emcworld.common.compact.Projecte.ModifyCollector;
import biggestxuan.emcworld.common.config.ClientConfigManager;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.events.commandEvent;
import biggestxuan.emcworld.common.network.PacketHandler;
import biggestxuan.emcworld.common.network.toClient.SkillPacket.SkillNetworking;
import biggestxuan.emcworld.common.network.toClient.UtilPacket.UtilNetworking;
import biggestxuan.emcworld.common.registry.*;
import biggestxuan.emcworld.common.traits.TraitManager;
import biggestxuan.emcworld.common.utils.ModUtils;
import biggestxuan.emcworld.common.utils.RaidUtils;
import biggestxuan.emcworld.common.utils.Sponsors.Sponsors;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.resources.FilePack;
import net.minecraft.resources.IPackNameDecorator;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;
import noobanidus.mods.lootr.init.ModTiles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mod(EMCWorld.MODID)
public class EMCWorld {
    public static final Logger LOGGER = LogManager.getLogger("EMCWorld");
    public static final String MODID = "emcworld";
    public static final int ModPackVersion = 19;
    public static final String PackVersion = "1.0.6";
    public static final String TITLE = "EMCWorld " + PackVersion;
    public static final String PREFIX = "[EMCWorld] ";
    public static final long MAX_EMC = 1_000_000_000_000_000L;
    public static final int HOMO = ConfigManager.GENG.get() ? 114514 : 115000;
    public static boolean isBackingUp = false;
    public static final File RP = new File(FMLPaths.GAMEDIR.get().toFile(),"resources/EMCWorld Language.zip");
    public static boolean isOffline = false;

    public EMCWorld(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::enqueueIMC);
        bus.addListener(this::processIMC);
        bus.addListener(this::doClientStuff);
        bus.addListener(this::doStuff);

        EWSounds.SOUND.register(bus);
        EWEffects.EFFECTS.register(bus);
        EWTileEntityTypes.TILE_ENTITIES.register(bus);
        EWBlocks.BLOCKS.register(bus);
        EWBlocks.B.register(bus);
        EWItems.ITEMS.register(bus);
        EWSlurries.SLURRY.register(bus);
        EWGases.GASES.register(bus);
        EWFluids.FLUIDS.register(bus);
        EWInfuseTypes.INFUSE_TYPES.register(bus);
        EWPigments.PIGMENTS.register(bus);
        EWContainerTypes.CONTAINERS.register(bus);
        EWRecipeTypes.RECIPES.register(bus);
        EWVillagers.POI.register(bus);
        EWVillagers.PROFESSION.register(bus);
        EWModules.MODULES.register(bus);
        EWEnchantments.EN.register(bus);

        bus.addGenericListener(Block.class,EWBlocks::botaniaInit);

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigManager.COMMON_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfigManager.CLIENT_CONFIG);
        if(FMLEnvironment.dist == Dist.CLIENT){
            Minecraft.getInstance().getResourcePackRepository().addPackFinder((consumer, factory) -> {
                ResourcePackInfo info = ResourcePackInfo.create(MODID,true,() -> new FilePack(RP),factory, ResourcePackInfo.Priority.TOP, IPackNameDecorator.BUILT_IN);
                if(info != null){
                    consumer.accept(info);
                }
            });
            Minecraft.getInstance().getResourcePackRepository().reload();
        }
    }

    private void doStuff(FMLCommonSetupEvent event){
        event.enqueueWork(UtilNetworking::registerMessage);
        event.enqueueWork(SkillNetworking::registerMessage);
        event.enqueueWork(RaidUtils::init);
        event.enqueueWork(PacketHandler::init);
        TraitManager.init();
        MinecraftForge.EVENT_BUS.register(new commandEvent());
        ModifyCollector.init();
        welcome();
        //ModUtils.modList();
        LOGGER.info(ModUtils.addMod());
        //Champions.scalingHealthLoaded = false;
    }

    private void doClientStuff(FMLClientSetupEvent event) {
        event.enqueueWork(()->{
            ScreenManager.register(EWContainerTypes.advancedUpdateContainer.get(), AdvancedUpdateGUI::new);
            ScreenManager.register(EWContainerTypes.infuserContainer.get(), InfuserGUI::new);
            ScreenManager.register(EWContainerTypes.weaponUpgradeContainer.get(), WeaponUpgradeGUI::new);
            ScreenManager.register(EWContainerTypes.gemstoneContainer.get(), GemstoneGUI::new);
            ScreenManager.register(EWContainerTypes.steelFurnaceContainer.get(), SteelFurnaceGUI::new);
            ScreenManager.register(EWContainerTypes.prefixContainer.get(), PrefixGUI::new);
            ScreenManager.register(EWContainerTypes.superEMCContainer.get(), SuperEMCGUI::new);
            ScreenManager.register(EWContainerTypes.topCoreContainer.get(), TopCoreGUI::new);
            ScreenManager.register(EWContainerTypes.emcOreCoreContainer.get(), EMCOreCoreGUI::new);
            ScreenManager.register(EWContainerTypes.personalLinkContainer.get(),PersonalLinkScreen::new);
            ClientRegistry.registerKeyBinding(Admin.ADMIN_KEY);
            ClientRegistry.registerKeyBinding(SpeedControl.SPEED_KEY);
            ClientRegistry.registerKeyBinding(ArcanaDisplay.ArcanaKey);
            ClientRegistry.registerKeyBinding(LastShield.Last_Shield);
            ClientRegistry.registerKeyBinding(RangeAttack.Range_Attack);
            ClientRegistry.registerKeyBinding(PickModeKey.pickMode);
            ClientRegistry.registerKeyBinding(LiveMode.LiveMode);
            //ClientRegistry.registerKeyBinding(Trait.Trait); (Only in 1.1.0)
            ClientRegistry.bindTileEntityRenderer(EWTileEntityTypes.starPedestalTileEntity.get(), StarPedestalRender::new);
            //ClientRegistry.bindTileEntityRenderer(ModTiles.LOOT_CHEST, ContainerDenyRender::new);
            if(ConfigManager.SUNDRY_PILLAGER_CHEST_PREVENT.get()){
                ClientRegistry.bindTileEntityRenderer(ModTiles.LOOT_BARREL, ContainerDenyRender::new);
            }
        });
    }

    @Nonnull
    public static ItemStack getItem(String id){
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(id));
        return item == null ? new ItemStack(Items.AIR) : new ItemStack(item,1);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {}

    private void processIMC(final InterModProcessEvent event) {}

    public static ResourceLocation rl(String id){
        return new ResourceLocation(MODID,id);
    }

    public static TranslationTextComponent tc(String id){
        return new TranslationTextComponent(id);
    }

    public static <T> TranslationTextComponent tc(T id){
        return tc(String.valueOf(id));
    }

    public static TranslationTextComponent tc(String id,Object... name){
        return new TranslationTextComponent(id,name);
    }

    public static List<Ingredient> itemstack2ingredient(List<ItemStack> stacks){
        List<Ingredient> list = new ArrayList<>();
        stacks.forEach((i)-> list.add(Ingredient.of(i)));
        return list;
    }

    public static List<Ingredient> itemstack2ingredient(ItemStack[] stacks){
        return itemstack2ingredient(new ArrayList<>(Arrays.asList(stacks)));
    }

    public static List<ItemStack> ingredient2itemstackList(Ingredient ingredient){
        return List.of(ingredient.getItems());
    }

    private static void welcome(){
        Arrays.stream(Sponsors.all.values()).forEach(s -> {
            if(s.getSponsors().getMaxIndex() >= 3 || s.getSponsors().getMaxIndex() >= 6){
                LOGGER.info("Thanks for: "+s.getSponsors().getPlayerName());
            }
        });
    }
}