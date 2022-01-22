package me.osmanfurkan906.upgradeplugin.task;

import me.osmanfurkan906.upgradeplugin.UpgradePlugin;
import me.osmanfurkan906.upgradeplugin.manager.ForcefieldManager;
import me.osmanfurkan906.upgradeplugin.model.UpgradeType;
import me.osmanfurkan906.upgradeplugin.model.User;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ForcefieldTask extends BukkitRunnable {
    private final UpgradePlugin plugin;
    private final ForcefieldManager forcefieldManager;
    private final Player player;
    private int runTime;
    private final int secondLimit;

    public ForcefieldTask(UpgradePlugin plugin, Player player) {
        this.plugin = plugin;
        this.forcefieldManager = plugin.getForcefieldManager();
        this.player = player;
        final User user = plugin.getUserManager().getUser(player);
        secondLimit = 30 + (user.getUpgrades().getOrDefault(UpgradeType.FORCEFIELD, 0) * 30);
    }

    public void startTask() {
        this.runTaskTimer(plugin, 0, 2L);
    }

    @Override
    public void run() {
        final int passedSeconds = runTime / 10;
        if(passedSeconds >= secondLimit) {
            this.cancel();
            forcefieldManager.getPlayers().remove(player);
            forcefieldManager.getCooldown().createCooldown(player);
        }
        forcefieldManager.createForcefieldParticle(player);
        runTime++;
    }
}
