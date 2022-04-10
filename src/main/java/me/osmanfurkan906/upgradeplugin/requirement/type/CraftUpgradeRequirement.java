package me.osmanfurkan906.upgradeplugin.requirement.type;

import lombok.Getter;
import me.osmanfurkan906.upgradeplugin.model.User;
import me.osmanfurkan906.upgradeplugin.requirement.RequirementType;
import me.osmanfurkan906.upgradeplugin.requirement.UpgradeRequirement;
import org.bukkit.entity.Player;
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

    @Override
    public boolean check(Player player, User user) {
        if(itemStack == null) return false;
        if (player.getInventory().containsAtLeast(itemStack, amount)) {
            itemStack.setAmount(amount);
            player.getInventory().removeItem(itemStack);
            return true;
        }
        return false;
    }
}
