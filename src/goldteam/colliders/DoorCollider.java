/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.colliders;

import goldteam.characters.StationaryGhost;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.Delta;
import goldteam.domain.Depletable;
import goldteam.domain.ModType;

/**
 *
 * @author Mishal
 */

public class DoorCollider implements CollisionListener{
    private Collidable door;
    private Collidable collidable;
    
    public DoorCollider() {
    }

    private void DoCollision() {
        Depletable g1 = (Depletable) collidable;
        g1.setCountDelta(Delta.create(-1.0, ModType.FIXED));
    }
    

    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        //System.out.println(a + "   " + b);
        if ((b instanceof Depletable)) {
            this.collidable = b;
            DoCollision();

        } else if ((a instanceof StationaryGhost)) {
            this.collidable = a;
            DoCollision();
        }
        // Otherwise ignore the event, another colider should be used
    }
}
