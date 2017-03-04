/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.colliders;

import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.CollisionPlane;
import goldteam.domain.Delta;
import goldteam.domain.ModType;
import goldteam.domain.Movable;
import goldteam.domain.Platform;

/**
 *
 * @author Mishal
 */
/*
public class DoorCollider implements CollisionListener{
    private Collidable door;
    private Collidable collidable;
    private final Delta xdelta;
    private final Delta ydelta;
    
    public DoorCollider() {
        xdelta = Delta.create(0.0d, ModType.FIXED);
        ydelta = Delta.create(-1.0d, ModType.PERCENTAGE);
    }

    private void DoCollision() {
        // No change in x direction
        // Reduce y dirction by 100% or subtract y from y to net 0 ... 
        if (door.getColliders().get(collidable) == CollisionPlane.TOP) {
            Movable m = (Movable) collidable;
            m.setVelocityVectorDelta(xdelta, ydelta);
        }
    }

    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        if ((a instanceof Platform) && (b instanceof Movable)) {
            this.door = a;
            this.collidable = b;
            DoCollision();

        } else if ((b instanceof Platform) && (a instanceof Movable)) {
            this.door = b;
            this.collidable = a;
            DoCollision();
        }
        // Otherwise ignore the event, another colider should be used

    }
    
}*/
