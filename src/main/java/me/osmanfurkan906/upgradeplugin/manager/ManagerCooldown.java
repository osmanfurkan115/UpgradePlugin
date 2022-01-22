package me.osmanfurkan906.upgradeplugin.manager;

import me.osmanfurkan906.upgradeplugin.model.Cooldown;

public abstract class ManagerCooldown {

    protected final Cooldown cooldown;

    public ManagerCooldown(int cooldownTime) {
        cooldown = new Cooldown(cooldownTime);
    }

    public Cooldown getCooldown() {
        return cooldown;
    }
}
