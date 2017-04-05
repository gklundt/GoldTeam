/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.builders;

import goldteam.animators.CollectableWeaponBoostAnimation;
import goldteam.collectables.CollectableWeaponBoost;
import goldteam.domain.AnimationState;
import goldteam.domain.CollectableBuilderBase;
import goldteam.domain.GameEngine;

/**
 *
 * @author faaez
 */
public class CollectableWeaponBoostBuilder extends CollectableBuilderBase {
        
    private CollectableWeaponBoost boost;

    public CollectableWeaponBoostBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject() {
        this.gameObject = new CollectableWeaponBoost(gameData,point);
        this.boost = (CollectableWeaponBoost) gameObject;
    }

    @Override
    protected void addAnimations() {
        CollectableWeaponBoostAnimation wba = new CollectableWeaponBoostAnimation(this.boost, gameData.getMapDimensions(), "assets/boost.png");
        this.boost.addAnimator(AnimationState.DEFAULT, wba);
    }
}
