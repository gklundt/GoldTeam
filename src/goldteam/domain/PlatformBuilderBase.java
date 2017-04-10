/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

public abstract class PlatformBuilderBase extends PlatformObjectBuilderBase {

    public PlatformBuilderBase(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void buildTemplate(int width, int height) {
        this.createObject(width, height);
        this.addAnimations();
    }

    protected abstract void createObject(int height, int width);

    protected abstract void addAnimations();
}
