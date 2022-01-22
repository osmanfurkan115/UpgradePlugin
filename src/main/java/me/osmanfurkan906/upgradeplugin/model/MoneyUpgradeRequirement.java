package me.osmanfurkan906.upgradeplugin.model;

import lombok.Getter;

@Getter
public class MoneyUpgradeRequirement extends UpgradeRequirement {
    private final int moneyAmount;

    public MoneyUpgradeRequirement(int moneyAmount) {
        super(RequirementType.MONEY);
        this.moneyAmount = moneyAmount;
    }
}
