package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/04
 */

import biggestxuan.emcworld.api.item.IOwnerItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.UUID;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow @Nullable public abstract MinecraftServer getServer();

    @Inject(method = "remove()V",at = @At("HEAD"))
    public void remove(CallbackInfo ci){
        Entity entity = (Entity) (Object) this;
        if(entity instanceof ItemEntity){
            ItemEntity itemEntity = (ItemEntity) entity;
            ItemStack itemStack = itemEntity.getItem();
            if(itemStack.getItem() instanceof IOwnerItem){
                IOwnerItem ownerItem = (IOwnerItem) itemStack.getItem();
                UUID owner = ownerItem.getOwner(itemStack);
                MinecraftServer server = getServer();
                if(server != null){
                    ServerPlayerEntity player = server.getPlayerList().getPlayer(owner);
                    if(player != null){
                        itemEntity.setPos(player.getX(),player.getY()+3,player.getZ());
                        player.level.addFreshEntity(itemEntity);
                    }
                }
            }
        }
    }
}
