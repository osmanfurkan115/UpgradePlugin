package me.osmanfurkan906.upgradeplugin.manager;

import dev.jcsoftware.jscoreboards.JPerPlayerScoreboard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.osmanfurkan906.upgradeplugin.UpgradePlugin;
import me.osmanfurkan906.upgradeplugin.model.UpgradeType;
import me.osmanfurkan906.upgradeplugin.model.User;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ScoreboardManager {
    private final UpgradePlugin plugin;

    @Getter
    private JPerPlayerScoreboard scoreboard;

    public void initializeScoreboard() {
        scoreboard = new JPerPlayerScoreboard(
                (player) -> plugin.getConfig().getString("scoreboard.title"),
                (player) -> plugin.getConfig().getStringList("scoreboard.lines").stream()
                        .map(s -> s = replacePlaceholders(player, s)).collect(Collectors.toList())

        );
        Bukkit.getOnlinePlayers().forEach(this::addToScoreboard);
    }

    public void addToScoreboard(Player player) {
        scoreboard.addPlayer(player);
        scoreboard.updateScoreboard();
    }

    private String replacePlaceholders(Player player, String string) {
        final User user = plugin.getUserManager().getUser(player);
        return string.replace("%player%", player.getName())
                .replace("%balance%", (int) plugin.getWalletManager().getBalance(user) + "")
                .replace("%wallet_size%", user.getWalletSize() + "")
                .replace("%kill%", player.getStatistic(Statistic.PLAYER_KILLS) + "")
                .replace("%death%", player.getStatistic(Statistic.DEATHS) + "")
                .replace("%armor_level%", user.getUpgrades().getOrDefault(UpgradeType.ARMOR, 0) + "")
                .replace("%forcefield%", plugin.getForcefieldManager().getCooldown().getCooldownTime(player) + "")
                .replace("%cloaking_device%", plugin.getCloakingManager().getCooldown().getCooldownTime(player) + "")
                .replace("%slot_size%", user.getStorageSize() + "");
    }
}
