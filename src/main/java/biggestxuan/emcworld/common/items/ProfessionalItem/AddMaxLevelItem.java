package biggestxuan.emcworld.common.items.ProfessionalItem;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/07
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.items.EWItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class AddMaxLevelItem extends EWItem {
    private final int maxLevel;
    private final int modify;

    public AddMaxLevelItem(int maxLevel,int modify){
        this.maxLevel = maxLevel;
        this.modify = modify;
    }

    public int getMaxLevel(){
        return this.maxLevel;
    }

    public int getModify() {
        return this.modify;
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, @Nonnull List<ITextComponent> p_77624_3_,@Nonnull ITooltipFlag p_77624_4_) {
        if(maxLevel == 110){
            p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.maxlevel",this.maxLevel));
        }
        else{
            p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.maxlevel_item",this.maxLevel));
        }
        if(Minecraft.getInstance().player == null) return;
        int profession = Minecraft.getInstance().player.getCapability(EMCWorldCapability.PLAYER_LEVEL).orElseThrow(NullPointerException::new).getProfession();
        if(this.modify != 0){
            p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.changeprofession"));
            if(profession == 1 && this.modify == 1){
                p_77624_3_.add(EMCWorld.tc("profession.emcworld.modify_kill"));
            }
            if(profession == 1 && this.modify == 2){
                p_77624_3_.add(EMCWorld.tc("profession.emcworld.modify_blood"));
            }
            if(profession == 2 && this.modify == 1){
                p_77624_3_.add(EMCWorld.tc("profession.emcworld.modify_shied"));
            }
            if(profession == 2 && this.modify == 2){
                p_77624_3_.add(EMCWorld.tc("profession.emcworld.modify_addon"));
            }
            if(profession == 3 && this.modify == 1){
                p_77624_3_.add(EMCWorld.tc("profession.emcworld.modify_big_wizard"));
            }
            if(profession == 3 && this.modify == 2){
                p_77624_3_.add(EMCWorld.tc("profession.emcworld.modify_protect_wizard"));
            }
        }
    }

    @Override
    public boolean isFireResistant() {
        return true;
    }
}
