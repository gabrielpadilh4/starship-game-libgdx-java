package io.github.startship.game.models;

public class StarshipModel extends BaseModel {

    private int life;

    public StarshipModel(float positionX, float positionY, String shipImage) {
        super(positionX, positionY, shipImage);
        this.life = 3;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
