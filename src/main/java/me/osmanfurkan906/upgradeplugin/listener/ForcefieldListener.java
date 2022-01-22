package me.osmanfurkan906.upgradeplugin.listener;

import me.osmanfurkan906.upgradeplugin.UpgradePlugin;
import me.osmanfurkan906.upgradeplugin.manager.ForcefieldManager;
import me.osmanfurkan906.upgradeplugin.model.Cooldown;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

public class ForcefieldListener implements Listener {
    private final UpgradePlugin plugin;
    private final ForcefieldManager forcefieldManager;

    public ForcefieldListener(UpgradePlugin plugin) {
        this.plugin = plugin;
        this.forcefieldManager = plugin.getForcefieldManager();
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        final Player player = e.getPlayer();
        if(!isForcefieldItem(e.getItemDrop().getItemStack())) return;
        plugin.getInvisibilityManager().useInvisibility(player);
        e.setCancelled(true);
    }

    @EventHandler
    public void onSwapHand(PlayerSwapHandItemsEvent e) {
        final Player player = e.getPlayer();
        if(!isForcefieldItem(e.getOffHandItem())) return;
        e.setCancelled(true);
        final Cooldown cooldown = forcefieldManager.getCooldown();
        if(cooldown.isInCooldown(player)) {
            cooldown.sendCooldownMessage(player);
            return;
        }
        forcefieldManager.useForcefield(player);
    }

    @EventHandler
    public void onItemChange(PlayerItemHeldEvent e) {
        final Player player = e.getPlayer();
        if(!isForcefieldItem(player.getInventory().getItem(e.getPreviousSlot()))) return;
        player.setInvisible(false);
    }

    private boolean isForcefieldItem(ItemStack item) {
        return item != null && item.isSimilar(plugin.getForceFieldItem());
    }
}
