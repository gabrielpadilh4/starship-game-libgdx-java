package io.github.startship.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import io.github.startship.game.models.BaseModel;
import io.github.startship.game.util.Util;

import java.util.Iterator;

public class GameController {
    private boolean gameOver;
    private int score;
    private int life;
    private long lastEnemyTime;
    private long enemiesSpawnSpeed;
    private StarshipController shipController;
    private EnemyController enemyController;

    public GameController() {
        this.initializeVariables();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getScore() {
        return score;
    }

    public int getLife() {
        return life;
    }

    public StarshipController getShipController() {
        return shipController;
    }

    public EnemyController getEnemyController() {
        return enemyController;
    }

    public void startGame() {
        this.shipController.moveShip();
        this.shipController.moveMissile();
        this.moveEnemies();
    }

    public void restartGame() {
        this.initializeVariables();
    }

    public void moveEnemies() {

        if ((System.currentTimeMillis() - this.lastEnemyTime) / this.enemiesSpawnSpeed > 1) {
            this.enemyController.spawnEnemies();
            this.lastEnemyTime = System.currentTimeMillis();
        }

        for (Iterator<Rectangle> iter = this.enemyController.getEnemies().iterator(); iter.hasNext(); ) {
            Rectangle enemy = iter.next();
            enemy.x -= 500 * Gdx.graphics.getDeltaTime();

            BaseModel enemyCoordinates = new BaseModel();
            enemyCoordinates.setPositionX(enemy.x);
            enemyCoordinates.setPositionY(enemy.y);
            enemyCoordinates.setHeight(enemy.getHeight());
            enemyCoordinates.setWidth(enemy.getWidth());

            if (Util.calculateCollision(enemyCoordinates, this.shipController.getMissileModel()) && this.shipController.sendingMissile()) {
                iter.remove();
                this.shipController.stopMissile();
                this.score++;

                if (this.score % 10 == 0) {
                    this.enemiesSpawnSpeed -= 100;
                }

            } else if (Util.calculateCollision(enemyCoordinates, this.shipController.getStarshipModel()) && !this.gameOver) {
                iter.remove();
                this.life = this.life - 1;
                if (this.life == 0) {
                    this.gameOver = true;
                }
            }

            if (enemy.x + this.enemyController.getEnemyTexture().getWidth() < 0) {
                iter.remove();
            }
        }
    }

    private void initializeVariables() {
        this.gameOver = false;
        this.score = 0;
        this.life = 3;
        this.enemiesSpawnSpeed = 1000;
        this.lastEnemyTime = System.currentTimeMillis();
        this.shipController = new StarshipController();
        this.enemyController = new EnemyController();
    }
}
