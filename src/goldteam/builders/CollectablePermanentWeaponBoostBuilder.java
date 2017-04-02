/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.builders;

import goldteam.animators.CollectablePermanentWeaponBoostAnimation;
import goldteam.collectables.CollectablePermanentWeaponBoost;
import goldteam.domain.AnimationState;
import goldteam.domain.CollectableBuilderBase;
import goldteam.domain.GameEngine;

/**
 *
 * @author faaez
 */
public class CollectablePermanentWeaponBoostBuilder extends CollectableBuilderBase{
    
    private CollectablePermanentWeaponBoost boost;

    public CollectablePermanentWeaponBoostBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject() {
        this.gameObject = new CollectablePermanentWeaponBoost(gameData,point);
        this.boost = (CollectablePermanentWeaponBoost) gameObject;
    }

    @Override
    protected void addAnimations() {
        CollectablePermanentWeaponBoostAnimation wba = new CollectablePermanentWeaponBoostAnimation(this.boost, gameData.getMapDimensions(), "assets/shield.png");
        this.boost.addAnimator(AnimationState.DEFAULT, wba);
    }
}
