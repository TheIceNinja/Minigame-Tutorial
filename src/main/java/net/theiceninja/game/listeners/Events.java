package net.theiceninja.game.listeners;

import net.theiceninja.game.manager.GameManager;
import net.theiceninja.game.manager.GameState;
import net.theiceninja.game.utils.ColorUtils;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {

    private final GameManager gameManager;


    public Events(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onBuild(BlockPlaceEvent event) {
        if (gameManager.getGameState() == GameState.OFFLINE) {
            if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
                event.setCancelled(false);
                return;
            }
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onBuild(BlockBreakEvent event) {
        if (gameManager.getGameState() == GameState.OFFLINE) {
            if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
                event.setCancelled(false);
                return;
            }
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onBuild(PlayerDropItemEvent event) {
        if (gameManager.getGameState() == GameState.OFFLINE) {
            if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
                event.setCancelled(false);
                return;
            }
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPVP(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player && gameManager.getGameState() == GameState.OFFLINE) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (gameManager.getGameState().equals(GameState.OFFLINE) || gameManager.getGameState() == GameState.COOLDOWN)
            event.setCancelled(true);

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        event.setQuitMessage(ColorUtils.color("&7[&4-&7] &c" + p.getDisplayName()));
        if (gameManager.getGameState() == GameState.ACTIVE || gameManager.getGameState() == GameState.COOLDOWN) {
            if (gameManager.getAlivePlayers().isEmpty()) {
                gameManager.setGameState(GameState.OFFLINE);
            }

                gameManager.addSpectatorPlayers(p);
            /*
                        if (gameManager.getAlivePlayers().size() <= 1) {
                for (UUID playerUUID : gameManager.getAlivePlayers()) {
                    @Nullable Player player = Bukkit.getPlayer(playerUUID);
                    if (player == null) continue;
                    gameManager.sendGameMessage("&cThe winner is " + Objects.requireNonNull(player).getDisplayName());
                    gameManager.setGameState(GameState.OFFLINE);
                }
            }
             */

        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        event.setJoinMessage(ColorUtils.color("&7[&2+&7] &a" + p.getDisplayName()));
        p.getInventory().clear();
        p.setGameMode(GameMode.SURVIVAL);
        if (gameManager.getGameState() == GameState.ACTIVE || gameManager.getGameState() == GameState.COOLDOWN) {
            gameManager.addSpectatorPlayers(p);
            p.setGameMode(GameMode.SPECTATOR);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player killer = event.getPlayer().getKiller();
        Player p = event.getPlayer();
        if (gameManager.getGameState() == GameState.ACTIVE) {
                gameManager.addSpectatorPlayers(event.getPlayer());
            /*
                        if (gameManager.getAlivePlayers().size() <= 1 && killer != null) {
                gameManager.sendGameMessage("&cThe winner is " + killer.getDisplayName());
                gameManager.setGameState(GameState.OFFLINE);
            } else if (gameManager.getAlivePlayers().size() <= 1 && killer == null) {
                for (UUID playerUUID : gameManager.getAlivePlayers()) {
                    @Nullable Player player = Bukkit.getPlayer(playerUUID);
                    if (player == null) return;
                    gameManager.sendGameMessage("&cThe winner is " + Objects.requireNonNull(player).getDisplayName());
                    gameManager.setGameState(GameState.OFFLINE);
                }
            } else if (gameManager.getAlivePlayers().isEmpty()) {
                gameManager.setGameState(GameState.OFFLINE);
            }
             */
            event.setDeathMessage(ColorUtils.color("&c" + p.getDisplayName() + " died!"));
            event.setDeathSound(Sound.ENTITY_LIGHTNING_BOLT_THUNDER);
        }
    }
}
