package me.osmanfurkan906.upgradeplugin.model;


import me.osmanfurkan906.upgradeplugin.UpgradePlugin;
import me.osmanfurkan906.upgradeplugin.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;


public class UpgradeRecipe {
    private String name;

    private XMaterial target_material = XMaterial.AIR;

    private int target_quantity = 1;

    private HashMap<Integer, XMaterial> slot_materials;

    private ShapedRecipe recipe;

    public UpgradeRecipe(String paramString) {
        fillSlotMaterials();
        if (paramString.equalsIgnoreCase("null"))
            return;
        load(paramString);
    }

    public XMaterial getProduct() {
        return this.target_material;
    }

    public int getQuantity() {
        return this.target_quantity;
    }

    public String getName() {
        return this.name;
    }

    public HashMap<Integer, XMaterial> getIngredients() {
        return this.slot_materials;
    }

    public void register() {
        if (this.recipe != null)
            try {
                Bukkit.addRecipe(this.recipe);
            } catch (IllegalStateException illegalStateException) {
                Bukkit.getServer().getLogger().severe("Recipe " + getName() + " already registered.");
            }
    }

    public void loadFromInventory(Inventory paramInventory) {
        for (byte b = 1; b <= 9; b++) {
            ItemStack itemStack = paramInventory.getItem(b);
            if (itemStack == null) this.slot_materials.put((int) b, XMaterial.AIR);
            else this.slot_materials.put((int) b, XMaterial.matchXMaterial(itemStack));
        }
        loadRecipe();
    }

    private void fillSlotMaterials() {
        this.slot_materials = new HashMap<>();
        for (byte b = 1; b <= 9; b++)
            this.slot_materials.put((int) b, XMaterial.AIR);
    }

    public void load(String paramString) {
        String str1 = paramString.split("&")[0];
        this.name = str1.split("!")[0].replaceAll("dcr-", "");
        this.target_material = XMaterial.valueOf(str1.split("!")[1]);
        this.target_quantity = Integer.parseInt(str1.split("!")[2]);
        String str2 = paramString.split("&")[1];
        if ((str2.split("!")).length == 9) {
            byte b1 = 1;
            byte b2;
            int i;
            String[] arrayOfString;
            for (i = (arrayOfString = str2.split("!")).length, b2 = 0; b2 < i; ) {
                String str = arrayOfString[b2];
                this.slot_materials.put((int) b1, XMaterial.valueOf(str));
                b1++;
                b2++;
            }
            loadRecipe();
        }
    }

    public void loadRecipe() {
        if (!isValid())
            return;
        ItemStack itemStack = new ItemStack(target_material.parseMaterial(), target_quantity);
        itemStack.setDurability(target_material.getData());
            NamespacedKey namespacedKey = new NamespacedKey(JavaPlugin.getProvidingPlugin(UpgradePlugin.class), "dcr-" + this.target_material.name());
            this.recipe = new ShapedRecipe(namespacedKey, itemStack);
        this.recipe.shape("abc", "def", "ghi");
        String str2 = "abcdefghi";
        byte b = 0;
        for (int i : this.slot_materials.keySet()) {
            this.recipe.setIngredient(str2.charAt(b), this.slot_materials.get(i).parseMaterial());
            b++;
        }
    }

    private boolean isValid() {
        for (Integer integer : this.slot_materials.keySet()) {
            int i = integer;
            if (this.slot_materials.get(i) != XMaterial.AIR)
                return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder str = new StringBuilder("dcr-" + getName() + "!" + this.target_material.name() + "!" + this.target_quantity + "&");
        for (Integer integer : this.slot_materials.keySet()) {
            int i = integer;
            str.append(this.slot_materials.get(i).name()).append("!");
        }
        return str.substring(0, str.length() - 1);
    }
}
