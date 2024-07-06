package biggestxuan.emcworld.common.blocks;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/05
 */

import hellfirepvp.astralsorcery.common.block.tile.altar.AltarType;
import hellfirepvp.astralsorcery.common.tile.altar.TileAltar;

public class FakeAltar extends TileAltar {
    @Override
    public AltarType getAltarType() {
        return AltarType.ATTUNEMENT;
    }
}
