package io.github.starship.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.starship.game.models.MissileModel;
import io.github.starship.game.models.StarshipModel;

public class StarshipController {
    private final float STARSHIP_SPEED = 15;
    private final float MISSILE_SPEED = 30;

    private final StarshipModel starshipModel;
    private final MissileModel missileModel;
    private boolean sendMissile;

    public StarshipController() {
        this.starshipModel = new StarshipModel();
        this.missileModel = new MissileModel();
        this.sendMissile = false;
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

    public void moveShip() {

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

    public void moveMissile() {

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !this.sendingMissile()) {
            this.sendMissile = true;
            this.missileModel.setPositionY(this.starshipModel.getPositionY());
        }

        if (sendMissile) {
            if (this.missileModel.getPositionX() < Gdx.graphics.getWidth()) {
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
