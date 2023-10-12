package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.api.EMCWorldSince;
import dev.latvian.mods.projectex.block.LinkBaseBlock;
import dev.latvian.mods.projectex.block.PersonalLinkBlock;
import dev.latvian.mods.projectex.block.entity.PersonalLinkBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.spongepowered.asm.mixin.Mixin;

import javax.annotation.Nonnull;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/11
 */

@EMCWorldSince("1.0.5")
@Mixin(PersonalLinkBlock.class)
public abstract class PersonalLinkBlockMixin extends LinkBaseBlock {
    @Nonnull
    @Override
    public ActionResultType use(@Nonnull BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand handIn, @Nonnull BlockRayTraceResult hit){
        if(!worldIn.isClientSide){
            PersonalLinkBlockEntity entity = (PersonalLinkBlockEntity) worldIn.getBlockEntity(pos);
            NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) entity,(PacketBuffer p)-> p.writeBlockPos(pos));
        }
        return ActionResultType.SUCCESS;
    }
}
