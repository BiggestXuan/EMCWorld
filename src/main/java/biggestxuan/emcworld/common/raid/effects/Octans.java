package biggestxuan.emcworld.common.raid.effects;

import biggestxuan.emcworld.common.raid.RaidEffect;
import biggestxuan.emcworld.common.utils.CommandUtils;
import hellfirepvp.astralsorcery.common.lib.ConstellationsAS;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.raid.Raid;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/12
 */
public class Octans extends RaidEffect {
    public Octans(@Nonnull Raid raid) {
        super(raid,"octans", ConstellationsAS.octans);
    }

    @Override
    public void tick() {
        if(world.getGameTime() % 500 == 0){
            ServerWorld world1 = (ServerWorld) world;
            world1.setWeatherParameters(0,1200,true,true);
        }
    }

    @Override
    public float onPlayerAttack(PlayerEntity player, Entity target, float amount) {
        float rate = hasAlcara ? 0.98F : 0.8F;
        return lossDamage(player) ? amount * rate : rate;
    }

    @Override
    public float onPlayerHeal(PlayerEntity player, float amount) {
        float rate = hasAlcara ? 1.01F : 1.1F;
        return amount * rate;
    }

    private boolean lossDamage(PlayerEntity player){
        return world.isRainingAt(player.blockPosition()) && playerCanSeeSky(player);
    }

    private boolean playerCanSeeSky(PlayerEntity player){
        BlockPos pos = player.blockPosition();
        int y = pos.getY() + 2;
        for(int i = y; i < 255; i++){
            BlockState blockState = world.getBlockState(new BlockPos(pos.getX(), i, pos.getZ()));
            if(!blockState.getBlock().equals(Blocks.AIR)){
                return false;
            }
        }
        return true;
    }
}
