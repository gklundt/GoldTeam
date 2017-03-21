/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.providers;

import goldteam.animators.ArcherAnimation;
import goldteam.animators.ArrowAnimation;
import goldteam.characters.ArcherMan;
import goldteam.characters.Arrow;
import goldteam.domain.AnimationState;
import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.CharacterBuilderBase;
import goldteam.domain.DoubleVector;
import goldteam.domain.GameEngine;
import java.awt.Point;

public class ArrowBuilder extends CharacterBuilderBase {

    private final GameEngine gameData;
    private Arrow arrow;
    private final Point point;
    private final DoubleVector speed;

    public ArrowBuilder(GameEngine gameData, Point point, DoubleVector speed) {
        this.gameData = gameData;
        this.point = point;
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
                : new ArcherAnimation(arrow, gameData.getVisibleDimensions(), "assets/Archer/Arrow_Shot_Right.png");

        arrow.setAnimator(arrowAnimation);
    }

}
