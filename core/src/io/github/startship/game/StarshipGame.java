package io.github.startship.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.startship.game.controllers.EnemyController;
import io.github.startship.game.controllers.StarshipController;

public class StarshipGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture backgroundImage;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private BitmapFont bitmapFont;
    private StarshipController shipController;
    private EnemyController enemyController;

    @Override
    public void create() {
        batch = new SpriteBatch();
        backgroundImage = new Texture("bg.png");
        shipController = new StarshipController();
        enemyController = new EnemyController();
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30;
        parameter.borderWidth = 1;
        parameter.borderColor = Color.BLUE;
        parameter.color = Color.WHITE;
        bitmapFont = generator.generateFont(parameter);
    }

    @Override
    public void render() {
        shipController.runGame();
        enemyController.moveEnemies(this.shipController);

        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        batch.draw(backgroundImage, 0, 0);

        if (!this.shipController.isGameOver()) {

            if (this.shipController.sendingMissile()) {
                float missilePositionX = shipController.getMissileModel().getPositionX() + shipController.getStarshipModel().getWidth() / 2;
                float missilePositionY = shipController.getMissileModel().getPositionY() + shipController.getStarshipModel().getHeight() / 2 - 30;
                batch.draw(shipController.getMissileModel().getModelTexture(), missilePositionX, missilePositionY);
            }

            for (Rectangle enemy : this.enemyController.getEnemies()) {
                batch.draw(this.enemyController.getEnemyTexture(), enemy.x, enemy.y);
            }

            batch.draw(shipController.getStarshipModel().getModelSprite(), shipController.getStarshipModel().getPositionX(), shipController.getStarshipModel().getPositionY());
            bitmapFont.draw(batch, "Score: " + this.enemyController.getEnemyScore(), 20, Gdx.graphics.getHeight() - 20);
            bitmapFont.draw(batch, "Life: " + this.shipController.getStarshipModel().getLife(), Gdx.graphics.getWidth() - 250, Gdx.graphics.getHeight() - 20);
        } else {
            bitmapFont.draw(batch, "Score: " + this.enemyController.getEnemyScore(), 20, Gdx.graphics.getHeight() - 20);
            bitmapFont.draw(batch, "GAME OVER", Gdx.graphics.getWidth() - 300, Gdx.graphics.getHeight() - 20);

            if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                this.shipController = new StarshipController();
                this.enemyController = new EnemyController();
            }
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundImage.dispose();
        shipController.getStarshipModel().getModelTexture().dispose();
        shipController.getMissileModel().getModelTexture().dispose();
    }
}
