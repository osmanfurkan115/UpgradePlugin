package me.osmanfurkan906.upgradeplugin.model;

import lombok.Getter;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

@Getter
public class KillUpgradeRequirement extends UpgradeRequirement {
    private final int killAmount;

    public KillUpgradeRequirement(int killAmount) {
        super(RequirementType.KILL);
        this.killAmount = killAmount;
    }

    @Override
    public boolean check(Player player, User user) {
        return player.getStatistic(Statistic.PLAYER_KILLS) >= killAmount;
    }
}
