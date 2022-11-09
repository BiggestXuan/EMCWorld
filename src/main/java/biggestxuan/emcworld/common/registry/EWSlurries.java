package biggestxuan.emcworld.common.registry;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/26
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.Mekanism.EWSlurryBuilder;
import biggestxuan.emcworld.common.compact.Mekanism.EWSlurry;
import mekanism.api.chemical.slurry.Slurry;
import mekanism.common.registration.impl.SlurryRegistryObject;

import javax.swing.plaf.SliderUI;

public class EWSlurries {
    private EWSlurries(){}

    public static final EWSlurryBuilder SLURRY = new EWSlurryBuilder(EMCWorld.MODID);

    public static final SlurryRegistryObject<Slurry, Slurry> EMC_GEM = SLURRY.registry(new EWSlurry("emc_gem",0xF805D2));
    public static final SlurryRegistryObject<Slurry, Slurry> GOBBER = SLURRY.registry(new EWSlurry("gobber",0x4C6799));
    public static final SlurryRegistryObject<Slurry, Slurry> NETHER_GOBBER = SLURRY.registry(new EWSlurry("nether_gobber",0x9C3057));
    public static final SlurryRegistryObject<Slurry, Slurry> END_GOBBER = SLURRY.registry(new EWSlurry("end_gobber",0x1FB0A1));
    public static final SlurryRegistryObject<Slurry, Slurry> COLD = SLURRY.registry(new EWSlurry("cold",0x3B33EB));
    public static final SlurryRegistryObject<Slurry, Slurry> CHLOROPHYTE = SLURRY.registry(new EWSlurry("chlorophyte",0x075420));
    public static final SlurryRegistryObject<Slurry, Slurry> ORICHALCOS = SLURRY.registry(new EWSlurry("orichalcos",0xE65813));
    public static final SlurryRegistryObject<Slurry, Slurry> PENDORITE = SLURRY.registry(new EWSlurry("pendorite",0x6432BE));
    public static final SlurryRegistryObject<Slurry, Slurry> ALUMINUM = SLURRY.registry(new EWSlurry("aluminum",0xBC7E55));
    public static final SlurryRegistryObject<Slurry, Slurry> NICKEL = SLURRY.registry(new EWSlurry("nickel",0x6D848D));
    public static final SlurryRegistryObject<Slurry, Slurry> SILVER = SLURRY.registry(new EWSlurry("silver",0x72C6E7));
    public static final SlurryRegistryObject<Slurry, Slurry> AQUAMARINE = SLURRY.registry(new EWSlurry("aquamarine",0x67CACE));
    public static final SlurryRegistryObject<Slurry, Slurry> ROSE_COPPER = SLURRY.registry(new EWSlurry("rose_copper",0x0A7E82));
    public static final SlurryRegistryObject<Slurry, Slurry> ORATCHALCUM = SLURRY.registry(new EWSlurry("oratchalcum",0x8C845A));
    public static final SlurryRegistryObject<Slurry, Slurry> MOONSTEEL = SLURRY.registry(new EWSlurry("moonsteel",0x0023AE));
    public static final SlurryRegistryObject<Slurry, Slurry> CRYSTILLIUM = SLURRY.registry(new EWSlurry("crystillium",0x456569));
    public static final SlurryRegistryObject<Slurry, Slurry> LUNARITE_SCRAP = SLURRY.registry(new EWSlurry("lunarite_scrap",0x28383A));
    public static final SlurryRegistryObject<Slurry, Slurry> CLOGGRUM = SLURRY.registry(new EWSlurry("cloggrum",0x615850));
    public static final SlurryRegistryObject<Slurry, Slurry> FROSTSTEEL = SLURRY.registry(new EWSlurry("froststeel",0x8E93AA));
    public static final SlurryRegistryObject<Slurry, Slurry> UTHERIUM = SLURRY.registry(new EWSlurry("utherium",0x762A2F));
    public static final SlurryRegistryObject<Slurry, Slurry> REGALIUM = SLURRY.registry(new EWSlurry("regalium",0x987F55));
    public static final SlurryRegistryObject<Slurry, Slurry> SUNLIT = SLURRY.registry(new EWSlurry("sunlit",0xD89800));
    public static final SlurryRegistryObject<Slurry, Slurry> DRYSTONE = SLURRY.registry(new EWSlurry("drystone",0xBA7E3E));
    public static final SlurryRegistryObject<Slurry, Slurry> GRAVITITE = SLURRY.registry(new EWSlurry("gravitite",0x7B2475));
    public static final SlurryRegistryObject<Slurry, Slurry> RAINBOW = SLURRY.registry(new EWSlurry("rainbow",0xA714E5));
    public static final SlurryRegistryObject<Slurry, Slurry> ZITRITE = SLURRY.registry(new EWSlurry("zitrite",0x000000));
    public static final SlurryRegistryObject<Slurry, Slurry> MAGNESIUM = SLURRY.registry(new EWSlurry("magnesium",0xD9D1C4));
    public static final SlurryRegistryObject<Slurry, Slurry> TITANIUM = SLURRY.registry(new EWSlurry("titanium",0x91DBD9));
    public static final SlurryRegistryObject<Slurry, Slurry> INDIUM = SLURRY.registry(new EWSlurry("indium",0x9C5701));
    public static final SlurryRegistryObject<Slurry, Slurry> STARMETAL = SLURRY.registry(new EWSlurry("starmetal",0x2B56BE));
    public static final SlurryRegistryObject<Slurry, Slurry> CUPRIC_SULFATE = SLURRY.registry(new EWSlurry("cupric_sulfate",0x00745B));
}
