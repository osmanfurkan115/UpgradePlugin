package me.osmanfurkan906.upgradeplugin.model;

import lombok.Getter;
import me.osmanfurkan906.upgradeplugin.UpgradePlugin;
import org.bukkit.entity.Player;

@Getter
public class MoneyUpgradeRequirement extends UpgradeRequirement {
    private final int moneyAmount;
    private final UpgradePlugin plugin;

    public MoneyUpgradeRequirement(UpgradePlugin plugin, int moneyAmount) {
        super(RequirementType.MONEY);
        this.plugin = plugin;
        this.moneyAmount = moneyAmount;
    }

    @Override
    public boolean check(Player player, User user) {
        if (plugin.getWalletManager().getBalance(user) >= moneyAmount) {
            plugin.getWalletManager().withdrawPlayer(player, moneyAmount);
            return true;
        }
        return false;
    }
}
