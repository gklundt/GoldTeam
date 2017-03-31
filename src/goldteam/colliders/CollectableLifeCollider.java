/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.colliders;

import goldteam.collectables.CollectableLife;
import goldteam.domain.CollectableItem;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.Delta;
import goldteam.domain.Depletable;
import goldteam.domain.ModType;

/**
 *
 * @author cece
 */
public class CollectableLifeCollider implements CollisionListener {

    private Collidable collectableItem;
    private Collidable movable;

    public void DoCollision() {
        Depletable am = (Depletable) movable;

        CollectableItem item = (CollectableItem) collectableItem;

        if (item instanceof CollectableLife) {
            System.out.println("Archer : "+am.getCount());
            if (am.getCount() < 10) {
                am.setCountDelta(Delta.create(1.0, ModType.FIXED));
                item.undoCollider();
            }
        }
    }

    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        if ((a instanceof CollectableLife) && (b instanceof Depletable)) {
            this.collectableItem = a;
            this.movable = b;
            DoCollision();

        } else if ((b instanceof CollectableLife) && (a instanceof Depletable)) {
            this.collectableItem = b;
            this.movable = a;
            DoCollision();
        }
    }

}
