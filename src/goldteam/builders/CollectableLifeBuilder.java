/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.builders;

import goldteam.animators.CollectableLifeAnimation;
import goldteam.collectables.CollectableLife;
import goldteam.domain.AnimationState;
import goldteam.domain.CollectableBuilderBase;
import goldteam.domain.GameEngine;

/**
 *
 * @author cece
 */
public class CollectableLifeBuilder extends CollectableBuilderBase{
    
    private CollectableLife life;

    public CollectableLifeBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject() {
        this.gameObject = new CollectableLife(gameData,point);
        this.life = (CollectableLife) gameObject;
    }

    @Override
    protected void addAnimations() {
        CollectableLifeAnimation cla = new CollectableLifeAnimation(this.life, gameData.getMapDimensions(), "assets/Archer_Head.png");
        this.life.addAnimator(AnimationState.DEFAULT, cla);
    }
    
}
