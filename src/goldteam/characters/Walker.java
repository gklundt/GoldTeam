package goldteam.characters;

import goldteam.domain.DoubleVector;
import goldteam.domain.Fallable;
import goldteam.domain.GameEngine;
import goldteam.domain.VectorMath;
import java.awt.Point;
import java.awt.Polygon;

public class Walker extends BaseEnemy implements Fallable {

    private final int maxSpeed;
    private boolean grounded;
    private double yAcceleration;
    private final Point initialPoint;
    private final Point projection;

    public Walker(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
        maxSpeed = 6;
        yAcceleration = 3.0;
        this.velocityVector.y = yAcceleration;
                
        

        this.initialPoint = initialPoint.getLocation();

        int mapX = this.gamedata.getMapLocation().x;
        int mapY = this.gamedata.getMapLocation().y;
        this.positionVector.x = initialPoint.x + mapX;
        this.positionVector.y = initialPoint.y + mapY;

        projection = new Point();

        collider = new Polygon();
        super.shape = collider;

        super.health = 5;
    }

    @Override
    protected void Update() {

        int mapX = this.gamedata.getMapLocation().x;
        int mapY = this.gamedata.getMapLocation().y;

        Point mainCharMapLocation = gamedata.getMovableCharacter().PositionVector();
        projection.y = mainCharMapLocation.y;
        projection.x = this.positionVector.x;

        DoubleVector diff = VectorMath.getVelocityVector(projection, mainCharMapLocation, this.maxSpeed);
        this.initialPoint.x += diff.x;
        this.initialPoint.y += yAcceleration; // fall

        this.positionVector.y = initialPoint.y + mapY;
        this.positionVector.x = initialPoint.x + mapX;

        if (this.collider != null) {
            this.collider.reset();
            this.collider.addPoint(this.positionVector.x - 12, this.positionVector.y - 48);
            this.collider.addPoint(this.positionVector.x + 12, this.positionVector.y - 48);
            this.collider.addPoint(this.positionVector.x + 12, this.positionVector.y + 48);
            this.collider.addPoint(this.positionVector.x - 12, this.positionVector.y + 48);
        }
    }

    @Override
    public void land(double yPos) {
        if (!grounded) {
            this.positionVector.y = (int) (yPos - 46);
            this.yAcceleration = 0.0;
            this.velocityVector.y = yAcceleration;
            this.grounded = true;
        }
    }

    @Override
    public void fall() {
        this.yAcceleration = 10.0;
        this.velocityVector.y = yAcceleration;
        grounded = false;
    }

    @Override
    public int getOffset() {
        return 48;
    }

}
