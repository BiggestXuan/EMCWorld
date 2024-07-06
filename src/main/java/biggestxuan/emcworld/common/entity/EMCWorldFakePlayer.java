package biggestxuan.emcworld.common.entity;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/25
 */

import com.mojang.authlib.GameProfile;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.FakePlayer;

import java.util.UUID;

public class EMCWorldFakePlayer extends FakePlayer {
    public static String name = "emcworld_fake_player";
    public static UUID uuid = UUID.fromString("76a254aa-3a1c-22ba-1804-d8ef8bf18403");

    public EMCWorldFakePlayer(ServerWorld world) {
        super(world, new GameProfile(uuid,name));
    }
}
