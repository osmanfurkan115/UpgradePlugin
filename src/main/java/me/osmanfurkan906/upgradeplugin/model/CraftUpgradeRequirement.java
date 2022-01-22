package me.osmanfurkan906.upgradeplugin.model;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

@Getter
public class CraftUpgradeRequirement extends UpgradeRequirement {
    private final ItemStack itemStack;
    private final int amount;

    public CraftUpgradeRequirement(ItemStack itemStack, int amount) {
        super(RequirementType.CRAFT);
        this.itemStack = itemStack;
        this.amount = amount;
    }
}
