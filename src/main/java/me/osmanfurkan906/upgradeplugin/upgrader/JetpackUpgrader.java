package me.osmanfurkan906.upgradeplugin.upgrader;

import me.osmanfurkan906.upgradeplugin.model.User;

public class JetpackUpgrader extends Upgrader {
    @Override
    public void upgrade(User user) {
        user.applyJump();
    }
}
