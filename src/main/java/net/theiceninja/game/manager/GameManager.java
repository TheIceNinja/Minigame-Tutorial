package net.theiceninja.game.manager;

import net.theiceninja.game.tasks.CooldownTask;
import net.theiceninja.game.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static net.theiceninja.game.Main.plugin;

public class GameManager {

    private PlayerManager playerManager;

    public GameManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    private List<UUID> alivePlayers = new ArrayList<>();
    private List<UUID> spectatorPlayers = new ArrayList<>();
    private GameState gameState;

    private CooldownTask cooldownTask;

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        switch (gameState) {
            case OFFLINE:
                stopGame();
                break;
            case COOLDOWN:
                if (this.cooldownTask != null) cooldownTask.cancel();
                this.cooldownTask = new CooldownTask(this);
                sendGameMessage("&a&l-> &6&lTHE GAME IS STARTING &a&l<-");
                cooldownTask.runTaskTimer(plugin, 20, 20);
                break;
            case ACTIVE:
                playerManager.giveKit();
                for (Player players : Bukkit.getOnlinePlayers()) {
                    addAlivePlayers(players);
                    players.setHealth(20);
                    players.setFoodLevel(20);
                }
                break;
        }
    }

    public void addAlivePlayers(Player player) {
        alivePlayers.add(player.getUniqueId());
        player.setGameMode(GameMode.SURVIVAL);
    }
    public void addSpectatorPlayers(Player player) {
        if (!alivePlayers.contains(player.getUniqueId())) return;
            alivePlayers.remove(player.getUniqueId());
            spectatorPlayers.add(player.getUniqueId());
            player.setGameMode(GameMode.SPECTATOR);
            player.getInventory().clear();
            player.sendMessage(ColorUtils.color("&cYou died!"));
            if (getGameState() == GameState.ACTIVE && getAlivePlayers().size() == 1) {
                for (Player player1 : Bukkit.getOnlinePlayers()) {
                    if (getAlivePlayers().contains(player1.getUniqueId())) {
                        if (!player1.isOnline() || player1 == null) return;
                        sendGameMessage("&cThe winner is " + player1.getDisplayName());
                        setGameState(GameState.OFFLINE);
                    }
                }
            }
    }
    public void stopGame() {
        sendGameMessage("&4&l-> &c&lTHE GAME IS NOW ENDING &4&l<-");
        if (this.cooldownTask != null) cooldownTask.cancel();
        for (Player players : Bukkit.getOnlinePlayers()) {
            players.setGameMode(GameMode.SURVIVAL);
            players.getInventory().clear();
        }
             alivePlayers.clear();
             spectatorPlayers.clear();
    }
    public void sendGameMessage(String str) {
        Bukkit.broadcastMessage(ColorUtils.color(str));
    }

    public void sendActionBar(String str) {
        for (Player players : Bukkit.getOnlinePlayers()) {
            players.sendActionBar(ColorUtils.color(str));
        }
    }

    public List<UUID> getSpectatorPlayers() {
        return spectatorPlayers;
    }

    public List<UUID> getAlivePlayers() {
        return alivePlayers;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }
}
