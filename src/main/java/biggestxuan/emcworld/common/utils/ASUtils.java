package biggestxuan.emcworld.common.utils;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.OnlyDev;
import hellfirepvp.astralsorcery.common.base.MoonPhase;
import hellfirepvp.astralsorcery.common.constellation.Constellation;
import hellfirepvp.astralsorcery.common.constellation.ConstellationItem;
import hellfirepvp.astralsorcery.common.constellation.IConstellation;
import hellfirepvp.astralsorcery.common.constellation.SkyHandler;
import hellfirepvp.astralsorcery.common.constellation.world.ConstellationHandler;
import hellfirepvp.astralsorcery.common.constellation.world.WorldContext;
import hellfirepvp.astralsorcery.common.lib.RegistriesAS;
import hellfirepvp.astralsorcery.common.util.MiscUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@EMCWorldSince("1.1.0")
public class ASUtils {
    public static boolean canSeeSky(BlockPos pos, World world){
        return MiscUtils.canSeeSky(world,pos,true,false);
    }

    @Nonnull
    public static List<IConstellation> getWorldConstellation(World world){
        List<IConstellation> constellations = new ArrayList<>();
        WorldContext ctx = SkyHandler.getContext(world);
        if(ctx == null) return new ArrayList<>();
        ConstellationHandler handler = ctx.getConstellationHandler();
        for(IConstellation s : RegistriesAS.REGISTRY_CONSTELLATIONS.getValues()){
            if(handler.isActiveCurrently(s, MoonPhase.fromWorld(world))){
                constellations.add(s);
            }
        }
        return constellations;
    }

    public static boolean hasConstellation(IConstellation c,World world){
        return getWorldConstellation(world).contains(c);
    }

    @Nullable
    public static IConstellation getItemStackConstellation(ItemStack stack){
        Item item = stack.getItem();
        if(!(item instanceof ConstellationItem)) return null;
        ConstellationItem itemConstellation = (ConstellationItem) item;
        return itemConstellation.getAttunedConstellation(stack);
    }

    @OnlyDev
    public static String getConstellationName(IConstellation c){
        if(c == null) return "null";
        if(c instanceof Constellation){
            Constellation cConstellation = (Constellation) c;
            return cConstellation.toString();
        }
        return "-";
    }

    public static boolean itemActiveConstellation(ItemStack stack,World world){
        return hasConstellation(getItemStackConstellation(stack),world);
    }
}
