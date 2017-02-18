package goldteam.colliders;

import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.CollisionPlane;
import goldteam.domain.Delta;
import goldteam.domain.ModType;
import goldteam.domain.Movable;
import goldteam.domain.Platform;

public class PlatformCollider implements CollisionListener {

    private Collidable platform;
    private Collidable collidable;
    private final Delta xdelta;
    private final Delta ydelta;

    public PlatformCollider() {
        xdelta = Delta.create(0.0d, ModType.FIXED);
        ydelta = Delta.create(-1.0d, ModType.PERCENTAGE);
    }

    private void DoCollision() {
        // No change in x direction
        // Reduce y dirction by 100% or subtract y from y to net 0 ... 
        if (platform.getColliders().get(collidable) == CollisionPlane.TOP) {
            Movable m = (Movable) collidable;
            m.setVelocityVectorDelta(xdelta, ydelta);
        }
    }

    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        if ((a instanceof Platform) && (b instanceof Movable)) {
            this.platform = a;
            this.collidable = b;
            DoCollision();

        } else if ((b instanceof Platform) && (a instanceof Movable)) {
            this.platform = b;
            this.collidable = a;
            DoCollision();
        }
        // Otherwise ignore the event, another colider should be used
    }

}
