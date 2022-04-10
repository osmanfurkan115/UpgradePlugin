package me.osmanfurkan906.upgradeplugin.requirement;

import lombok.Getter;
import me.osmanfurkan906.upgradeplugin.requirement.type.CraftRequirement;
import me.osmanfurkan906.upgradeplugin.requirement.type.KillRequirement;
import me.osmanfurkan906.upgradeplugin.requirement.type.MoneyRequirement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Requirement {
    private final int level;
    private final List<UpgradeRequirement> upgradeRequirements = new ArrayList<>();

    public Requirement(int level, KillRequirement killRequirement,
                       MoneyRequirement moneyRequirement,
                       CraftRequirement craftRequirement) {
        this.level = level;
        upgradeRequirements.addAll(Arrays.asList(killRequirement, craftRequirement, moneyRequirement));
    }
}
