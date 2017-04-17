package goldteam.colliders;

import goldteam.characters.ArcherMan;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.Fallable;
import goldteam.domain.GameObject;
import goldteam.domain.Platform;
import goldteam.platforms.FlatPlatform;
import goldteam.platforms.HorizontalPlatform;
import goldteam.platforms.PitPlatform;

public class FlatPlatformFallableCollider implements CollisionListener {

    private Platform platform;
    private Fallable collidable;

    public FlatPlatformFallableCollider() {
    }

    private void DoCollision() { 
        if(platform instanceof PitPlatform){
            ((Fallable)(collidable)).startFalling();
        }
        else if(platform instanceof FlatPlatform)
        {
            FlatPlatform fp = (FlatPlatform) platform;
            GameObject obj = (GameObject) collidable;
            //if(obj.PositionVector().x < fp.PositionVector().x + fp.getWidth() && obj.PositionVector().x > fp.PositionVector().x - fp.getWidth())
            if(obj.getVelocityVector().y > 0)
                ((Fallable) collidable).stopFalling(fp.getPositionVector().y);
        }
        else if(platform instanceof HorizontalPlatform)
        {
            HorizontalPlatform hp = (HorizontalPlatform) platform;
            if(collidable instanceof ArcherMan)
            {
                System.out.println("CHECKING");
                ArcherMan am = (ArcherMan) collidable;
                if(am.checkPlatformList(hp))
                {
                    System.out.println("CANT LAND");
                    return;
                }
                if(am.getDown())
                {
                    am.specialFall(hp);
                    return;
                }
            }
            GameObject obj = (GameObject) collidable;
            //if(obj.PositionVector().x < hp.PositionVector().x + hp.getWidth() && obj.PositionVector().x > hp.PositionVector().x - hp.getWidth())
            if(obj.getVelocityVector().y > 0 && obj.getPositionVector().y - hp.getPositionVector().y + ((Fallable)(obj)).getOffset() < 40)
                ((Fallable) collidable).stopFalling(hp.getPositionVector().y);
        }
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
