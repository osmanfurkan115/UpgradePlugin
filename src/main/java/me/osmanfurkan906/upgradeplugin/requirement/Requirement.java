package me.osmanfurkan906.upgradeplugin.requirement;

import lombok.Getter;
import me.osmanfurkan906.upgradeplugin.requirement.type.CraftUpgradeRequirement;
import me.osmanfurkan906.upgradeplugin.requirement.type.KillUpgradeRequirement;
import me.osmanfurkan906.upgradeplugin.requirement.type.MoneyUpgradeRequirement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Requirement {
    private final int level;
    private final List<UpgradeRequirement> upgradeRequirements = new ArrayList<>();

    public Requirement(int level, KillUpgradeRequirement killUpgradeRequirement,
                       MoneyUpgradeRequirement moneyUpgradeRequirement,
                       CraftUpgradeRequirement craftUpgradeRequirement) {
        this.level = level;
        upgradeRequirements.addAll(Arrays.asList(killUpgradeRequirement, craftUpgradeRequirement, moneyUpgradeRequirement));
    }
}
