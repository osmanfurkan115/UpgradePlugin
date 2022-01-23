package me.osmanfurkan906.upgradeplugin.manager;

import lombok.RequiredArgsConstructor;
import me.osmanfurkan906.upgradeplugin.UpgradePlugin;
import me.osmanfurkan906.upgradeplugin.model.*;
import me.osmanfurkan906.upgradeplugin.utils.Utils;
import me.osmanfurkan906.upgradeplugin.utils.Yaml;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class UpgradeManager {
    private static final int MAX_LEVEL = 6;
    private final UpgradePlugin plugin;
    private final Map<Integer, Requirement> upgradeRequirements = new HashMap<>();

    public void upgradeLevel(Player player, UpgradeType upgradeType) {
        final User user = plugin.getUserManager().getUser(player);
        if (!canUpgradeLevel(user, upgradeType)) {
            player.sendMessage(Utils.colored("&cYou are on the max level"));
            return;
        }
        final int newLevel = user.getUpgrades().getOrDefault(upgradeType, 0) + 1;
        if (!canBuyLevel(user, newLevel)) {
            player.sendMessage(Utils.colored("&cYou don't meet the requirements"));
            return;
        }
        user.getUpgrades().put(upgradeType, newLevel);
        upgradeType.getUpgrader().upgrade(user);
        player.sendMessage(Utils.getMessage(plugin.getConfig(), "upgrade")
                .replace("%type%", upgradeType.toString())
                .replace("%level%", newLevel + ""));
    }

    private boolean canUpgradeLevel(User user, UpgradeType upgradeType) {
        return user.getUpgrades().getOrDefault(upgradeType, 0) < MAX_LEVEL;
    }

    private boolean canBuyLevel(User user, int newLevel) {
        if (!user.getPlayer().isPresent()) return false;
        final Player player = user.getPlayer().get();
        final Requirement requirement = upgradeRequirements.get(newLevel);
        for (UpgradeRequirement upgradeRequirement : requirement.getUpgradeRequirements()) {
            if (upgradeRequirement.check(player, user)) {
                return true;
            }
        }
        return false;
    }

    public void initializeRequirements() {
        upgradeRequirements.clear();
        final Yaml upgrades = plugin.getUpgrades();
        if(upgrades.getConfigurationSection("upgrades") == null) return;
        upgrades.getConfigurationSection("upgrades").getKeys(false).forEach(key -> {
            final String path = "upgrades." + key + ".";
            final String requirementPath = path + "requirements.";
            final Requirement requirement = new Requirement(upgrades.getInt(path + "level"),
                    new KillUpgradeRequirement(upgrades.getInt(requirementPath + "kill")),
                    new MoneyUpgradeRequirement(plugin, upgrades.getInt(requirementPath + "money")),
                    plugin.getCustomItemManager().getCraftUpgrades().getOrDefault(upgrades.getString(requirementPath + "craft"),
                            new CraftUpgradeRequirement(null, 1))
            );
            upgradeRequirements.put(upgrades.getInt(path + "level"), requirement);
        });
    }
}
