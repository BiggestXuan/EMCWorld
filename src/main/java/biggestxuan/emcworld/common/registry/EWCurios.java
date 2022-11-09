package biggestxuan.emcworld.common.registry;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/25
 */

import biggestxuan.emcworld.EMCWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import top.theillusivec4.curios.api.SlotTypeMessage;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EWCurios {

    public static final ResourceLocation icon = EMCWorld.rl("curios/emc_totem");
    public static final ResourceLocation icon1 = EMCWorld.rl("curios/curios_nuclear_ball");

    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent event) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,()-> new SlotTypeMessage.Builder("emc_totem").icon(icon).size(3).build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,()-> new SlotTypeMessage.Builder("curios_nuclear_ball").icon(icon1).size(1).build());
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void stitch(TextureStitchEvent.Pre event){
        event.addSprite(icon);
        event.addSprite(icon1);
    }
}
