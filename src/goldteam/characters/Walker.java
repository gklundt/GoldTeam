package goldteam.characters;

import goldteam.domain.Fallable;
import goldteam.domain.GameEngine;
import java.awt.Point;

public class Walker extends BaseEnemy implements Fallable {

    private final int maxSpeed;
    private boolean grounded;
    private double yVelocity;
    private double yAcceleration;

    public Walker(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
        maxSpeed = 6;
        yVelocity = 0;
        yAcceleration = 3.0;
    }

    @Override
    protected void Update() {
        int xdif = gamedata.getMovableCharacter().PositionVector().x - positionVector.x;
        if (xdif > 0) {
            moveRight(xdif);
        } else {
            moveLeft(xdif);
        }
        yVelocity += yAcceleration;
        positionVector.y += yVelocity;
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
    
    @Override
    public void land(double yPos)
    {
        System.out.println("ADUISD");
        if(!grounded)
        {
            this.positionVector.y = (int)(yPos - 28);
            this.yAcceleration = 0.0;
            this.yVelocity = 0.0;
            this.grounded = true;
        }
    }
    
    @Override
    public void fall()
    {
        this.yAcceleration = 3.0;
        grounded = false;
    }

}
