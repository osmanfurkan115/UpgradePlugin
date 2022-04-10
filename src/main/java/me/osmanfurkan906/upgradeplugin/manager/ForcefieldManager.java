package me.osmanfurkan906.upgradeplugin.manager;

import lombok.Getter;
import me.osmanfurkan906.upgradeplugin.UpgradePlugin;
import me.osmanfurkan906.upgradeplugin.model.Cooldown;
import me.osmanfurkan906.upgradeplugin.task.ForcefieldTask;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class ForcefieldManager {
    private final UpgradePlugin plugin;
    @Getter private final Set<Player> players = new HashSet<>();
    @Getter private final Cooldown cooldown = new Cooldown(60);

    public ForcefieldManager(UpgradePlugin plugin) {
        this.plugin = plugin;
    }

    public void useForcefield(Player player) {
        if(cooldown.isInCooldown(player)) return;
        if(players.contains(player)) return;
        new ForcefieldTask(plugin, player).startTask();
        players.add(player);
    }

    public void createForcefieldParticle(Player player) {
        final double dps = 36.0D;
        final double radius = 1.0D;
        final Location location = player.getLocation();
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            for (int a1 = 0; a1 < 360.0D / dps; a1++) {
                for (int a2 = 0; a2 < 360.0D / dps; a2++) {
                    double x = location.getX() + radius * Math.cos(a1 * dps) * Math.cos(a2 * dps);
                    double z = location.getZ() + radius * Math.cos(a1 * dps) * Math.sin(a2 * dps);
                    double y = location.getY() + 1.0D + radius * Math.sin(a1 * dps);
                    player.getWorld().spawnParticle(Particle.REDSTONE, x, y, z, 0, new Particle.DustOptions(Color.AQUA, 0.5F));
                }
            }
        });
    }
}
