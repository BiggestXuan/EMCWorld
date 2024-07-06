package biggestxuan.emcworld.common.items.CDReduceScroll;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.common.items.EWItem;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/09/26
 */

@EMCWorldSince("1.0.5")
public abstract class CDReduceScrollItem extends EWItem {
    public CDReduceScrollItem(int maxSize){
        super(maxSize);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World p_77624_2_, List<ITextComponent> list, ITooltipFlag p_77624_4_) {
        super.appendHoverText(stack, p_77624_2_, list, p_77624_4_);
        CDScrollType type = getType(stack);
        list.add(EMCWorld.tc("tooltip.cd_scroll.mode").append(type.getName()));
        list.add(EMCWorld.tc("tooltip.cd_scroll.time", MathUtils.format(getCanReduceCD(stack, Minecraft.getInstance().player,type)/20)));
    }

    public abstract CDScrollType[] getCanReduceType();

    public abstract long getCanReduceCD(ItemStack stack,PlayerEntity player,CDScrollType type);

    public boolean canReduceCD(CDScrollType type){
        return Arrays.asList(getCanReduceType()).contains(type);
    }

    protected long getAfterCD(ItemStack stack,PlayerEntity player,CDScrollType type,long origin){
        return Math.max(0,origin-getCanReduceCD(stack, player, type));
    }

    protected boolean reduceCD(ItemStack stack,PlayerEntity player){
        CDScrollType type = getType(stack);
        try{
            IUtilCapability util = EMCWorldAPI.getInstance().getUtilCapability(player);
            IPlayerSkillCapability skill = EMCWorldAPI.getInstance().getPlayerSkillCapability(player);
            switch (type){
                case CRAFT:
                    if(canReduceCD(CDScrollType.CRAFT)){
                        util.setCoolDown(getAfterCD(stack,player,type,util.getCoolDown()));
                    }
                    return true;
                case SKILL1:
                    if(canReduceCD(CDScrollType.SKILL1)){
                        skill.setSkills(7, (int) getAfterCD(stack,player,type,skill.getSkills()[7]));
                    }
                    return true;
                case SKILL2:
                    if(canReduceCD(CDScrollType.SKILL2)){
                        skill.setSkills(19, (int) getAfterCD(stack,player,type,skill.getSkills()[19]));
                    }
                    return true;
                case SKILL3:
                    if(canReduceCD(CDScrollType.SKILL3)){
                        skill.setSkills(43, (int) getAfterCD(stack,player,type,skill.getSkills()[43]));
                    }
                    return true;
            }
        }catch (NullPointerException e){
            return false;
        }
        return false;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        super.inventoryTick(stack, world, p_77663_3_, p_77663_4_, p_77663_5_);
        CDScrollType[] types = getCanReduceType();
        if(!stack.hasTag() && !world.isClientSide){
            CompoundNBT nbt = stack.getOrCreateTag();
            nbt.putInt("craftType", types.length >= 2 ? 0 : types[0].index);
        }
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        super.use(world,player,hand);
        ItemStack stack = player.getItemInHand(hand);
        if(world.isClientSide){
            return ActionResult.fail(stack);
        }
        CDScrollType type = getType(stack);
        if(player.isShiftKeyDown()){
            changeScrollType(stack,player);
        }else{
            reduceCD(stack,player);
            player.getCooldowns().addCooldown(this,60);
        }
        return ActionResult.success(stack);
    }

    private void changeScrollType(ItemStack stack,PlayerEntity player){
        CDScrollType type = getType(stack);
        List<CDScrollType> list = Arrays.asList(getCanReduceType());
        int index = list.indexOf(type);
        if(list.size() <= 1){
            return;
        }
        if(index >= getCanReduceType().length - 1){
            index = 0;
        }else{
            index++;
        }
        stack.getOrCreateTag().putInt("craftType",list.get(index).index);
    }

    protected static CDScrollType getType(ItemStack stack){
        CompoundNBT nbt = stack.getOrCreateTag();
        int index = nbt.getInt("craftType");
        for(CDScrollType t : CDScrollType.values()){
            if(index == t.index){
                return t;
            }
        }
        return CDScrollType.NONE;
    }

    public enum CDScrollType{
        NONE(0,"none",0),
        CRAFT(1,"craft",1),
        SKILL1(2,"skill1",3),
        SKILL2(3,"skill2",7),
        SKILL3(4,"skill3",15);

        private final int index;
        private final String name;
        private final int weight;

        CDScrollType(int index,String name,int weight){
            this.index = index;
            this.name = name;
            this.weight = weight;
        }

        public ITextComponent getName(){
            return EMCWorld.tc("tooltip.cd_scroll."+name);
        }

        public int getWeight(){
            return this.weight;
        }
    }
}
