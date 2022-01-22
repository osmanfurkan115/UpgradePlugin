package me.osmanfurkan906.upgradeplugin.manager;

import lombok.RequiredArgsConstructor;
import me.osmanfurkan906.upgradeplugin.UpgradePlugin;
import me.osmanfurkan906.upgradeplugin.model.EconomyResponse;
import me.osmanfurkan906.upgradeplugin.model.User;
import org.bukkit.OfflinePlayer;

@RequiredArgsConstructor
public class WalletManager {
    private final UpgradePlugin plugin;

    public EconomyResponse depositPlayer(OfflinePlayer player, int amount) {
        final User user = plugin.getUserManager().getUser(player);
        if(!canDeposit(user, amount)) return EconomyResponse.LIMIT;
        user.setWalletMoney(user.getWalletMoney() + amount);
        return EconomyResponse.SUCCESS;
    }

    public EconomyResponse withdrawPlayer(OfflinePlayer player, int amount) {
        final User user = plugin.getUserManager().getUser(player);
        if(!canWithdraw(user, amount)) return EconomyResponse.LIMIT;
        user.setWalletMoney(user.getWalletMoney() - amount);
        return EconomyResponse.SUCCESS;
    }

    private boolean canDeposit(User user, int amount) {
        return user.getWalletSize() >= user.getWalletMoney() + amount;
    }

    private boolean canWithdraw(User user, int amount) {
        return user.getWalletMoney() >= amount;
    }
}
