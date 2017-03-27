package goldteam.domain;

import goldteam.builders.ArrowBuilder;
import goldteam.characters.ArcherBow;
import goldteam.providers.ProjectileProvider;
import java.awt.Point;

/**
 *
 * @author Joshua
 */
public class ShootBuff implements ShootingStrategy
{
    @Override
    public void shoot(ArcherBow archerWeapon, ProjectileProvider provider, ArrowBuilder arrowBuilder, GameObject centralGameObject, Point mouseLocation, GamePanelBase panel)
    {
        //Point mouseLocation2 = new Point(400, 100);
        Point mouseLocation2 = mouseLocation;
        
        Point point = centralGameObject.PositionVector();
        Point start = new Point(point);
        Point end = new Point(mouseLocation2);
        double arrowSpeed = /*archerWeapon.getChargeValue() + */25;
        DoubleVector speed = VectorMath.getVelocityVector(start, end, arrowSpeed);
        panel.addGameObject(provider.build(arrowBuilder, start, speed));
        
        
        double currentAngle = Math.atan(((double)(end.x - start.x))/(double)(end.y - start.y));
        double dx = Math.signum(end.x - start.x);
        dx = (dx != 0) ? 50 * Math.cos(currentAngle) * dx : 50 * Math.cos(currentAngle);
        double dy = Math.signum(end.y - start.y);
        dy = (dy != 0) ? 50 * Math.sin(currentAngle) * dy : 50 * Math.sin(currentAngle);

        start = new Point(point);
        end = new Point((int)(mouseLocation2.x + dx), (int)(mouseLocation2.y + dy));
        speed = VectorMath.getVelocityVector(start, end, arrowSpeed);
        panel.addGameObject(provider.build(arrowBuilder, start, speed));
        
        System.out.print(start);
        
        start = new Point(point);
        end = new Point((int)(mouseLocation2.x - dx), (int)(mouseLocation2.y + dy));
        speed = VectorMath.getVelocityVector(start, end, arrowSpeed);
        panel.addGameObject(provider.build(arrowBuilder, start, speed));
        
        
        System.out.println(start);
    }
}
