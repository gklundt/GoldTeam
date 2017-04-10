package goldteam.colliders;

import goldteam.characters.ArcherMan;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.CollisionPlane;
import goldteam.domain.Delta;
import goldteam.domain.Fallable;
import goldteam.domain.ModType;
import goldteam.domain.Movable;
import goldteam.domain.Platform;
import goldteam.platforms.*;
import goldteam.platforms.PitPlatform;

public class PlatformCollider implements CollisionListener {

    private Collidable platform;
    private Collidable collidable;
    private final Delta xdelta;
    private final Delta ydelta;

    public PlatformCollider() {
        xdelta = Delta.create(0.0d, ModType.FIXED);
        ydelta = Delta.create(1.0d, ModType.PERCENTAGE);
    }

    private void DoCollision() {
        if(platform instanceof PitPlatform && collidable instanceof Fallable){
            ((Fallable)(collidable)).fall();
            /*
            am.setVelocityVectorDelta(Delta.create(0.0d, ModType.FIXED), 
                    Delta.create(1.0d, ModType.PERCENTAGE));
            if(am.PositionVector().y > 400){
                am.die();
            }
            */
        }
        
        if(platform instanceof FlatPlatform && collidable instanceof Fallable)
        {
            FlatPlatform fp = (FlatPlatform) platform;
            ((Fallable) collidable).land(fp.getPositionVector().y);
        }
    }

    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        if ((a instanceof Platform) && (b instanceof Fallable)) {
            this.platform = a;
            this.collidable = b;
            DoCollision();

        } else if ((b instanceof Platform) && (a instanceof Fallable)) {
            this.platform = b;
            this.collidable = a;
            DoCollision();
        }
        // Otherwise ignore the event, another colider should be used
    }


}
