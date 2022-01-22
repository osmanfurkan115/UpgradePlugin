package me.osmanfurkan906.upgradeplugin.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class User {
    private final UUID playerUUID;
    private final Map<UpgradeType, Integer> upgrades;
    @Setter private int walletMoney = 0;
    @Setter private int walletSize = 5000;
    @Setter private int storageSize = 9;

    public Optional<Player> getPlayer() {
        return Optional.ofNullable(Bukkit.getPlayer(playerUUID));
    }

    public void applyJump() {
        if(upgrades.containsKey(UpgradeType.JETPACK)) {
            PotionEffect potionEffect = new PotionEffect(PotionEffectType.JUMP,
                    999999999, upgrades.get(UpgradeType.JETPACK) - 1, false, false);
            getPlayer().ifPresent(player -> player.addPotionEffect(potionEffect));
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "playerUUID=" + playerUUID +
                ", upgrades=" + upgrades +
                ", walletMoney=" + walletMoney +
                ", walletSize=" + walletSize +
                ", storageSize=" + storageSize +
                '}';
    }
}
