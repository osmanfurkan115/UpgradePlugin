package me.osmanfurkan906.upgradeplugin.upgrader;

import me.osmanfurkan906.upgradeplugin.model.User;

public class StorageSlotUpgrader extends Upgrader {
    @Override
    public void upgrade(User user) {
        user.setStorageSize(user.getStorageSize() + 9);
    }
}
