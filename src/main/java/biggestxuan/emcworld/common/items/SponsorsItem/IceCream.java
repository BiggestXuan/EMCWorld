package biggestxuan.emcworld.common.items.SponsorsItem;

import biggestxuan.emcworld.api.item.ISponsorItem;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.entity.IceCreamEntity;
import biggestxuan.emcworld.common.items.EWItem;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import biggestxuan.emcworld.common.registry.EWEntities;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.Sponsors.Sponsors;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/09/14
 */

public class IceCream extends EWItem implements ISponsorItem {
    public static final Food ICE_FOOD = new Food.Builder().saturationMod(20f).nutrition(2).build();

    public IceCream(){
        super(new Properties().stacksTo(1).tab(EWCreativeTabs.EW_CREATIVE_TAB).food(ICE_FOOD));
    }

    @Nullable
    @Override
    public Sponsors getSponsor() {
        return Sponsors.all.SKY_LIN.getSponsors();
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        super.use(world, player, hand);
        ItemStack stack = player.getItemInHand(hand);
        if(!world.isClientSide && player.isShiftKeyDown()){
            IceCreamEntity entity = new IceCreamEntity(player,world);
            entity.shootFromRotation(player, player.xRot, player.yRot, 0.0F, 1.5F, 1.0F);
            world.addFreshEntity(entity);
            //player.getCooldowns().addCooldown(this,600);
            long emc = (long) (-10000000 * MathUtils.getCommonBaseCost(player) * ConfigManager.DIFFICULTY.get());
            EMCHelper.modifyPlayerEMC(player,new EMCSource.IceCreamSource(emc,player));
        }
        return ActionResult.fail(stack);
    }
}
