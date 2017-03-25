/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.awt.Point;


public abstract class GameStageBuilderBase extends GameObjectBuilderBase {

    public GameStageBuilderBase(GameEngine gameData, Point point) {
        super(gameData, point);
    }

    @Override
    protected void buildTemplate() {
        this.createObject();
        this.addAnimations();
        
    }
    
    protected abstract void createObject();
    protected abstract void addAnimations();
    
}
