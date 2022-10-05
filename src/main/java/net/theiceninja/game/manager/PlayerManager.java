package net.theiceninja.game.manager;

import net.theiceninja.game.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerManager {

    public void giveKit() {
        ItemStack ironSword = ItemBuilder.createItem(Material.IRON_SWORD, 1, "&cIron Sword");
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getInventory().addItem(ironSword);
        }
    }
}
