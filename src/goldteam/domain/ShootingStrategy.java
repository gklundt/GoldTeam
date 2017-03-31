package goldteam.domain;

import goldteam.builders.ArrowBuilder;
import goldteam.characters.ArcherBow;
import goldteam.providers.ProjectileProvider;
import java.awt.Point;

/**
 *
 * @author Joshua
 */
public interface ShootingStrategy
{
    public void shoot(ArcherBow archerWeapon, ProjectileProvider provider, ArrowBuilder arrowBuilder, GameObject centralGameObject, Point mouseLocation, GamePanelBase panel);
}
