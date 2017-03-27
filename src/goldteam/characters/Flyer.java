package goldteam.characters;

import goldteam.domain.GameEngine;
import goldteam.gamedata.GameData;
import java.awt.Point;

public class Flyer extends BaseEnemy {

    private int timeSinceAttacked;
    private final int maxSpeed;

    public Flyer(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
        maxSpeed = 6;
    }

    @Override
    protected void Update() {
        timeSinceAttacked++;
        int xdif = gamedata.getMovableCharacter().PositionVector().x - positionVector.x;
        if (xdif > 0) {
            moveRight(xdif);
        } else {
            moveLeft(xdif);
        }

        if (timeSinceAttacked > 20) {
            attack();
        }
    }

    private void moveLeft(int xdif) {
        xdif /= 10;
        if (xdif < -maxSpeed) {
            xdif = -maxSpeed;
        }
        positionVector.x += xdif;

    }

    private void moveRight(int xdif) {
        xdif /= 10;
        if (xdif > maxSpeed) {
            xdif = maxSpeed;
        }
        positionVector.x += xdif;

    }

    private void attack() {
//        ((GameData) (gamedata)).createBomb(this.positionVector);
        timeSinceAttacked = 0;
    }

}
