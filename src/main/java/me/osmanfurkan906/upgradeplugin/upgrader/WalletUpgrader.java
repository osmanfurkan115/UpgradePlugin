package me.osmanfurkan906.upgradeplugin.upgrader;

import me.osmanfurkan906.upgradeplugin.model.User;

public class WalletUpgrader extends Upgrader {
    @Override
    public void upgrade(User user) {
        user.setWalletSize(user.getWalletSize() + 5000);
    }
}
