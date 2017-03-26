/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;


public abstract class GameStageBuilderBase extends GameObjectBuilderBase {

    public GameStageBuilderBase(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void buildTemplate() {
        this.createObject();
        this.addAnimations();
        
    }
    
    protected abstract void createObject();
    protected abstract void addAnimations();
    
}
