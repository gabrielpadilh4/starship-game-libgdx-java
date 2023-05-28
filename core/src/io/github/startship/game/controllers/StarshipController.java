package io.github.startship.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.startship.game.models.MissileModel;
import io.github.startship.game.models.StarshipModel;

public class StarshipController {

    private final String STARSHIP_IMAGE = "ship.png";
    private final String MISSILE_IMAGE = "missile.png";
    private final float STARSHIP_START_POSITION_XY = 0;
    private final StarshipModel starshipModel;
    private final MissileModel missileModel;
    private boolean sendMissile, gameOver;

    public StarshipController() {
        this.starshipModel = new StarshipModel(STARSHIP_START_POSITION_XY, STARSHIP_START_POSITION_XY, STARSHIP_IMAGE);
        this.missileModel = new MissileModel(STARSHIP_START_POSITION_XY, STARSHIP_START_POSITION_XY, MISSILE_IMAGE);
        this.sendMissile = false;
        this.gameOver = false;
    }

    public StarshipModel getStarshipModel() {
        return starshipModel;
    }

    public MissileModel getMissileModel() {
        return missileModel;
    }

    public boolean sendingMissile() {
        return sendMissile;
    }

    public void stopMissile() {
        this.sendMissile = false;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void runGame() {
        this.moveShip();
        this.moveMissile();
    }

    private void moveShip() {
        float STARSHIP_SPEED = 15;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (this.starshipModel.getPositionX() < Gdx.graphics.getWidth() - this.starshipModel.getWidth()) {
                this.starshipModel.setPositionX(this.starshipModel.getPositionX() + STARSHIP_SPEED);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (this.starshipModel.getPositionX() > 0) {
                this.starshipModel.setPositionX(this.starshipModel.getPositionX() - STARSHIP_SPEED);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (this.starshipModel.getPositionY() < Gdx.graphics.getHeight() - this.starshipModel.getHeight()) {
                this.starshipModel.setPositionY(this.starshipModel.getPositionY() + STARSHIP_SPEED);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (this.starshipModel.getPositionY() > 0) {
                this.starshipModel.setPositionY(this.starshipModel.getPositionY() - STARSHIP_SPEED);
            }
        }
    }

    private void moveMissile() {

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !this.sendingMissile()) {
            this.sendMissile = true;
            this.missileModel.setPositionY(this.starshipModel.getPositionY());
        }

        if (sendMissile) {
            if (this.missileModel.getPositionX() < Gdx.graphics.getWidth()) {
                float MISSILE_SPEED = 30;
                this.missileModel.setPositionX(this.missileModel.getPositionX() + MISSILE_SPEED);
            } else {
                this.missileModel.setPositionX(this.starshipModel.getPositionX());
                this.sendMissile = false;
            }
        } else {
            this.missileModel.setPositionX(this.starshipModel.getPositionX());
            this.missileModel.setPositionY(this.starshipModel.getPositionY());
        }
    }
}
