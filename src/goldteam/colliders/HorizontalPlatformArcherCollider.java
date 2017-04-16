package goldteam.colliders;

import goldteam.characters.ArcherMan;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.platforms.HorizontalPlatform;

public class HorizontalPlatformArcherCollider implements CollisionListener {

    private HorizontalPlatform platform;
    private ArcherMan collidable;

    public HorizontalPlatformArcherCollider() {
    }

    private void DoCollision() {
        if (collidable.checkPlatformList(platform)) {
            return;
        }
        if (collidable.getDown()) {
            collidable.specialFall(platform);
        }
    }

    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        if ((a instanceof HorizontalPlatform) && (b instanceof ArcherMan)) {
            this.platform = (HorizontalPlatform) a;
            this.collidable = (ArcherMan) b;
            DoCollision();
        } else if ((b instanceof HorizontalPlatform) && (a instanceof ArcherMan)) {
            this.platform = (HorizontalPlatform) b;
            this.collidable = (ArcherMan) a;
            DoCollision();
        }
        // Otherwise ignore the event, another colider should be used
    }

}
