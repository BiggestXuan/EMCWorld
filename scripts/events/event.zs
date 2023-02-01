#priority 55
import crafttweaker.api.events.CTEventManager;
import crafttweaker.api.events.EventPriority;
import crafttweaker.api.event.entity.player.MCPlayerLoggedInEvent;
import crafttweaker.api.event.entity.living.MCLivingDeathEvent;

/*
import crafttweaker.api.event.entity.player.MCItemPickupEvent;
import crafttweaker.api.event.block.MCBlockBreakEvent;
*/

import crafttweaker.api.util.BlockPos;

CTEventManager.register<MCPlayerLoggedInEvent>(EventPriority.LOWEST,(event)=>{
    var player = event.player;
    player.world.asServerWorld().server.executeCommand("gamerule keepInventory true", true);
    if(player.LogCount() == 1){
        player.getInventory().remove(<item:patchouli:guide_book>);
        player.give(<item:patchouli:guide_book>.withTag({"akashictome:displayName": {text: "EMCWorld 指导手册" as string}, "patchouli:book": "emcworld:guide" as string, "akashictome:data": {extendedcrafting: {id: "patchouli:guide_book" as string, Count: 1 as byte, tag: {"patchouli:book": "extendedcrafting:guide" as string}}, botania: {id: "botania:lexicon" as string, Count: 1 as byte, tag: {}}, ftbquests: {id: "ftbquests:book" as string, Count: 1 as byte}, crockpot: {id: "patchouli:guide_book" as string, Count: 1 as byte, tag: {"patchouli:book": "crockpot:book" as string}}, bloodmagic: {id: "patchouli:guide_book" as string, Count: 1 as byte, tag: {"patchouli:book": "bloodmagic:guide" as string}}, the_afterlight: {id: "patchouli:guide_book" as string, Count: 1 as byte, tag: {"patchouli:book": "the_afterlight:afterlight_tome" as string}}, thermal: {id: "patchouli:guide_book" as string, Count: 1 as byte, tag: {"patchouli:book": "thermal:guidebook" as string}}, twilightforest: {id: "patchouli:guide_book" as string, Count: 1 as byte, tag: {"patchouli:book": "twilightforest:guide" as string}}, divinerpg: {id: "patchouli:guide_book" as string, Count: 1 as byte, tag: {"patchouli:book": "divinerpg:divine" as string}}, allthemodium: {id: "patchouli:guide_book" as string, Count: 1 as byte, tag: {"patchouli:book": "allthemodium:allthemodium" as string}}}, "akashictome:is_morphing": 1 as byte, display: {Name: "{\"translate\":\"akashictome.sudo_name\",\"with\":[{\"color\":\"green\",\"text\":\"EMCWorld 指导手册\"}]}" as string}}));
    }
});

CTEventManager.register<MCLivingDeathEvent>((event)=>{
    if(event.getEntityLiving().getName() == "emcworld:cxk"){
        var pos as BlockPos = event.getEntityLiving().getPosition();
        event.getEntityLiving().world.asServerWorld().server.executeCommand("playsound emcworld:cxk_death ambient @p "+pos.x+" "+pos.y+" "+pos.z, true);
    }
});

/*
CTEventManager.register<MCItemPickupEvent>((event) =>{
    println(event.getStack().toString());
});
*/