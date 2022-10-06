package net.theiceninja.game.tasks;

import net.theiceninja.game.manager.GameManager;
import net.theiceninja.game.manager.GameState;
import org.bukkit.scheduler.BukkitRunnable;

public class CooldownTask extends BukkitRunnable {

    private int timeLeft = 11;
    private final GameManager gameManager;

    public CooldownTask(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void run() {
        timeLeft--;
        if (timeLeft <= 0) {
            cancel();
            gameManager.setGameState(GameState.ACTIVE);
            return;
        }
        gameManager.sendActionBar("&cTime left until game starts: &e" + timeLeft);
    }
}
