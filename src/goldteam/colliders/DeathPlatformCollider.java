package goldteam.colliders;

import goldteam.characters.ArcherMan;
import goldteam.characters.Launcher;
import goldteam.characters.Walker;
import goldteam.domain.Attackable;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.Delta;
import goldteam.domain.GameObject;
import goldteam.domain.GameSounds;
import goldteam.domain.ModType;
import goldteam.platforms.DeathPlatform;

public class DeathPlatformCollider implements CollisionListener {

    private DeathPlatform platform;
    private GameObject collidable;
    private final GameSounds deathHit = GameSounds.sounds[11];

    public DeathPlatformCollider() {
    }

    private void DoCollision() {
        if(!(collidable instanceof ArcherMan))
        {
            Attackable l = (Attackable) collidable;
            if (l.getHealthValue() > 0) {
                l.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
            }
            ((GameObject) (collidable)).remove();
        }
        else
        {
            ArcherMan am = (ArcherMan) collidable;
            deathHit.play();
            am.die();
            
        }
    }

    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        if ((a instanceof DeathPlatform) && (b instanceof Launcher || b instanceof ArcherMan || b instanceof Walker)) {
            this.platform = (DeathPlatform) a;
            this.collidable = (GameObject) b;
            DoCollision();
        } else if ((b instanceof DeathPlatform) && (a instanceof Launcher || a instanceof ArcherMan || a instanceof Walker)) {
            this.platform = (DeathPlatform) b;
            this.collidable = (GameObject) a;
            DoCollision();
        }
        // Otherwise ignore the event, another colider should be used
    }

}
