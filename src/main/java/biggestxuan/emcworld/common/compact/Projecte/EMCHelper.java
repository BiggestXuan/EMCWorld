package biggestxuan.emcworld.common.compact.Projecte;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/25
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.client.Message;
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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import org.openzen.zencode.java.ZenCodeType;

import java.math.BigInteger;
import java.util.Set;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.EMCHelper")
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
    @ZenCodeType.Method
    public static void modifyPlayerEMC(PlayerEntity player,long emc,boolean showMessage){
        long modify = emc;
        if(player.hasEffect(EWEffects.EMC_PROTECT.get()) && modify < 0){
            modify *= 0.9d;
            modify = Math.round(modify);
        }
        if(showMessage){
            if(modify < 0){
                Message.LossEMCMessage(player,Math.negateExact(modify));
            }
            else {
                Message.addEMCMessage(player,modify);
            }
        }
        if(emc<0){
            MinecraftForge.EVENT_BUS.post(new PlayerCostEMCEvent(player,emc));
            modify = Math.negateExact(PlayerCurios.costTotem(player,Math.negateExact(emc)));
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
}
