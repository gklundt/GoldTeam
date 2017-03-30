/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.colliders;

import goldteam.characters.ArcherBow;
import goldteam.collectables.Arrows;
import goldteam.domain.CollectableItem;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.Delta;
import goldteam.domain.ModType;

/**
 *
 * @author faaez
 */
public class ArrowCollectablesCollider implements CollisionListener {

    private Collidable collectableItem;
    private Collidable movable;
    
    public void DoCollision(){
        ArcherBow am = (ArcherBow) movable;
        
        CollectableItem item = (CollectableItem) collectableItem;
        
        if(item instanceof Arrows){
            if(am.getCount()< 20){
                am.setCountDelta(Delta.create(100.0, ModType.FIXED));
                item.setState(false);
                item.undoCollider();
            }
        } 
    }
    
    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        if ((a instanceof Arrows) && (b instanceof ArcherBow)) {
            this.collectableItem = a;
            this.movable = b;
            DoCollision();

        } else if ((b instanceof Arrows) && (a instanceof ArcherBow)) {
            this.collectableItem = b;
            this.movable = a;
            DoCollision();
        }
    }
    
}
