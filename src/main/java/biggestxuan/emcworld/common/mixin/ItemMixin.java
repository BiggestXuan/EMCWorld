package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/23
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.IWIP;
import biggestxuan.emcworld.api.item.IEMCInfuserItem;
import biggestxuan.emcworld.api.item.INameItem;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.api.item.equipment.IStarItem;
import biggestxuan.emcworld.common.compact.BloodMagic.BloodMagicHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.*;
import net.minecraftforge.common.extensions.IForgeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin implements INameItem, IForgeItem {
    @Shadow
    private String descriptionId;

    @Inject(method = "getOrCreateDescriptionId",at = @At("TAIL"),cancellable = true)
    //I18n translated non-Chinese items into English, resulting in having to modify their local keys.
    public void name(CallbackInfoReturnable<String> cir){
        switch (descriptionId){
            case "item.bloodmagic.ingot_hellforged":
                cir.setReturnValue("item.emcworld.ingot_hellforged");
                break;
            case "item.bloodmagic.sand_hellforged":
                cir.setReturnValue("item.emcworld.sand_hellforged");
                break;
            case "item.projecte.black_alchemical_bag":
                cir.setReturnValue("item.emcworld.blacklist_bag");
                break;
            case "item.projecte.white_alchemical_bag":
                cir.setReturnValue("item.emcworld.whitelist_bag");
                break;
            case "item.projecte.yellow_alchemical_bag":
                cir.setReturnValue("item.emcworld.unknown_bag");
                break;
        }
    }
    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        if(stack.getItem() instanceof IEMCInfuserItem){
            return true;
        }
        return stack.isDamaged();
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        if(stack.getItem() instanceof IEMCInfuserItem){
            var i = (IEMCInfuserItem) stack.getItem();
            return 1 - 1d * i.getInfuser(stack) / i.getMaxInfuser(stack);
        }
        return 1d * stack.getDamageValue() / stack.getMaxDamage();
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        if(stack.getItem() instanceof IEMCInfuserItem){
            return 0xD7C8F3;
        }
        return MathHelper.hsvToRgb(Math.max(0.0F, (float) (1.0F - getDurabilityForDisplay(stack))) / 3.0F, 1.0F, 1.0F);
    }

    @Override
    public IFormattableTextComponent getKey(ItemStack stack) {
        return null;
    }

    /**
     * @author Biggest_Xuan
     * @reason Item's level and prefix.
     */
    @Overwrite
    public ITextComponent getName(ItemStack p_200295_1_) {
        Item item = (Item) (Object) this;
        ResourceLocation rl = item.getRegistryName();
        TranslationTextComponent text = rl == null ? EMCWorld.tc("") : new TranslationTextComponent(item.getDescriptionId(p_200295_1_));
        IFormattableTextComponent f = getKey(p_200295_1_);
        if(item instanceof IWIP){
            return EMCWorld.tc("WIP").withStyle(Style.EMPTY.withColor(Color.fromRgb(0xff0000)));
        }
        if(f != null){
            text = (TranslationTextComponent) f;
        }
        if(BloodMagicHelper.isEMCModifyDagger(p_200295_1_)){
            return EMCWorld.tc("item.emcworld.emc_dagger");
        }
        if(p_200295_1_.hasTag()){
            CompoundNBT nbt = p_200295_1_.getTag();
            if(nbt != null && nbt.contains("level")){
                if(item instanceof IUpgradeableItem){
                    IUpgradeableItem upgradeableItem = (IUpgradeableItem) item;
                    int level = upgradeableItem.getLevel(p_200295_1_);
                    if(upgradeableItem.getMaxLevel() >0){
                        text.append(" (+"+level+")");
                    }
                }
            }
        }
        if(item instanceof IPrefixItem && ConfigManager.UPGRADE_PREFIX.get()){
            IPrefixItem prefixItem = (IPrefixItem) item;
            if(prefixItem.getPrefix(p_200295_1_).getLevel() != 0){
                IPrefixItem.Prefix prefix = prefixItem.getPrefix(p_200295_1_);
                text = (TranslationTextComponent) prefix.getName().setStyle(Style.EMPTY.withColor(Color.fromRgb(prefix.getColor()))).append(text);
            }
        }
        if(item instanceof IStarItem && ConfigManager.UPGRADE_STAR.get()){
            IStarItem item1 = (IStarItem) item;
            String star = " "+"\u2605".repeat(Math.max(0, item1.getStar(p_200295_1_)));
            text.append(star);
        }
        return text;
    }
}
