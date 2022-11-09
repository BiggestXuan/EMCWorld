function addOreSpawn(a,b,c,d,e){
    onEvent('worldgen.add',event =>{
        event.addOre(ore =>{
            ore.block = a
            ore.clusterMaxSize = b
            ore.minHeight = c
            ore.maxHeight = d
            ore.clusterCount = e
        })
    })
}

function addEnderOreSpawn(a,b,c,d,e){
    onEvent('worldgen.add',event =>{
        event.addOre(ore =>{
            ore.block = a
            ore.clusterMaxSize = b
            ore.minHeight = c
            ore.maxHeight = d
            ore.clusterCount = e
            ore.spawnsIn.blacklist = false
            ore.spawnsIn.values = ['minecraft:end_stone']
            ore.biomes.blacklist = false
            ore.biomes.values = ['minecraft:the_end']
        })
    })
}

addOreSpawn('emcworld:emc_ore',6,16,64,5)
addOreSpawn('emcworld:rich_emc_ore',5,0,24,3)
addEnderOreSpawn('emcworld:end_emc_ore',6,0,64,4)
addEnderOreSpawn('emcworld:titanium_ore',6,0,64,4)
addEnderOreSpawn('emcworld:end_rich_emc_ore',4,0,24,2)


onEvent('worldgen.add',event=>{
    event.addOre(ore=>{
        ore.block = 'emcworld:indium_ore'
        ore.clusterMaxSize = 8
        ore.spawnsIn.blacklist = false
        ore.spawnsIn.values = ['minecraft:netherrack']
        ore.biomes.values = ['#nether']
        ore.minHeight = 0
        ore.maxHeight = 110
        ore.clusterCount = 6
    })
    event.addOre(ore=>{
        ore.block = 'emcworld:nether_emc_ore'
        ore.clusterMaxSize = 8
        ore.spawnsIn.blacklist = false
        ore.spawnsIn.values = ['minecraft:netherrack']
        ore.biomes.values = ['#nether']
        ore.minHeight = 0
        ore.maxHeight = 110
        ore.clusterCount = 6
    })
    event.addOre(ore=>{
        ore.block = 'emcworld:cold_ore'
        ore.clusterMaxSize = 5
        ore.spawnsIn.blacklist = false
        ore.spawnsIn.values = ['minecraft:packed_ice','minecraft:ice']
        ore.minHeight = 0
        ore.maxHeight = 128
        ore.clusterCount = 100
    })
    event.addOre(ore=>{
        ore.block = 'emcworld:chlorophyte_ore'
        ore.clusterMaxSize = 5
        ore.spawnsIn.blacklist = false
        ore.spawnsIn.values = ['minecraft:dirt']
        ore.biomes.values = ['#jungle']
        ore.minHeight = 0
        ore.maxHeight = 96
        ore.clusterCount = 45
    })
    event.addOre(ore=>{
        ore.block = 'emcworld:aquamarine_ore'
        ore.clusterMaxSize = 5
        ore.spawnsIn.blacklist = false
        ore.spawnsIn.values = ['minecraft:sand']
        ore.biomes.values = ['atlantis:atlantis_biome','hem:lush_forest']
        ore.minHeight = 0
        ore.maxHeight = 96
        ore.clusterCount = 70
    })
    event.addOre(ore=>{
        ore.block = 'emcworld:sunlit_ore'
        ore.clusterMaxSize = 5
        ore.spawnsIn.blacklist = false
        ore.spawnsIn.values = ['aether:holystone']
        ore.minHeight = 32
        ore.maxHeight = 50
        ore.clusterCount = 20
    })
    event.addOre(ore=>{
        ore.block = 'emcworld:drystone_ore'
        ore.clusterMaxSize = 5
        ore.spawnsIn.blacklist = false
        ore.spawnsIn.values = ['minecraft:terracotta']
        ore.minHeight = 16
        ore.maxHeight = 96
        ore.clusterCount = 35
    })
});