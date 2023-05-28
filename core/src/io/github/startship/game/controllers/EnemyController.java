package io.github.startship.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import io.github.startship.game.models.BaseModel;
import io.github.startship.game.util.Util;

import java.util.Iterator;

public class EnemyController {
    private final String ENEMY_IMAGE = "enemy.png";
    private Texture enemyTexture;
    private Array enemies;
    private long lastEnemyTime;
    private int enemyScore;

    public EnemyController() {
        this.enemyTexture = new Texture(ENEMY_IMAGE);
        this.enemies = new Array<Rectangle>();
        this.lastEnemyTime = System.currentTimeMillis();
    }

    public Texture getEnemyTexture() {
        return enemyTexture;
    }

    public Array<Rectangle> getEnemies() {
        return enemies;
    }

    public int getEnemyScore() {
        return enemyScore;
    }

    public void setEnemyScore(int enemyScore) {
        this.enemyScore = enemyScore;
    }

    private void spawnEnemies() {
        Rectangle enemy = new Rectangle(Gdx.graphics.getWidth(), MathUtils.random(0, Gdx.graphics.getHeight() - this.enemyTexture.getHeight()), this.enemyTexture.getWidth(), this.enemyTexture.getHeight());
        enemies.add(enemy);
        this.lastEnemyTime = System.currentTimeMillis();
    }

    public void moveEnemies(StarshipController starshipController) {

        if ( (System.currentTimeMillis() - this.lastEnemyTime) / 1000 > 1 ) {
            this.spawnEnemies();
        }

        for (Iterator<Rectangle> iter = enemies.iterator(); iter.hasNext(); ) {
            Rectangle enemy = iter.next();
            enemy.x -= 400 * Gdx.graphics.getDeltaTime();

            BaseModel enemyCoordinates = new BaseModel();
            enemyCoordinates.setPositionX(enemy.x);
            enemyCoordinates.setPositionY(enemy.y);
            enemyCoordinates.setHeight(enemy.getHeight());
            enemyCoordinates.setWidth(enemy.getWidth());

            if(Util.calculateCollision(enemyCoordinates, starshipController.getMissileModel()) && starshipController.sendingMissile()) {
                iter.remove();
                starshipController.stopMissile();
                this.enemyScore++;
                System.out.println("Score: " + this.getEnemyScore());
            } else if(Util.calculateCollision(enemyCoordinates, starshipController.getStarshipModel()) && !starshipController.isGameOver()) {
                iter.remove();
                starshipController.getStarshipModel().setLife(starshipController.getStarshipModel().getLife() -1);
                if (starshipController.getStarshipModel().getLife() == 0) {
                    starshipController.setGameOver(true);
                }
            }

            if (enemy.x + this.enemyTexture.getWidth() < 0) {
                iter.remove();
            }
        }
    }
}
