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
import io.github.startship.game.controllers.GameController;

public class StarshipGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture backgroundImage;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private BitmapFont bitmapFont;

    private GameController gameController;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.backgroundImage = new Texture("bg.png");
        this.generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
        this.parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        this.parameter.size = 30;
        this.parameter.borderWidth = 1;
        this.parameter.borderColor = Color.BLUE;
        this.parameter.color = Color.WHITE;
        this.bitmapFont = this.generator.generateFont(this.parameter);
        this.gameController = new GameController();
    }

    @Override
    public void render() {

        gameController.startGame();

        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        batch.draw(backgroundImage, 0, 0);

        if (!this.gameController.isGameOver()) {

            if (this.gameController.getShipController().sendingMissile()) {
                float missilePositionX = this.gameController.getShipController().getMissileModel().getPositionX() + this.gameController.getShipController().getStarshipModel().getWidth() / 2;
                float missilePositionY = this.gameController.getShipController().getMissileModel().getPositionY() + this.gameController.getShipController().getStarshipModel().getHeight() / 2 - 30;
                batch.draw(this.gameController.getShipController().getMissileModel().getModelTexture(), missilePositionX, missilePositionY);
            }

            for (Rectangle enemy : this.gameController.getEnemyController().getEnemies()) {
                batch.draw(this.gameController.getEnemyController().getEnemyTexture(), enemy.x, enemy.y);
            }

            batch.draw(this.gameController.getShipController().getStarshipModel().getModelSprite(), this.gameController.getShipController().getStarshipModel().getPositionX(), this.gameController.getShipController().getStarshipModel().getPositionY());
            bitmapFont.draw(batch, "Score: " + this.gameController.getScore(), 20, Gdx.graphics.getHeight() - 20);
            bitmapFont.draw(batch, "Life: " + this.gameController.getLife(), Gdx.graphics.getWidth() - 250, Gdx.graphics.getHeight() - 20);
        } else {
            bitmapFont.draw(batch, "Score: " + this.gameController.getScore(), 20, Gdx.graphics.getHeight() - 20);
            bitmapFont.draw(batch, "GAME OVER", Gdx.graphics.getWidth() - 300, Gdx.graphics.getHeight() - 20);

            if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                this.gameController.restartGame();
            }
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundImage.dispose();
        this.gameController.getShipController().getStarshipModel().getModelTexture().dispose();
        this.gameController.getShipController().getMissileModel().getModelTexture().dispose();
    }
}
