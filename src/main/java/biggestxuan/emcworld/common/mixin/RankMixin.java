package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/15
 */

import biggestxuan.emcworld.common.config.ConfigManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
//import top.theillusivec4.champions.common.rank.Rank;

//@Mixin(Rank.class)
public abstract class RankMixin {

    /*@Shadow(remap = false)
    @Final
    private int tier;

    @Shadow(remap = false)
    @Final
    private float chance;

    @Shadow(remap = false)
    @Final
    private int growthFactor;

    @Shadow(remap = false)
    @Final
    private int defaultColor;

    *//**
     * @author Biggest_Xuan
     * @reason no reason~
     *//*
    @Overwrite(remap = false)
    public float getChance() {
        return tier == 0 ? chance : (float) Math.min(chance * ConfigManager.DIFFICULTY.get(), 1D);
    }

    *//**
     * @author Biggest_Xuan
     * @reason no reason~
     *//*
    @Overwrite(remap = false)
    public int getGrowthFactor() {
        return tier <= 4 && tier > 1 ? Math.round(growthFactor / 2.5F) : growthFactor;
    }

    *//**
     * @author Biggest_Xuan
     * @reason no reason~
     *//*
    @Overwrite(remap = false)
    public int getDefaultColor() {
        int color = 0x000000;
        switch (tier){
            case 0:
            case 1:
                color = 0x404040;
                break;
            case 2:
                color = 0x00cc00;
                break;
            case 3:
                color = 0xffff00;
                break;
            case 4:
                color = 0x0066cc;
                break;
            case 5:
                color = 0x6600cc;
                break;
            case 6:
                color = 0xcc6600;
                break;
            case 7:
                color = 0xcc0000;
                break;
            case 8:
                color = 0xffffff;
                break;
        }
        return color;
    }*/
}
