package me.osmanfurkan906.upgradeplugin;

import com.hakan.inventoryapi.InventoryAPI;
import com.hakan.itembuilder.ItemBuilderAPI;
import lombok.Getter;
import me.osmanfurkan906.upgradeplugin.command.UpgradeCommand;
import me.osmanfurkan906.upgradeplugin.command.WalletCommand;
import me.osmanfurkan906.upgradeplugin.listener.ForcefieldListener;
import me.osmanfurkan906.upgradeplugin.listener.PlayerListener;
import me.osmanfurkan906.upgradeplugin.listener.StorageListener;
import me.osmanfurkan906.upgradeplugin.manager.*;
import me.osmanfurkan906.upgradeplugin.task.ScoreboardTask;
import me.osmanfurkan906.upgradeplugin.utils.Yaml;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class UpgradePlugin extends JavaPlugin {

    private InventoryAPI inventoryAPI;
    private ItemBuilderAPI itemBuilderAPI;

    private CloakingManager cloakingManager;
    private ForcefieldManager forcefieldManager;
    private InvisibilityManager invisibilityManager;
    private UpgradeManager upgradeManager;
    private UserManager userManager;
    private ScoreboardManager scoreboardManager;
    private StorageManager storageManager;
    private WalletManager walletManager;

    private ItemStack forceFieldItem;
    private ItemStack cloakingItem;

    private Yaml data;
    private Yaml upgrades;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        inventoryAPI = InventoryAPI.getInstance(this);
        itemBuilderAPI = ItemBuilderAPI.getInstance(this);
        forceFieldItem = itemBuilderAPI.getItemBuilder().setAmount(1).setName(getConfig().getString("forcefield.name"))
                .setType(Material.matchMaterial(getConfig().getString("forcefield.type"))).build();
        cloakingItem = itemBuilderAPI.getItemBuilder().setAmount(1).setName(getConfig().getString("cloaking.name"))
                .setType(Material.matchMaterial(getConfig().getString("cloaking.type"))).build();
        cloakingManager = new CloakingManager(this);
        forcefieldManager = new ForcefieldManager(this);
        invisibilityManager = new InvisibilityManager();
        upgradeManager = new UpgradeManager(this);
        userManager = new UserManager(this);
        scoreboardManager = new ScoreboardManager(this);
        storageManager = new StorageManager(this);
        walletManager = new WalletManager(this);

        data = new Yaml(getDataFolder() + "/data.yml", "data.yml");
        upgrades = new Yaml(getDataFolder() + "/upgrades.yml", "upgrades.yml");

        userManager.loadUsers();
        scoreboardManager.initializeScoreboard();
        upgradeManager.initializeRequirements();

        getCommand("upgrade").setExecutor(new UpgradeCommand(this));
        getCommand("wallet").setExecutor(new WalletCommand(this));

        getServer().getPluginManager().registerEvents(new ForcefieldListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new StorageListener(this), this);

        new ScoreboardTask(this).runTaskTimer(this, 20L, 20L);
    }

    @Override
    public void onDisable() {
        userManager.saveUsers();
    }

}
