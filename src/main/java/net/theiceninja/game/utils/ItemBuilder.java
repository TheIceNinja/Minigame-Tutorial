package net.theiceninja.game.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilder {

    /**
     *
     * @param material the Material
     * @param amount of item
     * @param displayName name of the Item
     * @param lore of the Item
     * @param customModelData customModelData
     * @return the itemstack
     */

    public static ItemStack createItem(Material material, int amount, String displayName, List<String> lore, int customModelData) {

        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemStack.setAmount(amount);
        itemMeta.setDisplayName(ColorUtils.color(displayName));
        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(customModelData);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
    public static ItemStack createItem(Material material, int amount, String displayName) {

        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemStack.setAmount(amount);
        itemMeta.setDisplayName(ColorUtils.color(displayName));
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
    public static ItemStack createItem(Material material, int amount, String displayName, Integer customModelDate) {

        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemStack.setAmount(amount);
        itemMeta.setDisplayName(ColorUtils.color(displayName));
        itemMeta.setCustomModelData(customModelDate);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

}
