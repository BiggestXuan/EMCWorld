function addOre(a,b,c,d,e,f,g){
    onEvent('worldgen.add',event =>{
        event.addOre(ore =>{
            ore.block = a
            ore.clusterMaxSize = b
            ore.spawnsIn.blacklist = false
            ore.spawnsIn.values = c
            ore.biomes.values = d
            ore.minHeight = e
            ore.maxHeight = f
            ore.clusterCount = g
        })
    })
}

function addOreSpawn(a,b,c,d,e){
    addOre(a,b,['minecraft:stone'],['minecraft:overworld'],c,d,e)
}

function addEnderOreSpawn(a,b,c,d,e){
    addOre(a,b,['minecraft:end_stone'],['#the_end'],c,d,e)
}

addOreSpawn('emcworld:emc_ore',6,16,64,5)
addOreSpawn('emcworld:rich_emc_ore',5,0,24,3)
addEnderOreSpawn('emcworld:end_emc_ore',6,0,64,4)
addEnderOreSpawn('emcworld:titanium_ore',6,0,64,4)
addEnderOreSpawn('emcworld:end_rich_emc_ore',4,0,24,2)
addOre('emcworld:indium_ore',8,['minecraft:netherrack'],['#nether'],0,110,6)
addOre('emcworld:nether_emc_ore',8,['minecraft:netherrack'],['#nether'],0,110,6)
addOre('emcworld:cold_ore',5,['minecraft:packed_ice','minecraft:ice'],['#icy'],0,128,100)
addOre('emcworld:chlorophyte_ore',5,['minecraft:dirt'],['#jungle'],0,96,45)
addOre('emcworld:aquamarine_ore',5,['minecraft:sand'],['atlantis:atlantis_biome','hem:lush_forest'],0,96,70)
addOre('emcworld:sunlit_ore',5,['aether:holystone'],['aether:aether_skylands'],32,50,20)
addOre('emcworld:drystone_ore',5,['minecraft:terracotta'],['#mesa'],16,96,35)