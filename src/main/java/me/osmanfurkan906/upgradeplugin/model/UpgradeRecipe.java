package me.osmanfurkan906.upgradeplugin.model;

import me.osmanfurkan906.upgradeplugin.UpgradePlugin;
import me.osmanfurkan906.upgradeplugin.utils.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.HashMap;
import java.util.Map;

public class UpgradeRecipe {
    private String name;
    private String[] shape;
    private ItemStack resultItem;
    private final Map<Character, Material> ingredients = new HashMap<>();

    public UpgradeRecipe fromConfig(UpgradePlugin plugin, String recipeName) {
        this.name = recipeName;
        final Yaml upgrades = plugin.getUpgrades();
        this.shape = upgrades.getStringList("items." + recipeName + ".shape").toArray(new String[0]);
        this.resultItem = plugin.getItemBuilderAPI().getItemBuilder().setAmount(1)
                .setName(upgrades.getString("items." + recipeName + ".name"))
                .setType(Material.matchMaterial(upgrades.getString("items." + recipeName + ".material")))
                .build();
        upgrades.getConfigurationSection("items." + recipeName + ".ingredients").getKeys(false).forEach(ingredient ->
                ingredients.put(ingredient.charAt(0), Material.matchMaterial(upgrades.getString("items." + recipeName + ".ingredients." + ingredient + ".material"))));
        return this;
    }

    public void register(UpgradePlugin plugin) {
        if(resultItem == null) throw new IllegalStateException("A problem has occurred while registering the recipe");
        final NamespacedKey namespacedKey = new NamespacedKey(plugin, name);
        final ShapedRecipe shapedRecipe = new ShapedRecipe(namespacedKey, resultItem);
        shapedRecipe.shape(shape);
        ingredients.forEach(shapedRecipe::setIngredient);
        Bukkit.addRecipe(shapedRecipe);
    }
}
