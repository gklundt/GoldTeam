/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.builders;

import goldteam.animators.CollectableHealthBoostAnimation;
import goldteam.collectables.CollectableHealthBoost;
import goldteam.domain.AnimationState;
import goldteam.domain.CollectableBuilderBase;
import goldteam.domain.GameEngine;

/**
 *
 * @author faaez
 */
public class CollectableHealthBoostBuilder extends CollectableBuilderBase {
    private CollectableHealthBoost boost;

    public CollectableHealthBoostBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject() {
        this.gameObject = new CollectableHealthBoost(gameData,point);
        this.boost = (CollectableHealthBoost) gameObject;
    }

    @Override
    protected void addAnimations() {
        CollectableHealthBoostAnimation hba = new CollectableHealthBoostAnimation(this.boost, gameData.getMapDimensions(), "assets/potion.png");
        this.boost.addAnimator(AnimationState.DEFAULT, hba);
    }
}
