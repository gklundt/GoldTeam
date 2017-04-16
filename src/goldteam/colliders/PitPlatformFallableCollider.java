package goldteam.colliders;

import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.Fallable;
import goldteam.platforms.PitPlatform;

public class PitPlatformFallableCollider implements CollisionListener {

    private PitPlatform platform;
    private Fallable collidable;

    public PitPlatformFallableCollider() {
    }

    private void DoCollision() {
        collidable.startFalling();
    }

    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        if ((a instanceof PitPlatform) && (b instanceof Fallable)) {
            this.platform = (PitPlatform) a;
            this.collidable = (Fallable) b;
            DoCollision();
        } else if ((b instanceof PitPlatform) && (a instanceof Fallable)) {
            this.platform = (PitPlatform) b;
            this.collidable = (Fallable) a;
            DoCollision();
        }
        // Otherwise ignore the event, another colider should be used
    }

}
