package goldteam.domain;

import java.awt.Polygon;
import java.awt.event.ActionListener;
import java.util.*;

public interface Collidable {

    public Polygon getPolygon();

    public void setCollider(Collidable obj, CollisionPlane direction);

    public void removeCollider(Collidable obj);

    public HashMap<Collidable, CollisionPlane> getColliders();

}