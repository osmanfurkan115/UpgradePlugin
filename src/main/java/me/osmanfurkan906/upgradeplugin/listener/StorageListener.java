package me.osmanfurkan906.upgradeplugin.listener;

import lombok.RequiredArgsConstructor;
import me.osmanfurkan906.upgradeplugin.UpgradePlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

@RequiredArgsConstructor
public class StorageListener implements Listener {
    private final UpgradePlugin plugin;

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent e) {
        if(!(e.getEntity() instanceof Player)) return;
        if(!plugin.getStorageManager().canPickup((Player) e.getEntity(), e.getItem())) e.setCancelled(true);
    }
}
