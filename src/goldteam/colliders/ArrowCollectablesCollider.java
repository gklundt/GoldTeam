/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.colliders;

import goldteam.characters.ArcherBow;
import goldteam.characters.ArcherMan;
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
        ArcherBow ar = ((ArcherMan) movable).getArcherBow();
        
        CollectableItem item = (CollectableItem) collectableItem;
        
        if(item instanceof Arrows){
            System.out.println(ar.getCount());
            if(ar.getCount()< 101){
                ar.setCountDelta(Delta.create(200.0, ModType.FIXED));
                item.setState(false);
                item.undoCollider();
            }
        } 
    }
    
    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        
        if ((a instanceof Arrows) && (b instanceof ArcherMan)) {
            this.collectableItem = a;
            this.movable = b;
            DoCollision();

        } else if ((b instanceof Arrows) && (a instanceof ArcherMan)) {
            this.collectableItem = b;
            this.movable = a;
            DoCollision();
        }
    }
    
}
