package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.utils.MathUtils;
import divinerpg.enums.BulletType;
import divinerpg.items.base.ItemModRanged;
import divinerpg.items.iceika.ItemMusicShooter;
import divinerpg.registries.ItemRegistry;
import divinerpg.util.LocalizeUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.Inject;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/26
 */

@Mixin(ItemMusicShooter.class)
public abstract class ItemMusicShooterMixin extends ItemModRanged implements IPrefixItem, IUpgradeableItem {
    public ItemMusicShooterMixin(String name, EntityType<?> entityType, BulletType bulletType, SoundEvent sound, SoundCategory soundCategory, int maxDamage, int delay, Supplier<Item> ammoSupplier, int arcanaConsuming, ItemGroup tab) {
        super(name, entityType, bulletType, sound, soundCategory, maxDamage, delay, ammoSupplier, arcanaConsuming, tab);
    }

    @Override
    public int getMaxLevel() {
        return (int) (8 * ConfigManager.DIFFICULTY.get() / 3d);
    }

    /**
     * @author Biggest_Xuan
     * @reason Modify tool tip.
     */
    @OnlyIn(Dist.CLIENT)
    @Override
    @Overwrite(remap = false)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (this.equals(ItemRegistry.soundOfCarols)) {
            tooltip.add(EMCWorld.tc("tooltip.emcworld.weapon_god_addition_damage",f(MathUtils.getMusicShooterDamage(16, stack))));
        } else {
            tooltip.add(EMCWorld.tc("tooltip.emcworld.weapon_god_addition_damage",f(MathUtils.getMusicShooterDamage(10, stack))));
        }
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ActionResult<ItemStack> result = super.use(world,player,hand);
        player.getCooldowns().addCooldown(this,(int) (6*ConfigManager.DIFFICULTY.get()));
        return result;
    }

    private static String f(double o){
        return String.format("%.2f",o);
    }
}
