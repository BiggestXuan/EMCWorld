package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/08
 */

import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ArrowItem.class)
public abstract class ArrowItemMixin extends Item {

    public ArrowItemMixin(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    /***
     * @author Biggest_Xuan
     * @reason Add addition damage.
     */
    @Overwrite
    public AbstractArrowEntity createArrow(World p_200887_1_, ItemStack p_200887_2_, LivingEntity p_200887_3_) {
        ArrowEntity arrowentity = new ArrowEntity(p_200887_1_, p_200887_3_);
        arrowentity.setEffectsFromItem(p_200887_2_);
        if(p_200887_3_ instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) p_200887_3_;
            ItemStack stack = player.getProjectile(p_200887_2_);
            arrowentity.setBaseDamage(arrowentity.getBaseDamage()+MathUtils.getBowAdditionDamage(stack));
        }
        return arrowentity;
    }
}
