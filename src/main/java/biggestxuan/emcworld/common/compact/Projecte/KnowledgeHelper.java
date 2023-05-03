package biggestxuan.emcworld.common.compact.Projecte;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/14
 */

import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.capabilities.IAlchBagProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KnowledgeHelper {
    public static void addPlayerKnowledge(PlayerEntity player, ItemStack item){
        EMCHelper.getPlayerIKP(player).addKnowledge(item);
        EMCHelper.flushPlayer(player);
    }

    public static List<ItemStack> getAlchemyBag(PlayerEntity player,DyeColor color){
        List<ItemStack> out = new ArrayList<>();
        Optional<IAlchBagProvider> provider = player.getCapability(ProjectEAPI.ALCH_BAG_CAPABILITY).resolve();
        if(provider.isPresent()){
            IAlchBagProvider provider1 = provider.get();
            IItemHandler handler = provider1.getBag(color);
            for (int i = 0; i < 104; i++) {
                ItemStack stack = handler.getStackInSlot(i);
                if(stack.equals(ItemStack.EMPTY)) continue;
                out.add(stack);
            }
        }
        return out;
    }

    public static boolean itemInAlchemyBag(ItemStack stack,PlayerEntity player,DyeColor color,boolean nbt){
        for(ItemStack i : getAlchemyBag(player,color)){
            if(i.getItem().equals(stack.getItem())){
                if(!nbt){
                    return true;
                }else{
                    if(!stack.hasTag() && !i.hasTag()){
                        return true;
                    }
                    if(stack.hasTag() && i.hasTag()){
                        if(stack.getOrCreateTag().equals(i.getOrCreateTag())){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
