package net.theiceninja.game.commands;

import net.theiceninja.game.manager.GameManager;
import net.theiceninja.game.manager.GameState;
import net.theiceninja.game.utils.ColorUtils;
import net.theiceninja.game.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StopCommand implements CommandExecutor {

    private final GameManager gameManager;

    public StopCommand(GameManager gameManager) {
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

        if (gameManager.getGameState() != GameState.ACTIVE) {
            player.sendMessage(ColorUtils.color("&cYou cant end the game because there is no game online"));
            return true;
        }

        gameManager.setGameState(GameState.OFFLINE);

        return true;
    }
}
