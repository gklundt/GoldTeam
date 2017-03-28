/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.builders;

import goldteam.animators.CollectableShieldAnimation;
import goldteam.collectables.Shields;
import goldteam.domain.AnimationState;
import goldteam.domain.CollectableBuilderBase;
import goldteam.domain.GameEngine;

/**
 *
 * @author faaez
 */
public class ShieldCollectableBuilder extends CollectableBuilderBase{
    
    private Shields shield;

    public ShieldCollectableBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject() {
        this.gameObject = new Shields(gameData,point);
        this.shield = (Shields) gameObject;
    }

    @Override
    protected void addAnimations() {
        CollectableShieldAnimation csa = new CollectableShieldAnimation(this.shield, gameData.getMapDimensions(), "assets/shield.png");
        this.shield.addAnimator(AnimationState.DEFAULT, csa);
    }
    
}
