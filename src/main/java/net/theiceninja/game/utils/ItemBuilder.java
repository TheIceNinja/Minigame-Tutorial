package net.theiceninja.game.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {

    /**
     *
     * @param material the Material of the Item
     * @param amount amount of item
     * @param displayName name of the Item
     * @return the ItemStack
     */
    public static ItemStack createItem(Material material, int amount, String displayName) {

        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemStack.setAmount(amount);
        itemMeta.setDisplayName(ColorUtils.color(displayName));
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
