package biggestxuan.emcworld.common.traits;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.item.IContainerTraitItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/13
 */

@EMCWorldSince("1.1.0")
public final class TraitUtils {

    public static TraitType getTraitType(String name){
        return TraitType.valueOf(name);
    }

    public static List<ITrait> getStackTraits(ItemStack stack){
        List<ITrait> list = new ArrayList<>();
        if(!stack.hasTag() || !(stack.getItem() instanceof IContainerTraitItem)){
            return list;
        }
        CompoundNBT nbt = stack.getOrCreateTag();
        CompoundNBT nbts = nbt.getCompound("emcworld_trait");
        for (int i = 1; i <= 5; i++) {
            CompoundNBT trait_nbt = nbts.getCompound("trait_"+i);
            String trait_name = trait_nbt.getString("trait_name");
            if(!trait_name.equals("")){
                list.add(findTrait(trait_nbt.getString("trait_name"), getTraitType(trait_nbt.getString("trait_type")), trait_nbt.getInt("trait_level")));
            }//todo
        }
        list.sort(Comparator.comparingInt(ITrait::getPriority));
        return list;
    }

    public static ITrait findTrait(String name,TraitType type,int level){
        for(ITrait t : TraitManager.list){
            if(t.getName().toString().equals(name)){
                t.setTraitType(type);
                t.setLevel(level);
                return t;
            }
        }
        return new AbstractTrait("null",TraitType.OTHER,-1,0) {};
    }

    public static void removeTrait(ItemStack stack,String name,boolean all){
        if(!stack.hasTag()){
            return;
        }
        CompoundNBT nbt = stack.getOrCreateTag();
        CompoundNBT nbts = nbt.getCompound("emcworld_trait");
        for (int i = 1; i <= 5; i++) {
            CompoundNBT trait_nbt = nbts.getCompound("trait_"+i);
            String trait_name = trait_nbt.getString("trait_name");
            if(trait_name.equals(name)){
                nbts.remove("trait_"+i);
                if(!all){
                    break;
                }
            }
        }
    }

    public static void removeTrait(ItemStack stack,ITrait trait,boolean all){
        removeTrait(stack,trait.getName().toString(),all);
    }

    public static boolean addTrait(ItemStack stack,ITrait trait){
        CompoundNBT nbt = stack.getOrCreateTag();
        CompoundNBT nbts = nbt.getCompound("emcworld_trait");
        for (int i = 1; i <= 5; i++) {
            CompoundNBT trait_nbt = nbts.getCompound("trait_"+i);
            if(trait_nbt.equals(new CompoundNBT())){
                trait_nbt.putString("trait_name",trait.getName().toString());
                trait_nbt.putString("trait_type",trait.getTraitType().toString());
                trait_nbt.putInt("trait_int",trait.getTraitLevel());
                return true;
            }
        }
        return false;
    }
}
