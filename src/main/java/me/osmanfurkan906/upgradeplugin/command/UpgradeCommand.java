package me.osmanfurkan906.upgradeplugin.command;

import lombok.RequiredArgsConstructor;
import me.osmanfurkan906.upgradeplugin.UpgradePlugin;
import me.osmanfurkan906.upgradeplugin.gui.UpgradeGUI;
import me.osmanfurkan906.upgradeplugin.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class UpgradeCommand implements CommandExecutor {
    private final UpgradePlugin plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return true;
        final Player player = (Player) sender;
        if(player.isOp()) {
            player.getInventory().addItem(plugin.getForceFieldItem());
            player.getInventory().addItem(plugin.getCloakingItem());
            player.sendMessage(Utils.colored("&cYou got the items since you are an operator"));
        }
        new UpgradeGUI(plugin).open(player);
        return true;
    }
}
