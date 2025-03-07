package biggestxuan.emcworld.common.registry;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/24
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.item.base.BaseGetStageItem;
import biggestxuan.emcworld.common.compact.MysticalAgriculture.EMCWorldEssenceItem;
import biggestxuan.emcworld.common.compact.MysticalAgriculture.EMCWorldMysticalAgriculturePlugin;
import biggestxuan.emcworld.common.items.CDReduceScroll.CommonCDReduceScrollItem;
import biggestxuan.emcworld.common.items.CDReduceScroll.CraftCDReduceScrollItem;
import biggestxuan.emcworld.common.items.Equipment.Scroll.*;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Dagger.RainbowDagger;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Gun.ShengXuan;
import biggestxuan.emcworld.common.compact.Mekanism.Module.EMCProtect.EnergyProtectModuleItem;
import biggestxuan.emcworld.common.compact.Mekanism.Module.Infinity.InfinityModuleItem;
import biggestxuan.emcworld.common.items.Curios.EMCShieldSupply;
import biggestxuan.emcworld.common.items.Curios.NuclearBall;
import biggestxuan.emcworld.common.items.Curios.StoredTotem;
import biggestxuan.emcworld.common.items.*;
import biggestxuan.emcworld.common.items.Equipment.Armor.fireRedArmor;
import biggestxuan.emcworld.common.items.Equipment.Armor.guardianArmor;
import biggestxuan.emcworld.common.items.Equipment.BaseWeaponGemItem;
import biggestxuan.emcworld.common.items.Equipment.PrefixScroll;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Dagger.DaggerItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Dagger.NightLight;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Dagger.RedGreenDagger;
import biggestxuan.emcworld.common.items.Equipment.Weapon.GodWeapon.*;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Gun.GunItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Gun.RainbowGunItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.LuckyItem.LuckyItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Other.HamBat;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Staff.NatureStaff;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Staff.PurpleStaff;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Staff.RainbowStaff;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Staff.StaffItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Sword.*;
import biggestxuan.emcworld.common.items.Equipment.Weapon.WarHammer.Creation;
import biggestxuan.emcworld.common.items.Equipment.Weapon.WarHammer.RainbowWarhammer;
import biggestxuan.emcworld.common.items.Equipment.Weapon.WarHammer.SuperStar;
import biggestxuan.emcworld.common.items.Equipment.Weapon.WarHammer.WarHammerItem;
import biggestxuan.emcworld.common.items.FestivalItem.MoonCake;
import biggestxuan.emcworld.common.items.FestivalItem.TangYuan;
import biggestxuan.emcworld.common.items.FestivalItem.YearCake;
import biggestxuan.emcworld.common.items.FestivalItem.ZongZi;
import biggestxuan.emcworld.common.items.Food.MoneyFood;
import biggestxuan.emcworld.common.items.ModPack.EndLight;
import biggestxuan.emcworld.common.items.ModPack.RainFallStar;
import biggestxuan.emcworld.common.items.ModPack.Voucher;
import biggestxuan.emcworld.common.items.ProfessionalItem.AddMaxLevelItem;
import biggestxuan.emcworld.common.items.ProfessionalItem.ClearProfessionItem;
import biggestxuan.emcworld.common.items.ProfessionalItem.ProfessionalItem;
import biggestxuan.emcworld.common.items.RaidItem.IllagerShard;
import biggestxuan.emcworld.common.items.RaidItem.RaidLightItem;
import biggestxuan.emcworld.common.items.SponsorsItem.*;
import dev.latvian.mods.projectex.Matter;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@SuppressWarnings("unused")
public class EWItems {
    private static final EMCWorldAPI api = EMCWorldAPI.getInstance();

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EMCWorld.MODID);

    public static final RegistryObject<Item> SMALL_EMC_GEM = ITEMS.register("small_emc_gem", EMCGemItem::new);
    public static final RegistryObject<Item> BIG_EMC_GEM = ITEMS.register("big_emc_gem", EMCGemItem::new);
    public static final RegistryObject<Item> BIGGEST_EMC_GEM = ITEMS.register("biggest_emc_gem", EMCGemItem::new);
    public static final RegistryObject<Item> ADVANCED_EMC_GEM = ITEMS.register("advanced_emc_gem", EMCGemItem::new);
    public static final RegistryObject<Item> SUPER_EMC_GEM = ITEMS.register("super_emc_gem", EMCGemItem::new);
    public static final RegistryObject<Item> EPIC_EMC_GEM = ITEMS.register("epic_emc_gem", EMCGemItem::new);
    public static final RegistryObject<Item> INFINITY_EMC_GEM = ITEMS.register("infinity_emc_gem", EMCGemItem::new);
    public static final RegistryObject<Item> ACTIVATED_CHARCOAL = ITEMS.register("activated_charcoal", EWItem::new);
    public static final RegistryObject<Item> ENRICHED_IRON = ITEMS.register("enriched_iron", EWItem::new);
    public static final RegistryObject<Item> ENRICHED_NICKEL = ITEMS.register("enriched_nickel", EWItem::new);
    public static final RegistryObject<Item> ENRICHED_SILVER = ITEMS.register("enriched_silver", EWItem::new);
    public static final RegistryObject<Item> ENRICHED_ENDER = ITEMS.register("enriched_ender", EWItem::new);
    public static final RegistryObject<Item> ENRICHED_GOBBER = ITEMS.register("enriched_gobber", EWItem::new);
    public static final RegistryObject<Item> LUCKY_GEM_BLUE = ITEMS.register("lucky_gem_blue",() -> new LuckyItem(1.25f,1.0f));
    public static final RegistryObject<Item> LUCKY_GEM_RED = ITEMS.register("lucky_gem_red",() -> new LuckyItem(1.5f,1.5f));
    public static final RegistryObject<Item> LUCKY_GEM_PURPLE = ITEMS.register("lucky_gem_purple",() -> new LuckyItem(1.75f,2.0f));
    public static final RegistryObject<Item> LUCKY_GEM_GOLD = ITEMS.register("lucky_gem_gold",() -> new LuckyItem(2.1f,2.5f));
    public static final RegistryObject<Item> LUCKY_GEM_EMERALD = ITEMS.register("lucky_gem_emerald",() -> new LuckyItem(3f,3f));
    public static final RegistryObject<Item> MOONCAKE = ITEMS.register("mooncake", MoonCake::new);
    public static final RegistryObject<Item> TANGYUAN = ITEMS.register("tangyuan", TangYuan::new);
    public static final RegistryObject<Item> YEARCAKE = ITEMS.register("year_cake", YearCake::new);
    public static final RegistryObject<Item> ZONGZI = ITEMS.register("zongzi", ZongZi::new);
    public static final RegistryObject<Item> XIANGSHUSHUMIAO_PILLOW = ITEMS.register("xiangshushumiao_pillow", XiangshushumiaoPillow::new);
    public static final RegistryObject<Item> EXCEPTION_APPLE = ITEMS.register("exception_apple", ExceptionApple::new);
    public static final RegistryObject<Item> EMC_CHECK = ITEMS.register("emc_check", EWItem::new);
    public static final RegistryObject<Item> MONEY = ITEMS.register("money", MoneyFood::new);
    public static final RegistryObject<Item> STONE_SHARD = ITEMS.register("stone_shard", EWItem::new);
    public static final RegistryObject<Item> UNREAL_METAL = ITEMS.register("unreal_metal", EWItem::new);
    public static final RegistryObject<Item> GOD_STEEL_INGOT = ITEMS.register("god_steel_ingot", EWItem::new);
    public static final RegistryObject<Item> BLOOD_EYE = ITEMS.register("blood_eye", EWItem::new);
    public static final RegistryObject<Item> COPPER_MEDAL = ITEMS.register("copper_medal", EWItem::new);
    public static final RegistryObject<Item> SILVER_MEDAL = ITEMS.register("silver_medal", EWItem::new);
    public static final RegistryObject<Item> GOLD_MEDAL = ITEMS.register("gold_medal", EWItem::new);
    public static final RegistryObject<Item> GAIA_NUGGET = ITEMS.register("gaia_nugget", EWItem::new);
    public static final RegistryObject<Item> TWILIGHT_KEY = ITEMS.register("twilight_key", () -> new BaseGetStageItem("twilight"));
    public static final RegistryObject<Item> BASE_KEY = ITEMS.register("base_key",EWItem::new);
    public static final RegistryObject<Item> INFUSE_CORE = ITEMS.register("infuse_core",EWItem::new);
    public static final RegistryObject<Item> PREFIX_CORE = registryBlock("prefix_core", EWBlocks.PREFIX_CORE);
    public static final RegistryObject<Item> TOP_CORE = registryBlock("top_core", EWBlocks.TOP_CORE);
    public static final RegistryObject<Item> EMC_ORE_CORE = registryEMCOreCoreBlock("emc_ore_core",EWBlocks.EMC_ORE_CORE);
    public static final RegistryObject<Item> EMC_CORE_ASSEMBLER = registryEMCOreCoreBlock("emc_core_assembler",EWBlocks.EMC_CORE_ASSEMBLER);
    public static final RegistryObject<Item> EMC_CORE_PULLER = registryEMCOreCoreBlock("emc_core_puller",EWBlocks.EMC_CORE_PULLER);
    public static final RegistryObject<Item> EMC_CORE_GENERATOR = registryEMCOreCoreBlock("emc_core_generator",EWBlocks.EMC_CORE_GENERATOR);
    public static final RegistryObject<Item> EMC_CORE_PUNCHER = registryEMCOreCoreBlock("emc_core_puncher",EWBlocks.EMC_CORE_PUNCHER);
    public static final RegistryObject<Item> EMC_SUPER = registryBlock("emc_super", EWBlocks.EMC_SUPER);
    public static final RegistryObject<Item> STAR_PEDESTAL = registryBlock("star_pedestal", EWBlocks.STAR_PEDESTAL);
    public static final RegistryObject<Item> PROFESSION_SWORD = ITEMS.register("profession_sword",() -> new ProfessionalItem(1));
    public static final RegistryObject<Item> PROFESSION_TANK = ITEMS.register("profession_tank",() -> new ProfessionalItem(2));
    public static final RegistryObject<Item> NOPOWER_STAFF = ITEMS.register("nopower_staff", () -> new ProfessionalItem(3));
    public static final RegistryObject<Item> BROKEN_DAGGER = ITEMS.register("broken_dagger",() -> new ProfessionalItem(4));
    public static final RegistryObject<Item> BROKEN_HAMMER = ITEMS.register("broken_hammer",() -> new ProfessionalItem(5));
    public static final RegistryObject<Item> BROKEN_GUN = ITEMS.register("broken_gun",() -> new ProfessionalItem(6));
    public static final RegistryObject<Item> BIGGEST_XUAN_INGOT = ITEMS.register("biggest_xuan_ingot",EWItem::new);
    public static final RegistryObject<Item> ILLAGER_SHARD = ITEMS.register("illager_shard", IllagerShard::new);
    public static final RegistryObject<Item> SKILL_ITEM1 = ITEMS.register("skill_item1",EWItem::new);
    public static final RegistryObject<Item> SKILL_ITEM2 = ITEMS.register("skill_item2",EWItem::new);
    public static final RegistryObject<Item> CHAOS_SWORD = ITEMS.register("chaos_sword", ChaosSword::new);
    public static final RegistryObject<Item> GAIA_SWORD = ITEMS.register("gaia_sword", GaiaSword::new);
    public static final RegistryObject<Item> EMC_SHIELD_SUPPLY = ITEMS.register("emc_shield_supply", EMCShieldSupply::new);
    public static final RegistryObject<Item> HAM_BAT = ITEMS.register("ham_bat", HamBat::new);
    public static final RegistryObject<Item> RED_MATTER_CRYSTAL = ITEMS.register("red_matter_crystal",() -> new AddMaxLevelItem(20,0));
    public static final RegistryObject<Item> ANCIENT_ICON = ITEMS.register("ancient_icon",() -> new AddMaxLevelItem(30,0));
    public static final RegistryObject<Item> FANTASY_GEM = ITEMS.register("fantasy_gem",() -> new AddMaxLevelItem(40,0));
    public static final RegistryObject<Item> ANOTHER_WORLD_CORE = ITEMS.register("another_world_core",() -> new AddMaxLevelItem(50,0));
    public static final RegistryObject<Item> DEMON_DUST = ITEMS.register("demon_dust",() -> new AddMaxLevelItem(60,0));
    public static final RegistryObject<Item> HERO_MEDAL = ITEMS.register("hero_medal",() -> new AddMaxLevelItem(70,0));
    public static final RegistryObject<Item> BROKEN_DIAMOND_SWORD = ITEMS.register("broken_diamond_sword",() -> new AddMaxLevelItem(80,0));
    public static final RegistryObject<Item> BRIGHT_STONE = ITEMS.register("bright_stone",() -> new AddMaxLevelItem(90,1));
    public static final RegistryObject<Item> DARK_STONE = ITEMS.register("dark_stone",() -> new AddMaxLevelItem(90,2));
    public static final RegistryObject<Item> EVIL_BOOK = ITEMS.register("evil_book",() -> new AddMaxLevelItem(100,0));
    public static final RegistryObject<Item> ULTIMATE_SINGULARITY = ITEMS.register("ultimate_singularity",() -> new AddMaxLevelItem(110,0));
    public static final RegistryObject<Item> EMC_LEAF = ITEMS.register("emc_leaf",EWItem::new);
    public static final RegistryObject<Item> EMC_TRAIT_CORE = ITEMS.register("emc_trait_core",EMCWorldTraitCoreItem::new);
    public static final RegistryObject<Item> EMCWORLD_QUEST_ITEM = ITEMS.register("quest_item",EMCWorldQuestsItem::new);
    public static final RegistryObject<Item> NETHERITE_STICK = ITEMS.register("netherite_stick",EWItem::new);
    public static final RegistryObject<Item> NONAME_CATFOOD = ITEMS.register("noname_catfood", NoNameCatFood::new);
    public static final RegistryObject<Item> PREFIX_SCROLL = ITEMS.register("prefix_scroll", PrefixScroll::new);
    public static final RegistryObject<Item> CRAFT_CD_SCROLL = ITEMS.register("craft_cd_scroll", CraftCDReduceScrollItem::new);
    public static final RegistryObject<Item> COMMON_CD_SCROLL = ITEMS.register("common_cd_scroll", CommonCDReduceScrollItem::new);
    public static final RegistryObject<Item> RAID_LIGHT = ITEMS.register("raid_light", RaidLightItem::new);
    public static final RegistryObject<Item> ABUNANA_LOOT = ITEMS.register("abunana_loot", AbunanaLoot::new);
    public static final RegistryObject<Item> BLUE_MATTER = ITEMS.register("blue_matter",() -> new MatterItem(Matter.BLUE));
    public static final RegistryObject<Item> CLAY_MATTER = ITEMS.register("clay_matter",() -> new MatterItem(Matter.FINAL,2));
    public static final RegistryObject<Item> CYAN_MATTER = ITEMS.register("cyan_matter",() -> new MatterItem(Matter.CYAN));
    public static final RegistryObject<Item> FADING_MATTER = ITEMS.register("fading_matter",() -> new MatterItem(Matter.FADING,2));
    public static final RegistryObject<Item> GREEN_MATTER = ITEMS.register("green_matter",() -> new MatterItem(Matter.GREEN));
    public static final RegistryObject<Item> LIME_MATTER = ITEMS.register("lime_matter",() -> new MatterItem(Matter.LIME));
    public static final RegistryObject<Item> MAGENTA_MATTER = ITEMS.register("magenta_matter",() -> new MatterItem(Matter.MAGENTA));
    public static final RegistryObject<Item> ORANGE_MATTER = ITEMS.register("orange_matter",() -> new MatterItem(Matter.ORANGE,1));
    public static final RegistryObject<Item> PINK_MATTER = ITEMS.register("pink_matter",() -> new MatterItem(Matter.PINK));
    public static final RegistryObject<Item> PURPLE_MATTER = ITEMS.register("purple_matter",() -> new MatterItem(Matter.PURPLE));
    public static final RegistryObject<Item> VIOLET_MATTER = ITEMS.register("violet_matter",() -> new MatterItem(Matter.VIOLET));
    public static final RegistryObject<Item> WHITE_MATTER = ITEMS.register("white_matter",() -> new MatterItem(Matter.WHITE,3));
    public static final RegistryObject<Item> YELLOW_MATTER = ITEMS.register("yellow_matter",() -> new MatterItem(Matter.YELLOW));
    public static final RegistryObject<Item> ICE_SWORD = ITEMS.register("god_ice_sword", IceSword::new);
    public static final RegistryObject<Item> FIRE_SWORD = ITEMS.register("god_fire_sword", FireSword::new);
    public static final RegistryObject<Item> NATURE_SWORD = ITEMS.register("god_nature_sword", NatureSword::new);
    public static final RegistryObject<Item> NULL_SWORD = ITEMS.register("god_null_sword", NullSword::new);
    public static final RegistryObject<Item> CHARA_SWORD = ITEMS.register("chara_sword", CharaSword::new);
    public static final RegistryObject<Item> RAINBOW_SWORD = ITEMS.register("rainbow_sword", RainbowSword::new);
    public static final RegistryObject<Item> RAINBOW_DAGGER = ITEMS.register("rainbow_dagger", RainbowDagger::new);
    public static final RegistryObject<Item> RAINBOW_WARHAMMER = ITEMS.register("rainbow_warhammer", RainbowWarhammer::new);
    public static final RegistryObject<Item> SCROLL_WHITE = ITEMS.register("scroll_white",() -> new ScrollItem(1.0f,15));
    public static final RegistryObject<Item> SCROLL_GREEN = ITEMS.register("scroll_green",() -> new ScrollItem(1.0f,45));
    public static final RegistryObject<Item> SCROLL_BLUE = ITEMS.register("scroll_blue",() -> new ScrollItem(1.2f,120));
    public static final RegistryObject<Item> SCROLL_PURPLE = ITEMS.register("scroll_purple",() -> new ScrollItem(1.75f,425));
    public static final RegistryObject<Item> SCROLL_RED = ITEMS.register("scroll_red",() -> new ScrollItem(2f,1500));
    public static final RegistryObject<Item> SCROLL_GOLD = ITEMS.register("scroll_gold", () -> new ScrollItem(3f,5000));
    public static final RegistryObject<Item> SCROLL_TULYE = ITEMS.register("scroll_tulye", TulyeScroll::new);
    public static final RegistryObject<Item> SCROLL_BX = ITEMS.register("scroll_bx", BiggestXuanScroll::new);
    public static final RegistryObject<Item> SCROLL_GAIA = ITEMS.register("scroll_gaia", GaiaScroll::new);
    public static final RegistryObject<Item> SCROLL_BASE_EMC = ITEMS.register("scroll_base_emc", () -> new EMCGodScroll(1,200));
    public static final RegistryObject<Item> SCROLL_ADVANCED_EMC = ITEMS.register("scroll_advanced_emc",() -> new EMCGodScroll(2,700));
    public static final RegistryObject<Item> SCROLL_SUPER_EMC = ITEMS.register("scroll_super_emc", () -> new EMCGodScroll(3,2000));
    public static final RegistryObject<Item> SCROLL_ENLIGHTENING = ITEMS.register("scroll_enlightening_glimmer", FixedScroll.EnlighteningScroll::new);
    public static final RegistryObject<Item> SCROLL_MYSTIC = ITEMS.register("scroll_mystic_shadow", FixedScroll.MysticScroll::new);
    public static final RegistryObject<Item> SCROLL_AZURE = ITEMS.register("scroll_celestial_azure", FixedScroll.AzureScroll::new);
    public static final RegistryObject<Item> SCROLL_FEAST = ITEMS.register("scroll_feast", () -> new FeastScroll(100));
    public static final RegistryObject<Item> SCROLL_GOURMAND_FEAST = ITEMS.register("scroll_gourmand_feast", () -> new FeastScroll(300));
    public static final RegistryObject<Item> SCROLL_WOLF_FEAST = ITEMS.register("scroll_voracious_wolf", () -> new FeastScroll(500));
    public static final RegistryObject<Item> UnEqual = ITEMS.register("unequal", NoEqualItem::new);
    public static final RegistryObject<Item> ANTIMATTER_DROP = ITEMS.register("drop_antimatter", () -> new EWItem(Rarity.EPIC));
    public static final RegistryObject<Item> END_LIGHT = ITEMS.register("end_light", EndLight::new);
    public static final RegistryObject<Item> EXORCISM_CANDLE = ITEMS.register("exorcism_candle", () -> new EWItem(){
        @Override
        public void appendHoverText(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, @Nonnull List<ITextComponent> p_77624_3_, @Nonnull ITooltipFlag p_77624_4_){
            p_77624_3_.add(EMCWorld.tc("tooltip.emcworld.exorcism_candle"));
        }
    });
    public static final RegistryObject<Item> FXT_XY = ITEMS.register("fxt_xy", RainFallStar::new);
    public static final RegistryObject<Item> EPIC_ESSENCE = ITEMS.register("epic_essence",() -> new EMCWorldEssenceItem(EMCWorldMysticalAgriculturePlugin.CROP_TIER_7));
    public static final RegistryObject<Item> FINAL_ESSENCE = ITEMS.register("final_essence",() -> new EMCWorldEssenceItem(EMCWorldMysticalAgriculturePlugin.CROP_TIER_8));
    public static final RegistryObject<Item> ICE_CREAM = ITEMS.register("ice_cream",IceCream::new);
    public static final RegistryObject<Item> STAINLESS_STEEL = ITEMS.register("stainless_steel",() -> new EWItem(Rarity.UNCOMMON));
    public static final RegistryObject<Item> LOTTERY = ITEMS.register("lottery",LotteryItem::new);
    public static final RegistryObject<Item> HARD_STEEL = ITEMS.register("hard_steel",() -> new EWItem(Rarity.UNCOMMON));
    public static final RegistryObject<Item> NETHER_KEY = ITEMS.register("nether_key",() -> new BaseGetStageItem("nether"));
    public static final RegistryObject<Item> FLYING_GEM = ITEMS.register("flying_gem",() -> new BaseGetStageItem("fly"));
    public static final RegistryObject<Item> RAINBOW_INGOT = ITEMS.register("rainbow_ingot",() -> new EWItem(Rarity.UNCOMMON));
    public static final RegistryObject<Item> ATM_SWORD = ITEMS.register("atm_sword",AtmSword::new);
    public static final RegistryObject<Item> INFINITY_SWORD = ITEMS.register("infinity_sword", InfinitySword::new);
    public static final RegistryObject<Item> CREATION = ITEMS.register("creation", Creation::new);
    public static final RegistryObject<Item> SUPER_STAR = ITEMS.register("super_star", SuperStar::new);
    public static final RegistryObject<Item> NIGHT_LIGHT = ITEMS.register("night_light", NightLight::new);
    public static final RegistryObject<Item> RED_GREEN_DAGGER = ITEMS.register("red_green_dagger", RedGreenDagger::new);
    public static final RegistryObject<Item> UNIVERSAL_BALL = ITEMS.register("universal_ball",() -> new FinalItem(1));
    public static final RegistryObject<Item> EMC_CORE = ITEMS.register("emc_core",() -> new FinalItem(2));
    public static final RegistryObject<Item> WOODEN_INGOT = ITEMS.register("wooden_ingot",() -> new FinalItem(2));
    public static final RegistryObject<Item> STONE_INGOT = ITEMS.register("stone_ingot",() -> new FinalItem(2));
    public static final RegistryObject<Item> RESET_SCROLL = ITEMS.register("reset_scroll", ClearProfessionItem::new);
    public static final RegistryObject<Item> RESTORE_SCROLL = ITEMS.register("restore_scroll", RestoreStageScroll::new);
    public static final RegistryObject<Item> RUNE_INGOT = ITEMS.register("rune_ingot",() -> new FinalItem(2));
    public static final RegistryObject<Item> NATURE_INGOT = ITEMS.register("nature_ingot",() -> new FinalItem(2));
    public static final RegistryObject<Item> DRAGON_STEEL = ITEMS.register("dragon_steel",() -> new FinalItem(1));
    public static final RegistryObject<Item> INFINITY_CATALYST = ITEMS.register("infinity_catalyst",() -> new FinalItem(3));
    public static final RegistryObject<Item> CRYSTAL_MATRIX_INGOT = ITEMS.register("crystal_matrix_ingot",() -> new FinalItem(3));
    public static final RegistryObject<Item> VIBRANIUM_SWORD = ITEMS.register("vibranium_sword", VibraniumSword::new);
    public static final RegistryObject<Item> UNOBTAINIUM_SWORD = ITEMS.register("unobtainium_sword", UnobtainiumSword::new);
    public static final RegistryObject<Item> BLOOD_GEMSTONE = ITEMS.register("blood_gemstone",() -> new BaseWeaponGemItem(BaseWeaponGemItem.WeaponGem.BLOOD));
    public static final RegistryObject<Item> NATURE_GEMSTONE = ITEMS.register("nature_gemstone",() -> new BaseWeaponGemItem(BaseWeaponGemItem.WeaponGem.NATURE));
    public static final RegistryObject<Item> LAKE_GEMSTONE = ITEMS.register("lake_gemstone",() -> new BaseWeaponGemItem(BaseWeaponGemItem.WeaponGem.LAKE));
    public static final RegistryObject<Item> ABYSS_GEMSTONE = ITEMS.register("abyss_gemstone",() -> new BaseWeaponGemItem(BaseWeaponGemItem.WeaponGem.ABYSS));
    public static final RegistryObject<Item> ILLAGER_GEM = ITEMS.register("illager_gem", EWItem::new);
    public static final RegistryObject<Item> EMC_HEALING_BAG = ITEMS.register("emc_healing_bag",EMCHealingItem::new);
    public static final RegistryObject<Item> EMC_CHARGE_GEM = ITEMS.register("emc_charge_gem",EMCChargeItem::new);
    public static final RegistryObject<Item> ENERGY_PROTECT_MODULE = ITEMS.register("energy_protect_module", () -> new EnergyProtectModuleItem(EWModules.ENERGY_PROTECT_MODULE));
    public static final RegistryObject<Item> INFINITY_MODULE = ITEMS.register("infinity_module", () -> new InfinityModuleItem(EWModules.INFINITY_MODULE_UNIT_MODULE));
    public static final RegistryObject<Item> CXK_ADVENTURE_RECORD = ITEMS.register("ikun_adventure_record",() -> new EWRecordItem(EWSounds.CXK_ADVENTURE,true));
    public static final RegistryObject<Item> CXK_RICH_RECORD = ITEMS.register("ikun_rich_record",() -> new EWRecordItem(EWSounds.CXK_RICH,true));
    public static final RegistryObject<Item> CXK_RABBIT_RECORD = ITEMS.register("ikun_rabbit_record",() -> new EWRecordItem(EWSounds.CXK_RABBIT,true));

    public static final RegistryObject<Item> FIRE_RED_HELMET = ITEMS.register("fire_red_helmet",()->new fireRedArmor(1));
    public static final RegistryObject<Item> FIRE_RED_CHESTPLATE = ITEMS.register("fire_red_chestplate",()->new fireRedArmor(2));
    public static final RegistryObject<Item> FIRE_RED_LEGGINGS = ITEMS.register("fire_red_leggings",()->new fireRedArmor(3));
    public static final RegistryObject<Item> FIRE_RED_BOOTS = ITEMS.register("fire_red_boots",()->new fireRedArmor(4));
    public static final RegistryObject<Item> GUARDIAN_HELMET = ITEMS.register("guardian_helmet",()->new guardianArmor(1));
    public static final RegistryObject<Item> GUARDIAN_CHESTPLATE = ITEMS.register("guardian_chestplate",()->new guardianArmor(2));
    public static final RegistryObject<Item> GUARDIAN_LEGGINGS = ITEMS.register("guardian_leggings",()->new guardianArmor(3));
    public static final RegistryObject<Item> GUARDIAN_BOOTS = ITEMS.register("guardian_boots",()->new guardianArmor(4));

    public static final RegistryObject<Item> WOODEN_WARHAMMER = ITEMS.register("wooden_warhammer",() -> new WarHammerItem(api.getWarHammerTier("wooden")));
    public static final RegistryObject<Item> STONE_WARHAMMER = ITEMS.register("stone_warhammer",() -> new WarHammerItem(api.getWarHammerTier("stone")));
    public static final RegistryObject<Item> IRON_WARHAMMER = ITEMS.register("iron_warhammer",() -> new WarHammerItem(api.getWarHammerTier("iron")));
    public static final RegistryObject<Item> GOLDEN_WARHAMMER = ITEMS.register("golden_warhammer",() -> new WarHammerItem(api.getWarHammerTier("golden")));
    public static final RegistryObject<Item> DIAMOND_WARHAMMER = ITEMS.register("diamond_warhammer",() -> new WarHammerItem(api.getWarHammerTier("diamond")));
    public static final RegistryObject<Item> GAIA_WARHAMMER = ITEMS.register("gaia_warhammer",() -> new WarHammerItem(api.getWarHammerTier("gaia")));
    public static final RegistryObject<Item> NETHERITE_WARHAMMER = ITEMS.register("netherite_warhammer",() -> new WarHammerItem(api.getWarHammerTier("netherite")));

    public static final RegistryObject<Item> WOODEN_DAGGER = ITEMS.register("wooden_dagger",() -> new DaggerItem(api.getDaggerTier("wooden")));
    public static final RegistryObject<Item> STONE_DAGGER = ITEMS.register("stone_dagger",() -> new DaggerItem(api.getDaggerTier("stone")));
    public static final RegistryObject<Item> IRON_DAGGER = ITEMS.register("iron_dagger",() -> new DaggerItem(api.getDaggerTier("iron")));
    public static final RegistryObject<Item> GOLDEN_DAGGER = ITEMS.register("golden_dagger",() -> new DaggerItem(api.getDaggerTier("golden")));
    public static final RegistryObject<Item> DIAMOND_DAGGER = ITEMS.register("diamond_dagger",() -> new DaggerItem(api.getDaggerTier("diamond")));
    public static final RegistryObject<Item> GAIA_DAGGER = ITEMS.register("gaia_dagger",() -> new DaggerItem(api.getDaggerTier("gaia")));
    public static final RegistryObject<Item> NETHERITE_DAGGER = ITEMS.register("netherite_dagger",() -> new DaggerItem(api.getDaggerTier("netherite")));

    public static final RegistryObject<Item> WOODEN_GUN = ITEMS.register("wooden_gun",() -> new GunItem(api.getGunTier("wooden")));
    public static final RegistryObject<Item> STONE_GUN = ITEMS.register("stone_gun",() -> new GunItem(api.getGunTier("stone")));
    public static final RegistryObject<Item> IRON_GUN = ITEMS.register("iron_gun",() -> new GunItem(api.getGunTier("iron")));
    public static final RegistryObject<Item> GOLDEN_GUN = ITEMS.register("golden_gun",() -> new GunItem(api.getGunTier("golden")));
    public static final RegistryObject<Item> DIAMOND_GUN = ITEMS.register("diamond_gun",() -> new GunItem(api.getGunTier("diamond")));
    public static final RegistryObject<Item> GAIA_GUN = ITEMS.register("gaia_gun",() -> new GunItem(api.getGunTier("gaia")));
    public static final RegistryObject<Item> RAINBOW_GUN = ITEMS.register("rainbow_gun", RainbowGunItem::new);
    public static final RegistryObject<Item> NETHERITE_GUN = ITEMS.register("netherite_gun",() -> new GunItem(api.getGunTier("netherite")));
    public static final RegistryObject<Item> SHENG_XUAN = ITEMS.register("shengxuan", ShengXuan::new);

    public static final RegistryObject<Item> EMC_FLOWER = registryBlock("emc_flower",EWBlocks.EMC_FLOWER);

    public static final RegistryObject<Item> WOODEN_STAFF = ITEMS.register("wooden_staff",() -> new StaffItem(api.getStaffTier("wooden")));
    public static final RegistryObject<Item> STONE_STAFF = ITEMS.register("stone_staff",() -> new StaffItem(api.getStaffTier("stone")));
    public static final RegistryObject<Item> IRON_STAFF = ITEMS.register("iron_staff",() -> new StaffItem(api.getStaffTier("iron")));
    public static final RegistryObject<Item> GOLDEN_STAFF = ITEMS.register("golden_staff",() -> new StaffItem(api.getStaffTier("golden")));
    public static final RegistryObject<Item> DIAMOND_STAFF = ITEMS.register("diamond_staff",() -> new StaffItem(api.getStaffTier("diamond")));
    public static final RegistryObject<Item> GAIA_STAFF = ITEMS.register("gaia_staff",() -> new StaffItem(api.getStaffTier("gaia")));
    public static final RegistryObject<Item> NETHERITE_STAFF = ITEMS.register("netherite_staff",() -> new StaffItem(api.getStaffTier("netherite")));
    public static final RegistryObject<Item> RAINBOW_STAFF = ITEMS.register("rainbow_staff", RainbowStaff::new);
    public static final RegistryObject<Item> NATURE_STAFF = ITEMS.register("nature_staff", NatureStaff::new);
    public static final RegistryObject<Item> PURPLE_STAFF = ITEMS.register("purple_staff", PurpleStaff::new);

    public static final RegistryObject<Item> CRYSTAL_EMC_GEM = ITEMS.register("crystal_emc_gem", EWOresItem::new);
    public static final RegistryObject<Item> SHARD_EMC_GEM = ITEMS.register("shard_emc_gem", EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_EMC_GEM = ITEMS.register("clump_emc_gem", EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_EMC_GEM = ITEMS.register("dirty_dust_emc_gem", EWOresItem::new);
    public static final RegistryObject<Item> DUST_EMC_GEM = ITEMS.register("dust_emc_gem", EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_GOBBER = ITEMS.register("crystal_gobber", EWOresItem::new);
    public static final RegistryObject<Item> SHARD_GOBBER = ITEMS.register("shard_gobber", EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_GOBBER = ITEMS.register("clump_gobber", EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_GOBBER = ITEMS.register("dirty_dust_gobber", EWOresItem::new);
    public static final RegistryObject<Item> DUST_EMC_GOBBER = ITEMS.register("dust_gobber", EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_NETHER_GOBBER = ITEMS.register("crystal_nether_gobber", EWOresItem::new);
    public static final RegistryObject<Item> SHARD_NETHER_GOBBER = ITEMS.register("shard_nether_gobber", EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_NETHER_GOBBER = ITEMS.register("clump_nether_gobber", EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_NETHER_GOBBER = ITEMS.register("dirty_dust_nether_gobber", EWOresItem::new);
    public static final RegistryObject<Item> DUST_NETHER_GOBBER = ITEMS.register("dust_nether_gobber", EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_END_GOBBER = ITEMS.register("crystal_end_gobber", EWOresItem::new);
    public static final RegistryObject<Item> SHARD_END_GOBBER = ITEMS.register("shard_end_gobber", EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_END_GOBBER = ITEMS.register("clump_end_gobber", EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_END_GOBBER = ITEMS.register("dirty_dust_end_gobber", EWOresItem::new);
    public static final RegistryObject<Item> DUST_END_GOBBER = ITEMS.register("dust_end_gobber", EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_STARSMETAL = ITEMS.register("crystal_starmetal", EWOresItem::new);
    public static final RegistryObject<Item> SHARD_STARSMETAL = ITEMS.register("shard_starmetal", EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_STARSMETAL = ITEMS.register("clump_starmetal", EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_STARSMETAL = ITEMS.register("dirty_dust_starmetal", EWOresItem::new);
    public static final RegistryObject<Item> DUST_STARSMETAL = ITEMS.register("dust_starmetal", EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_PENDORITE= ITEMS.register("crystal_pendorite",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_PENDORITE= ITEMS.register("shard_pendorite",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_PENDORITE= ITEMS.register("clump_pendorite",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_PENDORITE= ITEMS.register("dirty_dust_pendorite",EWOresItem::new);
    public static final RegistryObject<Item> DUST_PENDORITE= ITEMS.register("dust_pendorite",EWOresItem::new);

    public static final RegistryObject<Item> CLUMP_KNIGHTMETAL = ITEMS.register("clump_knightmetal", EWOresItem::new);
    public static final RegistryObject<Item> CRYSTAL_KNIGHTMETAL = ITEMS.register("crystal_knightmetal", EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_KNIGHTMETAL = ITEMS.register("dirty_dust_knightmetal", EWOresItem::new);
    public static final RegistryObject<Item> DUST_KNIGHTMETAL = ITEMS.register("dust_knightmetal", EWOresItem::new);
    public static final RegistryObject<Item> SHARD_KNIGHTMETAL = ITEMS.register("shard_knightmetal", EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_AQUAMARINE= ITEMS.register("crystal_aquamarine",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_AQUAMARINE= ITEMS.register("shard_aquamarine",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_AQUAMARINE= ITEMS.register("clump_aquamarine",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_AQUAMARINE= ITEMS.register("dirty_dust_aquamarine",EWOresItem::new);
    public static final RegistryObject<Item> DUST_AQUAMARINE= ITEMS.register("dust_aquamarine",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_ROSE_COPPER= ITEMS.register("crystal_rose_copper",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_ROSE_COPPER= ITEMS.register("shard_rose_copper",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_ROSE_COPPER= ITEMS.register("clump_rose_copper",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_ROSE_COPPER= ITEMS.register("dirty_dust_rose_copper",EWOresItem::new);
    public static final RegistryObject<Item> DUST_ROSE_COPPER= ITEMS.register("dust_rose_copper",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_ORATCHALCUM= ITEMS.register("crystal_oratchalcum",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_ORATCHALCUM= ITEMS.register("shard_oratchalcum",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_ORATCHALCUM= ITEMS.register("clump_oratchalcum",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_ORATCHALCUM= ITEMS.register("dirty_dust_oratchalcum",EWOresItem::new);
    public static final RegistryObject<Item> DUST_ORATCHALCUM= ITEMS.register("dust_oratchalcum",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_MOONSTEEL= ITEMS.register("crystal_moonsteel",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_MOONSTEEL= ITEMS.register("shard_moonsteel",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_MOONSTEEL= ITEMS.register("clump_moonsteel",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_MOONSTEEL= ITEMS.register("dirty_dust_moonsteel",EWOresItem::new);
    public static final RegistryObject<Item> DUST_MOONSTEEL= ITEMS.register("dust_moonsteel",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_CRYSTILLIUM= ITEMS.register("crystal_crystillium",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_CRYSTILLIUM= ITEMS.register("shard_crystillium",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_CRYSTILLIUM= ITEMS.register("clump_crystillium",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_CRYSTILLIUM= ITEMS.register("dirty_dust_crystillium",EWOresItem::new);
    public static final RegistryObject<Item> DUST_CRYSTILLIUM= ITEMS.register("dust_crystillium",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_LUNARITE_SCRAP= ITEMS.register("crystal_lunarite_scrap",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_LUNARITE_SCRAP= ITEMS.register("shard_lunarite_scrap",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_LUNARITE_SCRAP= ITEMS.register("clump_lunarite_scrap",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_LUNARITE_SCRAP= ITEMS.register("dirty_dust_lunarite_scrap",EWOresItem::new);
    public static final RegistryObject<Item> DUST_LUNARITE_SCRAP= ITEMS.register("dust_lunarite_scrap",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_CLOGGRUM= ITEMS.register("crystal_cloggrum",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_CLOGGRUM= ITEMS.register("shard_cloggrum",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_CLOGGRUM= ITEMS.register("clump_cloggrum",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_CLOGGRUM= ITEMS.register("dirty_dust_cloggrum",EWOresItem::new);
    public static final RegistryObject<Item> DUST_CLOGGRUM= ITEMS.register("dust_cloggrum",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_GRAVITITE= ITEMS.register("crystal_gravitite",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_GRAVITITE= ITEMS.register("shard_gravitite",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_GRAVITITE= ITEMS.register("clump_gravitite",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_GRAVITITE= ITEMS.register("dirty_dust_gravitite",EWOresItem::new);
    public static final RegistryObject<Item> DUST_GRAVITITE= ITEMS.register("dust_gravitite",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_FROSTSTEEL= ITEMS.register("crystal_froststeel",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_FROSTSTEEL= ITEMS.register("shard_froststeel",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_FROSTSTEEL= ITEMS.register("clump_froststeel",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_FROSTSTEEL= ITEMS.register("dirty_dust_froststeel",EWOresItem::new);
    public static final RegistryObject<Item> DUST_FROSTSTEEL= ITEMS.register("dust_froststeel",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_UTHERIUM= ITEMS.register("crystal_utherium",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_UTHERIUM= ITEMS.register("shard_utherium",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_UTHERIUM= ITEMS.register("clump_utherium",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_UTHERIUM= ITEMS.register("dirty_dust_utherium",EWOresItem::new);
    public static final RegistryObject<Item> DUST_UTHERIUM= ITEMS.register("dust_utherium",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_REGALIUM= ITEMS.register("crystal_regalium",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_REGALIUM= ITEMS.register("shard_regalium",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_REGALIUM= ITEMS.register("clump_regalium",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_REGALIUM= ITEMS.register("dirty_dust_regalium",EWOresItem::new);
    public static final RegistryObject<Item> DUST_REGALIUM= ITEMS.register("dust_regalium",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_ZITRITE= ITEMS.register("crystal_zitrite",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_ZITRITE= ITEMS.register("shard_zitrite",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_ZITRITE= ITEMS.register("clump_zitrite",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_ZITRITE= ITEMS.register("dirty_dust_zitrite",EWOresItem::new);
    public static final RegistryObject<Item> DUST_ZITRITE= ITEMS.register("dust_zitrite",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_RAINBOW= ITEMS.register("crystal_rainbow",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_RAINBOW= ITEMS.register("shard_rainbow",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_RAINBOW= ITEMS.register("clump_rainbow",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_RAINBOW= ITEMS.register("dirty_dust_rainbow",EWOresItem::new);
    public static final RegistryObject<Item> DUST_RAINBOW= ITEMS.register("dust_rainbow",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_COLD= ITEMS.register("crystal_cold",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_COLD= ITEMS.register("shard_cold",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_COLD= ITEMS.register("clump_cold",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_COLD= ITEMS.register("dirty_dust_cold",EWOresItem::new);
    public static final RegistryObject<Item> DUST_COLD= ITEMS.register("dust_cold",EWOresItem::new);
    public static final RegistryObject<Item> COLD_INGOT = ITEMS.register("cold_ingot",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_CHLOROPHYTE= ITEMS.register("crystal_chlorophyte",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_CHLOROPHYTE= ITEMS.register("shard_chlorophyte",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_CHLOROPHYTE= ITEMS.register("clump_chlorophyte",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_CHLOROPHYTE= ITEMS.register("dirty_dust_chlorophyte",EWOresItem::new);
    public static final RegistryObject<Item> DUST_CHLOROPHYTE= ITEMS.register("dust_chlorophyte",EWOresItem::new);
    public static final RegistryObject<Item> CHLOROPHYTE_INGOT = ITEMS.register("chlorophyte_ingot",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_ORICHALCOS= ITEMS.register("crystal_orichalcos",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_ORICHALCOS= ITEMS.register("shard_orichalcos",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_ORICHALCOS= ITEMS.register("clump_orichalcos",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_ORICHALCOS= ITEMS.register("dirty_dust_orichalcos",EWOresItem::new);
    public static final RegistryObject<Item> DUST_ORICHALCOS= ITEMS.register("dust_orichalcos",EWOresItem::new);
    public static final RegistryObject<Item> ORICHALCOS_INGOT = ITEMS.register("orichalcos_ingot",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_ALUMINUM= ITEMS.register("crystal_aluminum",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_ALUMINUM= ITEMS.register("shard_aluminum",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_ALUMINUM= ITEMS.register("clump_aluminum",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_ALUMINUM= ITEMS.register("dirty_dust_aluminum",EWOresItem::new);
    public static final RegistryObject<Item> DUST_ALUMINUM= ITEMS.register("dust_aluminum",EWOresItem::new);
    public static final RegistryObject<Item> ALUMINUM_INGOT = ITEMS.register("aluminum_ingot",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_NICKEL= ITEMS.register("crystal_nickel",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_NICKEL= ITEMS.register("shard_nickel",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_NICKEL= ITEMS.register("clump_nickel",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_NICKEL= ITEMS.register("dirty_dust_nickel",EWOresItem::new);
    public static final RegistryObject<Item> DUST_NICKEL= ITEMS.register("dust_nickel",EWOresItem::new);
    public static final RegistryObject<Item> NICKEL_INGOT = ITEMS.register("nickel_ingot",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_SILVER= ITEMS.register("crystal_silver",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_SILVER= ITEMS.register("shard_silver",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_SILVER= ITEMS.register("clump_silver",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_SILVER= ITEMS.register("dirty_dust_silver",EWOresItem::new);
    public static final RegistryObject<Item> DUST_SILVER= ITEMS.register("dust_silver",EWOresItem::new);
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_SUNLIT= ITEMS.register("crystal_sunlit",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_SUNLIT= ITEMS.register("shard_sunlit",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_SUNLIT= ITEMS.register("clump_sunlit",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_SUNLIT= ITEMS.register("dirty_dust_sunlit",EWOresItem::new);
    public static final RegistryObject<Item> DUST_SUNLIT= ITEMS.register("dust_sunlit",EWOresItem::new);
    public static final RegistryObject<Item> SUNLIT_INGOT = ITEMS.register("sunlit_ingot",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_DRYSTONE= ITEMS.register("crystal_drystone",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_DRYSTONE= ITEMS.register("shard_drystone",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_DRYSTONE= ITEMS.register("clump_drystone",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_DRYSTONE= ITEMS.register("dirty_dust_drystone",EWOresItem::new);
    public static final RegistryObject<Item> DUST_DRYSTONE= ITEMS.register("dust_drystone",EWOresItem::new);
    public static final RegistryObject<Item> DRYSTONE_INGOT = ITEMS.register("drystone_ingot",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_MAGNESIUM= ITEMS.register("crystal_magnesium",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_MAGNESIUM= ITEMS.register("shard_magnesium",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_MAGNESIUM= ITEMS.register("clump_magnesium",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_MAGNESIUM= ITEMS.register("dirty_dust_magnesium",EWOresItem::new);
    public static final RegistryObject<Item> DUST_MAGNESIUM= ITEMS.register("dust_magnesium",EWOresItem::new);
    public static final RegistryObject<Item> MAGNESIUM_INGOT = ITEMS.register("magnesium_ingot",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_INDIUM= ITEMS.register("crystal_indium",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_INDIUM= ITEMS.register("shard_indium",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_INDIUM= ITEMS.register("clump_indium",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_INDIUM= ITEMS.register("dirty_dust_indium",EWOresItem::new);
    public static final RegistryObject<Item> DUST_INDIUM= ITEMS.register("dust_indium",EWOresItem::new);
    public static final RegistryObject<Item> INDIUM_INGOT = ITEMS.register("indium_ingot",EWOresItem::new);

    public static final RegistryObject<Item> CRYSTAL_TITANIUM= ITEMS.register("crystal_titanium",EWOresItem::new);
    public static final RegistryObject<Item> SHARD_TITANIUM= ITEMS.register("shard_titanium",EWOresItem::new);
    public static final RegistryObject<Item> CLUMP_TITANIUM= ITEMS.register("clump_titanium",EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_TITANIUM= ITEMS.register("dirty_dust_titanium",EWOresItem::new);
    public static final RegistryObject<Item> DUST_TITANIUM= ITEMS.register("dust_titanium",EWOresItem::new);
    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",EWOresItem::new);
    public static final RegistryObject<Item> NIOBIUM_INGOT = ITEMS.register("niobium_ingot",EWOresItem::new);

    public static final RegistryObject<Item> CLUMP_RUPEE = ITEMS.register("clump_rupee", EWOresItem::new);
    public static final RegistryObject<Item> CRYSTAL_RUPEE = ITEMS.register("crystal_rupee", EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_RUPEE = ITEMS.register("dirty_dust_rupee", EWOresItem::new);
    public static final RegistryObject<Item> DUST_RUPEE = ITEMS.register("dust_rupee", EWOresItem::new);
    public static final RegistryObject<Item> SHARD_RUPEE = ITEMS.register("shard_rupee", EWOresItem::new);

    public static final RegistryObject<Item> CLUMP_ARLEMITE = ITEMS.register("clump_arlemite", EWOresItem::new);
    public static final RegistryObject<Item> CRYSTAL_ARLEMITE = ITEMS.register("crystal_arlemite", EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_ARLEMITE = ITEMS.register("dirty_dust_arlemite", EWOresItem::new);
    public static final RegistryObject<Item> DUST_ARLEMITE = ITEMS.register("dust_arlemite", EWOresItem::new);
    public static final RegistryObject<Item> SHARD_ARLEMITE = ITEMS.register("shard_arlemite", EWOresItem::new);

    public static final RegistryObject<Item> CLUMP_REALMITE = ITEMS.register("clump_realmite", EWOresItem::new);
    public static final RegistryObject<Item> CRYSTAL_REALMITE = ITEMS.register("crystal_realmite", EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_REALMITE = ITEMS.register("dirty_dust_realmite", EWOresItem::new);
    public static final RegistryObject<Item> DUST_REALMITE = ITEMS.register("dust_realmite", EWOresItem::new);
    public static final RegistryObject<Item> SHARD_REALMITE = ITEMS.register("shard_realmite", EWOresItem::new);

    public static final RegistryObject<Item> CLUMP_TUNGSTEN = ITEMS.register("clump_tungsten", EWOresItem::new);
    public static final RegistryObject<Item> CRYSTAL_TUNGSTEN = ITEMS.register("crystal_tungsten", EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_TUNGSTEN = ITEMS.register("dirty_dust_tungsten", EWOresItem::new);
    public static final RegistryObject<Item> DUST_TUNGSTEN = ITEMS.register("dust_tungsten", EWOresItem::new);
    public static final RegistryObject<Item> SHARD_TUNGSTEN = ITEMS.register("shard_tungsten", EWOresItem::new);

    public static final RegistryObject<Item> CLUMP_TORRIDITE = ITEMS.register("clump_torridite", EWOresItem::new);
    public static final RegistryObject<Item> CRYSTAL_TORRIDITE = ITEMS.register("crystal_torridite", EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_TORRIDITE = ITEMS.register("dirty_dust_torridite", EWOresItem::new);
    public static final RegistryObject<Item> DUST_TORRIDITE = ITEMS.register("dust_torridite", EWOresItem::new);
    public static final RegistryObject<Item> SHARD_TORRIDITE = ITEMS.register("shard_torridite", EWOresItem::new);

    public static final RegistryObject<Item> CLUMP_BLOODGEM = ITEMS.register("clump_bloodgem", EWOresItem::new);
    public static final RegistryObject<Item> CRYSTAL_BLOODGEM = ITEMS.register("crystal_bloodgem", EWOresItem::new);
    public static final RegistryObject<Item> DIRTY_DUST_BLOODGEM = ITEMS.register("dirty_dust_bloodgem", EWOresItem::new);
    public static final RegistryObject<Item> DUST_BLOODGEM = ITEMS.register("dust_bloodgem", EWOresItem::new);
    public static final RegistryObject<Item> SHARD_BLOODGEM = ITEMS.register("shard_bloodgem", EWOresItem::new);

    public static final RegistryObject<Item> NIOBIUM_NUGGET = ITEMS.register("niobium_nugget",EWOresItem::new);
    public static final RegistryObject<Item> VOUCHER = ITEMS.register("voucher",Voucher::new);

    public static final RegistryObject<Item> NUCLEAR_BALL = ITEMS.register("nuclear_ball",() -> new NuclearBall(10000,1));
    public static final RegistryObject<Item> NUCLEAR_ADVANCED_BALL = ITEMS.register("nuclear_advanced_ball",() -> new NuclearBall(30000,2));
    public static final RegistryObject<Item> NUCLEAR_EPIC_BALL= ITEMS.register("nuclear_epic_ball",() -> new NuclearBall(100000,3));

    public static final RegistryObject<Item> HARDCORE_STONE = registryBlock("hardcore_stone",EWBlocks.HARDCORE_STONE);
    public static final RegistryObject<Item> CONTROL_UPDATE_CORE = registryBlock("control_update_core",EWBlocks.CONTROL_UPDATE_CORE);
    public static final RegistryObject<Item> VIS_CONVERSION_CORE = registryBlock("vis_conversion_core",EWBlocks.VIS_CONVERSION_CORE);
    public static final RegistryObject<Item> PROFESSION_CORE = registryBlock("profession_core",EWBlocks.PROFESSION_CORE);
    public static final RegistryObject<Item> ADVANCED_UPDATE_CORE = registryBlock("advanced_update_core",EWBlocks.ADVANCED_UPDATE_CORE);
    public static final RegistryObject<Item> INFUSER_CORE = registryBlock("infuser_core",EWBlocks.INFUSER_BLOCK_CORE);
    public static final RegistryObject<Item> EMC_ORE = registryBlock("emc_ore",EWBlocks.EMC_ORE);
    public static final RegistryObject<Item> STEEL_FURNACE_BRICK = registryBlock("steel_furnace_brick",EWBlocks.STEEL_FURNACE_BRICK);
    public static final RegistryObject<Item> STEEL_FURNACE_CORE = registryBlock("steel_furnace_core",EWBlocks.STEEL_FURNACE_CORE);

    public static final RegistryObject<Item> WEAPON_UPGRADE_CORE = registryBlock("weapon_upgrade_core",EWBlocks.WEAPON_UPGRADE_CORE);
    public static final RegistryObject<Item> GEMSTONE_CORE = registryBlock("gemstone_core",EWBlocks.GEMSTONE_CORE);
    public static final RegistryObject<Item> RICH_EMC_ORE = registryBlock("rich_emc_ore",EWBlocks.RICH_EMC_ORE);
    public static final RegistryObject<Item> NETHER_EMC_ORE = registryBlock("nether_emc_ore",EWBlocks.NETHER_EMC_ORE);
    public static final RegistryObject<Item> END_EMC_ORE = registryBlock("end_emc_ore",EWBlocks.END_EMC_ORE);
    public static final RegistryObject<Item> END_RICH_EMC_ORE = registryBlock("end_rich_emc_ore",EWBlocks.END_RICH_EMC_ORE);
    public static final RegistryObject<Item> COLD_ORE = registryBlock("cold_ore",EWBlocks.COLD_ORE);
    public static final RegistryObject<Item> CHLOROPHYTE_ORE = registryBlock("chlorophyte_ore",EWBlocks.CHLOROPHYTE_ORE);
    public static final RegistryObject<Item> ORICHALCOS_ORE = registryBlock("orichalcos_ore",EWBlocks.ORICHALCOS_ORE);
    public static final RegistryObject<Item> NICKEL_ORE = registryBlock("nickel_ore",EWBlocks.NICKEL_ORE);
    public static final RegistryObject<Item> ALUMINUM_ORE = registryBlock("aluminum_ore",EWBlocks.ALUMINUM_ORE);
    public static final RegistryObject<Item> SILVER_ORE = registryBlock("silver_ore",EWBlocks.SILVER_ORE);
    public static final RegistryObject<Item> SUNLIT_ORE = registryBlock("sunlit_ore",EWBlocks.SUNLIT_ORE);
    public static final RegistryObject<Item> DRYSTONE_ORE = registryBlock("drystone_ore",EWBlocks.DRYSTONE_ORE);
    public static final RegistryObject<Item> AQUAMARINE_ORE = registryBlock("aquamarine_ore",EWBlocks.AQUAMARINE_ORE);
    public static final RegistryObject<Item> INDIUM_ORE = registryBlock("indium_ore",EWBlocks.INDIUM_ORE);
    public static final RegistryObject<Item> MAGNESIUM_ORE = registryBlock("magnesium_ore",EWBlocks.MAGNESIUM_ORE);
    public static final RegistryObject<Item> TITANIUM_ORE = registryBlock("titanium_ore",EWBlocks.TITANIUM_ORE);
    public static final RegistryObject<Item> TUNGSTEN_ORE = registryBlock("tungsten_ore",EWBlocks.TUNGSTEN_ORE);

    public static final RegistryObject<Item> BASE_EMC_STORED_TOTEM = ITEMS.register("base_emc_stored_totem",()-> new StoredTotem(500000));
    public static final RegistryObject<Item> ADVANCED_EMC_STORED_TOTEM = ITEMS.register("advanced_emc_stored_totem",()-> new StoredTotem(10000000));
    public static final RegistryObject<Item> ELITE_EMC_STORED_TOTEM = ITEMS.register("elite_emc_stored_totem",()-> new StoredTotem(150000000));
    public static final RegistryObject<Item> ULTIMATE_EMC_STORED_TOTEM = ITEMS.register("ultimate_emc_stored_totem",()-> new StoredTotem(Integer.MAX_VALUE));

    public static final RegistryObject<Item> UPDATE_BASE_PURPLE = registryUpdateBlock("update_base_purple",EWBlocks.UPDATE_BASE_PURPLE);
    public static final RegistryObject<Item> UPDATE_BASE_BX_PURPLE = registryUpdateBlock("update_base_bx_purple",EWBlocks.UPDATE_BASE_BX_PURPLE);
    public static final RegistryObject<Item> UPDATE_BASE_BLUE = registryUpdateBlock("update_base_blue",EWBlocks.UPDATE_BASE_BLUE);
    public static final RegistryObject<Item> UPDATE_BASE_CYAN = registryUpdateBlock("update_base_cyan",EWBlocks.UPDATE_BASE_CYAN);
    public static final RegistryObject<Item> UPDATE_BASE_GREEN = registryUpdateBlock("update_base_green",EWBlocks.UPDATE_BASE_GREEN);
    public static final RegistryObject<Item> UPDATE_BASE_YELLOW = registryUpdateBlock("update_base_yellow",EWBlocks.UPDATE_BASE_YELLOW);
    public static final RegistryObject<Item> UPDATE_BASE_ORANGE = registryUpdateBlock("update_base_orange",EWBlocks.UPDATE_BASE_ORANGE);
    public static final RegistryObject<Item> UPDATE_BASE_RED = registryUpdateBlock("update_base_red",EWBlocks.UPDATE_BASE_RED);

    public static final RegistryObject<Item> UPDATE_ADDON_PURPLE = registryUpdateBlock("update_addon_purple",EWBlocks.UPDATE_ADDON_PURPLE);
    public static final RegistryObject<Item> UPDATE_ADDON_BX_PURPLE = registryUpdateBlock("update_addon_bx_purple",EWBlocks.UPDATE_ADDON_BX_PURPLE);
    public static final RegistryObject<Item> UPDATE_ADDON_BLUE = registryUpdateBlock("update_addon_blue",EWBlocks.UPDATE_ADDON_BLUE);
    public static final RegistryObject<Item> UPDATE_ADDON_CYAN = registryUpdateBlock("update_addon_cyan",EWBlocks.UPDATE_ADDON_CYAN);
    public static final RegistryObject<Item> UPDATE_ADDON_GREEN = registryUpdateBlock("update_addon_green",EWBlocks.UPDATE_ADDON_GREEN);
    public static final RegistryObject<Item> UPDATE_ADDON_YELLOW = registryUpdateBlock("update_addon_yellow",EWBlocks.UPDATE_ADDON_YELLOW);
    public static final RegistryObject<Item> UPDATE_ADDON_ORANGE = registryUpdateBlock("update_addon_orange",EWBlocks.UPDATE_ADDON_ORANGE);
    public static final RegistryObject<Item> UPDATE_ADDON_RED = registryUpdateBlock("update_addon_red",EWBlocks.UPDATE_ADDON_RED);

    public static final RegistryObject<Item> UPDATE_TIME_PURPLE = registryUpdateBlock("update_time_purple",EWBlocks.UPDATE_TIME_PURPLE);
    public static final RegistryObject<Item> UPDATE_TIME_BX_PURPLE = registryUpdateBlock("update_time_bx_purple",EWBlocks.UPDATE_TIME_BX_PURPLE);
    public static final RegistryObject<Item> UPDATE_TIME_BLUE = registryUpdateBlock("update_time_blue",EWBlocks.UPDATE_TIME_BLUE);
    public static final RegistryObject<Item> UPDATE_TIME_CYAN = registryUpdateBlock("update_time_cyan",EWBlocks.UPDATE_TIME_CYAN);
    public static final RegistryObject<Item> UPDATE_TIME_GREEN = registryUpdateBlock("update_time_green",EWBlocks.UPDATE_TIME_GREEN);
    public static final RegistryObject<Item> UPDATE_TIME_YELLOW = registryUpdateBlock("update_time_yellow",EWBlocks.UPDATE_TIME_YELLOW);
    public static final RegistryObject<Item> UPDATE_TIME_ORANGE = registryUpdateBlock("update_time_orange",EWBlocks.UPDATE_TIME_ORANGE);
    public static final RegistryObject<Item> UPDATE_TIME_RED = registryUpdateBlock("update_time_red",EWBlocks.UPDATE_TIME_RED);

    public static final RegistryObject<Item> UPDATE_COST_PURPLE = registryUpdateBlock("update_cost_purple",EWBlocks.UPDATE_COST_PURPLE);
    public static final RegistryObject<Item> UPDATE_COST_BX_PURPLE = registryUpdateBlock("update_cost_bx_purple",EWBlocks.UPDATE_COST_BX_PURPLE);
    public static final RegistryObject<Item> UPDATE_COST_BLUE = registryUpdateBlock("update_cost_blue",EWBlocks.UPDATE_COST_BLUE);
    public static final RegistryObject<Item> UPDATE_COST_CYAN = registryUpdateBlock("update_cost_cyan",EWBlocks.UPDATE_COST_CYAN);
    public static final RegistryObject<Item> UPDATE_COST_GREEN = registryUpdateBlock("update_cost_green",EWBlocks.UPDATE_COST_GREEN);
    public static final RegistryObject<Item> UPDATE_COST_YELLOW = registryUpdateBlock("update_cost_yellow",EWBlocks.UPDATE_COST_YELLOW);
    public static final RegistryObject<Item> UPDATE_COST_ORANGE = registryUpdateBlock("update_cost_orange",EWBlocks.UPDATE_COST_ORANGE);
    public static final RegistryObject<Item> UPDATE_COST_RED = registryUpdateBlock("update_cost_red",EWBlocks.UPDATE_COST_RED);

    public static final RegistryObject<Item> TEST_BLOCK = registryBlock("test_block",EWBlocks.TEST_BLOCK);

    private static RegistryObject<Item> registryBlock(String name, RegistryObject<Block> block){
        return ITEMS.register(name,() -> new BlockItem(block.get(),new Item.Properties().tab(EWCreativeTabs.EW_BLOCKS_TAB)));
    }

    private static RegistryObject<Item> registryBlock(String name, Block block){
        return ITEMS.register(name,() -> new BlockItem(block,new Item.Properties().tab(EWCreativeTabs.EW_BLOCKS_TAB)));
    }

    private static RegistryObject<Item> registryUpdateBlock(String name,RegistryObject<Block> block){
        return ITEMS.register(name,() -> new UpdateBlockItem(block.get()));
    }

    private static RegistryObject<Item> registryEMCOreCoreBlock(String name,RegistryObject<Block> block){
        return ITEMS.register(name,() -> new EMCCoreBlockItem(block.get()));
    }
}
