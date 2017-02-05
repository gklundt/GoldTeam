package goldteam.domain;

import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;

public abstract class GameObject {

    public GameObject() {
    }

    protected Point positionVector;
    protected Polygon shape;
    protected Image image;

    public abstract void Update();

    public abstract void Render();
}