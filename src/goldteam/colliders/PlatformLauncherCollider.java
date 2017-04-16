package goldteam.colliders;

import goldteam.characters.Launcher;
import goldteam.domain.Attackable;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.Delta;
import goldteam.domain.GameObject;
import goldteam.domain.ModType;
import goldteam.platforms.FlatPlatform;

public class PlatformLauncherCollider implements CollisionListener {

    private FlatPlatform platform;
    private Launcher collidable;

    public PlatformLauncherCollider() {
    }

    private void DoCollision() {
        Attackable l = (Attackable) collidable;
        if (l.getHealthValue() > 0) {
            l.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
        }
        ((GameObject) (collidable)).remove();
    }

    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        if ((a instanceof FlatPlatform) && (b instanceof Launcher)) {
            this.platform = (FlatPlatform) a;
            this.collidable = (Launcher) b;
            DoCollision();
        } else if ((b instanceof FlatPlatform) && (a instanceof Launcher)) {
            this.platform = (FlatPlatform) b;
            this.collidable = (Launcher) a;
            DoCollision();
        }
        // Otherwise ignore the event, another colider should be used
    }

}
