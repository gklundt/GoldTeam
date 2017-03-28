/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

/**
 *
 * @author faaez
 */
public abstract class CollectableBuilderBase extends CollectableObjectBuilderBase{
    
    public CollectableBuilderBase(GameEngine gameData) {
        super(gameData);
    }
    
    @Override
    protected void buildTemplate(int height, int width) {
        this.createObject(height, width);
        this.addAnimations();
    }

    protected abstract void createObject(int height, int width);

    protected abstract void addAnimations();
}
