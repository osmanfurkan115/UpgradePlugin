package me.osmanfurkan906.upgradeplugin.gui;

import com.hakan.inventoryapi.inventory.ClickableItem;
import com.hakan.inventoryapi.inventory.HInventory;
import me.osmanfurkan906.upgradeplugin.UpgradePlugin;
import me.osmanfurkan906.upgradeplugin.model.UpgradeType;
import me.osmanfurkan906.upgradeplugin.model.User;
import me.osmanfurkan906.upgradeplugin.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class UpgradeGUI {
    private final UpgradePlugin plugin;

    public UpgradeGUI(UpgradePlugin plugin) {
        this.plugin = plugin;
    }

    public void open(Player player) {
        final User user = plugin.getUserManager().getUser(player);
        final HInventory inventory = plugin.getInventoryAPI().getInventoryCreator().setSize(6).setTitle(ChatColor.GRAY + "Upgrade").create();
        int paperSlot = 0;

        for (UpgradeType upgradeType : UpgradeType.values()) {
            final ItemStack item = plugin.getItemBuilderAPI().getItemBuilder().setAmount(1).setType(Material.PAPER)
                    .setName("&eLevel: " + user.getUpgrades().getOrDefault(upgradeType, 0)).build();
            inventory.setItem(paperSlot, ClickableItem.empty(item));
            final ItemStack materialItem = plugin.getItemBuilderAPI().getItemBuilder().setAmount(1).setType(upgradeType.getMaterial())
                    .setName("&b" + upgradeType).build();
            inventory.setItem(paperSlot + 1, ClickableItem.empty(materialItem));

            for (int i = 1; i <= 5; i++) {
                final int level = user.getUpgrades().getOrDefault(upgradeType, 0);
                ClickableItem clickableItem;
                if (level >= i) {
                    clickableItem = ClickableItem.empty(plugin.getItemBuilderAPI().getItemBuilder().setType(XMaterial.GREEN_STAINED_GLASS_PANE.parseMaterial())
                            .setName("&7").setAmount(1).setGlow(true).build());
                } else {
                    clickableItem = ClickableItem.of((plugin.getItemBuilderAPI().getItemBuilder().setType(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName("&7").setAmount(1).build()), (event) -> {
                        plugin.getUpgradeManager().upgradeLevel(player, upgradeType);
                        inventory.close(player);
                    });
                }
                inventory.setItem(paperSlot + 1 + i, clickableItem);
            }

            paperSlot += 9;
        }

        inventory.open(player);
    }
}
