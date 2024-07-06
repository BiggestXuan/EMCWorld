package biggestxuan.emcworld.common.compact.AstralSorcery;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/09
 */

import biggestxuan.emcworld.common.registry.EWDamageSource;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.MathUtils;
import hellfirepvp.astralsorcery.common.constellation.IMinorConstellation;
import hellfirepvp.astralsorcery.common.constellation.IWeakConstellation;
import hellfirepvp.astralsorcery.common.constellation.effect.ConstellationEffect;
import hellfirepvp.astralsorcery.common.constellation.effect.ConstellationEffectProperties;
import hellfirepvp.astralsorcery.common.event.PlayerAffectionFlags;
import hellfirepvp.astralsorcery.common.tile.TileRitualPedestal;
import hellfirepvp.astralsorcery.common.util.block.ILocatable;
import mekanism.api.Coord4D;
import mekanism.api.MekanismAPI;
import mekanism.api.radiation.IRadiationManager;
import net.mehvahdjukaar.dummmmmmy.setup.Registry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class EMCConstellationEffects extends ConstellationEffect {

    public static PlayerAffectionFlags.AffectionFlag FLAG = makeAffectionFlag("emc");
    public static EMCConfig CONFIG = new EMCConfig();

    protected EMCConstellationEffects(@Nonnull ILocatable origin, @Nonnull IWeakConstellation cst) {
        super(origin, cst);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void playClientEffect(World world, BlockPos blockPos, TileRitualPedestal tileRitualPedestal, float v, boolean b) {
    }

    @Override
    public boolean playEffect(World world, BlockPos blockPos, ConstellationEffectProperties properties, @Nullable IMinorConstellation iMinorConstellation) {
        if(world.isClientSide) return false;
        if(properties.isCorrupted()){
            BlockPos newPos = new BlockPos(blockPos.getX(),blockPos.getY()+2,blockPos.getZ());
            IRadiationManager radiationManager = MekanismAPI.getRadiationManager();
            double radiation = radiationManager.getRadiationLevel(new Coord4D(blockPos,world));
            if(radiation < 1.0d) return false;
            int amount = Math.max((int) radiation/20,1);
            world.addFreshEntity(new ItemEntity(world,newPos.getX(),newPos.getY(),newPos.getZ(),new ItemStack(EWItems.SMALL_EMC_GEM.get(),amount)));
        }
        else{
            float allCost = 0f;
            for(LivingEntity entity:getEntity(blockPos,world)){
                float damage = entity.getMaxHealth() /4f;
                if(entity.getHealth() > damage){
                    entity.hurt(EWDamageSource.REALLY,damage);
                    allCost += damage;
                }
                else{
                    entity.setHealth(0f);
                    allCost += entity.getHealth();
                }
            }
            if(allCost == 0) return false;
            for (int i = 0; i < (int) allCost /3; i++) {
                world.addFreshEntity(new ItemEntity(world,blockPos.getX(),blockPos.getY()+2,blockPos.getZ(),new ItemStack(EWItems.SMALL_EMC_GEM.get())));
            }
        }
        return true;
    }

    public static List<? extends LivingEntity> getEntity(BlockPos pos,World world){
        int range = CONFIG.range.get().intValue();
        List<LivingEntity> output = new ArrayList<>();
        List<LivingEntity> cache = world.getEntitiesOfClass(LivingEntity.class,new AxisAlignedBB(new BlockPos(pos.getX()+range,pos.getY()+range/2,pos.getZ()+range),new BlockPos(pos.getX()-range,pos.getY()-range/2,pos.getZ()-range)));
        for(LivingEntity entity:cache){
            if(entity instanceof PlayerEntity || entity instanceof TameableEntity || entity.getType().equals(Registry.TARGET_DUMMY.get())){
                continue;
            }
            output.add(entity);
        }
        return output;
    }

    @Override
    public Config getConfig() {
        return CONFIG;
    }

    @Override
    public PlayerAffectionFlags.AffectionFlag getPlayerAffectionFlag() {
        return FLAG;
    }

    public static class EMCConfig extends Config{
        public EMCConfig() {
            super("emc",8,8);
        }
        @Override
        public void createEntries(ForgeConfigSpec.Builder builder) {
            builder.push(this.getPath());
            super.createEntries(builder);
        }
        @Override
        protected String translationKey(String key) {
            return "config." + this.getFullPath() + '.' + key;
        }
    }
}
