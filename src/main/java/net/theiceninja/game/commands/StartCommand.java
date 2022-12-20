package net.theiceninja.game.commands;

import net.theiceninja.game.manager.GameManager;
import net.theiceninja.game.manager.GameState;
import net.theiceninja.game.utils.ColorUtils;
import net.theiceninja.game.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {
    private final GameManager gameManager;

    public StartCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
       if (!(sender instanceof Player)) {
           sender.sendMessage(MessageUtils.MUST_BE_PLAYER_ERR);
           return true;
       }

       Player player = (Player) sender;

       if (!player.hasPermission("game.admin")) {
           player.sendMessage(MessageUtils.NO_PERMISSION);
           return true;
       }

       if (gameManager.getGameState() == GameState.COOLDOWN || gameManager.getGameState() == GameState.ACTIVE) {
           player.sendMessage(ColorUtils.color("&cThe game already started, you cant start another one."));
           return true;
       }

       if (Bukkit.getOnlinePlayers().size() <= 1) {
           player.sendMessage(ColorUtils.color("&cYou cant start the game while there is only " + Bukkit.getOnlinePlayers().size() + " player|s on the server."));
           return true;
       }

        gameManager.setGameState(GameState.COOLDOWN);

        return true;
    }
}
