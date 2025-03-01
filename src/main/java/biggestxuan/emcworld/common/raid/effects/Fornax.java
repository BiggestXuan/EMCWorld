package biggestxuan.emcworld.common.raid.effects;

import biggestxuan.emcworld.common.events.PlayerEvent.PlayerTickEvent;
import biggestxuan.emcworld.common.raid.RaidEffect;
import biggestxuan.emcworld.common.utils.RaidUtils;
import hellfirepvp.astralsorcery.common.lib.ConstellationsAS;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.raid.Raid;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/12
 */
public class Fornax extends RaidEffect {
    public Fornax(@Nonnull Raid raid) {
        super(raid,"fornax", ConstellationsAS.fornax);
    }

    @Override
    public void tick() {
        if(world.getGameTime() % 300 == 0){
            RaidUtils utils = new RaidUtils(raid);
            List<LivingEntity> list = getPlayerListByRaidInfo(RaidUtils.getRaidAllPlayers(raid));
            list.addAll(utils.getVillager());
            list.addAll(utils.getRaids());
            int index = list.size() - 1;
            if(index < 0){
                return;
            }
            for (int i = 1; i < 5; i++) {
                int ix = Math.max(0,index / i);
                LivingEntity entity = list.get(ix);
                entity.setSecondsOnFire(hasAlcara ? 1 : 5);
            }
        }
    }

    @Override
    public float onPlayerHurt(PlayerEntity player, DamageSource source, float amount) {
        return source.isFire() ? 0 : amount;
    }

    public static List<LivingEntity> getPlayerListByRaidInfo(List<PlayerTickEvent.RaidInfo> list){
        List<LivingEntity> list1 = new ArrayList<>();
        list.forEach(e -> list1.add(e.getPlayer()));
        return list1;
    }
}
