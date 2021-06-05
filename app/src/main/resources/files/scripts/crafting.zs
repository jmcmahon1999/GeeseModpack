//Crafting Tweaks

craftingTable.removeRecipe(<item:angelring:itemring>);
craftingTable.addShaped("angel_ring", <item:angelring:itemring>, [[<item:minecraft:feather>, <item:minecraft:netherite_block>, <item:minecraft:feather>], [<item:minecraft:gold_block>, <item:angelring:itemdiamondring>, <item:minecraft:gold_block>], [<item:minecraft:nether_star>, <item:minecraft:netherite_block>, <item:minecraft:nether_star>]]);

furnace.addRecipe("obsidian_ingot", <item:contenttweaker:obsidian_ingot>, <item:create:powdered_obsidian>, 5.0, 60);

//Obsidian Gear.
craftingTable.removeRecipe(<item:morevanillaarmor:obsidian_helmet>);
craftingTable.addShaped("obsidian_helmet", <item:morevanillaarmor:obsidian_helmet>, [[<item:contenttweaker:obsidian_ingot>, <item:contenttweaker:obsidian_ingot>, <item:contenttweaker:obsidian_ingot>], [ <item:contenttweaker:obsidian_ingot>, <item:minecraft:gold_ingot>, <item:contenttweaker:obsidian_ingot>]]);

craftingTable.removeRecipe(<item:morevanillaarmor:obsidian_chestplate>);
craftingTable.addShaped("obsidian_chestplate", <item:morevanillaarmor:obsidian_chestplate>, [[<item:contenttweaker:obsidian_ingot>, <item:minecraft:gold_ingot>, <item:contenttweaker:obsidian_ingot>], [<item:contenttweaker:obsidian_ingot>, <item:contenttweaker:obsidian_ingot>, <item:contenttweaker:obsidian_ingot>], [<item:contenttweaker:obsidian_ingot>, <item:contenttweaker:obsidian_ingot>, <item:contenttweaker:obsidian_ingot>]]);

craftingTable.removeRecipe(<item:morevanillaarmor:obsidian_leggings>);
craftingTable.addShaped("obsidian_leggings", <item:morevanillaarmor:obsidian_chestplate>, [[<item:contenttweaker:obsidian_ingot>, <item:contenttweaker:obsidian_ingot>, <item:contenttweaker:obsidian_ingot>], [<item:contenttweaker:obsidian_ingot>, <item:minecraft:gold_ingot>, <item:contenttweaker:obsidian_ingot>], [<item:contenttweaker:obsidian_ingot>, <item:minecraft:gold_ingot>, <item:contenttweaker:obsidian_ingot>]]);

craftingTable.removeRecipe(<item:morevanillaarmor:obsidian_boots>);
craftingTable.addShaped("obsidian_boots", <item:morevanillaarmor:obsidian_boots>, [[<item:contenttweaker:obsidian_ingot>, <item:minecraft:gold_ingot>, <item:contenttweaker:obsidian_ingot>], [<item:contenttweaker:obsidian_ingot>, <item:minecraft:gold_ingot>, <item:contenttweaker:obsidian_ingot>]]);

//Dragon Mounts.
craftingTable.addShapeless("ice_diamond", <item:contenttweaker:ice_diamond>, [<item:minecraft:diamond>, <item:minecraft:blue_ice>, <item:minecraft:blue_ice>, <item:minecraft:blue_ice>, <item:minecraft:blue_ice>, <item:minecraft:blue_ice>, <item:minecraft:blue_ice>, <item:minecraft:blue_ice>, <item:minecraft:blue_ice>]);
craftingTable.addShapeless("ice_dragon_egg", <item:dragonmounts:ice_dragon_egg>, [<item:dragonmounts:ender_dragon_egg>, <item:contenttweaker:ice_diamond>, <item:contenttweaker:ice_diamond>, <item:contenttweaker:ice_diamond>, <item:contenttweaker:ice_diamond>, <item:contenttweaker:ice_diamond>, <item:contenttweaker:ice_diamond>, <item:contenttweaker:ice_diamond>, <item:contenttweaker:ice_diamond>]);

craftingTable.addShapeless("water_dragon_egg", <item:dragonmounts:water_dragon_egg>, [<item:dragonmounts:ender_dragon_egg>, <item:minecraft:heart_of_the_sea>, <item:minecraft:heart_of_the_sea>, <item:minecraft:heart_of_the_sea>, <item:minecraft:nautilus_shell>, <item:minecraft:nautilus_shell>, <item:minecraft:nautilus_shell>, <item:minecraft:nautilus_shell>, <item:minecraft:nautilus_shell>]);

craftingTable.addShapeless("fire_dragon_egg", <item:dragonmounts:fire_dragon_egg>, [<item:dragonmounts:ender_dragon_egg>, <item:minecraft:fire_charge>, <item:minecraft:fire_charge>, <item:minecraft:fire_charge>, <item:minecraft:fire_charge>, <item:minecraft:fire_charge>, <item:minecraft:fire_charge>, <item:minecraft:fire_charge>, <item:minecraft:fire_charge>]);


