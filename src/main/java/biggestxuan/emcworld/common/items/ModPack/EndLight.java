package biggestxuan.emcworld.common.items.ModPack;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.api.item.IModpackItem;
import biggestxuan.emcworld.api.item.ISponsorItem;
import biggestxuan.emcworld.api.item.equipment.IGemInlaidItem;
import biggestxuan.emcworld.api.item.equipment.armor.IEMCShieldArmor;
import biggestxuan.emcworld.common.items.Equipment.BaseWeaponGemItem;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.Sponsors.Sponsors;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/11/07
 */

@EMCWorldSince("1.0.7")
public class EndLight extends BaseWeaponGemItem implements IModpackItem, ISponsorItem {
    public EndLight() {
        super(WeaponGem.END_LIGHT);
    }

    @Nullable
    @Override
    public Sponsors getSponsor() {
        return Sponsors.all.CSZXYMZX.getSponsors();
    }

    @Override
    public String getModpackName() {
        return "寄生科技";
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        super.appendHoverText(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        for (int i = 1; i <= 7; i++) {
            Style style = Style.EMPTY.withColor(TextFormatting.DARK_PURPLE);
            if(i <= 2){
                style.withItalic(true);
            }
            p_77624_3_.add(EMCWorld.tc("tooltip.end_light.desc"+i).withStyle(style));
        }
    }

    public static boolean weaponHasEndLight(ItemStack stack){
        if(stack.getItem() instanceof IGemInlaidItem){
            var gemItem = (IGemInlaidItem) stack.getItem();
            return gemItem.getGemIndex(stack) == 50;
        }
        return false;
    }

    public static int playerHasEndLightArmor(PlayerEntity player){
        int c = 0;
        for(ItemStack s : player.inventory.armor){
            if(weaponHasEndLight(s)){
                c++;
            }
        }
        return c;
    }

    public static double getAttackTimeDamageBoost(PlayerEntity player){
        double base = 1;
        if(playerHasEndLightArmor(player) >= 3){
            try{
                var cap = EMCWorldAPI.getInstance().getUtilCapability(player);
                base += MathUtils.log(2,cap.getLastAttackTime()+1) / 100;
                return base;
            }catch (Exception ignored){

            }
        }
        return base;
    }

    public static void triggerParasitism(PlayerEntity player, float damage){
        try{
            if(playerHasEndLightArmor(player) >= 2){
                IUtilCapability cap = EMCWorldAPI.getInstance().getUtilCapability(player);
                if(player.getHealth() / player.getMaxHealth() <= 0.6D || cap.getShield() / cap.getMaxShield() <= 0.2D){
                    player.heal(damage * 0.05F);
                    for(ItemStack s : player.inventory.armor){
                        if(s.getItem() instanceof IEMCShieldArmor){
                            IEMCShieldArmor armor = (IEMCShieldArmor) s.getItem();
                            armor.modifyShield(s,damage * 0.01F / playerHasEndLightArmor(player));
                        }
                    }
                }
            }
        }catch (Exception ignored){

        }
    }
}
