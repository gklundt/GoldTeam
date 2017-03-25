/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import goldteam.characters.ArcherMan;
import java.awt.Point;

/**
 *
 * @author gordon
 */
public abstract class GameObjectBuilderBase {

    protected final GameEngine gameData;
    protected final Point point;

    public GameObjectBuilderBase(GameEngine gameData, Point point) {
        this.gameData = gameData;
        this.point = point;
    }
    
    protected GameObject gameObject;

    public GameObject getGameObject() {
        return this.gameObject;
    }

    public void createGameObject() {
        this.buildTemplate();
    }

    protected abstract void buildTemplate();
}
