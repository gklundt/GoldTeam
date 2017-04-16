package goldteam.colliders;

import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.Fallable;
import goldteam.domain.Platform;

public class FlatPlatformFallableCollider implements CollisionListener {

    private Platform platform;
    private Fallable collidable;

    public FlatPlatformFallableCollider() {
    }

    private void DoCollision() {
        collidable.stopFalling();
    }

    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        if ((a instanceof Platform) && (b instanceof Fallable)) {
            this.platform = (Platform) a;
            this.collidable = (Fallable) b;
            DoCollision();
        } else if ((b instanceof Platform) && (a instanceof Fallable)) {
            this.platform = (Platform) b;
            this.collidable = (Fallable) a;
            DoCollision();
        }
        // Otherwise ignore the event, another colider should be used
    }

}
