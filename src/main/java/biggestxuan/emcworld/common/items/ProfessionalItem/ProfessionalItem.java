package biggestxuan.emcworld.common.items.ProfessionalItem;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/31
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.common.skill.PlayerSkillModify;
import biggestxuan.emcworld.common.items.EWItem;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ProfessionalItem extends EWItem {
    private final int profession;

    public ProfessionalItem(int p){
        profession = p;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> use(@Nonnull World p_77659_1_, @Nonnull PlayerEntity p_77659_2_, @Nonnull Hand p_77659_3_){
        ItemStack stack = p_77659_2_.getItemInHand(p_77659_3_);
        IPlayerSkillCapability cap = MathUtils.getPlayerLevelCapability(p_77659_2_);
        if(p_77659_3_.equals(Hand.OFF_HAND) || cap.getProfession() !=0) return ActionResult.fail(stack);
        stack.shrink(1);
        cap.setProfession(profession);
        cap.setMaxLevel(10);
        cap.setModify(0);
        if(profession == 1){
            PlayerSkillModify.default1_skill0(p_77659_2_);
        }
        if(profession == 2){
            PlayerSkillModify.default2_skill0(p_77659_2_);
        }
        if(profession == 3){
            PlayerSkillModify.default3_skill0(p_77659_2_);
        }
        if(profession == 4){
            PlayerSkillModify.default4_skill0(p_77659_2_);
        }
        if(profession == 5){
            PlayerSkillModify.default5_skill0(p_77659_2_);
        }
        if(profession == 6){
            PlayerSkillModify.default6_skill0(p_77659_2_);
        }
        return ActionResult.consume(stack);
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, @Nonnull List<ITextComponent> p_77624_3_, @Nonnull ITooltipFlag p_77624_4_){
        p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.profession_item", I18n.get("profession.emcworld."+profession)));
    }
}
