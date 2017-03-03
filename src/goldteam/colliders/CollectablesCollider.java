/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.colliders;

import goldteam.Collectables.Arrows;
import goldteam.Collectables.Health;
import goldteam.Collectables.Shields;
import goldteam.characters.StationaryGhost;
import goldteam.domain.CollectableItem;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.Delta;
import goldteam.domain.ModType;
import goldteam.domain.Movable;

/**
 *
 * @author faaez
 */
public class CollectablesCollider implements CollisionListener {

    private Collidable collectableItem;
    private Collidable movable;
    
    public void DoCollision(){
        StationaryGhost g1 = (StationaryGhost) movable;
        
        CollectableItem item = (CollectableItem) collectableItem;
        
        if(item instanceof Arrows){
            if(g1.getArrowCount() < 20){
                g1.setArrowDelta(Delta.create(1.0, ModType.FIXED));
                item.setState(false);
                item.undoCollider();
            }
        } else if(item instanceof Shields){
            if(g1.getShieldValue() < 10){
                g1.setShieldDelta(Delta.create(1.0, ModType.FIXED));
                item.setState(false);
                item.undoCollider();
            }
        } else if (item instanceof Health){
            if(g1.getHealthValue() < 5){
                g1.setHealthDelta(Delta.create(1.0, ModType.FIXED));
                item.setState(false);
                item.undoCollider();
            }
        }
    }
    
    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        if ((a instanceof CollectableItem) && (b instanceof Movable)) {
            this.collectableItem = a;
            this.movable = b;
            DoCollision();

        } else if ((b instanceof CollectableItem) && (a instanceof Movable)) {
            this.collectableItem = b;
            this.movable = a;
            DoCollision();
        }
    }
    
}
