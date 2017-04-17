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
    private final double gravity;
    private final Point initialPoint;
    private final Point projection;
    private boolean isFalling;

    public Walker(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
        this.maxSpeed = 6;
        this.gravity = 6.0;
        this.velocityVector.y = this.gravity;

        this.isFalling = true;
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

        Double velY = this.velocityVector.y;
        Double velX = this.velocityVector.x;

        if (this.isFalling) {
            velY += gravity / 5;
        } else {
            velY = 0d;
        }

        int mapX = this.gamedata.getMapLocation().x;
        int mapY = this.gamedata.getMapLocation().y;

        Point mainCharMapLocation = gamedata.getMovableCharacter().PositionVector();
        projection.y = mainCharMapLocation.y;
        projection.x = this.positionVector.x;

        DoubleVector diff = VectorMath.getVelocityVector(projection, mainCharMapLocation, this.maxSpeed);
        this.initialPoint.x += diff.x;
        this.initialPoint.y += velY; // fall

        this.positionVector.y = initialPoint.y;
        this.positionVector.x = initialPoint.x + mapX;

        if (this.collider != null) {
            this.collider.reset();
            this.collider.addPoint(this.positionVector.x - 12, this.positionVector.y - 48);
            this.collider.addPoint(this.positionVector.x + 12, this.positionVector.y - 48);
            this.collider.addPoint(this.positionVector.x + 12, this.positionVector.y + 48);
            this.collider.addPoint(this.positionVector.x - 12, this.positionVector.y + 48);
        }
        
        this.velocityVector.x = velX;
        this.velocityVector.y = velY;
    }

    @Override
    public void startFalling() {
        this.isFalling = true;
    }

    @Override
    public void stopFalling(int y) {
        if(this.isFalling)
        {
            this.initialPoint.y = y - getOffset();
            this.isFalling = false;
        }
    }
    
    @Override
    public int getOffset()
    {
        return 46;
    }

}
