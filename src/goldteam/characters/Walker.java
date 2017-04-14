package goldteam.characters;

import goldteam.domain.Fallable;
import goldteam.domain.GameEngine;
import java.awt.Point;
import java.awt.Polygon;

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
        
        int[] xPoly = {this.positionVector.x - 12,
            this.positionVector.x + 12,
            this.positionVector.x + 12,
            this.positionVector.x - 12
        };
        int[] yPoly = {this.positionVector.y - 48,
            this.positionVector.y - 48,
            this.positionVector.y + 48,
            this.positionVector.y + 48
        };
        
        collider = new Polygon(xPoly, yPoly, xPoly.length);
        super.shape = collider;
        
        super.health = 5;
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
        this.velocityVector.y = yVelocity;
        positionVector.y += yVelocity;
        
        this.collider = new Polygon();
        this.collider.addPoint(this.positionVector.x - 12, this.positionVector.y - 48);
        this.collider.addPoint(this.positionVector.x + 12, this.positionVector.y - 48);
        this.collider.addPoint(this.positionVector.x + 12, this.positionVector.y + 48);
        this.collider.addPoint(this.positionVector.x - 12, this.positionVector.y + 48);
        super.shape = collider;
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
        if(!grounded)
        {
            this.positionVector.y = (int)(yPos - 46);
            this.yAcceleration = 0.0;
            this.yVelocity = 0.0;
            this.grounded = true;
        }
    }
    
    @Override
    public void fall()
    {
        this.yAcceleration = 10.0;
        grounded = false;
    }
    
    @Override
    public int getOffset()
    {
        return 46;
    }

}
