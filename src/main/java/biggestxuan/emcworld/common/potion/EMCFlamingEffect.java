package biggestxuan.emcworld.common.potion;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/14
 */

import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.awt.*;
import java.util.Random;

public class EMCFlamingEffect extends Effect {
    private static final Random random = new Random();
    public EMCFlamingEffect(){
        super(EffectType.HARMFUL,new Color(0xB00202).getRGB());
    }

    @Override
    public boolean isDurationEffectTick(int p_76397_1_, int p_76397_2_){
        return p_76397_1_ % 200 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity p_76394_1_, int p_76394_2_){
        World world = p_76394_1_.level;
        if(world != null){
            if(!world.isClientSide && p_76394_1_ instanceof PlayerEntity){
                PlayerEntity player = (PlayerEntity) p_76394_1_;
                int level = p_76394_2_ + 1;
                long cost = (long) (EMCHelper.getPlayerEMC(player) * 0.001 * level);
                EMCHelper.modifyPlayerEMC(player,Math.negateExact(cost),true);
            }
            BlockPos pos = p_76394_1_.blockPosition();
            for (int i = 0; i < 15; i++) {
                world.addParticle(ParticleTypes.FLAME, pos.getX() + random.nextDouble(), pos.getY() + random.nextDouble(), pos.getZ() + random.nextDouble(), 0, 0, 0);
            }
        }
    }
}
