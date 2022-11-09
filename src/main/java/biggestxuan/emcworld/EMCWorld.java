package biggestxuan.emcworld;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/24
 */

import biggestxuan.emcworld.client.event.ClientTickEvent;
import biggestxuan.emcworld.client.key.Admin;
import biggestxuan.emcworld.client.key.SpeedControl;
import biggestxuan.emcworld.common.blocks.AdvancedUpdateBlock.AdvancedUpdateGUI;
import biggestxuan.emcworld.common.blocks.InfuserBlock.InfuserGUI;
import biggestxuan.emcworld.common.blocks.WeaponUpgradeBlock.WeaponUpgradeGUI;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.events.commandEvent;
import biggestxuan.emcworld.common.network.PacketHandler;
import biggestxuan.emcworld.common.network.SkillPacket.SkillNetworking;
import biggestxuan.emcworld.common.network.UtilPacket.UtilNetworking;
import biggestxuan.emcworld.common.registry.*;
import biggestxuan.emcworld.common.utils.RaidMembers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(EMCWorld.MODID)
public class EMCWorld
{
    public static final Logger LOGGER = LogManager.getLogger("EMCWorld");
    public static final String MODID = "emcworld";
    public static final int ModPackVersion = 1;
    public static final String PackVersion = "0.1.0";
    public static final String TITLE = "EMCWorld " + PackVersion;
    public static final String PREFIX = "[EMCWorld]";

    public static final long MAX_EMC = 100_000_000_000_000L;

    public EMCWorld(){
        IEventBus event = FMLJavaModLoadingContext.get().getModEventBus();
        event.addListener(this::enqueueIMC);
        event.addListener(this::processIMC);
        event.addListener(this::doClientStuff);
        event.addListener(this::doStuff);

        EWItems.ITEMS.register(event);
        EWBlocks.BLOCKS.register(event);
        EWBlocks.B.register(event);
        EWSlurries.SLURRY.register(event);
        EWGases.GASES.register(event);
        EWFluids.FLUIDS.register(event);
        EWInfuseTypes.INFUSE_TYPES.register(event);
        EWPigments.PIGMENTS.register(event);
        EWTileEntityTypes.TILE_ENTITIES.register(event);
        EWContainerTypes.CONTAINERS.register(event);
        EWEffects.EFFECTS.register(event);
        EWRecipeTypes.RECIPES.register(event);
        EWSounds.SOUND.register(event);

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigManager.COMMON_CONFIG);
    }

    private void doStuff(FMLCommonSetupEvent event){
        event.enqueueWork(UtilNetworking::registerMessage);
        event.enqueueWork(SkillNetworking::registerMessage);
        event.enqueueWork(RaidMembers::init);
        event.enqueueWork(PacketHandler::init);
        MinecraftForge.EVENT_BUS.register(new commandEvent());
    }

    private void doClientStuff(FMLClientSetupEvent event) {
        event.enqueueWork(()->{
            ScreenManager.register(EWContainerTypes.advancedUpdateContainer.get(), AdvancedUpdateGUI::new);
            ScreenManager.register(EWContainerTypes.infuserContainer.get(), InfuserGUI::new);
            ScreenManager.register(EWContainerTypes.weaponUpgradeContainer.get(), WeaponUpgradeGUI::new);
            ClientRegistry.registerKeyBinding(Admin.ADMIN_KEY);
            ClientRegistry.registerKeyBinding(SpeedControl.SPEED_KEY);
            LOGGER.info(ClientTickEvent.isCrash); //DEBUG
        });
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
    }
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
}