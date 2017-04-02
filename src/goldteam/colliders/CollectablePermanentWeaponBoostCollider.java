/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.colliders;

import goldteam.collectables.CollectablePermanentWeaponBoost;
import goldteam.domain.Boostable;
import goldteam.domain.CollectableItem;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;

/**
 *
 * @author faaez
 */
public class CollectablePermanentWeaponBoostCollider implements CollisionListener{
    
    private Collidable collectableItem;
    private Collidable movable;

    public void DoCollision() {
        Boostable am = (Boostable) movable;

        CollectableItem item = (CollectableItem) collectableItem;

        if (item instanceof CollectablePermanentWeaponBoost) {
            if (am.isPermanentBoostableWeapon() == false) {
                am.setPermanentBoostableWeapon(true);
                item.undoCollider();
            }
        }
    }

    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        if ((a instanceof CollectablePermanentWeaponBoost) && (b instanceof Boostable)) {
            this.collectableItem = a;
            this.movable = b;
            DoCollision();

        } else if ((b instanceof CollectablePermanentWeaponBoost) && (a instanceof Boostable)) {
            this.collectableItem = b;
            this.movable = a;
            DoCollision();
        }
    }
}
