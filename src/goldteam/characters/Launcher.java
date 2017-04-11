package goldteam.characters;

import goldteam.domain.DoubleVector;
import goldteam.domain.GameEngine;
import goldteam.domain.VectorMath;
import java.awt.Point;
import java.awt.Polygon;

public class Launcher extends BaseEnemy
{
    public Launcher(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
        
        int[] xPoly = {this.positionVector.x - 12,
            this.positionVector.x + 12,
            this.positionVector.x + 12,
            this.positionVector.x - 12
        };
        int[] yPoly = {this.positionVector.y - 12,
            this.positionVector.y - 12,
            this.positionVector.y + 12,
            this.positionVector.y + 12
        };
        
        collider = new Polygon(xPoly, yPoly, xPoly.length);
        super.shape = collider;
        super.health = 1;
    }

    @Override
    protected void Update()
    {
        Point prevPos = (Point)positionVector.clone();
        DoubleVector velocityVector = new DoubleVector((double)gamedata.getMovableCharacter().PositionVector().x - positionVector.x, (double)gamedata.getMovableCharacter().PositionVector().y - positionVector.y);
        velocityVector = VectorMath.getVelocityVector(velocityVector, 5.0);
        positionVector.x += velocityVector.x;
        positionVector.y += velocityVector.y;
        
        this.collider = new Polygon();
        this.collider.addPoint(this.positionVector.x - 10, this.positionVector.y - 10);
        this.collider.addPoint(this.positionVector.x + 10, this.positionVector.y - 10);
        this.collider.addPoint(this.positionVector.x + 10, this.positionVector.y + 10);
        this.collider.addPoint(this.positionVector.x - 10, this.positionVector.y + 10);
        super.shape = collider;
        
        if(this.animator != null)
        {
            DoubleVector dangle = new DoubleVector((double)(positionVector.x - prevPos.x), (double)(positionVector.y - prevPos.y));
            double currentAngle = Math.atan(dangle.y/dangle.x);// + Math.PI/2;
            if(velocityVector.x < -0.0005)
                currentAngle -= Math.PI;
            animator.af.setToRotation(currentAngle + Math.PI / 2);
        }
        
        
    }
}
