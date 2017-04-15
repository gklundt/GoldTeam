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
    private final Point initialPoint;

    public Walker(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
        maxSpeed = 6;
        yVelocity = 0;
        yAcceleration = 3.0;

        this.initialPoint = initialPoint.getLocation();
        int mapX = this.gamedata.getMapLocation().x;
        int mapY = this.gamedata.getMapLocation().y;
        this.positionVector.x = initialPoint.x + mapX;
        this.positionVector.y = initialPoint.y + mapY;

        collider = new Polygon();
        super.shape = collider;

        super.health = 5;
    }

    @Override
    protected void Update() {
        int mapX = this.gamedata.getMapLocation().x;
        int mapY = this.gamedata.getMapLocation().y;

        Point currentLocation = this.positionVector.getLocation();
        System.out.println("currentLocation = " + currentLocation);
        

        int mainCharMapLocation = gamedata.getMovableCharacter().PositionVector().x;
        int xdif = mainCharMapLocation - positionVector.x;
        if (xdif > 0) {
            xdif /= 10;
            if (xdif > maxSpeed) {
                xdif = maxSpeed;
            }
            positionVector.x += xdif;
        } else {
            xdif /= 10;
            if (xdif < -maxSpeed) {
                xdif = -maxSpeed;
            }
            positionVector.x += xdif;

        }
        yVelocity += yAcceleration;
        this.velocityVector.y = yVelocity;
        positionVector.y += yVelocity;

        if (this.collider != null) {
            this.collider.reset();
            this.collider.addPoint(this.positionVector.x - 12, this.positionVector.y - 48);
            this.collider.addPoint(this.positionVector.x + 12, this.positionVector.y - 48);
            this.collider.addPoint(this.positionVector.x + 12, this.positionVector.y + 48);
            this.collider.addPoint(this.positionVector.x - 12, this.positionVector.y + 48);
        }
    }

    private void moveLeft(int xdif) {

    }

    private void moveRight(int xdif) {

    }

    @Override
    public void land(double yPos) {
        if (!grounded) {
            this.positionVector.y = (int) (yPos - 46);
            this.yAcceleration = 0.0;
            this.yVelocity = 0.0;
            this.grounded = true;
        }
    }

    @Override
    public void fall() {
        this.yAcceleration = 10.0;
        grounded = false;
    }

    @Override
    public int getOffset() {
        return 0;
    }

}
