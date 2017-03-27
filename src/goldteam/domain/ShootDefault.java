package goldteam.domain;

import goldteam.builders.ArrowBuilder;
import goldteam.characters.ArcherBow;
import goldteam.providers.ProjectileProvider;
import java.awt.Point;

/**
 *
 * @author Joshua
 */
public class ShootDefault implements ShootingStrategy
{
    @Override
    public void shoot(ArcherBow archerWeapon, ProjectileProvider provider, ArrowBuilder arrowBuilder, GameObject centralGameObject, Point mouseLocation, GamePanelBase panel)
    {
        mouseLocation = new Point(200, 170);
        Point point = centralGameObject.PositionVector();
        Point start = new Point(point);
        Point end = new Point(mouseLocation);
        DoubleVector speed = VectorMath.getVelocityVector(start, end, archerWeapon.getChargeValue() + 20);
        panel.addGameObject(provider.build(arrowBuilder, start, speed));
    }
}
