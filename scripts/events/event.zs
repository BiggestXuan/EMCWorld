#priority 55
import crafttweaker.api.events.CTEventManager;
import crafttweaker.api.event.entity.player.MCPlayerLoggedInEvent;
import crafttweaker.api.event.entity.living.MCLivingDeathEvent;

/*
import crafttweaker.api.event.entity.player.MCItemPickupEvent;
import crafttweaker.api.event.block.MCBlockBreakEvent;
*/

import crafttweaker.api.util.BlockPos;

CTEventManager.register<MCPlayerLoggedInEvent>((event)=>{
    event.player.world.asServerWorld().server.executeCommand("gamerule keepInventory true", true);
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