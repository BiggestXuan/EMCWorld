package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/02
 */

import biggestxuan.emcworld.api.item.IFakeEMCItem;
import biggestxuan.emcworld.api.item.IPrefixItem;
import moze_intel.projecte.api.ItemInfo;
import moze_intel.projecte.emc.nbt.NBTManager;
import moze_intel.projecte.utils.EMCHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EMCHelper.class)
public abstract class EMCHelperMixin {

    /**
     * @author Biggest_Xuan
     * @reason ..
     */
    @Overwrite(remap = false)
    public static long getEmcValue(ItemInfo info){
        if(info.getItem() instanceof IPrefixItem){
            return 0L;
        }
        if(info.getItem() instanceof IFakeEMCItem){
            return ((IFakeEMCItem) info.getItem()).getActEMC(info.createStack());
        }
        return NBTManager.getEmcValue(info);
    }
}
