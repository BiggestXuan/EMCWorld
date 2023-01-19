package biggestxuan.emcworld.api.item.equipment.weapon;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/30
 */

import net.minecraft.item.ItemStack;

public interface IRangeAttackWeapon {
    String name = "attack_range_mode";

    double getAttackRange(ItemStack stack);

    default AttackMode getAttackMode(ItemStack stack){
        int index = stack.getOrCreateTag().getInt(name);
        return getMode(index);
    }

    default void setAttackMode(ItemStack stack,int index){
        stack.getOrCreateTag().putInt(name,index);
    }

    default void switchAttackMode(ItemStack stack){
        if(getAttackRange(stack) < 0)return;
        int index = getAttackMode(stack).index;
        index++;
        if(index > 5){
            index = 0;
        }
        setAttackMode(stack,index);
    }

    default AttackMode getMode(int index){
        for(AttackMode mode:AttackMode.values()){
            if(mode.index == index){
                return mode;
            }
        }
        return AttackMode.DUMMY;
    }

    enum AttackMode{
        DUMMY(0,"none"),
        ALL(1,"all"),
        ONLY_MOB(2,"mob"),
        ONLY_ANIMAL(3,"animal"),
        EXCEPT_PLAYER(4,"except_player"),
        ONLY_PLAYER(5,"only_player")
        ;
        private final int index;
        private final String name;

        AttackMode(int index,String name){
            this.index = index;
            this.name = name;
        }

        public String getName() {
            return "attack_range_"+name;
        }
    }
}
