package me.osmanfurkan906.upgradeplugin.model;

import me.osmanfurkan906.upgradeplugin.upgrader.*;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;

import java.util.Arrays;

public enum UpgradeType {
    ARMOR("armor", new ArmorUpgrader(), Material.LEATHER_HELMET),
    CLOAKING_DEVICE("cloaking_device", new CloakingDeviceUpgrader(), Material.CLOCK),
    FORCEFIELD("forcefield", new ForcefieldUpgrader(), Material.SHIELD),
    JETPACK("jetpack", new JetpackUpgrader(), Material.LEATHER_BOOTS),
    STORAGE_SLOT("storage_slot", new StorageSlotUpgrader(), Material.CHEST),
    WALLET("wallet", new WalletUpgrader(), Material.DIAMOND);

    UpgradeType(String path, Upgrader upgrader, Material material) {
        this.path = path;
        this.upgrader = upgrader;
        this.material = material;
    }

    private final String path;
    private final Upgrader upgrader;
    private final Material material;


    public String getPath() {
        return path;
    }

    public Upgrader getUpgrader() {
        return upgrader;
    }

    public Material getMaterial() {
        return material;
    }

    public String toString() {
        String name = this.name();
        name = name.replace("_", " ");
        name = WordUtils.capitalizeFully(name).replace("ı", "i");
        return name;
    }

    public static UpgradeType fromPath(String path) {
        return Arrays.stream(values())
                .filter(upgradeType -> upgradeType.getPath().equalsIgnoreCase(path))
                .findFirst()
                .get();
    }
}
