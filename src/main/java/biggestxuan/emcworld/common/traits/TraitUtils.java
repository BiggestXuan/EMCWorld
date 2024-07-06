package biggestxuan.emcworld.common.traits;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.trait.AbstractTrait;
import biggestxuan.emcworld.api.trait.IHasTraitItem;
import biggestxuan.emcworld.api.trait.ITrait;
import biggestxuan.emcworld.api.trait.TraitType;
import biggestxuan.emcworld.common.registry.EWItems;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/13
 */

@EMCWorldSince("1.1.0")
public final class TraitUtils {

    public static TraitType getTraitType(String name){
        return TraitType.valueOf(name.toUpperCase());
    }

    public static int getTraitsCount(ItemStack stack){
        AtomicInteger i = new AtomicInteger();
        getStackTraits(stack).forEach(e -> {
            if(!e.isFake()){
                i.getAndIncrement();
            }
        });
        return i.get();
    }

    public static List<ITrait> getStackTraits(ItemStack stack){
        List<ITrait> list = new ArrayList<>();
        if(!stack.hasTag() || !(stack.getItem() instanceof IHasTraitItem)){
            return list;
        }
        CompoundNBT nbt = stack.getOrCreateTag();
        CompoundNBT nbts = nbt.getCompound("emcworld_trait");
        for (int i = 1; i <= 5; i++) {
            CompoundNBT trait_nbt = nbts.getCompound("trait_"+i);
            ITrait trait = getTrait(trait_nbt);
            if(!trait.isFake()){
                list.add(trait);
            }
        }
        list.sort(Comparator.comparingInt(ITrait::getPriority));
        return list;
    }

    public static ITrait getTrait(CompoundNBT nbt){
        String trait_name = nbt.getString("trait_name");
        if(!trait_name.equals("")){
            return findTrait(nbt.getString("trait_name"), getTraitType(nbt.getString("trait_type")), nbt.getInt("trait_level"));
        }
        return AbstractTrait.FAKE_TRAIT;
    }

    public static ITrait findTrait(ResourceLocation rl,TraitType type,int level){
        return findTrait(rl.toString(),type,level);
    }

    public static List<ITrait> getAllTraits(){
        return TraitManager.list;
    }

    public static ITrait findTrait(String name,TraitType type,int level){
        for(ITrait t : TraitManager.list){
            if(t.getRL().toString().equals(name)){
                t.setTraitType(type);
                t.setLevel(level);
                return t;
            }
        }
        return AbstractTrait.FAKE_TRAIT;
    }

    public static ITrait getTrait(ItemStack stack,int index){
        if(!stack.hasTag()){
            return AbstractTrait.FAKE_TRAIT;
        }
        CompoundNBT nbt = stack.getOrCreateTag();
        CompoundNBT nbts = nbt.getCompound("emcworld_trait");
        return getTrait(nbts.getCompound("trait_"+index));
    }

    public static void setTrait(ITrait trait,ItemStack stack,int index){ //index start:1
        CompoundNBT nbt = stack.getOrCreateTag();
        CompoundNBT nbts = nbt.getCompound("emcworld_trait");
        CompoundNBT trait_nbt = nbts.getCompound("trait_"+index);
        trait_nbt.putString("trait_name",trait.getRL().toString());
        trait_nbt.putString("trait_type",trait.getTraitType().toString());
        trait_nbt.putInt("trait_level",trait.getTraitLevel());
    }

    public static void removeTrait(ItemStack stack,int index){
        CompoundNBT nbt = stack.getOrCreateTag();
        CompoundNBT nbts = nbt.getCompound("emcworld_trait");
        nbts.put("trait_"+index,new CompoundNBT());
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

    public static ItemStack getTraitCoreItem(ITrait trait){
        ItemStack stack = new ItemStack(EWItems.EMC_TRAIT_CORE.get(),1);
        IHasTraitItem.init(stack);
        TraitUtils.setTrait(trait,stack,1);
        return stack;
    }

    public static void removeTrait(ItemStack stack,ITrait trait,boolean all){
        removeTrait(stack,trait.getRL().toString(),all);
    }

    public static boolean addTrait(ItemStack stack,ITrait trait){
        CompoundNBT nbt = stack.getOrCreateTag();
        CompoundNBT nbts = nbt.getCompound("emcworld_trait");
        for (int i = 1; i <= 5; i++) {
            CompoundNBT trait_nbt = nbts.getCompound("trait_"+i);
            if(trait_nbt.equals(new CompoundNBT())){
                trait_nbt.putString("trait_name",trait.getRL().toString());
                trait_nbt.putString("trait_type",trait.getTraitType().toString());
                trait_nbt.putInt("trait_level",trait.getTraitLevel());
                return true;
            }
        }
        return false;
    }

    public static List<IFormattableTextComponent> getDesc(ItemStack stack){
        return getDesc(stack,false);
    }

    public static List<IFormattableTextComponent> getDesc(ItemStack stack,boolean isShift){
        List<IFormattableTextComponent> list = new ArrayList<>();
        if(isShift){
            for (int i = 1; i <= 5; i++) {
                ITrait trait = getTrait(stack,i);
                if(!trait.isFake()){
                    list.add(EMCWorld.tc("["+i+"]").append(getDesc(trait)));

                }
            }
        }
        return list;
    }

    public static IFormattableTextComponent getDesc(ITrait trait){
        Style style = trait.getName().getStyle().withColor(Color.fromRgb(trait.getColor()));
        return trait.getName().append(" - Lvl:"+trait.getTraitLevel()).setStyle(style);
    }
}
