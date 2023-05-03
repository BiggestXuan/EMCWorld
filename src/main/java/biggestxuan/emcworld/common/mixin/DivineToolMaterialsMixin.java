package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/28
 */

import divinerpg.util.DivineToolMaterials;
import net.minecraft.item.IItemTier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DivineToolMaterials.class)
public abstract class DivineToolMaterialsMixin implements IItemTier {

    @Shadow(remap = false)
    @Final
    private int maxUses;

    @Shadow(remap = false)
    @Final
    private float attackDamage;

    @Shadow(remap = false)
    @Final
    private int harvestLevel;

    @Shadow(remap = false)
    @Final
    private int enchantability;

    /***
     * @author Biggest_Xuan
     * @reason Shut up your f**king errors!
     */
    @Override
    @Overwrite(remap = false)
    public int getUses() {
        return this.maxUses / 3;
    }

    /***
     * @author Biggest_Xuan
     * @reason Shut up your f**king errors!
     */
    @Override
    @Overwrite(remap = false)
    public float getAttackDamageBonus() {
        return this.attackDamage / 3f;
    }

    /***
     * @author Biggest_Xuan
     * @reason Shut up your f**king errors!
     */
    @Override
    @Overwrite(remap = false)
    public int getLevel() {
        return Math.max(0,harvestLevel-1);
    }

    /***
     * @author Biggest_Xuan
     * @reason Shut up your f**king errors!
     */
    @Override
    @Overwrite(remap = false)
    public int getEnchantmentValue() {
        return this.enchantability / 3;
    }
}
