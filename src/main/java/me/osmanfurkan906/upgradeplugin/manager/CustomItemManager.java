package me.osmanfurkan906.upgradeplugin.manager;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.osmanfurkan906.upgradeplugin.UpgradePlugin;
import me.osmanfurkan906.upgradeplugin.model.CraftUpgradeRequirement;
import me.osmanfurkan906.upgradeplugin.utils.XMaterial;
import org.bukkit.inventory.ShapedRecipe;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class CustomItemManager {
    private final UpgradePlugin plugin;
    @Getter
    private final Map<String, CraftUpgradeRequirement> craftUpgrades = new HashMap<>();

//    public void load(String paramString) {
//        String str1 = paramString.split("&")[0];
//        this.name = str1.split("!")[0].replaceAll("dcr-", "");
//        this.target_material = XMaterial.valueOf(str1.split("!")[1]);
//        String str2 = paramString.split("&")[1];
//        if ((str2.split("!")).length == 9) {
//            byte b1 = 1;
//            byte b2;
//            int i;
//            String[] arrayOfString;
//            for (i = (arrayOfString = str2.split("!")).length, b2 = 0; b2 < i; ) {
//                String str = arrayOfString[b2];
//                this.slot_materials.put((int) b1, XMaterial.valueOf(str));
//                b1++;
//                b2++;
//            }
//            loadRecipe();
//        }
//    }
}
