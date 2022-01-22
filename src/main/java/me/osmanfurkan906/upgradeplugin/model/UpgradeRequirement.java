package me.osmanfurkan906.upgradeplugin.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;


@Getter @RequiredArgsConstructor
public abstract class UpgradeRequirement {
    private final RequirementType requirementType;

    public RequirementType getRequirementType() {
        return requirementType;
    }

    public abstract boolean check(Player player, User user);

}
