package biggestxuan.emcworld.common.utils;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/24
 */

import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.capabilities.IAlchBagProvider;
import net.darkhax.itemstages.Restriction;
import net.darkhax.itemstages.RestrictionManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UnknownPouchUtils {
    private static IItemHandler getItems(PlayerEntity player){
        Optional<IAlchBagProvider> provider = player.getCapability(ProjectEAPI.ALCH_BAG_CAPABILITY).resolve();
        return provider.map(i -> i.getBag(DyeColor.YELLOW)).orElseGet(() -> new IItemHandler() {
            @Override
            public int getSlots() {
                return 0;
            }

            @Nonnull
            @Override
            public ItemStack getStackInSlot(int slot) {
                return ItemStack.EMPTY;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                return ItemStack.EMPTY;
            }

            @Nonnull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                return ItemStack.EMPTY;
            }

            @Override
            public int getSlotLimit(int slot) {
                return 0;
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return false;
            }
        });
    }

    public static ItemStack putItem(PlayerEntity player, ItemStack stack){
        IItemHandler items = getItems(player);
        for (int i = 0; i < items.getSlots(); i++) {
            if(stack.equals(ItemStack.EMPTY)){
                return ItemStack.EMPTY;
            }
            stack = items.insertItem(i,stack,false);
        }
        return stack;
    }

    public static List<ItemStack> receiveItem(PlayerEntity player){
        IItemHandler items = getItems(player);
        List<ItemStack> list = new ArrayList<>();
        for (int i = 0; i < items.getSlots(); i++) {
            ItemStack stack = items.getStackInSlot(i);
            Restriction restriction = RestrictionManager.INSTANCE.getRestriction(player, stack);
            if (restriction != null && restriction.shouldPreventPickup()) {
                continue;
            }
            list.add(items.extractItem(i,stack.getCount(),false));
        }
        return list;
    }

    public static int getCount(PlayerEntity player){
        int count = 0;
        IItemHandler handler = getItems(player);
        for (int i = 0; i < handler.getSlots(); i++) {
            ItemStack stack = handler.getStackInSlot(i);
            Restriction restriction = RestrictionManager.INSTANCE.getRestriction(player, stack);
            if (!(restriction != null && restriction.shouldPreventPickup()) && !stack.equals(ItemStack.EMPTY) && !stack.getItem().equals(Items.AIR)) {
                count++;
            }
        }
        return count;
    }
}
