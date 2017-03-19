/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

/**
 *
 * @author gordon
 */
public abstract class GameObjectBuilderBase {

    protected GameObject gameObject;

    public GameObject getGameObject() {
        return this.gameObject;
    }

    public void createGameObject() {
        this.buildTemplate();
    }

    protected abstract void buildTemplate();
}
