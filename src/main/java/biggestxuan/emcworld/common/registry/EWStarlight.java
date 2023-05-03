package biggestxuan.emcworld.common.registry;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/09
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.AstralSorcery.EMCConstellationEffects;
import biggestxuan.emcworld.common.compact.AstralSorcery.EMCMantleEffects;
import biggestxuan.emcworld.common.compact.AstralSorcery.Perk.ArmorPerk;
import hellfirepvp.astralsorcery.common.constellation.Constellation;
import hellfirepvp.astralsorcery.common.constellation.IConstellation;
import hellfirepvp.astralsorcery.common.constellation.IWeakConstellation;
import hellfirepvp.astralsorcery.common.constellation.effect.ConstellationEffect;
import hellfirepvp.astralsorcery.common.constellation.effect.ConstellationEffectProvider;
import hellfirepvp.astralsorcery.common.constellation.engraving.EngravingEffect;
import hellfirepvp.astralsorcery.common.constellation.mantle.MantleEffect;
import hellfirepvp.astralsorcery.common.constellation.star.StarLocation;
import hellfirepvp.astralsorcery.common.crystal.CrystalProperty;
import hellfirepvp.astralsorcery.common.crystal.property.PropertyConstellation;
import hellfirepvp.astralsorcery.common.perk.type.ModifierType;
import hellfirepvp.astralsorcery.common.perk.type.PerkAttributeType;
import hellfirepvp.astralsorcery.common.util.block.ILocatable;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.awt.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,modid = EMCWorld.MODID)
public class EWStarlight {
    public static final Lazy<IWeakConstellation> EMC_STAR = Lazy.of(()->{
        StarLocation a,b,c,f,g,h;
        IWeakConstellation emc = new Constellation.Weak("emc",new Color(0xD03C40));
        a = emc.addStar(15,1);
        b = emc.addStar(6,7);
        c = emc.addStar(24,7);
        f = emc.addStar(6,19);
        g = emc.addStar(24,19);
        h = emc.addStar(15,25);
        emc.addConnection(a,f);
        emc.addConnection(a,g);
        emc.addConnection(b,c);
        emc.addConnection(b,h);
        emc.addConnection(c,h);
        emc.addConnection(f,g);
        return emc;
    });

    public static PerkAttributeType armorPerk;

    @SubscribeEvent
    public static void registerConstellation(RegistryEvent.Register<IConstellation> event){
        event.getRegistry().register(EMC_STAR.get());
    }

    @SubscribeEvent
    public static void registerConstellationEffects(RegistryEvent.Register<ConstellationEffectProvider> event){
        event.getRegistry().register(new ConstellationEffectProvider(EMC_STAR.get()) {
            @Override
            public ConstellationEffect createEffect(@Nullable ILocatable iLocatable) {
                return new EMCConstellationEffects(iLocatable,EMC_STAR.get()) {
                };
            }
        });
    }

    @SubscribeEvent
    public static void registerEngravingEffects(RegistryEvent.Register<EngravingEffect> event) {
        EngravingEffect effect = new EngravingEffect(EMC_STAR.get());
        effect.addEffect(new EngravingEffect.EnchantmentEffect(()-> Enchantments.SHARPNESS,3,6));
        effect.addEffect(new EngravingEffect.ModifierEffect(()-> armorPerk, ModifierType.ADDITION,1,1).addApplicableType(EnchantmentType.ARMOR));
        effect.addEffect(new EngravingEffect.PotionEffect(EWEffects.EMC_PROTECT,1,1));
        event.getRegistry().register(effect);
    }

    @SubscribeEvent
    public static void registerCrystalProperties(RegistryEvent.Register<CrystalProperty> event) {
        event.getRegistry().register(new PropertyConstellation(EMC_STAR.get()));
    }

    @SubscribeEvent
    public static void registerPerkAttributes(RegistryEvent.Register<PerkAttributeType> event) {
        event.getRegistry().registerAll(armorPerk = new ArmorPerk());
    }

    @SubscribeEvent
    public static void registerMantleEffects(RegistryEvent.Register<MantleEffect> event) {
        event.getRegistry().register(new EMCMantleEffects(EMC_STAR.get()));
    }
}
