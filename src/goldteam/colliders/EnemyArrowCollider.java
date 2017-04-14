package goldteam.colliders;

import goldteam.characters.Arrow;
import goldteam.characters.Ghost;
import goldteam.domain.Attackable;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.Delta;
import goldteam.domain.ModType;
import goldteam.domain.Enemy;
import goldteam.domain.GameSounds;

/**
 * If an Arrow hits an Enemy Decrease the health of the enemy
 *
 * @author Caleb Dunham
 */
public class EnemyArrowCollider implements CollisionListener {

    private Collidable ghost;
    private Collidable arrow;
    private boolean workingFlag;

    public EnemyArrowCollider() {
        this.workingFlag = false;
    }

    @Override
    public void CollisionDetected(Collidable a, Collidable b) {

        boolean iAmHandler = false;
        if ((a instanceof Enemy) && (b instanceof Arrow)) {
            this.ghost = a;
            this.arrow = b;
            iAmHandler = true;
        } else if ((b instanceof Ghost) && (a instanceof Arrow)) {
            this.ghost = b;
            this.arrow = a;
            iAmHandler = true;
        }
        if (iAmHandler) {
            Arrow remove = (Arrow) this.arrow;
            remove.markForRemoval();
            this.DoCollision();
        }
    }

    private void DoCollision() {

        if (this.workingFlag) {
            return;
        }
        GameSounds.sounds[2].play();
        this.workingFlag = true;
        if (this.ghost instanceof Attackable) {
            Attackable attackable = (Attackable) ghost;
            if(attackable.getHealthValue() > 0){
                attackable.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
            }
        }

        this.workingFlag = false;
    }

}
