package biggestxuan.emcworld.api.item;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/31
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.equipment.IEMCGodWeaponLevel;
import biggestxuan.emcworld.common.compact.Mekanism.MekUtils;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.utils.MathUtils;
import mekanism.common.item.gear.ItemMekaSuitArmor;
import mekanism.common.item.gear.ItemMekaTool;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;

public interface IPrefixItem {
    static Prefix getPrefix(int level){
        for(Prefix pre : Prefix.values()){
            if(level == pre.level){
                return pre;
            }
        }
        return Prefix.NULL;
    }

    static Prefix getHighestPrefix(){
        double diff = ConfigManager.DIFFICULTY.get();
        if(diff == 0.5) return Prefix.NORMAL;
        if(diff <= 1) return Prefix.FINE;
        if(diff <= 1.5) return Prefix.EXCELLENT;
        if(diff <= 2) return Prefix.ULTIMATE;
        if(diff <= 2.5) return Prefix.EPIC;
        if(diff < 3) return Prefix.LEGEND;
        return Prefix.MYTH;
    }

    default Prefix getPrefix(ItemStack stack){
        if(MekUtils.isInfinityMekaTool(stack)){
            return Prefix.MYTH;
        }
        return getPrefix(stack.getOrCreateTag().getInt("prefix"));
    }

    default void init(ItemStack stack){
        if(MathUtils.isRandom(0.0001)) {
            setPrefix(stack,Prefix.MYTH);
        }else if(MathUtils.isRandom(0.0005)) {
            setPrefix(stack,Prefix.LEGEND);
        } else if(MathUtils.isRandom(0.002)) {
            setPrefix(stack,Prefix.EPIC);
        } else if(MathUtils.isRandom(0.007)) {
            setPrefix(stack,Prefix.ULTIMATE);
        } else if(MathUtils.isRandom(0.03)) {
            setPrefix(stack,Prefix.EXCELLENT);
        } else if(MathUtils.isRandom(0.25)) {
            setPrefix(stack,Prefix.FINE);
        } else if(MathUtils.isRandom(0.75)) {
            setPrefix(stack,Prefix.NORMAL);
        } else if(MathUtils.isRandom(0.85)) {
            setPrefix(stack,Prefix.OLD);
        } else if(MathUtils.isRandom(0.9)) {
            setPrefix(stack,Prefix.LITTER);
        } else {
            setPrefix(stack,Prefix.RAGGED);
        }
    }

    default void setPrefix(ItemStack stack,Prefix prefix){
        stack.getOrCreateTag().putInt("prefix",prefix.level);
        if(getPrefix(stack).getLevel() > getHighestPrefix().getLevel()){
            setPrefix(stack,getHighestPrefix());
        }
        if(stack.getItem() instanceof IUpgradeableItem){
            IUpgradeableItem item = (IUpgradeableItem) stack.getItem();
            if(item.getLevel(stack) >= 22 && getPrefix(stack).getLevel() < Prefix.EPIC.getLevel() && item instanceof IEMCGod){
                setPrefix(stack,Prefix.EPIC);
            }
        }
        if(stack.getItem() instanceof ItemMekaSuitArmor | stack.getItem() instanceof ItemMekaTool){
            if(getPrefix(stack).getLevel() != getHighestPrefix().getLevel()){
                setPrefix(stack,getHighestPrefix());
            }
        }
    }

    enum Prefix{
        NULL(0,"null",0x666666),
        RAGGED(1,"ragged",0x666666),
        LITTER(2,"litter",0x666666),
        OLD(3,"old",0x666666),
        NORMAL(4,"normal",0x6fa8dc),
        FINE(5,"fine",0x6fa8dc),
        EXCELLENT(6,"excellent",0xe06666),
        ULTIMATE(7,"ultimate",0xe06666),
        EPIC(8,"epic",0x674ea7),
        LEGEND(9,"legend",0xf6b26b),
        MYTH(10,"myth",0xff9900)
        ;
        private final int level;
        private final String name;
        private final int color;

        Prefix(int level,String name,int color){
            this.level = level;
            this.name = name;
            this.color = color;
        }

        public int getLevel() {
            return level;
        }

        public TranslationTextComponent getName(){
            return EMCWorld.tc("tooltip.emcworld.prefix."+name);
        }

        public int getColor() {
            return color;
        }

        @Override
        public String toString(){
            return name;
        }
    }
}
