package goldteam.domain;

import java.awt.Polygon;
import java.util.*;

public interface Collidable {

    public Polygon getPolygon();

    public void setCollider(Collidable obj, CollisionPlane direction);

    public void removeCollider(Collidable obj);

    public HashMap<Collidable, CollisionPlane> getColliders();
    
    public void notifyCollidableListeners();
    
    public void setCollided(boolean state);
    
    public boolean isCollided();

}
