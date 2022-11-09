package biggestxuan.emcworld.common.capability;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/18
 */

import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.common.capability.PlayerLevel.PlayerLevelCapability;
import biggestxuan.emcworld.common.capability.Util.UtilCapability;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetupEventHandler {
    @SubscribeEvent
    public static void onSetupEvent(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CapabilityManager.INSTANCE.register(
                    IUtilCapability.class,
                    new Capability.IStorage<IUtilCapability>() {
                        @Override
                        public INBT writeNBT(Capability<IUtilCapability> capability, IUtilCapability instance, Direction side) {
                            CompoundNBT tag = new CompoundNBT();
                            tag.putInt("sponsorLevel",instance.getLevel());
                            tag.putLong("CoolDown",instance.getCoolDown());
                            return tag;
                        }

                        @Override
                        public void readNBT(Capability<IUtilCapability> capability, IUtilCapability instance, Direction side, INBT nbt) {
                            if(nbt instanceof CompoundNBT && instance instanceof UtilCapability){
                                CompoundNBT tag = (CompoundNBT) nbt;
                                instance.setLevel(tag.getInt("sponsorLevel"));
                                instance.setCoolDown(tag.getLong("CoolDown"));
                            }
                        }
                    },
                    () -> null
            );
        });
        event.enqueueWork(() -> {
            CapabilityManager.INSTANCE.register(
                    IPlayerSkillCapability.class,
                    new Capability.IStorage<IPlayerSkillCapability>() {
                        @Override
                        public INBT writeNBT(Capability<IPlayerSkillCapability> capability, IPlayerSkillCapability instance, Direction side) {
                            CompoundNBT tag = new CompoundNBT();
                            tag.putInt("maxLevel",instance.getMaxLevel());
                            tag.putInt("level",instance.getLevel());
                            tag.putInt("xp",instance.getXP());
                            tag.putInt("modify",instance.getModify());
                            tag.putInt("profession",instance.getProfession());
                            tag.putIntArray("skills",instance.getSkills());
                            return tag;
                        }

                        @Override
                        public void readNBT(Capability<IPlayerSkillCapability> capability, IPlayerSkillCapability instance, Direction side, INBT nbt) {
                            if(nbt instanceof CompoundNBT && instance instanceof PlayerLevelCapability){
                                CompoundNBT tag = (CompoundNBT) nbt;
                                instance.setMaxLevel(tag.getInt("maxLevel"));
                                instance.setLevel(tag.getInt("level"));                                instance.setLevel(tag.getInt("level"));
                                instance.setXP(tag.getInt("xp"));
                                instance.setModify(tag.getInt("modify"));
                                instance.setProfession(tag.getInt("profession"));
                                instance.setSkill(tag.getIntArray("skills"));
                            }
                        }
                    },
                    () -> null
            );
        });
    }
}
