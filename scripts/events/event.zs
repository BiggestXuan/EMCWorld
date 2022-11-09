#priority 55
import crafttweaker.api.events.CTEventManager;

CTEventManager.register<crafttweaker.api.event.entity.player.MCPlayerLoggedInEvent>((event)=>{
    event.player.world.asServerWorld().server.executeCommand("gamerule keepInventory true", true);
});