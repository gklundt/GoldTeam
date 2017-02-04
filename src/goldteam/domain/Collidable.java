package goldteam.domain;

import java.awt.Polygon;
import java.util.*;

public interface Collidable {

    public Polygon getPolygon();

    public void setCollider(GameObject obj, CollisionPlane direction);

    public void removeCollider(GameObject obj);

    public ArrayList<GameObject> getColliders();

}