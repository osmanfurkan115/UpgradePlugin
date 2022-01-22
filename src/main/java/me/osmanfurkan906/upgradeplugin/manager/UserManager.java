package me.osmanfurkan906.upgradeplugin.manager;

import lombok.RequiredArgsConstructor;
import me.osmanfurkan906.upgradeplugin.UpgradePlugin;
import me.osmanfurkan906.upgradeplugin.model.UpgradeType;
import me.osmanfurkan906.upgradeplugin.model.User;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.*;

@RequiredArgsConstructor
public class UserManager {
    private final UpgradePlugin plugin;
    private final Set<User> users = new HashSet<>();

    public User getUser(OfflinePlayer player) {
        return getUser(player.getUniqueId());
    }

    public User getUser(Player player) {
        return getUser(player.getUniqueId());
    }

    public User getUser(UUID uuid) {
        final Optional<User> first = users.stream().filter(user -> user.getPlayerUUID().equals(uuid)).findFirst();
        return first.orElseGet(() -> createUser(uuid));
    }

    private User createUser(UUID uuid) {
        final User user = new User(uuid, new HashMap<>());
        users.add(user);
        return user;
    }

    private void loadUser(UUID uuid) {
        final Map<UpgradeType, Integer> upgrades = new HashMap<>();
        final ConfigurationSection upgradeSection = plugin.getData().getConfigurationSection("players." + uuid.toString() + ".upgrades");
        if(upgradeSection == null) return;
        upgradeSection.getKeys(false).forEach(key -> {
            final UpgradeType upgradeType = UpgradeType.fromPath(key);
            final int level = plugin.getData().getInt("players." + uuid + ".upgrades." + key);
            upgrades.put(upgradeType, level);
        });
        final User user = new User(uuid, upgrades);
        user.setWalletMoney(plugin.getData().getInt("players." + uuid + ".money"));
        user.setStorageSize(plugin.getData().getInt("players." + uuid + ".storage"));
        user.setWalletSize(upgrades.getOrDefault(UpgradeType.WALLET, 0) * 5000 + 5000);
        users.add(user);
    }

    public void loadUsers() {
        final ConfigurationSection players = plugin.getData().getConfigurationSection("players");
        if(players == null) return;
        players.getKeys(false).forEach(uuid -> loadUser(UUID.fromString(uuid)));
    }

    private void saveUser(User user) {
        user.getUpgrades().forEach((key, value) ->
                plugin.getData().set("players." + user.getPlayerUUID().toString() + ".upgrades." + key.getPath(), value));
        plugin.getData().set("players." + user.getPlayerUUID().toString() + ".money", user.getWalletMoney());
        plugin.getData().set("players." + user.getPlayerUUID().toString() + ".storage", user.getStorageSize());

        plugin.getData().save();
    }

    public void saveUsers() {
        users.forEach(this::saveUser);
    }

    public boolean isExist(Player player) {
        return users.stream().anyMatch(user -> user.getPlayerUUID().equals(player.getUniqueId()));
    }
}
