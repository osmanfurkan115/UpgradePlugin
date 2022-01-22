package me.osmanfurkan906.upgradeplugin.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class Requirement {
    private final int level;
    private final KillUpgradeRequirement killUpgradeRequirement;
    private final MoneyUpgradeRequirement moneyUpgradeRequirement;
    private final CraftUpgradeRequirement craftUpgradeRequirement;
    private final List<UpgradeRequirement> upgradeRequirements = new ArrayList<>(Arrays.asList(killUpgradeRequirement, craftUpgradeRequirement, moneyUpgradeRequirement));
}
