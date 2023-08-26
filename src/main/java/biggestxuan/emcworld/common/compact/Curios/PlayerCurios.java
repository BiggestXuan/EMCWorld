package biggestxuan.emcworld.common.compact.Curios;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/25
 */

import biggestxuan.emcworld.common.registry.EWItems;
import journeymap.client.ui.component.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.ArrayList;
import java.util.List;

public class PlayerCurios {
    public static List<SlotResult> getPlayerCurios(PlayerEntity player,Item item){
         return CuriosApi.getCuriosHelper().findCurios(player,item);
    }

    public static List<ItemStack> getPlayerAllEMCStoredTotem(PlayerEntity player){
        List<Item> allStoredTotem = new ArrayList<>();
        allStoredTotem.add(EWItems.BASE_EMC_STORED_TOTEM.get());
        allStoredTotem.add(EWItems.ADVANCED_EMC_STORED_TOTEM.get());
        allStoredTotem.add(EWItems.ELITE_EMC_STORED_TOTEM.get());
        allStoredTotem.add(EWItems.ULTIMATE_EMC_STORED_TOTEM.get());
        List<ItemStack> list = new ArrayList<>();
        List<SlotResult> sr = new ArrayList<>();
        for(Item item:allStoredTotem){
            sr.addAll(getPlayerCurios(player,item));
        }
        for(SlotResult s:sr){
            ItemStack itemStack = s.getStack();
            list.add(itemStack);
        }
        return list;
    }

    public static ItemStack getPlayerNuclearBall(PlayerEntity player){
        ItemStack stack;
        List<SlotResult> sr = new ArrayList<>();
        sr.addAll(getPlayerCurios(player, EWItems.NUCLEAR_BALL.get()));
        sr.addAll(getPlayerCurios(player, EWItems.NUCLEAR_ADVANCED_BALL.get()));
        sr.addAll(getPlayerCurios(player, EWItems.NUCLEAR_EPIC_BALL.get()));
        if(sr.size() == 0) return ItemStack.EMPTY;
        stack = sr.get(0).getStack();
        return stack;
    }

    public static ItemStack getStack(PlayerEntity player,RegistryObject<Item> object){
        List<SlotResult> results = getPlayerCurios(player,object.get());
        return results.size() == 0 ? ItemStack.EMPTY : results.get(0).getStack();
    }

    public static ItemStack getPlayerEMCShield(PlayerEntity player){
        return getStack(player,EWItems.EMC_SHIELD_SUPPLY);
    }

    public static ItemStack getPlayerExceptionApple(PlayerEntity player){
        return getStack(player,EWItems.EXCEPTION_APPLE);
    }

    public static long getPlayerAdditionEMC(PlayerEntity player){
        long emc = 0L;
        for(ItemStack itemStack : getPlayerAllEMCStoredTotem(player)){
            emc+=Math.max(itemStack.getMaxDamage()-itemStack.getDamageValue(),0);
        }
        return emc;
    }

    public static long costTotem(PlayerEntity player,long cost){
        long willCost = cost;
        for(ItemStack itemStack:getPlayerAllEMCStoredTotem(player)){
            long had = itemStack.getMaxDamage() - itemStack.getDamageValue();
            if(had > willCost){
                itemStack.setDamageValue((int) (itemStack.getDamageValue() + willCost));
                return 0L;
            }
            willCost -= had;
            itemStack.setCount(0);
        }
        return willCost;
    }

    public static boolean hasExorcismCandle(PlayerEntity player){
        return !getStack(player,EWItems.EXORCISM_CANDLE).equals(ItemStack.EMPTY);
    }
}
