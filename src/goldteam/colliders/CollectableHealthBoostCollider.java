/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.colliders;

import goldteam.collectables.CollectableHealthBoost;
import goldteam.collectables.CollectableWeaponBoost;
import goldteam.domain.Boostable;
import goldteam.domain.CollectableItem;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;

/**
 *
 * @author faaez
 */
public class CollectableHealthBoostCollider implements CollisionListener {

    private Collidable collectableItem;
    private Collidable movable;

    public void DoCollision() {
        Boostable am = (Boostable) movable;

        CollectableItem item = (CollectableItem) collectableItem;

        if (item instanceof CollectableHealthBoost) {
            if (am.isBoostableHealth()== false) {
                am.setBoostableHealth(true);
                item.undoCollider();
                ((CollectableHealthBoost) item).remove();
            }
        }
    }

    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        if ((a instanceof CollectableHealthBoost) && (b instanceof Boostable)) {
            this.collectableItem = a;
            this.movable = b;
            DoCollision();

        } else if ((b instanceof CollectableHealthBoost) && (a instanceof Boostable)) {
            this.collectableItem = b;
            this.movable = a;
            DoCollision();
        }
    }
}
