package biggestxuan.emcworld.api.event;

import biggestxuan.emcworld.api.EMCWorldSince;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/02
 */

@EMCWorldSince("0.9.0")
@Cancelable
public class PlayerEatFoodEvent extends PlayerEvent {
    private final int level;
    private final float saturation;
    private final Food food;
    private final ItemStack stack;

    public PlayerEatFoodEvent(PlayerEntity player,int level,float saturation,Food food,ItemStack stack) {
        super(player);
        this.level = level;
        this.saturation = saturation;
        this.food = food;
        this.stack = stack;
    }

    public ItemStack getStack() {
        return stack;
    }

    public int getLevel() {
        return level;
    }

    public float getSaturation() {
        return saturation;
    }

    public Food getFood() {
        return food;
    }
}
