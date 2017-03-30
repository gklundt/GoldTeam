/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.colliders;

import goldteam.collectables.CollectableHealth;
import goldteam.collectables.CollectableShields;
import goldteam.domain.Attackable;
import goldteam.domain.CollectableItem;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.Delta;
import goldteam.domain.ModType;

/**
 *
 * @author faaez
 */
public class CollectableHealthCollider implements CollisionListener {

    private Collidable collectableItem;
    private Collidable movable;

    public void DoCollision() {
        Attackable am = (Attackable) movable;

        CollectableItem item = (CollectableItem) collectableItem;

        if (item instanceof CollectableHealth) {
            System.out.println(am.getHealthValue());
            if (am.getHealthValue() < 11) {
                am.setHealthDelta(Delta.create(1.0, ModType.FIXED));
                item.undoCollider();
            }
        }
    }

    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        if ((a instanceof CollectableHealth) && (b instanceof Attackable)) {
            this.collectableItem = a;
            this.movable = b;
            DoCollision();

        } else if ((b instanceof CollectableHealth) && (a instanceof Attackable)) {
            this.collectableItem = b;
            this.movable = a;
            DoCollision();
        }
    }

}
