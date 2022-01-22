package me.osmanfurkan906.upgradeplugin.model;

import lombok.Getter;

@Getter
public class KillUpgradeRequirement extends UpgradeRequirement {
    private final int killAmount;

    public KillUpgradeRequirement(int killAmount) {
        super(RequirementType.KILL);
        this.killAmount = killAmount;
    }
}
