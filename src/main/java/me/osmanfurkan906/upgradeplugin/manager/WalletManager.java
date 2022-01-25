package me.osmanfurkan906.upgradeplugin.manager;

import lombok.RequiredArgsConstructor;
import me.osmanfurkan906.upgradeplugin.UpgradePlugin;
import me.osmanfurkan906.upgradeplugin.model.EconomyResponse;
import me.osmanfurkan906.upgradeplugin.model.User;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class WalletManager {
    private final UpgradePlugin plugin;

    public EconomyResponse depositPlayer(OfflinePlayer player, int amount) {
        final User user = plugin.getUserManager().getUser(player);
        if(!canDeposit(user, amount)) return EconomyResponse.LIMIT;
        plugin.getEconomy().depositPlayer(player, amount);
        return EconomyResponse.SUCCESS;
    }

    public EconomyResponse withdrawPlayer(OfflinePlayer player, int amount) {
        final User user = plugin.getUserManager().getUser(player);
        if(!canWithdraw(user, amount)) return EconomyResponse.LIMIT;
        plugin.getEconomy().withdrawPlayer(player, amount);
        return EconomyResponse.SUCCESS;
    }

    private boolean canDeposit(User user, int amount) {
        return user.getWalletSize() >= getBalance(user) + amount;
    }

    private boolean canWithdraw(User user, int amount) {
        return getBalance(user) >= amount;
    }

    public double getBalance(User user) {
        final Player player = user.getPlayer().get();
        final double balance = plugin.getEconomy().getBalance(player);
        if(balance >= user.getWalletSize()) plugin.getEconomy().withdrawPlayer(player, balance - user.getWalletSize());
        return plugin.getEconomy().getBalance(player);
    }
}
