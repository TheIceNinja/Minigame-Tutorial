package net.theiceninja.game;

import net.theiceninja.game.commands.StartCommand;
import net.theiceninja.game.commands.StopCommand;
import net.theiceninja.game.listeners.Events;
import net.theiceninja.game.manager.GameManager;
import net.theiceninja.game.manager.GameState;
import net.theiceninja.game.manager.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private PlayerManager playerManager = new PlayerManager();
   private GameManager gameManager = new GameManager(playerManager);

   public static Main plugin;
    @Override
    public void onEnable() {
        plugin = this;
        gameManager.setGameState(GameState.OFFLINE);
        getCommand("startgame").setExecutor(new StartCommand(gameManager));
        getCommand("stopgame").setExecutor(new StopCommand(gameManager));
        getServer().getPluginManager().registerEvents(new Events(gameManager), this);
    }

    @Override
    public void onDisable() {}
}
