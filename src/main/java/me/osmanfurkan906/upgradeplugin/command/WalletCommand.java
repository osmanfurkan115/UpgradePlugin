package me.osmanfurkan906.upgradeplugin.command;

import lombok.RequiredArgsConstructor;
import me.osmanfurkan906.upgradeplugin.UpgradePlugin;
import me.osmanfurkan906.upgradeplugin.model.EconomyResponse;
import me.osmanfurkan906.upgradeplugin.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


@RequiredArgsConstructor
public class WalletCommand implements CommandExecutor {
    private final UpgradePlugin plugin;

    @Override // /wallet add player 300
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.isOp()) {
            sender.sendMessage(Utils.colored("&cInsufficient permission"));
            return true;
        }
        if(args.length != 3 || !(args[0].equals("add") || args[0].equals("remove")) || !isInteger(args[2])) {
            sender.sendMessage(Utils.colored("&cUsage: /wallet <add/remove> <player> <amount>"));
            return true;
        }
        final Player player = Bukkit.getPlayer(args[1]);
        if(player == null) {
            sender.sendMessage(Utils.colored("&cPlayer not found"));
            return true;
        }
        EconomyResponse economyResponse = EconomyResponse.FAILURE;
        switch (args[0]) {
            case "add":
                economyResponse = plugin.getWalletManager().depositPlayer(player, Integer.parseInt(args[2]));
                break;
            case "remove":
                economyResponse = plugin.getWalletManager().withdrawPlayer(player, Integer.parseInt(args[2]));
                break;
        }
        player.sendMessage(economyResponse.getMessage());
        return true;
    }

    private boolean isInteger(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
