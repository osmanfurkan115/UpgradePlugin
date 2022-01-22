package me.osmanfurkan906.upgradeplugin.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter @RequiredArgsConstructor
public class UpgradeRequirement {
    private final RequirementType requirementType;

    public RequirementType getRequirementType() {
        return requirementType;
    }
}
