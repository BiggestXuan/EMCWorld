package biggestxuan.emcworld.common.registry;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/01
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.entity.Player.*;
import com.google.common.collect.Lists;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EWEntities {
    public static final List<EntityType<?>> ENTITIES = Lists.newArrayList();

    public static final EntityType<Biggest_Xuan> biggest_xuan = register("biggest_xuan",Biggest_Xuan::new);
    public static final EntityType<Tulye> tulye = register("tulye",Tulye::new);
    //public static final EntityType<ChiYuanOvO> chiyuanovo = register("chiyuanovo",ChiYuanOvO::new);
    public static final EntityType<Dctor_0415> dctor_0415 = register("dctor_0415",Dctor_0415::new);
    //public static final EntityType<Depair_Anwu> depair_anwu = register("depair_anwu",Depair_Anwu::new);
    public static final EntityType<Jaoxaono> jaoxaono = register("jaoxaono",Jaoxaono::new);
    //public static final EntityType<JueFei> juefei = register("juefei",JueFei::new);
    //public static final EntityType<Maplefung> maplefung = register("maplefung",Maplefung::new);
    public static final EntityType<MCyunxi> mcyunxi = register("mcyunxi",MCyunxi::new);
    public static final EntityType<Xy177> xy177 = register("xy177",Xy177::new);
    public static final EntityType<WangLaoTou> wanglaotou = register("wanglaotou",WangLaoTou::new);
    public static final EntityType<Xk9940> xk9940 = register("xk9940",Xk9940::new);
    //public static final EntityType<Abunana> abunana = register("abunana",Abunana::new);
    //public static final EntityType<Alfie_zh> alfie_zh = register("alfie_zh",Alfie_zh::new);
    public static final EntityType<Xiangshushumiao> xiangshushumiao = register("xiangshushumiao",Xiangshushumiao::new);
    public static final EntityType<Btmy> btmy = register("btmy",Btmy::new);
    //public static final EntityType<Cxk> cxk = register("cxk",Cxk::new);
    public static final EntityType<dytlj7788> dytlj7788 = register("dytlj7788",dytlj7788::new);
    public static final EntityType<LAMB_Kisara> lamb_kisara = register("lamb_kisara",LAMB_Kisara::new);
    public static final EntityType<cmzxymzx> cmzxymzx = register("cmzxymzx",cmzxymzx::new);

    @SubscribeEvent
    public static void bind(EntityAttributeCreationEvent event){
        event.put(biggest_xuan,Biggest_Xuan.create().build());
        event.put(tulye,Tulye.create().build());
        //event.put(chiyuanovo,ChiYuanOvO.create().build());
        event.put(dctor_0415,Dctor_0415.create().build());
        //event.put(depair_anwu,Depair_Anwu.create().build());
        event.put(jaoxaono,Jaoxaono.create().build());
        //event.put(juefei,JueFei.create().build());
        //event.put(maplefung,Maplefung.create().build());
        event.put(mcyunxi,MCyunxi.create().build());
        event.put(wanglaotou,WangLaoTou.create().build());
        //event.put(abunana,Abunana.create().build());
        event.put(xiangshushumiao, Xiangshushumiao.create().build());
        event.put(xy177, Xy177.create().build());
        event.put(xk9940, Xk9940.create().build());
        //event.put(alfie_zh, Alfie_zh.create().build());
        event.put(btmy, Btmy.create().build());
        //event.put(cxk,Cxk.create().build());
        event.put(dytlj7788,biggestxuan.emcworld.common.entity.Player.dytlj7788.create().build());
        event.put(lamb_kisara,LAMB_Kisara.create().build());
        event.put(cmzxymzx, biggestxuan.emcworld.common.entity.Player.cmzxymzx.create().build());
    }

    public static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        ResourceLocation location = EMCWorld.rl(name);
        return builder.build(location.toString());
    }
    public static <T extends Entity> EntityType<T> register(String name, EntityType.IFactory<T> type){
        ResourceLocation location = EMCWorld.rl(name);
        EntityType<T> entityType = register(name,EntityType.Builder.of(type,EntityClassification.MONSTER).sized(0.6F, 1.8F).setTrackingRange(32));
        entityType.setRegistryName(location);
        ENTITIES.add(entityType);
        return entityType;
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        for(EntityType<?> i :ENTITIES){
            event.getRegistry().register(i);
        }
    }
}
