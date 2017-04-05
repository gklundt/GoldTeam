/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.awt.Point;

/**
 *
 * @author gordon
 */
public abstract class HudObjectBuilderBase {

    protected final GameEngine gameData;
    protected Point point;
    protected Object watchable;

    public HudObjectBuilderBase(GameEngine gameData) {
        this.gameData = gameData;
    }
    
    protected GameObject gameObject;

    public GameObject getGameObject() {
        return this.gameObject;
    }

    public void createGameObject(Point point, Object watchable) {
        this.point = point;
        this.watchable = watchable;
        this.buildTemplate();
    }

    protected abstract void buildTemplate();
}
