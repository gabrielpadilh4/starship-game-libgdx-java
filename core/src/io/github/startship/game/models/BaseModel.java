package io.github.startship.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BaseModel {
    float positionX;
    float positionY;
    float width;
    float height;
    Texture modelTexture;
    Sprite modelSprite;

    public BaseModel() {
    }

    public BaseModel(float positionX, float positionY, String baseImage) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.modelTexture = new Texture(baseImage);
        this.modelSprite = new Sprite(this.modelTexture);
        this.width = this.modelSprite.getWidth();
        this.height = this.modelSprite.getHeight();
    }

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Texture getModelTexture() {
        return modelTexture;
    }

    public Sprite getModelSprite() {
        return modelSprite;
    }
}
