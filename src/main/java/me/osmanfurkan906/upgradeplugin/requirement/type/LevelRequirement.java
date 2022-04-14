package me.osmanfurkan906.upgradeplugin.requirement.type;

import me.osmanfurkan906.upgradeplugin.model.User;
import me.osmanfurkan906.upgradeplugin.requirement.RequirementType;
import me.osmanfurkan906.upgradeplugin.requirement.UpgradeRequirement;
import org.bukkit.entity.Player;

public class LevelRequirement extends UpgradeRequirement {
    private final int xp;

    public LevelRequirement(int xp) {
        super(RequirementType.LEVEL);
        this.xp = xp;
    }

    @Override
    public boolean check(Player player, User user) {
        return player.getExp() >= xp;
    }
}
