package io.github.startship.game.models;

import com.badlogic.gdx.Gdx;

public class MissileModel extends BaseModel {
    public MissileModel() {
        super(0, (float) Gdx.graphics.getWidth() / 2 - 30, "missile.png");
    }
}
