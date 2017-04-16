/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.colliders;

import goldteam.characters.ArcherMan;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.CollisionPlane;
import goldteam.domain.Delta;
import goldteam.domain.ModType;
import goldteam.domain.Movable;
import goldteam.platforms.FlatPlatform;

/**
 *
 * @author faaez
 */
public class WallCollider implements CollisionListener {

    private FlatPlatform platform;
    private ArcherMan collidable;

    private void DoCollision() {
        Movable a = (Movable) collidable;
        if (collidable.getColldierList(CollisionPlane.LEFT).getBounds2D().
                intersects(platform.getPolygon().getBounds2D())) {
            if (a.getVelocityVector().x > 0) {
                a.setVelocityScalarDelta(Delta.create(0.0, ModType.FIXED));
            }
        } else if (collidable.getColldierList(CollisionPlane.RIGHT).getBounds2D().
                intersects(platform.getPolygon().getBounds2D())) {
            if (a.getVelocityVector().x > 0) {
                a.setVelocityScalarDelta(Delta.create(0.0, ModType.FIXED));
            }
        }
    }

    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        if ((a instanceof FlatPlatform) && (b instanceof ArcherMan)) {
            this.platform = (FlatPlatform) a;
            this.collidable = (ArcherMan) b;
            DoCollision();
        } else if ((b instanceof FlatPlatform) && (a instanceof ArcherMan)) {
            this.platform = (FlatPlatform) b;
            this.collidable = (ArcherMan) a;
            DoCollision();
        }
        // Otherwise ignore the event, another colider should be used
    }
}
