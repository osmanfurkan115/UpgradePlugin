package me.osmanfurkan906.upgradeplugin.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class Requirement {
    private final int level;
    private final KillUpgradeRequirement killUpgradeRequirement;
    private final MoneyUpgradeRequirement moneyUpgradeRequirement;
    private final CraftUpgradeRequirement craftUpgradeRequirement;
}
