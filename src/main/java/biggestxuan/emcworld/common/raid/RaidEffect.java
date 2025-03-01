package biggestxuan.emcworld.common.raid;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.registry.EWDamageSource;
import biggestxuan.emcworld.common.utils.ASUtils;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import hellfirepvp.astralsorcery.common.constellation.IConstellation;
import hellfirepvp.astralsorcery.common.lib.ConstellationsAS;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.World;
import net.minecraft.world.raid.Raid;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/12
 */
@EMCWorldSince("1.2.0")
public abstract class RaidEffect {
    public static final Set<RaidEffect> effects = new HashSet<>();
    @Nonnull
    protected Raid raid;
    protected final String id;
    protected final IConstellation constellation;
    protected final World world;
    protected final int waves;
    protected final boolean hasAlcara;
    protected final MinecraftServer server;

    public RaidEffect(@Nonnull Raid raid, String id, IConstellation constellation) {
        this.raid = raid;
        this.id = id;
        this.constellation = constellation;
        this.world = raid.getLevel();
        this.waves = raid.groupsSpawned;
        this.hasAlcara = ASUtils.hasConstellation(ConstellationsAS.alcara,world);
        this.server = raid.getLevel().getServer();
        effects.add(this);
    }

    public void setRaid(Raid raid){
        this.raid = raid;
    }

    public ITextComponent getText(){
        String desc = "emcworld.raid.name_"+id;
        String buff = "emcworld.raid.buff_"+id;
        String debuff = "emcworld.raid.debuff_"+id;
        IFormattableTextComponent id = EMCWorld.tc(debuff).withStyle(Style.EMPTY.withColor(TextFormatting.GREEN));
        IFormattableTextComponent ib = EMCWorld.tc(buff).withStyle(Style.EMPTY.withColor(TextFormatting.RED));
        HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_TEXT, ib.append("\n").append(id));
        return EMCWorld.tc(desc).withStyle(Style.EMPTY.withColor(Color.fromRgb(constellation.getConstellationColor().getRGB())).withHoverEvent(event));
    }

    public void tick(){

    }

    public float onIllagerAttack(LivingEntity attacker,Entity target,float amount){
        return amount;
    }

    public float onIllagerHurt(LivingEntity livingEntity, DamageSource source, float amount){
        return amount;
    }

    public float onPlayerHurt(PlayerEntity player, DamageSource source, float amount){
        return amount;
    }

    public float onPlayerHeal(PlayerEntity player, float amount){
        return amount;
    }

    public float onPlayerAttack(PlayerEntity player, Entity target, float amount){
        return amount;
    }

    public void onPlayerTick(PlayerEntity player){

    }

    public void onIllagerTick(LivingEntity livingEntity){

    }

    public EMCSource<?> onPlayerEMCCost(PlayerEntity player, EMCSource<?> source){
        return source;
    }

    public void onSpawnIllager(LivingEntity livingEntity){

    }

    public float onPlayerEMCShiedCost(PlayerEntity player, DamageSource source, float amount){
        return amount;
    }

    public void onIllagerDeath(LivingEntity livingEntity,DamageSource source){

    }

    public boolean hasThisConstellation(){
        return ASUtils.hasConstellation(this.constellation,raid.getLevel());
    }

    @Override
    public boolean equals(Object effect) {
        if(effect instanceof RaidEffect){
            RaidEffect effect2 = (RaidEffect) effect;
            return effect2.id.equals(this.id) && isConstellation(effect2.constellation);
        }
        return false;
    }

    public boolean isConstellation(IConstellation constellation){
        return constellation.equals(this.constellation);
    }

    @Nullable
    protected PlayerEntity getKilledPlayer(DamageSource source){
        if(source.getDirectEntity() instanceof PlayerEntity){
            return (PlayerEntity) source.getDirectEntity();
        }
        if(EWDamageSource.isReallyDamage(source)){
            EWDamageSource source1 = (EWDamageSource) source;
            return source1.getPlayer();
        }
        return null;
    }
}
