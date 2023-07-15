package biggestxuan.emcworld.common.config;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/25
 */


import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.AstralSorcery.EMCConstellationEffects;
import biggestxuan.emcworld.common.compact.AstralSorcery.EMCMantleEffects;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus= Bus.MOD)
public class ConfigManager {
    public static ForgeConfigSpec COMMON_CONFIG ;
    public static ForgeConfigSpec.DoubleValue DIFFICULTY;
    public static ForgeConfigSpec.BooleanValue FORMAT;
    public static ForgeConfigSpec.BooleanValue SPONSOR_INFO;
    public static ForgeConfigSpec.BooleanValue ADMIN_MENU;
    public static ForgeConfigSpec.BooleanValue FREE_MODE;
    public static ForgeConfigSpec.BooleanValue OFFLINE_WARN;
    public static ForgeConfigSpec.BooleanValue GENG;
    public static ForgeConfigSpec.BooleanValue LOTTERY;
    //public static ForgeConfigSpec.BooleanValue SHARE_EMC;
    //public static ForgeConfigSpec.BooleanValue PREVENT_TOSS_WEAPON;
    public static ForgeConfigSpec.BooleanValue RSAutomation;
    public static ForgeConfigSpec.BooleanValue SpringFestival;
    public static ForgeConfigSpec.BooleanValue LanternFestival;
    public static ForgeConfigSpec.BooleanValue FoolsDay;
    public static ForgeConfigSpec.BooleanValue DragonBoatFestival;
    public static ForgeConfigSpec.BooleanValue MidAutumnFestival;
    public static ForgeConfigSpec.BooleanValue EMC_ATTACK;
    public static ForgeConfigSpec.BooleanValue EMC_WAKE;
    public static ForgeConfigSpec.BooleanValue EMC_HOE;
    public static ForgeConfigSpec.BooleanValue EMC_CONTAINER;
    public static ForgeConfigSpec.BooleanValue EMC_BUCKET;
    public static ForgeConfigSpec.BooleanValue EMC_CRAFT;
    public static ForgeConfigSpec.BooleanValue EMC_DESTROY;
    public static ForgeConfigSpec.BooleanValue EMC_DEATH;
    public static ForgeConfigSpec.BooleanValue EMC_HURT;
    public static ForgeConfigSpec.BooleanValue EMC_PICK;
    public static ForgeConfigSpec.BooleanValue EMC_LOCATE;
    public static ForgeConfigSpec.BooleanValue EMC_TELEPORT;
    public static ForgeConfigSpec.BooleanValue EMC_QUEST;
    public static ForgeConfigSpec.BooleanValue EMC_LOG;
    public static ForgeConfigSpec.IntValue UPGRADE_WEIGHT;
    public static ForgeConfigSpec.DoubleValue UPGRADE_WEIGHT_RATE;
    public static ForgeConfigSpec.BooleanValue UPGRADE_FAIL_LOSS;
    public static ForgeConfigSpec.BooleanValue UPGRADE_PREFIX;
    public static ForgeConfigSpec.BooleanValue UPGRADE_GEM;
    public static ForgeConfigSpec.BooleanValue UPGRADE_STAR;
    public static ForgeConfigSpec.BooleanValue UPGRADE_DISABLE_ANVIL;
    public static ForgeConfigSpec.BooleanValue UPGRADE_TULYE_SCROLL;
    public static ForgeConfigSpec.BooleanValue UPGRADE_BX_SCROLL;
    public static ForgeConfigSpec.BooleanValue UPGRADE_CRITICAL_OVERFLOW;
    public static ForgeConfigSpec.BooleanValue RAID_PLAYER_LEVEL;
    public static ForgeConfigSpec.BooleanValue RAID_PLAYER_DEATH;
    public static ForgeConfigSpec.BooleanValue RAID_PLAYER_DAMAGE;
    public static ForgeConfigSpec.BooleanValue RAID_DIMENSION_LIMIT;
    public static ForgeConfigSpec.BooleanValue RAID_SPONSORS;
    public static ForgeConfigSpec.BooleanValue RAID_ILLAGER_TEAM_DAMAGE;
    public static ForgeConfigSpec.BooleanValue RAID_BLOCK_SLEEP;
    public static ForgeConfigSpec.BooleanValue CHAMPIONS_EMC_FLAMING;
    public static ForgeConfigSpec.BooleanValue CHAMPIONS_EMC_BROKEN;
    public static ForgeConfigSpec.BooleanValue CHAMPIONS_EMC_PROTECTING;
    public static ForgeConfigSpec.BooleanValue CHAMPIONS_HEALING;
    public static ForgeConfigSpec.BooleanValue CHAMPIONS_SHIELD_FLAMING;
    public static ForgeConfigSpec.BooleanValue CRAFT_LOOT_EASY_DARK_MATTER;
    public static ForgeConfigSpec.DoubleValue CRAFT_LOOT_BLOOD_EYE;
    public static ForgeConfigSpec.DoubleValue CRAFT_LOOT_ATUM_MEDAL;
    public static ForgeConfigSpec.DoubleValue CRAFT_LOOT_ADVANCED_CRAFT_RATE;
    public static ForgeConfigSpec.IntValue SUNDRY_CRAFT_CD;
    public static ForgeConfigSpec.IntValue SUNDRY_RAID_LIGHT_CD;
    public static ForgeConfigSpec.IntValue SUNDRY_COLLECTOR_LIFESPAN;
    public static ForgeConfigSpec.DoubleValue SUNDRY_DIFFICULTY_STAGE;
    public static ForgeConfigSpec.BooleanValue SUNDRY_VIS_CORE;
    public static ForgeConfigSpec.BooleanValue SUNDRY_INFUSER_QUICK;
    public static ForgeConfigSpec.BooleanValue SUNDRY_GAIA_MULTI;
    public static ForgeConfigSpec.BooleanValue SUNDRY_GUARDIAN_DEATH_PORTAL;
    public static ForgeConfigSpec.BooleanValue SUNDRY_PILLAGER_CHEST_PREVENT;
    public static ForgeConfigSpec.BooleanValue SUNDRY_DISABLE_SACRIFICE;
    public static ForgeConfigSpec.BooleanValue SUNDRY_DISABLE_TELEPORT_COMMAND;
    public static ForgeConfigSpec.BooleanValue SUNDRY_DISABLE_MAP_TELEPORT;
    public static ForgeConfigSpec.BooleanValue SUNDRY_ASTRAL_CRAFT_LIMIT;
    public static ForgeConfigSpec.BooleanValue SUNDRY_ANNOUNCEMENT;

    static
    {
        ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

        BUILDER.push("General");
        DIFFICULTY = BUILDER.comment("Difficulty Setting").defineInRange("World Difficulty",3.0,0.5,3.0);
        ADMIN_MENU = BUILDER.comment("Whether unable EMCWorld Admin Menu").define("Able Admin Menu",false);
        FREE_MODE = BUILDER.comment("Enable free mode to delete all gamestage").define("Able Free Mode",false);
        FORMAT = BUILDER.comment("Let the values of the modpack be displayed in K, M, G instead of the full value").define("Format Text",true);
        OFFLINE_WARN = BUILDER.comment("Give a warn when logging server to offline mode player").define("Warn player",true);
        SPONSOR_INFO = BUILDER.comment("Enable sponsorship messages that players send every 100 times they enter the game.").define("Enable Info",true);
        GENG = BUILDER.comment("Enable some funny things?").define("geng",true);
        //SHARE_EMC = BUILDER.comment("").define("Share EMC",false);
        BUILDER.pop();

        BUILDER.push("Game Setting");
        //PREVENT_TOSS_WEAPON = BUILDER.define("Prevent toss all weapons",true);

        BUILDER.push("EMC Modify");
        EMC_ATTACK = BUILDER.define("Attack",true);
        EMC_WAKE = BUILDER.define("Wake",true);
        EMC_HOE = BUILDER.define("Hoe",true);
        EMC_CONTAINER = BUILDER.define("Container",true);
        EMC_BUCKET = BUILDER.define("Bucket",true);
        EMC_CRAFT = BUILDER.define("Craft",true);
        EMC_DESTROY = BUILDER.define("Destroy",true);
        EMC_DEATH = BUILDER.define("Death",true);
        EMC_HURT = BUILDER.define("Hurt",true);
        EMC_PICK = BUILDER.define("Pick",true);
        EMC_LOCATE = BUILDER.define("Locate",true);
        EMC_TELEPORT = BUILDER.define("Teleport",true);
        EMC_QUEST = BUILDER.define("Quest",true);
        EMC_LOG = BUILDER.define("Log",true);
        BUILDER.pop();

        BUILDER.push("Upgrade");
        UPGRADE_WEIGHT = BUILDER.defineInRange("BaseWeight",10,1,30000);
        UPGRADE_WEIGHT_RATE = BUILDER.defineInRange("BaseWeightRate",1.344,1,100);
        UPGRADE_FAIL_LOSS = BUILDER.define("FailLoss",true);
        UPGRADE_PREFIX = BUILDER.define("PrefixDisplay",true);
        UPGRADE_GEM = BUILDER.define("GemDisplay",true);
        UPGRADE_STAR = BUILDER.define("StarDisplay",true);
        UPGRADE_DISABLE_ANVIL = BUILDER.define("DisableAnvil",true);
        UPGRADE_TULYE_SCROLL = BUILDER.define("TulyeScrollFail",true);
        UPGRADE_BX_SCROLL = BUILDER.define("BXScrollFail",true);
        UPGRADE_CRITICAL_OVERFLOW = BUILDER.define("CriticalOverflow",true);
        BUILDER.pop();

        BUILDER.push("Raid");
        RAID_PLAYER_LEVEL = BUILDER.define("PlayerLevel",true);
        RAID_PLAYER_DEATH = BUILDER.define("PlayerDeath",true);
        RAID_PLAYER_DAMAGE = BUILDER.define("PlayerDamage",true);
        RAID_SPONSORS = BUILDER.define("Sponsors",true);
        RAID_ILLAGER_TEAM_DAMAGE = BUILDER.define("TeamDamage",true);
        RAID_BLOCK_SLEEP = BUILDER.define("BlockSleep",true);
        BUILDER.pop();

        BUILDER.push("Champions");
        CHAMPIONS_EMC_FLAMING = BUILDER.define("EMCFlaming",true);
        CHAMPIONS_EMC_BROKEN = BUILDER.define("EMCBroken",true);
        CHAMPIONS_EMC_PROTECTING = BUILDER.define("EMCProtecting",true);
        CHAMPIONS_HEALING = BUILDER.define("Healing",true);
        CHAMPIONS_SHIELD_FLAMING = BUILDER.define("ShieldFlaming",true);
        BUILDER.pop();

        BUILDER.push("Craft & Loot");
        CRAFT_LOOT_EASY_DARK_MATTER = BUILDER.define("EasyDarkMatter",false);
        CRAFT_LOOT_BLOOD_EYE = BUILDER.defineInRange("BloodEye",0.12,0,1);
        CRAFT_LOOT_ATUM_MEDAL = BUILDER.defineInRange("AtumMedal",0.15,0,1);
        CRAFT_LOOT_ADVANCED_CRAFT_RATE = BUILDER.defineInRange("AdvanceCraftEMCRate",1d,0,Short.MAX_VALUE);
        BUILDER.pop();

        BUILDER.push("Sundry");
        SUNDRY_CRAFT_CD = BUILDER.defineInRange("CraftCD",18000,0,Integer.MAX_VALUE);
        SUNDRY_RAID_LIGHT_CD = BUILDER.defineInRange("RaidLightCD",1200,0,Integer.MAX_VALUE);
        SUNDRY_COLLECTOR_LIFESPAN = BUILDER.defineInRange("CollectorLifespan",720000,0,Integer.MAX_VALUE);
        SUNDRY_DIFFICULTY_STAGE = BUILDER.defineInRange("DifficultyStageRate",1d,0,1500);
        SUNDRY_VIS_CORE = BUILDER.define("VisCore",true);
        SUNDRY_INFUSER_QUICK = BUILDER.define("InfuserQuickCraft",false);
        SUNDRY_GAIA_MULTI = BUILDER.define("GaiaMulti",true);
        SUNDRY_GUARDIAN_DEATH_PORTAL = BUILDER.define("GuardianDeathPortal",true);
        SUNDRY_PILLAGER_CHEST_PREVENT = BUILDER.define("PillagerChestStageLimit",true);
        SUNDRY_DISABLE_SACRIFICE = BUILDER.define("DisableSacrifice",true);
        SUNDRY_DISABLE_TELEPORT_COMMAND = BUILDER.define("DisableCommandTeleport",true);
        SUNDRY_DISABLE_MAP_TELEPORT = BUILDER.define("DisableMapTeleport",true);
        SUNDRY_ASTRAL_CRAFT_LIMIT = BUILDER.define("AstralDimensionCraftLimit",true);
        SUNDRY_ANNOUNCEMENT = BUILDER.define("Announcement",true);
        RSAutomation = BUILDER.comment("Enable Refined Storage Automation,such as Pattern,Crafter").define("Enable Refined Storage Automation",false);
        LOTTERY = BUILDER.define("Enable Lottery",false);
        BUILDER.pop();

        BUILDER.push("Festival");
        SpringFestival = BUILDER.comment("Get gifts on certain festivals,only configurable festivals that give gifts").define("SpringFestival",true);
        LanternFestival = BUILDER.define("LanternFestival",true);
        FoolsDay = BUILDER.define("FoolsDay",true);
        DragonBoatFestival = BUILDER.define("DragonBoatFestival",true);
        MidAutumnFestival = BUILDER.define("MidAutumnFestival",true);
        BUILDER.pop();

        BUILDER.pop();

        EMCConstellationEffects.CONFIG.createEntries(BUILDER);
        EMCMantleEffects.CONFIG.createEntries(BUILDER);

        COMMON_CONFIG = BUILDER.build();
    }
}
