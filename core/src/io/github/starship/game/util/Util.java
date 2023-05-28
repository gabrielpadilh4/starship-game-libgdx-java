package io.github.starship.game.util;

import io.github.starship.game.models.BaseModel;

public class Util {
    public static boolean calculateCollision(BaseModel coordinatesA, BaseModel coordinatesB) {
        boolean xIntersectionAOverB = coordinatesA.getPositionX() + coordinatesA.getWidth() > coordinatesB.getPositionX();
        boolean xIntersectionBOverA = coordinatesA.getPositionX() < coordinatesB.getPositionX() + coordinatesB.getWidth();
        boolean yIntersectionAOverB = coordinatesA.getPositionY() + coordinatesA.getHeight() > coordinatesB.getPositionY();
        boolean yIntersectionBOverA = coordinatesA.getPositionY() < coordinatesB.getPositionY() + coordinatesB.getHeight();

        return xIntersectionAOverB && xIntersectionBOverA && yIntersectionAOverB && yIntersectionBOverA;
    }
}
