/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.colliders;

import goldteam.Collectables.Arrows;
import goldteam.characters.StationaryGhost;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.Movable;

/**
 *
 * @author faaez
 */
public class CollectablesCollider implements CollisionListener {

    private Collidable arrow;
    private Collidable movable;
    
    public void DoCollision(){
        StationaryGhost g1 = (StationaryGhost) movable;
        //g1.setArrowDelta(Delta.create(5.0, ModType.FIXED));
        Arrows a = (Arrows) arrow;
        a.setState(false);
    }
    
    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        if ((a instanceof Arrows) && (b instanceof Movable)) {
            this.arrow = a;
            this.movable = b;
            DoCollision();

        } else if ((b instanceof Arrows) && (a instanceof Movable)) {
            this.arrow = b;
            this.movable = a;
            DoCollision();
        }
    }
    
}
