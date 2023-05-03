package biggestxuan.emcworld.common.compact.Champions.Affix;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/15
 */

import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.entity.LivingEntity;
import top.theillusivec4.champions.api.AffixCategory;
import top.theillusivec4.champions.api.IChampion;
import top.theillusivec4.champions.common.affix.core.BasicAffix;
import top.theillusivec4.champions.common.capability.ChampionCapability;

public class HealingAffix extends BasicAffix {
    public HealingAffix() {
        super("healing",AffixCategory.CC);
    }

    @Override
    public void onUpdate(IChampion champion) {
        LivingEntity living = champion.getLivingEntity();
        if(!living.level.isClientSide && living.level.getDayTime() % 300 == 0){
            ChampionCapability.getCapability(living).ifPresent(c -> c.getServer().getRank().ifPresent(r -> living.heal(living.getMaxHealth() * 0.015f * r.getTier() * Math.round(ConfigManager.DIFFICULTY.get()))));
        }
    }
}
