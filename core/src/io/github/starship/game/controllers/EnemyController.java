package io.github.starship.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class EnemyController {
    private Texture enemyTexture;
    private Array enemies;

    public EnemyController() {
        this.enemyTexture = new Texture("enemy.png");
        this.enemies = new Array<Rectangle>();
    }

    public Array<Rectangle> getEnemies() {
        return enemies;
    }

    public Texture getEnemyTexture() {
        return enemyTexture;
    }

    public void spawnEnemies() {
        Rectangle enemy = new Rectangle(Gdx.graphics.getWidth(), MathUtils.random(0, Gdx.graphics.getHeight() - this.enemyTexture.getHeight()), this.enemyTexture.getWidth(), this.enemyTexture.getHeight());
        enemies.add(enemy);
    }
}
