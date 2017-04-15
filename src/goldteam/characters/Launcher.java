package goldteam.characters;

import goldteam.domain.DoubleVector;
import goldteam.domain.GameEngine;
import goldteam.domain.VectorMath;
import java.awt.Point;
import java.awt.Polygon;

public class Launcher extends BaseEnemy {

    private final Point initialPoint;

    public Launcher(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
        this.initialPoint = initialPoint.getLocation();

        int mapX = this.gamedata.getMapLocation().x;
        int mapY = this.gamedata.getMapLocation().y;
        this.positionVector.x = initialPoint.x + mapX;
        this.positionVector.y = initialPoint.y + mapY;

        collider = new Polygon();
        super.shape = collider;
        super.health = 1;
    }

    @Override
    protected void Update() {
        int mapX = this.gamedata.getMapLocation().x;
        int mapY = this.gamedata.getMapLocation().y;

        Point mainCharMapLocation = gamedata.getMovableCharacter().PositionVector();

        DoubleVector diff = VectorMath.getVelocityVector(positionVector, mainCharMapLocation, 10d);
        this.initialPoint.x += diff.x;
        this.initialPoint.y += diff.y;

        this.positionVector.y = initialPoint.y + mapY;
        this.positionVector.x = initialPoint.x + mapX;

        if (this.collider != null) {
            this.collider.reset();
            this.collider.addPoint(this.positionVector.x - 10, this.positionVector.y - 10);
            this.collider.addPoint(this.positionVector.x + 10, this.positionVector.y - 10);
            this.collider.addPoint(this.positionVector.x + 10, this.positionVector.y + 10);
            this.collider.addPoint(this.positionVector.x - 10, this.positionVector.y + 10);
        }

        if (this.animator != null) {

            double currentAngle = Math.atan(diff.y / diff.x);// + Math.PI/2;
            if (velocityVector.x < -0.0005) {
                currentAngle -= Math.PI;
            }
            animator.af.setToRotation(currentAngle + Math.PI / 2);
        }

    }
}
