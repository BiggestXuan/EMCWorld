package biggestxuan.emcworld.common.compact.Mekanism;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/26
 */

import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.chemical.slurry.SlurryBuilder;
import mekanism.common.registration.WrappedDeferredRegister;
import mekanism.common.registration.impl.SlurryRegistryObject;

import java.util.function.UnaryOperator;

public class EWSlurryBuilder extends WrappedDeferredRegister<Slurry> {
    public EWSlurryBuilder(String modid) {
        super(modid, Slurry.class);
    }
    public SlurryRegistryObject<Slurry,Slurry> registry(EWSlurry slurry){
        return register(slurry.getName(),(builder)-> ((SlurryBuilder) builder.color(slurry.getTint())));
    }
    public SlurryRegistryObject<Slurry, Slurry> register(String baseName, UnaryOperator<SlurryBuilder> builderModifier) {
        return new SlurryRegistryObject<>(internal.register("dirty_" + baseName, () -> new Slurry(builderModifier.apply(SlurryBuilder.dirty()))),
                internal.register("clean_" + baseName, () -> new Slurry(builderModifier.apply(SlurryBuilder.clean()))));
    }
}
