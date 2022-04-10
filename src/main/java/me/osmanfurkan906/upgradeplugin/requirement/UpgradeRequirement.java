package me.osmanfurkan906.upgradeplugin.requirement;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.osmanfurkan906.upgradeplugin.model.User;
import me.osmanfurkan906.upgradeplugin.requirement.RequirementType;
import org.bukkit.entity.Player;


@Getter @RequiredArgsConstructor
public abstract class UpgradeRequirement {
    private final RequirementType requirementType;

    public RequirementType getRequirementType() {
        return requirementType;
    }

    public abstract boolean check(Player player, User user);

}
