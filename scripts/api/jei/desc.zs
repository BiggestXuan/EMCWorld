#priority 38

import mods.jei.JEI;
import mods.emcworld.compact.jei;

public function addJEIInfo() as void{
    JEI.addInfo(<item:emcworld:advanced_update_core>,[jei.getAdvancedCoreDesc()]);
}