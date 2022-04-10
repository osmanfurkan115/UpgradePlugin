package me.osmanfurkan906.upgradeplugin.manager;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.osmanfurkan906.upgradeplugin.UpgradePlugin;
import me.osmanfurkan906.upgradeplugin.requirement.type.CraftUpgradeRequirement;
import me.osmanfurkan906.upgradeplugin.model.UpgradeRecipe;
import me.osmanfurkan906.upgradeplugin.utils.Yaml;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class CustomItemManager {
    private final UpgradePlugin plugin;
    @Getter
    private final Map<String, CraftUpgradeRequirement> craftUpgrades = new HashMap<>();

    public void initializeCraftUpgrades() {
        final Yaml upgrades = plugin.getUpgrades();
        if(upgrades.getConfigurationSection("items") == null) return;
        upgrades.getConfigurationSection("items").getKeys(false).forEach(item -> {
            final UpgradeRecipe upgradeRecipe = new UpgradeRecipe().fromConfig(plugin, item).register(plugin);
            craftUpgrades.put(item, new CraftUpgradeRequirement(upgradeRecipe.getResultItem(), 1));
        });
    }
}
