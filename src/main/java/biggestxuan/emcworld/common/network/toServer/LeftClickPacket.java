package biggestxuan.emcworld.common.network.toServer;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/04
 */

import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.item.equipment.weapon.BaseEMCGodSword;
import biggestxuan.emcworld.common.compact.Mekanism.MekUtils;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.exception.EMCWorldIllegalPacketException;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Sword.InfinitySword;
import biggestxuan.emcworld.common.items.SponsorsItem.IceCream;
import biggestxuan.emcworld.common.registry.EWDamageSource;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.SkillUtils;
import hellfirepvp.astralsorcery.common.util.CelestialStrike;
import hellfirepvp.astralsorcery.common.util.data.Vector3;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

import static biggestxuan.emcworld.common.events.PlayerEvent.PlayerAttackEvent.getNearEntity;

public class LeftClickPacket {
    public static void encode(LeftClickPacket msg, PacketBuffer buf) {}

    public static LeftClickPacket decode(PacketBuffer buf) {
        return new LeftClickPacket();
    }

    public static void handle(LeftClickPacket msg, Supplier<NetworkEvent.Context> ctx){
        if (ctx.get().getDirection().getReceptionSide().isServer()) {
            if(ctx.get().getSender().getAttackStrengthScale(0) == 1){
                ctx.get().enqueueWork(() -> (
                        (BaseEMCGodSword) EWItems.ICE_SWORD.get()).spawnManaBurst(ctx.get().getSender()));
            }
            try{
                ServerPlayerEntity player = ctx.get().getSender();
                ItemStack stack = ctx.get().getSender().getMainHandItem();
                if(stack.getItem() instanceof InfinitySword || MekUtils.isInfinityMekaTool(stack)){
                    ctx.get().enqueueWork(()-> {
                        List<? extends LivingEntity> canRangeAttack = getNearEntity(ctx.get().getSender(),ctx.get().getSender(),64);
                        for(LivingEntity entity:canRangeAttack){
                            CelestialStrike.play(player, player.getLevel(), Vector3.atEntityCorner(entity), Vector3.atEntityCorner(entity));
                            entity.hurt(new EWDamageSource(ctx.get().getSender()).REALLY_PLAYER,114514);
                        }
                    });
                }
                if(stack.getItem() instanceof IceCream && player.isShiftKeyDown()){
                    ctx.get().enqueueWork(()->{
                        var cap = EMCWorldAPI.getInstance().getPlayerSkillCapability(player);
                        int level = cap.getLevel() / 15;
                        long emc = (long) (MathUtils.getCommonBaseCost(player) * ConfigManager.DIFFICULTY.get() * 100000);
                        if(EMCHelper.getPlayerEMC(player) >= emc){
                            SkillUtils.addEffect(player, Effects.DAMAGE_BOOST,1200,level);
                            SkillUtils.addEffect(player, Effects.ABSORPTION,1200,level);
                            EMCHelper.modifyPlayerEMC(player,new EMCSource.IceCreamSource(-emc,player));
                        }
                    });
                }
            }catch (NullPointerException e){
                throw new EMCWorldIllegalPacketException(msg);
            }

        }
        ctx.get().setPacketHandled(true);
    }
}
