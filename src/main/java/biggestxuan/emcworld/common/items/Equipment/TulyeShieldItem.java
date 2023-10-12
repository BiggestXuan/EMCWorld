package biggestxuan.emcworld.common.items.Equipment;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.event.PlayerShieldDefenseEvent;
import biggestxuan.emcworld.api.item.IDifficultyItem;
import biggestxuan.emcworld.api.item.IEMCGod;
import biggestxuan.emcworld.api.item.ISponsorItem;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.items.EWItem;
import biggestxuan.emcworld.common.registry.EWDamageSource;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.SkillUtils;
import biggestxuan.emcworld.common.utils.Sponsors.Sponsors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.potion.EffectUtils;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/09/29
 */

@EMCWorldSince("1.0.5")
public class TulyeShieldItem extends ShieldItem implements ISponsorItem, IEMCGod, IDifficultyItem {
    private static String name = "shield_strength";
    public static int[] PointsRequire = new int[]{1,40,120,160,240,400};

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World p_77624_2_, List<ITextComponent> list, ITooltipFlag p_77624_4_) {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        int level = getSkillLevel(player, stack);
        if(level >= 1){
            for (int i = 0; i < level; i++) {
                list.add(EMCWorld.tc("tooltip.tulye_shield.skill_"+level));
            }
        }
        int nextRequire = level >= 6 ? EMCWorld.HOMO : PointsRequire[level];
        list.add(EMCWorld.tc("tooltip.tulye_shield.strength",getStrength(stack),getMaxStrength(stack)));
        list.add(EMCWorld.tc("tooltip.tulye_shield.point",getSkillPoint(player,stack),nextRequire));
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        var res = super.use(world, player, hand);
        ItemStack stack = player.getItemInHand(hand);
        if(player.level.isClientSide){
            return res;
        }else{
            if(player.isShiftKeyDown()){
                AdvancedStrengthAttack(player,stack);
            }else{
                StrengthAttack(player,stack);
            }
        }
        return ActionResult.success(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        super.inventoryTick(stack,world, p_77663_3_, p_77663_4_, p_77663_5_);
        if(!world.isClientSide){
            var nbt = stack.getOrCreateTag();
            for (int i = 1; i < 4; i++) {
                if(!nbt.getBoolean("init")){
                    nbt.putInt("cd_"+i,0);
                    nbt.putBoolean("init",true);
                }

            }
        }
    }

    @Mod.EventBusSubscriber(modid = EMCWorld.MODID)
    public static class TulyeShieldEvent{
        @SubscribeEvent
        public void playerShieldDefense(PlayerShieldDefenseEvent event){
            ItemStack stack = event.stack;
            PlayerEntity player = event.getPlayer();
            if(player.level.isClientSide){
                return;
            }
            if(stack.getItem() instanceof TulyeShieldItem){
                var shield = (TulyeShieldItem) stack.getItem();
                shield.addStrength(stack,1,false);
                shield.cost(stack);
                long c = (long) (MathUtils.getCommonBaseCost(player) * -0.45);
                EMCHelper.modifyPlayerEMC(player,new EMCSource.TulyeShieldSource(c,player));
                if(shield.getSkillLevel(player,stack) >= 3){ //if cd>=0
                    long _cost = (long) (MathUtils.getCommonBaseCost(player) * 0.6d);
                    if(EMCHelper.getPlayerEMC(player) >= _cost){
                        for(var living : MathUtils.getNearLiving(player,10,true)){
                            SkillUtils.addEffect(living,Effects.DAMAGE_RESISTANCE,100000,127);
                        }
                        EMCHelper.modifyPlayerEMC(player,new EMCSource.TulyeShieldSource(-_cost,player));
                    }
                //add cd2
                }
            }
        }

        @SubscribeEvent
        public void playerTick(TickEvent.PlayerTickEvent event){
            PlayerEntity player = event.player;
            if(player.level.isClientSide || event.phase.equals(TickEvent.Phase.END)){
                return;
            }
            ItemStack stack = getPlayerTulyeShield(player);
            if(stack != null){
                TulyeShieldItem shield = (TulyeShieldItem) stack.getItem();
                if(shield.getSkillLevel(player,stack) >= 2 && player.level.getDayTime() % 60 == 0){
                    long _3s_cost = (long) (MathUtils.getCommonBaseCost(player) * 0.025 * ConfigManager.DIFFICULTY.get());
                    EMCHelper.modifyPlayerEMC(player,new EMCSource.TulyeShieldSource(-_3s_cost,player));
                }
            }
        }
    }

    @Nullable
    public static ItemStack getPlayerTulyeShield(PlayerEntity player){
        ItemStack mainHandItem = player.getMainHandItem();
        if(mainHandItem.isEmpty()){
            ItemStack stack = player.getOffhandItem();
            if(stack.getItem() instanceof TulyeShieldItem){
                return stack;
            }
        }
        if(mainHandItem.getItem() instanceof TulyeShieldItem){
            return mainHandItem;
        }
        return null;
    }

    public TulyeShieldItem() {
        super(EWItem.EWProperties);
    }

    public int getMaxStrength(ItemStack stack){
        int max = 100;
        max -= getLevel(stack) * 2;
        max -= getPrefix(stack).getLevel();
        max -= getStar(stack) * 10;
        return Math.max(0,max);
    }

    public int getSkillPoint(PlayerEntity player,ItemStack stack){
        if(!(stack.getItem() instanceof TulyeShieldItem)){
            return 0;
        }
        return 0;
    }

    public int getSkillLevel(PlayerEntity player,ItemStack stack){
        int point = getSkillPoint(player,stack);
        int level = 0;
        for(int i : PointsRequire){
            if(point >= i){
                level++;
            }
        }
        return level;
    }

    public static int getStrength(ItemStack stack){
        var nbt = stack.getOrCreateTag();
        return nbt.getInt(name);
    }

    public void addStrength(ItemStack stack,int value,boolean isAll){
        var nbt = stack.getOrCreateTag();
        int base = nbt.getInt(name);
        base += value;
        if(isAll){
            base = value >= 0 ? getMaxStrength(stack) : 0;
        }
        base = MathUtils.MaxMin(base,0,getMaxStrength(stack));
        nbt.putInt(name,base);
    }

    public void AdvancedStrengthAttack(PlayerEntity player,ItemStack stack){
        long cost = (long) (MathUtils.getAttackBaseCost(player) * 3 * player.getMaxHealth());
        if(EMCHelper.getPlayerEMC(player) >= cost && getSkillLevel(player,stack) >= 4){
            EMCHelper.modifyPlayerEMC(player,new EMCSource.TulyeShieldSource(-cost,player));

        }
    }

    public void StrengthAttack(PlayerEntity player,ItemStack stack){
        if(getStrength(stack) >= getMaxStrength(stack)){// if(cd>0)
            addStrength(stack,-1,true);
            SkillUtils.addEffect(player, Effects.MOVEMENT_SLOWDOWN,100,4);
            SkillUtils.addEffect(player, Effects.WEAKNESS,100,4);
            long cost = 0;
            for(LivingEntity living : MathUtils.getNearLiving(player,5,false)){
                cost += (long) (MathUtils.getAttackBaseCost(player) * 5 * GameStageManager.getPlayerGameStageByInt(player));
                if(EMCHelper.getPlayerEMC(player) >= cost){
                    living.hurt(EWDamageSource.REALLY,5 * GameStageManager.getPlayerGameStageByInt(player));
                }else{
                    break;
                }
            }
            EMCHelper.modifyPlayerEMC(player,new EMCSource.TulyeShieldSource(-cost,player));
            //add skill 1 cd.
        }
    }

    @Override
    public int getWeightRequired(ItemStack stack) {
        int level = getLevel(stack);
        switch (level){
            case 0:
                return 150;
            case 1:
                return 500;
            case 2:
                return 1500;
            case 3:
                return 4000;
            case 4:
                return 12000;
            case 5:
                return 30000;
            case 6:
            case 7:
                return 60000;
        }
        return EMCWorld.HOMO;
    }

    @Nullable
    @Override
    public Sponsors getSponsor() {
        return Sponsors.all.TULYE.getSponsors();
    }

    @Override
    public int getMaxLevel() {
        return 8;
    }

    @Override
    public long getMaxInfuser(ItemStack stack) {
        return 0;
    }

    @Override
    public long getTickCost(ItemStack stack) {
        return 60;
    }

    @Override
    public double getDifficulty() {
        return 3;
    }

    @Override
    public boolean isHardcore() {
        return false;
    }
}
