package biggestxuan.emcworld.common.compact.Champions.Affix;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/14
 */

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import top.theillusivec4.champions.api.AffixCategory;
import top.theillusivec4.champions.api.IChampion;
import top.theillusivec4.champions.common.affix.core.BasicAffix;
import top.theillusivec4.champions.common.rank.Rank;

import java.util.Optional;

public abstract class BaseEMCNegAffix extends BasicAffix {
    private final Effect effect;

    public BaseEMCNegAffix(String id, AffixCategory category, Effect effect) {
        super(id, category);
        this.effect = effect;
    }

    @Override
    public boolean onAttack(IChampion champion, LivingEntity target, DamageSource source, float amount) {
        if(!champion.getLivingEntity().level.isClientSide){
            Optional<Rank> rank = champion.getServer().getRank();
            int time = 25 * 20 * rank.map(Rank::getTier).orElse(1);
            int level = Math.round(0.5F * rank.map(Rank::getTier).orElse(0));
            target.addEffect(new EffectInstance(effect,time,level));
        }
        return true;
    }
}
