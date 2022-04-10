package me.osmanfurkan906.upgradeplugin.manager;

import lombok.Getter;
import me.osmanfurkan906.upgradeplugin.UpgradePlugin;
import me.osmanfurkan906.upgradeplugin.model.Cooldown;
import me.osmanfurkan906.upgradeplugin.model.UpgradeType;
import me.osmanfurkan906.upgradeplugin.model.User;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class CloakingManager {
    private final UpgradePlugin plugin;
    private final Set<Player> players = new HashSet<>();
    @Getter
    private final Cooldown cooldown = new Cooldown(60);

    public CloakingManager(UpgradePlugin plugin) {
        this.plugin = plugin;
    }

    public void useCloakingDevice(Player player) {
        if(cooldown.isInCooldown(player)) return;
        if(players.contains(player)) return;
        final User user = plugin.getUserManager().getUser(player);
        final int invisibilityTime = 30 + (user.getUpgrades().getOrDefault(UpgradeType.CLOAKING_DEVICE, 0)*30);
        plugin.getInvisibilityManager().useInvisibility(player, true);
        players.add(player);
        new BukkitRunnable() {
            @Override
            public void run() {
                plugin.getInvisibilityManager().useInvisibility(player, false);
                players.remove(player);
                cooldown.createCooldown(player);
            }
        }.runTaskLater(plugin, 20L * invisibilityTime);
    }
}
