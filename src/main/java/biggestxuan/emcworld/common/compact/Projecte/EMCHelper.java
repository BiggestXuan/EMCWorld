package biggestxuan.emcworld.common.compact.Projecte;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/25
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.utils.CommandUtils;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.EMCLog.EMCWriter;
import biggestxuan.emcworld.common.utils.Message;
import biggestxuan.emcworld.api.event.PlayerCostEMCEvent;
import biggestxuan.emcworld.common.compact.Curios.PlayerCurios;
import biggestxuan.emcworld.common.registry.EWEffects;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import moze_intel.projecte.api.ItemInfo;
import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
import moze_intel.projecte.api.nss.NSSItem;
import moze_intel.projecte.integration.crafttweaker.actions.CustomEMCAction;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.extensions.IForgeAdvancementBuilder;
import net.minecraftforge.registries.ForgeRegistries;
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
        return emc.longValue()+ PlayerCurios.getPlayerAdditionEMC(player);
    }

    public static long getPlayerActEMC(PlayerEntity player){
        BigInteger emc = getPlayerIKP(player).getEmc();
        if(emc.longValue() >= EMCWorld.MAX_EMC){
            setPlayerEMC(player, EMCWorld.MAX_EMC);
            return EMCWorld.MAX_EMC;
        }
        return emc.longValue();
    }

    public static void modifyPlayerEMC(PlayerEntity player, EMCSource<?> source, boolean showMessage){
        long modify = source.emc();
        if(player.hasEffect(EWEffects.EMC_PROTECT.get()) && modify < 0){
            modify *= 0.9d;
            modify = Math.round(modify);
        }
        if(player.hasEffect(EWEffects.EMC_BROKEN.get()) && modify < 0){
            int level = player.getEffect(EWEffects.EMC_BROKEN.get()).getAmplifier() + 1;
            modify *= level;
        }
        EMCWriter.WriteEMCLog(player,source);
        if(showMessage){
            if(modify < 0){
                Message.LossEMCMessage(player,Math.negateExact(modify));
            }
            else {
                Message.addEMCMessage(player,modify);
            }
        }
        if(modify<0){
            MinecraftForge.EVENT_BUS.post(new PlayerCostEMCEvent(player,modify));
            triggerAdvancement(player,modify);
            modify = Math.negateExact(PlayerCurios.costTotem(player,Math.negateExact(modify)));
        }
        long afterEMC = getPlayerActEMC(player)+modify;
        getPlayerIKP(player).setEmc(BigInteger.valueOf(Math.max(0,afterEMC)));
        flushPlayer(player);
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
            if(emc >= 1){
                awardAdvancement(player,EMCWorld.rl("cost/1"));
            }
            if(emc >= 1000){
                awardAdvancement(player,EMCWorld.rl("cost/2"));
            }
            if(emc >= 1000000){
                awardAdvancement(player,EMCWorld.rl("cost/3"));
            }
            if(emc >= 1000000000){
                awardAdvancement(player,EMCWorld.rl("cost/4"));
            }
            if(emc >= 1000000000000L){
                awardAdvancement(player,EMCWorld.rl("cost/5"));
            }
            if(emc >= EMCWorld.MAX_EMC - 1L){
                awardAdvancement(player,EMCWorld.rl("cost/6"));
            }
        }
    }

    public static void awardAdvancement(ServerPlayerEntity player,Advancement adv){
        new CommandUtils(player).awardAdvancement(adv);
    }

    public static void awardAdvancement(ServerPlayerEntity player, ResourceLocation adv){
        awardAdvancement(player,Advancement.Builder.advancement().build(adv));
    }
}
