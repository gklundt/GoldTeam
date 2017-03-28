/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.builders;

import goldteam.collectables.Arrows;
import goldteam.domain.AnimationState;
import goldteam.domain.CollectableBuilderBase;
import goldteam.domain.GameEngine;
import goldteam.animators.CollectableArrowAnimation;

/**
 *
 * @author fchishti-sw
 */
public class ArrowCollectableBuilder extends CollectableBuilderBase{
    
    private Arrows arrows;

    public ArrowCollectableBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject() {
        this.gameObject = new Arrows(gameData, point);
        this.arrows = (Arrows) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        CollectableArrowAnimation caa = new CollectableArrowAnimation(this.arrows, gameData.getMapDimensions(), "assets/crate.png");
        this.arrows.addAnimator(AnimationState.DEFAULT, caa);
    }
    
}
