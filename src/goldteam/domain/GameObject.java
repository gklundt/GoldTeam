package goldteam.domain;

import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.KeyEvent;

public abstract class GameObject {

    protected Point positionVector;
    protected Polygon shape;
    protected Image image;
    protected GameEngine gamedata;

    public GameObject(GameEngine gamedata, Point initialPoint) {
        this.gamedata = gamedata;
        this.gamedata.addGraphicsUpdateTimerListener(l -> GraphicsUpdateHandler());
        this.gamedata.addEffectsTimerListener(l -> UpdateEffectHandler());
        this.gamedata.addMapUpdateTimerListener(l -> MapUpdateTimerHandler());
        this.positionVector = initialPoint;
    }

    protected abstract void Update();
    
    protected abstract void GraphicsUpdateHandler();

    protected abstract void UpdateEffectHandler();
    
    protected abstract void MapUpdateTimerHandler();

    public Point PositionVector() {
        return this.positionVector;
    }
}
