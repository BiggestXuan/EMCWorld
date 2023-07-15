package biggestxuan.emcworld.common.items;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/22
 */

import biggestxuan.emcworld.common.compact.Projecte.itf.ICollectorLifeSpan;
import biggestxuan.emcworld.common.recipes.MatterFixRecipe;
import dev.latvian.mods.projectex.Matter;
import dev.latvian.mods.projectex.block.CollectorBlock;
import dev.latvian.mods.projectex.block.entity.CollectorBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class MatterItem extends FinalItem{
    private final Matter matter;

    public MatterItem(Matter matter,double difficulty){
        super(difficulty);
        this.matter = matter;
    }

    public MatterItem(Matter matter) {
        this(matter,0.5);
    }

    @Nonnull
    @Override
    public Rarity getRarity(ItemStack stack){
        return Rarity.COMMON;
    }

    @Nonnull
    @Override
    public ActionResultType useOn(ItemUseContext context){
        PlayerEntity player = context.getPlayer();
        if(player != null){
            World world = context.getLevel();
            BlockPos pos = context.getClickedPos();
            if(!world.isClientSide){
                TileEntity tile = world.getBlockEntity(pos);
                if(tile instanceof CollectorBlockEntity && tile instanceof ICollectorLifeSpan && world.getBlockState(pos).getBlock() instanceof CollectorBlock){
                    CollectorBlock block = (CollectorBlock) world.getBlockState(pos).getBlock();
                    ICollectorLifeSpan lifeSpan = (ICollectorLifeSpan) tile;
                    if(block.matter == this.matter){
                        ItemStack stack = context.getItemInHand();
                        MatterFixRecipe recipe = MatterFixRecipe.getRecipe(stack);
                        if(recipe != null){
                            context.getItemInHand().shrink(recipe.getCount());
                            lifeSpan.setLifeSpan(Math.max(0,lifeSpan.getLifeSpan()-(int) (lifeSpan.getMaxLifeSpan() * recipe.getRate())));
                            return ActionResultType.SUCCESS;
                        }
                    }
                }
            }
        }
        return ActionResultType.PASS;
    }
}
