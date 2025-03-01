package biggestxuan.emcworld.common.events.PlayerEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/18
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerChatEvent {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void playerChatEvent(ServerChatEvent event){
        LazyOptional<IUtilCapability> sponsorCap = event.getPlayer().getCapability(EMCWorldCapability.UTIL);
        MinecraftServer server = event.getPlayer().server;
        assert server != null;
        sponsorCap.ifPresent((cap) -> {
            ITextComponent component = event.getComponent();
            if(!cap.getOnline()) return;
            event.setComponent(pick(cap, event.getPlayer(), component));
        });
    }

    public static ITextComponent pick(IUtilCapability cap,PlayerEntity player,ITextComponent message){
        int level = cap.getDisplayIndex();
        IFormattableTextComponent component = EMCWorld.tc("");
        Style style = Style.EMPTY;
        HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new StringTextComponent(""));
        switch (level){
            case 1:
                component = EMCWorld.tc("§2[V]");
                event = getHoverEvent(EMCWorld.tc("message.sponsor.sponsor"));
                break;
            case 2:
                component = EMCWorld.tc("§3[A]");
                event = getHoverEvent(EMCWorld.tc("message.sponsor.senior_sponsor"));
                break;
            case 3:
                component = EMCWorld.tc("§6[P]");
                event = getHoverEvent(EMCWorld.tc("message.sponsor.top_sponsor"));
                break;
            case 4:
                component = EMCWorld.tc("§6[P]");
                event = getHoverEvent(EMCWorld.tc("message.sponsor.mascot"));
                break;
            case 5:
                component = EMCWorld.tc("§6[P]");
                event = getHoverEvent(EMCWorld.tc("message.sponsor.dev"));
                break;
            case 6:
                component = EMCWorld.tc("§5[T]");
                break;
            default:
                break;
        }
        component.setStyle(style.withHoverEvent(event));
        return component.append(message);
    }

    public static HoverEvent getHoverEvent(ITextComponent s){
        return new HoverEvent(HoverEvent.Action.SHOW_TEXT,s);
    }
}
