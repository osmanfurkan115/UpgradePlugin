package me.osmanfurkan906.upgradeplugin.task;

import lombok.RequiredArgsConstructor;
import me.osmanfurkan906.upgradeplugin.UpgradePlugin;
import org.bukkit.scheduler.BukkitRunnable;

@RequiredArgsConstructor
public class ScoreboardTask extends BukkitRunnable {
    private final UpgradePlugin plugin;

    @Override
    public void run() {
        plugin.getScoreboardManager().getScoreboard().updateScoreboard();
    }
}
