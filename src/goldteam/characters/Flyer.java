package goldteam.characters;

import goldteam.domain.DoubleVector;
import goldteam.domain.GameEngine;
import goldteam.domain.GamePanelBase;
import goldteam.domain.VectorMath;
import java.awt.Point;
import java.awt.Polygon;

public class Flyer extends BaseEnemy
{
    private GamePanelBase panel;
    private int timeSinceAttacked;
    private final int maxSpeed;
    private final Point initialPoint;
    private final Point projection;

    public Flyer(GameEngine gamedata, Point point, GamePanelBase panel)
    {
        super(gamedata, point);
        this.panel = panel;
        maxSpeed = 6;
        
        this.initialPoint = point.getLocation();

        int mapX = this.gamedata.getMapLocation().x;
        int mapY = this.gamedata.getMapLocation().y;
        this.positionVector.x = initialPoint.x + mapX;
        this.positionVector.y = initialPoint.y + mapY;

        projection = new Point();

        collider = new Polygon();
        super.shape = collider;
        super.health = 3;
    }

    @Override
    protected void Update() {
        timeSinceAttacked++;
        int mapX = this.gamedata.getMapLocation().x;
        int mapY = this.gamedata.getMapLocation().y;

        Point mainCharMapLocation = gamedata.getMovableCharacter().PositionVector();
        projection.y = mainCharMapLocation.y;
        projection.x = this.positionVector.x;

        DoubleVector diff = VectorMath.getVelocityVector(projection, mainCharMapLocation, this.maxSpeed);
        this.initialPoint.x += diff.x;
        this.initialPoint.y += diff.y;

        this.positionVector.y = initialPoint.y + mapY;
        this.positionVector.x = initialPoint.x + mapX;

        if (this.collider != null) {
            this.collider.reset();
            this.collider.addPoint(this.positionVector.x - 12, this.positionVector.y - 12);
            this.collider.addPoint(this.positionVector.x + 12, this.positionVector.y - 12);
            this.collider.addPoint(this.positionVector.x + 12, this.positionVector.y + 12);
            this.collider.addPoint(this.positionVector.x - 12, this.positionVector.y + 12);
        }

        if (timeSinceAttacked > 60 && health > 0) {
            attack(this.initialPoint);
        }
    }

    private void attack(Point location)
    {
        if(!this.removeMe)
        {
            this.panel.createLauncher(location);
            timeSinceAttacked = 0;
        }
    }

}
