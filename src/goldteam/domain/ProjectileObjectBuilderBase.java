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
public abstract class ProjectileObjectBuilderBase {

    protected final GameEngine gameData;
    protected Point point;

    public ProjectileObjectBuilderBase(GameEngine gameData) {
        this.gameData = gameData;
    }
    
    protected GameObject gameObject;

    public GameObject getGameObject() {
        return this.gameObject;
    }

    public void createGameObject(Point point, DoubleVector speed) {
        this.point = point;
        this.buildTemplate(speed);
    }

    protected abstract void buildTemplate(DoubleVector speed);
}
