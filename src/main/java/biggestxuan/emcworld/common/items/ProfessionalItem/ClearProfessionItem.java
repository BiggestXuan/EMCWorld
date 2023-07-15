package biggestxuan.emcworld.common.items.ProfessionalItem;

import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.common.items.EWItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * EMC WORLD MOD
 * @Author Biggest_Xuan
 * 2023/06/16
 */

public class ClearProfessionItem extends EWItem {
    @Override
    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
        super.use(p_77659_1_, p_77659_2_, p_77659_3_);
        ItemStack stack = p_77659_2_.getItemInHand(p_77659_3_);
        if(!p_77659_1_.isClientSide){
            try {
                IPlayerSkillCapability cap = EMCWorldAPI.getInstance().getPlayerSkillCapability(p_77659_2_);
                stack.shrink(1);
                cap.setProfession(0);
                cap.setModify(0);
                cap.setLevel(1);
                cap.setMaxLevel(10);
                for (int i = 0; i < cap.getSkills().length; i++) {
                    cap.setSkills(i,0);
                }
                return ActionResult.success(stack);
            }catch (NullPointerException ignored){

            }
        }
        return ActionResult.pass(stack);
    }
}
