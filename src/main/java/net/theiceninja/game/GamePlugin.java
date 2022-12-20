package net.theiceninja.game;

import net.theiceninja.game.commands.StartCommand;
import net.theiceninja.game.commands.StopCommand;
import net.theiceninja.game.listeners.Events;
import net.theiceninja.game.manager.GameManager;
import net.theiceninja.game.manager.GameState;
import net.theiceninja.game.manager.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class GamePlugin extends JavaPlugin {

    private PlayerManager playerManager;
   private GameManager gameManager;

   public static GamePlugin plugin;
    @Override
    public void onEnable() {
        plugin = this;
        this.playerManager = new PlayerManager();
        this.gameManager = new GameManager(playerManager);
        gameManager.setGameState(GameState.OFFLINE);
        getCommand("startgame").setExecutor(new StartCommand(gameManager));
        getCommand("stopgame").setExecutor(new StopCommand(gameManager));
        getServer().getPluginManager().registerEvents(new Events(gameManager), this);
    }

    @Override
    public void onDisable() {}
}
