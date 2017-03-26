/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.providers;

import goldteam.animators.ArcherAnimationStanding;
import goldteam.animators.ArrowAnimation;
import goldteam.characters.Arrow;
import goldteam.domain.AnimationState;
import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.CharacterBuilderBase;
import goldteam.domain.DoubleVector;
import goldteam.domain.GameEngine;
import java.awt.Point;

public class ArrowBuilder extends CharacterBuilderBase {

    private Arrow arrow;
    private final DoubleVector speed;

    public ArrowBuilder(GameEngine gameData, DoubleVector speed) {
        super(gameData);
        this.speed = speed;
    }

    @Override
    protected void createObject() {
        this.gameObject = new Arrow(gameData, point, speed);
        this.arrow = (Arrow) this.gameObject;
    }

    @Override
    protected void addAnimations() {

        CharacterAnimationBase arrowAnimation = speed.x >= 0
                ? new ArrowAnimation(arrow, gameData.getVisibleDimensions(), "assets/Archer/Arrow_Shot_Right.png")
                : new ArcherAnimationStanding(arrow, gameData.getVisibleDimensions(), "assets/Archer/Arrow_Shot_Right.png");

        arrow.addAnimator(AnimationState.DEFAULT, arrowAnimation);
    }

}
