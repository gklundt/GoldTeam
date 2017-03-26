package goldteam.domain;

import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class GameObject implements Removable {

    protected Point positionVector;
    protected Polygon shape;
    protected Image image;
    protected GameEngine gamedata;
    protected final ArrayList<ActionListener> removableListeners;
    protected boolean removeMe;

    public GameObject(GameEngine gamedata, Point initialPoint) {
        this.gamedata = gamedata;
        this.gamedata.addGraphicsUpdateTimerListener(l -> GraphicsUpdateHandler());
        this.gamedata.addEffectsTimerListener(l -> UpdateEffectHandler());
        this.gamedata.addMapUpdateTimerListener(l -> MapUpdateTimerHandler());
        this.removableListeners = new ArrayList<>();
        this.positionVector = initialPoint;
        this.removeMe = false;
    }

    protected abstract void Update();

    protected abstract void GraphicsUpdateHandler();

    protected abstract void UpdateEffectHandler();

    protected abstract void MapUpdateTimerHandler();

    @Override
    public void addRemovableListener(ActionListener listener) {
        this.removableListeners.add(listener);
    }

    @Override
    public void removeRemovableListener(ActionListener listener) {
        this.removableListeners.remove(listener);
    }

    @Override
    public void markForRemoval() {
        this.removeMe = true;
    }

    @Override
    public void remove() {
        this.notifyRemovableListeners();
    }

    private void notifyRemovableListeners() {
        ActionEvent e = new ActionEvent(this, 0, null);
        for (ActionListener removableListener : removableListeners) {
            removableListener.actionPerformed(e);
        }
    }

    public Point PositionVector() {
        return this.positionVector;
    }
}
