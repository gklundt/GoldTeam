package goldteam.domain;

import goldteam.builders.ArrowBuilder;
import goldteam.characters.ArcherBow;
import goldteam.providers.ProjectileProvider;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joshua
 */
public class ShootBuff implements ShootingStrategy
{
    @Override
    public void shoot(ArcherBow archerWeapon, ProjectileProvider provider, ArrowBuilder arrowBuilder, GameObject centralGameObject, Point mouseLocation, GamePanelBase panel)
    {
        double arrowSpeed = archerWeapon.getChargeValue() + 20;
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for(int i = 0; i < 3; i++)
                {
                    Point point = centralGameObject.PositionVector();
                    Point start = new Point(point);
                    Point end = new Point((int)(mouseLocation.x + (Math.random() * 30 - 15)), (int)(mouseLocation.y + (Math.random() * 30 - 15)));
                    DoubleVector speed = VectorMath.getVelocityVector(start, end, arrowSpeed);
                        panel.addGameObject(provider.build(arrowBuilder, start, speed));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ShootBuff.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }
}
