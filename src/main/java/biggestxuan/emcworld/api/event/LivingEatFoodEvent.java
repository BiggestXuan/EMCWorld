package biggestxuan.emcworld.api.event;

import biggestxuan.emcworld.api.EMCWorldSince;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/02
 */

@EMCWorldSince("0.9.0")
public class LivingEatFoodEvent extends LivingEvent {
    private final Food food;
    private final ItemStack stack;

    public LivingEatFoodEvent(LivingEntity living, Food food, ItemStack stack) {
        super(living);
        this.food = food;
        this.stack = stack;
    }

    public ItemStack getStack() {
        return stack;
    }

    public int getLevel() {
        return food.getNutrition();
    }

    public float getSaturation() {
        return food.getSaturationModifier();
    }

    public Food getFood() {
        return food;
    }
}
