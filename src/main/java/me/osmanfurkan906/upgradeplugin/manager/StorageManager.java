package me.osmanfurkan906.upgradeplugin.manager;

import lombok.RequiredArgsConstructor;
import me.osmanfurkan906.upgradeplugin.UpgradePlugin;
import me.osmanfurkan906.upgradeplugin.model.User;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class StorageManager {
    private final UpgradePlugin plugin;

    public boolean canPickup(Player player, Item item) {
        final int amount = item.getItemStack().getAmount();
        int filledSlotAmount = amount >= 64 ? amount / 64 : 1;
        for (ItemStack content : player.getInventory().getStorageContents()) {
            if(content != null && content.getType() != Material.AIR) filledSlotAmount++;
        }
        final User user = plugin.getUserManager().getUser(player);
        return user.getStorageSize() >= filledSlotAmount;
    }
}
