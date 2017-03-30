/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.builders;

import goldteam.collectables.CollectableArrows;
import goldteam.domain.AnimationState;
import goldteam.domain.CollectableBuilderBase;
import goldteam.domain.GameEngine;
import goldteam.animators.CollectableArrowAnimation;

/**
 *
 * @author fchishti-sw
 */
public class CollectableArrowBuilder extends CollectableBuilderBase{
    
    private CollectableArrows arrows;

    public CollectableArrowBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject() {
        this.gameObject = new CollectableArrows(gameData, point);
        this.arrows = (CollectableArrows) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        CollectableArrowAnimation caa = new CollectableArrowAnimation(this.arrows, gameData.getMapDimensions(), "assets/crate.png");
        this.arrows.addAnimator(AnimationState.DEFAULT, caa);
    }
    
}
