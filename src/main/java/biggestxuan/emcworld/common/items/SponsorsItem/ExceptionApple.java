package biggestxuan.emcworld.common.items.SponsorsItem;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.item.ISponsorItem;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.items.EWItem;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.Sponsors.Sponsors;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/02
 */

@EMCWorldSince("0.9.0")
public class ExceptionApple extends EWItem implements ISponsorItem {
    public ExceptionApple(){
        super(1);
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.exception_apple"));
    }

    @Override
    public boolean isFoil(ItemStack p_77636_1_) {
        return true;
    }

    @Override
    public ITextComponent getName(ItemStack p_200295_1_) {
        TextComponent component = (TextComponent) super.getName(p_200295_1_);
        return component.setStyle(Style.EMPTY.withColor(Color.fromRgb(0xff0000)));
    }

    @Nullable
    @Override
    @EMCWorldSince("1.0.0")
    public Sponsors getSponsor() {
        return Sponsors.all.xiangshushumiao.getSponsors();
    }

    public void WhenPlayerEatOtherFood(PlayerEntity player){
        long emc = EMCHelper.getPlayerEMC(player);
        CooldownTracker cd = player.getCooldowns();
        if(!cd.isOnCooldown(this)){
            if(emc <= 1000000){
                player.heal(3F);
                cd.addCooldown(this,60);
            }else if(emc <= 10000000){
                player.heal(6F);
                player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE,200,1));
                player.addEffect(new EffectInstance(Effects.ABSORPTION,200,1));
                cd.addCooldown(this,200);
                EMCHelper.modifyPlayerEMC(player,new EMCSource.ExceptionAppleEMCSource(-8192,player));
            }else {
                player.heal(15F);
                player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE,400,2));
                player.addEffect(new EffectInstance(Effects.ABSORPTION,1200,4));
                player.addEffect(new EffectInstance(Effects.LUCK,1200,1));
                cd.addCooldown(this,1200);
                EMCHelper.modifyPlayerEMC(player,new EMCSource.ExceptionAppleEMCSource(-65536,player));
            }
        }
    }
}
