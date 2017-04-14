package goldteam.colliders;

import goldteam.characters.ArcherMan;
import goldteam.characters.Launcher;
import goldteam.domain.Attackable;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.CollisionPlane;
import goldteam.domain.Delta;
import goldteam.domain.Fallable;
import goldteam.domain.GameObject;
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

    private void DoCollision()
    {
        if(collidable instanceof Fallable)
        {
            if(platform instanceof PitPlatform){
                ((Fallable)(collidable)).fall();
            }
            else if(platform instanceof FlatPlatform)
            {
                FlatPlatform fp = (FlatPlatform) platform;
                GameObject obj = (GameObject) collidable;
                //if(obj.PositionVector().x < fp.PositionVector().x + fp.getWidth() && obj.PositionVector().x > fp.PositionVector().x - fp.getWidth())
                if(obj.getVelocityVector().y > 0)
                    ((Fallable) collidable).land(fp.getPositionVector().y);
            }
            else if(platform instanceof HorizontalPlatform)
            {
                HorizontalPlatform hp = (HorizontalPlatform) platform;
                if(collidable instanceof ArcherMan)
                {
                    ArcherMan am = (ArcherMan) collidable;
                    if(am.checkPlatformList(hp))
                        return;
                    if(am.getDown())
                    {
                        am.specialFall(hp);
                        return;
                    }
                }
                GameObject obj = (GameObject) collidable;
                //if(obj.PositionVector().x < hp.PositionVector().x + hp.getWidth() && obj.PositionVector().x > hp.PositionVector().x - hp.getWidth())
                if(obj.getVelocityVector().y > 0 && obj.getPositionVector().y - hp.getPositionVector().y + ((Fallable)(obj)).getOffset() < 40)
                    ((Fallable) collidable).land(hp.getPositionVector().y);
            }
        }
        else if(collidable instanceof Launcher)
        {
            Attackable l = (Attackable) collidable;
            if(l.getHealthValue() > 0){
                l.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
            }
            ((GameObject)(collidable)).remove();
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
        } else if((a instanceof Platform) && (b instanceof Launcher)) {
            this.platform = a;
            this.collidable = b;
            DoCollision();
        } else if((b instanceof Platform) && (a instanceof Launcher)) {
            this.platform = b;
            this.collidable = a;
            DoCollision();
        }
        // Otherwise ignore the event, another colider should be used
    }


}
