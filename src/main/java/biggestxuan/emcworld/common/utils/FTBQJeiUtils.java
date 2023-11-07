package biggestxuan.emcworld.common.utils;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.events.PlayerEvent.PlayerQuestCompletedEvent;
import dev.ftb.mods.ftbquests.FTBQuests;
import dev.ftb.mods.ftbquests.quest.QuestFile;
import dev.ftb.mods.ftbquests.quest.reward.CustomReward;
import dev.ftb.mods.ftbquests.quest.reward.ItemReward;
import dev.ftb.mods.ftbquests.quest.task.ItemTask;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/12
 */

@EMCWorldSince("1.0.6")
@SuppressWarnings("unused")
public final class FTBQJeiUtils {
    public final String questName;
    public final List<String> dependencies;
    public final List<String> dependants;
    public final List<Ingredient> inputs;
    public final List<ItemStack> outputs;
    public final long emc;

    public FTBQJeiUtils(String questName, List<String> dependencies,List<String> dependants, List<Ingredient> inputs, List<ItemStack> outputs, long emc){
        this.questName = questName;
        this.dependencies = dependencies;
        this.dependants = dependants;
        this.inputs = inputs;
        this.outputs = outputs;
        this.emc = emc;
    }

    public boolean isValid(){
        return inputs.size() > 0 && outputs.size() > 0 && emc > 0;
    }

    public boolean hasDependence(){
        return dependencies.size() > 0;
    }

    public boolean hasDependant(){
        return dependants.size() > 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(questName);
        if(hasDependence()){
            builder.append("dependencies:");
            dependencies.forEach(builder::append);
        }
        if(hasDependant()){
            builder.append("dependant:");
            dependants.forEach(builder::append);
        }
        builder.append(", inputs:");
        inputs.forEach(builder::append);
        builder.append(", outputs:");
        outputs.forEach(builder::append);
        builder.append(", EMC:");
        builder.append(emc);
        return builder.toString();
    }

    public static List<FTBQJeiUtils> getAllQuests(boolean isClient){
        List<FTBQJeiUtils> list = new ArrayList<>();
        QuestFile file = FTBQuests.PROXY.getQuestFile(isClient);
        if(file != null){
            file.getAllChapters().forEach(chapter -> chapter.quests.forEach(quest -> {
                String name = quest.getTitle().getString();
                List<String> deps = new ArrayList<>();
                quest.dependencies.forEach(d -> deps.add(d.getTitle().getString()));
                List<String> dps = new ArrayList<>();
                quest.getDependants().forEach(d -> dps.add(d.getTitle().getString()));
                List<Ingredient> inputs = new ArrayList<>();
                quest.tasks.forEach(task -> {
                    if(task instanceof ItemTask){
                        ItemTask it = (ItemTask) task;
                        if(!it.item.toString().equals("1 tag")){
                            it.item.setCount((int) it.count);
                            inputs.addAll(EMCWorld.itemstack2ingredient(it.getValidDisplayItems()));
                        }else{
                            inputs.add(Ingredient.of(it.getValidDisplayItems().toArray(new ItemStack[0])));
                        }
                    }
                });
                AtomicLong emc = new AtomicLong(-1);
                List<ItemStack> outputs = new ArrayList<>();
                quest.rewards.forEach(reward -> {
                    if(reward instanceof ItemReward){
                        ItemReward ir = (ItemReward) reward;
                        ir.item.setCount(ir.count);
                        outputs.add(ir.item);
                    }
                    if(reward instanceof CustomReward){
                        CustomReward cr = (CustomReward) reward;
                        String questStage = PlayerQuestCompletedEvent.getQuestStage(reward);
                        if(questStage == null){
                            if(isClient){
                                emc.set(MathUtils.getQuestCompletedRewardBase(Minecraft.getInstance().player,cr).getEmc());
                            }else{
                                emc.set(-2L);
                            }
                        }else{
                            emc.set((long) MathUtils.getQuestCompletedRewardBase(questStage,cr));
                        }
                        emc.set((long) (emc.get()*2 / ConfigManager.DIFFICULTY.get()));
                    }
                });
                var recipe = new FTBQJeiUtils(name,deps,dps,inputs,outputs,emc.get());
                if(recipe.isValid()){
                    list.add(recipe);
                }
                EMCWorld.LOGGER.info(recipe.toString());
            }));
        }
        return list;
    }

    public static List<String> getAllQuestsName(boolean isClient){
        List<String> list = new ArrayList<>();
        QuestFile file = FTBQuests.PROXY.getQuestFile(isClient);
        if(file != null){
            file.getAllChapters().forEach(chapter -> chapter.quests.forEach(quest -> list.add(quest.getTitle().getString())));
        }
        return list;
    }
}
