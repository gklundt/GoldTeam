/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.providers;

import goldteam.animators.ArcherAnimationDrawing;
import goldteam.animators.ArcherAnimationStanding;
import goldteam.animators.ArcherAnimationWalking;
import goldteam.characters.ArcherMan;
import goldteam.domain.AnimationState;
import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.CharacterBuilderBase;
import goldteam.domain.GameEngine;
import java.awt.Point;

public class ArcherBuilder extends CharacterBuilderBase {

    private ArcherMan archer;

    public ArcherBuilder(GameEngine gameData, Point point) {
        super(gameData, point);
    }

    @Override
    protected void createObject() {
        this.gameObject = new ArcherMan(gameData, point);
        this.archer = (ArcherMan) this.gameObject;
        this.gameData.setMovableCharacter(this.archer);
        this.gameData.setDepletableCharacter(this.archer);
        this.gameData.setAttackableCharacter(this.archer);
    }

    @Override
    protected void addAnimations() {
        CharacterAnimationBase archerDefaultRight = new ArcherAnimationStanding(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Standing_Right.png");
        CharacterAnimationBase archerDefaultLeft = new ArcherAnimationStanding(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Standing_Left.png");
        CharacterAnimationBase archerWalkingRight = new ArcherAnimationWalking(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Walking_Right.png");
        CharacterAnimationBase archerWalkingLeft = new ArcherAnimationWalking(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Walking_Left.png");
        CharacterAnimationBase archerDrawingRight = new ArcherAnimationDrawing(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Drawing_Right.png");
        CharacterAnimationBase archerDrawingLeft = new ArcherAnimationDrawing(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Drawing_Left.png");
        this.archer.addAnimator(AnimationState.DEFAULT_RIGHT, archerDefaultRight);
        this.archer.addAnimator(AnimationState.DEFAULT_LEFT, archerDefaultLeft);
        this.archer.addAnimator(AnimationState.WALKING_RIGHT, archerWalkingRight);
        this.archer.addAnimator(AnimationState.WALKING_LEFT, archerWalkingLeft);
        this.archer.addAnimator(AnimationState.SHOOTING_RIGHT, archerDrawingRight);
        this.archer.addAnimator(AnimationState.SHOOTING_LEFT, archerDrawingLeft);
    }

}
