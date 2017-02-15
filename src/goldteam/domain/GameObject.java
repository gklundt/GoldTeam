package goldteam.domain;

import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;

public abstract class GameObject {

    protected Point positionVector;
    protected Polygon shape;
    protected Image image;
    protected GameEngine gamedata;
    protected Collider collider;

    public GameObject(GameEngine gamedata, Point initialPoint) {
        this.gamedata = gamedata;
        this.gamedata.addGraphicsUpdateTimerListener(l -> GraphicsUpdateHandler());
        this.gamedata.addClicksListener(l -> ClickHandler());
        this.gamedata.addKeysListener(l -> KeyHandler());
        this.gamedata.addEffectsTimerListener(l -> UpdateEffectHandler());
        this.gamedata.addMapUpdateTimerListener(l -> MapUpdateTimerHandler());
        this.positionVector = initialPoint;
    }

    protected abstract void Update();
    
    protected abstract void GraphicsUpdateHandler();

    protected abstract void ClickHandler();
    
    protected abstract void KeyHandler();
    
    protected abstract void UpdateEffectHandler();
    
    protected abstract void MapUpdateTimerHandler();
    
    public Collider getCollider(){
        return this.collider;
    }

    public Point PositionVector() {
        return this.positionVector;
    }

}
