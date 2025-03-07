package biggestxuan.emcworld.common.compact.Projecte;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/25
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.event.PlayerModifyEMCEvent;
import biggestxuan.emcworld.common.compact.Curios.PlayerCuriosUtils;
import biggestxuan.emcworld.common.raid.RaidEffectExecutor;
import biggestxuan.emcworld.common.registry.EWEffects;
import biggestxuan.emcworld.common.utils.CommandUtils;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.EMCLog.EMCWriter;
import biggestxuan.emcworld.common.utils.Message;
import biggestxuan.emcworld.common.utils.Network.OfficialServerNetWork;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import moze_intel.projecte.api.ItemInfo;
import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
import moze_intel.projecte.api.nss.NSSItem;
import moze_intel.projecte.integration.crafttweaker.actions.CustomEMCAction;
import net.minecraft.advancements.Advancement;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.raid.Raid;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import org.openzen.zencode.java.ZenCodeType;

import java.math.BigInteger;
import java.util.Set;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.EMCHelper")
@SuppressWarnings("unused")
public class EMCHelper {
    public static IKnowledgeProvider getPlayerIKP(PlayerEntity player){
        return ProjectEAPI.getTransmutationProxy().getKnowledgeProviderFor(player.getUUID());
    }

    public static void flushPlayer(PlayerEntity player){
        if(player instanceof ServerPlayerEntity){
            getPlayerIKP(player).sync((ServerPlayerEntity) player);
            getPlayerIKP(player).syncEmc((ServerPlayerEntity) player);
        }
    }

    @ZenCodeType.Method
    public static long getPlayerEMC(PlayerEntity player){
        BigInteger emc = getPlayerIKP(player).getEmc();
        if(emc.longValue() >= EMCWorld.MAX_EMC){
            setPlayerEMC(player, EMCWorld.MAX_EMC);
            return EMCWorld.MAX_EMC;
        }
        return emc.longValue()+ PlayerCuriosUtils.getPlayerAdditionEMC(player);
    }

    public static long getPlayerActEMC(PlayerEntity player){
        BigInteger emc = getPlayerIKP(player).getEmc();
        if(emc.longValue() >= EMCWorld.MAX_EMC){
            setPlayerEMC(player, EMCWorld.MAX_EMC);
            return EMCWorld.MAX_EMC;
        }
        return emc.longValue();
    }

    @EMCWorldSince("0.9.0")
    public static void modifyPlayerEMC(PlayerEntity player, EMCSource<?> source){
        modifyPlayerEMC(player,source,true);
    }

    public static void modifyPlayerEMC(PlayerEntity player, EMCSource<?> source, boolean showMessage){
        long modify = source.emc();
        if(modify == 0){
            return;
        }
        if(player.hasEffect(EWEffects.EMC_PROTECT.get()) && modify < 0){
            modify *= 0.9d;
            modify = Math.round(modify);
        }
        if(player.hasEffect(EWEffects.EMC_BROKEN.get()) && modify < 0){
            int level = player.getEffect(EWEffects.EMC_BROKEN.get()).getAmplifier() + 1;
            modify *= level;
        }
        source.setEmc(modify);
        PlayerModifyEMCEvent event = new PlayerModifyEMCEvent(player,source);
        if(!MinecraftForge.EVENT_BUS.post(event)) {
            modify = event.getSource().emc();
            if (modify < 0) {
                triggerAdvancement(player, modify);
                modify = Math.negateExact(PlayerCuriosUtils.costTotem(player, Math.negateExact(modify)));
            }
            if(player instanceof ServerPlayerEntity){
                ServerPlayerEntity playerEntity = (ServerPlayerEntity) player;
                ServerWorld world = playerEntity.getLevel();
                BlockPos pos = playerEntity.blockPosition();
                if(world.isRaided(pos)){
                    Raid raid = world.getRaidAt(pos);
                    if(raid != null){
                        modify = new RaidEffectExecutor(raid).onPlayerEMCCost(player,source).emc();
                        source.setEmc(modify);
                    }
                }
                //OfficialServerNetWork.writePlayerNetWorkLog(player, source);
                EMCWriter.WriteEMCLog(playerEntity, source);
            }
            if(showMessage){
                if(modify < 0){
                    Message.LossEMCMessage(player,Math.negateExact(modify));
                }
                else {
                    Message.addEMCMessage(player,modify);
                }
            }
            long afterEMC = getPlayerActEMC(player) + modify;
            getPlayerIKP(player).setEmc(BigInteger.valueOf(Math.max(0, afterEMC)));
            flushPlayer(player);
        }
    }

    @ZenCodeType.Method
    public static void clearPlayerEMC(PlayerEntity player){
        getPlayerIKP(player).setEmc(BigInteger.valueOf(0));
        flushPlayer(player);
    }

    @ZenCodeType.Method
    public static void setItemEMC(ItemStack item, long emc){
        CraftTweakerAPI.apply(new CustomEMCAction(NSSItem.createItem(item),emc));
    }

    @ZenCodeType.Method
    public static void setPlayerEMC(PlayerEntity player,long emc){
        getPlayerIKP(player).setEmc(BigInteger.valueOf(emc));
    }

    @ZenCodeType.Method
    public static void clearItemEMC(IItemStack item){
        setItemEMC(item.getInternal(),0);
    }

    public static long getItemEMC(ItemStack stack){
        return moze_intel.projecte.utils.EMCHelper.getEmcValue(stack);
    }

    public static Set<ItemInfo> getPlayerAllKnowledge(PlayerEntity player){
        return getPlayerIKP(player).getKnowledge();
    }

    private static void triggerAdvancement(PlayerEntity p,long emc){
        if(p instanceof ServerPlayerEntity){
            ServerPlayerEntity player = (ServerPlayerEntity) p;
            emc = - emc;
            if(emc >= 1 && getPlayerEMC(player) >= 1){
                awardAdvancement(player,EMCWorld.rl("cost/1"));
            }
            if(emc >= 1000 && getPlayerEMC(player) >= 1000){
                awardAdvancement(player,EMCWorld.rl("cost/2"));
            }
            if(emc >= 1000000 && getPlayerEMC(player) >= 1000000){
                awardAdvancement(player,EMCWorld.rl("cost/3"));
            }
            if(emc >= 1000000000 && getPlayerEMC(player) >= 1000000000){
                awardAdvancement(player,EMCWorld.rl("cost/4"));
            }
            if(emc >= 1000000000000L && getPlayerEMC(player) >= 1000000000000L){
                awardAdvancement(player,EMCWorld.rl("cost/5"));
            }
            if(emc >= EMCWorld.MAX_EMC - 1L && getPlayerEMC(player) >= EMCWorld.MAX_EMC - 1L){
                awardAdvancement(player,EMCWorld.rl("cost/6"));
            }
        }
    }

    public static boolean hasAdvancement(ServerPlayerEntity player,String s){
        MinecraftServer server = player.getServer();
        if(server == null) return false;
        Advancement adv = server.getAdvancements().getAdvancement(EMCWorld.rl(s));
        if(adv == null){
            return false;
        }
        return player.getAdvancements().getOrStartProgress(adv).isDone();
    }

    public static void awardAdvancement(ServerPlayerEntity player,Advancement adv){
        new CommandUtils(player).awardAdvancement(adv);
    }

    public static void awardAdvancement(ServerPlayerEntity player, ResourceLocation adv){
        awardAdvancement(player,Advancement.Builder.advancement().build(adv));
    }
}
