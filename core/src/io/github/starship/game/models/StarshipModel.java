package io.github.starship.game.models;

import com.badlogic.gdx.Gdx;

public class StarshipModel extends BaseModel {
    public StarshipModel() {
        super(0, (float) Gdx.graphics.getHeight() / 2, "ship.png");
    }
}
