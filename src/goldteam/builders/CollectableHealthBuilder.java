/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.builders;

import goldteam.animators.CollectableHealthAnimation;
import goldteam.collectables.CollectableHealth;
import goldteam.domain.AnimationState;
import goldteam.domain.CollectableBuilderBase;
import goldteam.domain.GameEngine;

/**
 *
 * @author faaez
 */
public class CollectableHealthBuilder extends CollectableBuilderBase{
    
    private CollectableHealth health;

    public CollectableHealthBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject() {
        this.gameObject = new CollectableHealth(gameData,point);
        this.health = (CollectableHealth) gameObject;
    }

    @Override
    protected void addAnimations() {
        CollectableHealthAnimation csa = new CollectableHealthAnimation(this.health, gameData.getMapDimensions(), "assets/heart.png");
        this.health.addAnimator(AnimationState.DEFAULT, csa);
    }
    
}
