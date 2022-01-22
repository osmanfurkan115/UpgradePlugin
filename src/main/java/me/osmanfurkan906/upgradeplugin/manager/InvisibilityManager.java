package me.osmanfurkan906.upgradeplugin.manager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class InvisibilityManager {

    public void useInvisibility(Player player, boolean invisible) {
        final ChatColor color = invisible ? ChatColor.RED : ChatColor.GREEN;
        final String active = invisible ? "invisible" : "visible";
        player.sendMessage(color + "Your visibility mode changed to " + active);
        player.setInvisible(invisible); //TODO Use packets to make the item invisible
    }

    public void useInvisibility(Player player) {
        useInvisibility(player, !player.isInvisible());
    }
}
