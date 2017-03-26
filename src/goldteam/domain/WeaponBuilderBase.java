/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.awt.Point;


public abstract class WeaponBuilderBase extends WeaponObjectBuilderBase {

    public WeaponBuilderBase(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void buildTemplate(Weapon weapon) {
        this.createObject(weapon);
        this.addAnimations();
    }

    protected abstract void createObject(Weapon weapon);
    protected abstract void addAnimations();
}
