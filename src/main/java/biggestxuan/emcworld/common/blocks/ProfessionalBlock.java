package biggestxuan.emcworld.common.blocks;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/31
 */

import biggestxuan.emcworld.client.screen.ProfessionGUI;
import biggestxuan.emcworld.common.capability.PlayerLevel.PlayerLevelCapability;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

import javax.annotation.Nonnull;

public class ProfessionalBlock extends EWBlock {

    public ProfessionalBlock() {
        super(10.0F);
    }

    @Nonnull
    @Override
    public ActionResultType use(@Nonnull BlockState p_225533_1_, @Nonnull World p_225533_2_, @Nonnull BlockPos p_225533_3_, @Nonnull PlayerEntity p_225533_4_, @Nonnull Hand p_225533_5_, @Nonnull BlockRayTraceResult p_225533_6_) {
        if(p_225533_2_.isClientSide){
            DistExecutor.unsafeCallWhenOn(Dist.CLIENT,()-> ()-> new ProfessionGUI.open((PlayerLevelCapability) MathUtils.getPlayerLevelCapability(p_225533_4_)));
        }
        return ActionResultType.SUCCESS;
    }
}
