package me.osmanfurkan906.upgradeplugin.model;

import me.osmanfurkan906.upgradeplugin.utils.Utils;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Cooldown {
    private final Map<UUID, Long> cooldown = new HashMap<>();
    private final int cooldownTime;

    public Cooldown(int cooldownTime) {
        this.cooldownTime = cooldownTime;
    }

    public boolean isInCooldown(Player player) {
        if(!cooldown.containsKey(player.getUniqueId())) return false;
        long timeLeft = System.currentTimeMillis() - cooldown.get(player.getUniqueId());
        return TimeUnit.MILLISECONDS.toSeconds(timeLeft) < cooldownTime;
    }

    public int getCooldownTime(Player player) {
        if(!isInCooldown(player)) return 0;
        long timeLeft = (System.currentTimeMillis() - cooldown.get(player.getUniqueId()));
        return (int) (cooldownTime - TimeUnit.MILLISECONDS.toSeconds(timeLeft));
    }

    public void createCooldown(Player player) {
        if(isInCooldown(player)) return;
        cooldown.put(player.getUniqueId(), System.currentTimeMillis());
    }

    public void sendCooldownMessage(Player player) {
        player.sendMessage(Utils.colored("&cYou are on cooldown for " + getCooldownTime(player)) + " seconds");
    }
}
