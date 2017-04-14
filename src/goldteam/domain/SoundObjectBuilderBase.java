/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.awt.Point;

/**
 *
 * @author Caleb Dunham
 */
public abstract class SoundObjectBuilderBase {
    protected final GameEngine gameData;
    protected Object watchable;

    public SoundObjectBuilderBase(GameEngine gameData) {
        this.gameData = gameData;
    }
    
    protected GameObject gameObject;

    public GameObject getGameObject() {
        return this.gameObject;
    }

    public void createGameObject(Object watchable) {
        this.watchable = watchable;
        this.buildTemplate();
    }

    protected abstract void buildTemplate();
}
