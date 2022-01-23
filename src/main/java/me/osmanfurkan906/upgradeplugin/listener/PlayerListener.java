package me.osmanfurkan906.upgradeplugin.listener;

import lombok.RequiredArgsConstructor;
import me.osmanfurkan906.upgradeplugin.UpgradePlugin;
import me.osmanfurkan906.upgradeplugin.model.Cooldown;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

@RequiredArgsConstructor
public class PlayerListener implements Listener {
    private final UpgradePlugin plugin;

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        applyJump(e.getPlayer());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        plugin.getScoreboardManager().addToScoreboard(e.getPlayer());
        applyJump(e.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        plugin.getScoreboardManager().getScoreboard().removePlayer(e.getPlayer());
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        if(!(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR))) return;
        if(e.getItem() == null || !e.getItem().isSimilar(plugin.getCloakingItem())) return;
        final Player player = e.getPlayer();
        final Cooldown cooldown = plugin.getCloakingManager().getCooldown();
        if(cooldown.isInCooldown(player)) {
            cooldown.sendCooldownMessage(player);
            return;
        }
        plugin.getCloakingManager().useCloakingDevice(player);
    }

    private void applyJump(Player player) {
        if(!plugin.getUserManager().isExist(player)) return;
        plugin.getUserManager().getUser(player).applyJump();
    }
}
