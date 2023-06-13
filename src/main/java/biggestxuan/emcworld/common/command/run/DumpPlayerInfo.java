package biggestxuan.emcworld.common.command.run;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/01
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.client.screen.ProfessionGUI; //Not use client method.
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.compact.Projecte.KnowledgeHelper;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;

import static biggestxuan.emcworld.common.events.PlayerEvent.PlayerTickEvent.getPlayerAllItem;

public class DumpPlayerInfo implements Command<CommandSource> {
    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = EntityArgument.getPlayer(context,"target");
        EMCWorld.LOGGER.info(dumpPlayerInfo(player).toString());
        return 0;
    }

    private static StringBuilder dumpPlayerInfo(PlayerEntity player){
        StringBuilder builder = new StringBuilder("EMCWorld Dump Player Info: \n");
        IUtilCapability util = EMCWorldAPI.getInstance().getUtilCapability(player);
        IPlayerSkillCapability skill = EMCWorldAPI.getInstance().getPlayerSkillCapability(player);
        builder.append("Player Name: ")
                .append(player.getScoreboardName()).append(" UUID: ")
                .append(player.getStringUUID()).append("\n")
                .append("Profession: ").append(skill.getProfession()).append(" Level: ").append(skill.getLevel()).append(" Exp: ").append(skill.getXP())
                .append("\n")
                .append("EMC: ").append(EMCHelper.getPlayerEMC(player))
                .append("\n")
                .append("Inventory: \n").append(getInventory(player))
                .append("\n")
                .append("Ender Chest Inventory: \n").append(getEnderChest(player))
                .append("\n")
                .append("Alchemy Bag Inventory: \n").append(getAlchemyBag(player));

        return builder;
    }

    private static StringBuilder getInventory(PlayerEntity player){
        StringBuilder sb = new StringBuilder();
        for(ItemStack stack:getPlayerAllItem(player)){
            if(stack.equals(ItemStack.EMPTY)) continue;
            sb.append(stackFormat(stack)).append(", \n");
        }
        return sb;
    }

    private static StringBuilder getEnderChest(PlayerEntity player){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < player.getEnderChestInventory().getContainerSize(); ++i) {
            ItemStack stack = player.getEnderChestInventory().getItem(i);
            if(stack.equals(ItemStack.EMPTY)) continue;
            sb.append(stackFormat(stack)).append(", \n");
        }
        return sb;
    }

    private static StringBuilder getAlchemyBag(PlayerEntity player){
        StringBuilder sb = new StringBuilder();
        for(DyeColor color:DyeColor.values()){
            sb.append("-- Color: ").append(color.getName()).append("\n");
            for(ItemStack stack:KnowledgeHelper.getAlchemyBag(player,color)){
                sb.append(stackFormat(stack)).append(", \n");
            }
        }
        return sb;
    }

    private static String stackFormat(ItemStack stack){
        String base = stack.getDisplayName().getString() + " * " + stack.getCount();
        if(stack.hasTag()){
            base += " "+stack.getOrCreateTag().toString();
        }
        return base;
    }
}
