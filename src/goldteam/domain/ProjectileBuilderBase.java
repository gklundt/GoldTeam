/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.awt.Point;

public abstract class ProjectileBuilderBase extends ProjectileObjectBuilderBase {

    public ProjectileBuilderBase(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void buildTemplate(DoubleVector speed) {
        this.createObject(speed);
        this.addAnimations();
    }

    protected abstract void createObject(DoubleVector speed);

    protected abstract void addAnimations();
}
