#priority 15

public function modifyPendoriteRecipe() as void{
    removeFurnaceRecipe([
        <item:byg:pendorite_scraps>,<item:byg:raw_pendorite>
    ]);
    addNormalOreProceRecipe(
        <item:byg:pendorite_ore>,
        <slurry:emcworld:dirty_pendorite>,
        <slurry:emcworld:clean_pendorite>,
        <item:emcworld:crystal_pendorite>,
        <item:emcworld:shard_pendorite>,
        <item:emcworld:clump_pendorite>,
        <item:emcworld:dirty_dust_pendorite>,
        <item:emcworld:dust_pendorite>,
        <item:byg:pendorite_scraps>
    );
}