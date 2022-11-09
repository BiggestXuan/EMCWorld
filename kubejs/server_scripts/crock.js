onEvent('recipes',event =>{
    event.recipes.crockpot.crockPotCooking(
    'farmersdelight:honey_glazed_ham_block',
    30,600,2
).requirementCategoryMin('meat',1.5)
.requirementCategoryMin('sweetener',1.5)
.requirement({ type: "must_contain_ingredient", ingredient: { item:"farmersdelight:smoked_ham" }, quantity: 1 })
})
