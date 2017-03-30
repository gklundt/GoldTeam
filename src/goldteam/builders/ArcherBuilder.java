/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.builders;

import goldteam.animators.ArcherAnimationDrawing;
import goldteam.animators.ArcherAnimationStanding;
import goldteam.animators.ArcherAnimationWalking;
import goldteam.animators.ArcherAnimationHurting;
import goldteam.animators.ArcherAnimationJumping;
import goldteam.animators.ArcherAnimationDying;
import goldteam.characters.ArcherMan;
import goldteam.domain.AnimationState;
import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.CharacterBuilderBase;
import goldteam.domain.GameEngine;
import java.awt.Point;

public class ArcherBuilder extends CharacterBuilderBase {

    private ArcherMan archer;

    public ArcherBuilder(GameEngine gameData) {
        super(gameData);
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
        CharacterAnimationBase archerDefaultRight = new ArcherAnimationStanding(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Standing_Right.png", 16);
        CharacterAnimationBase archerDefaultLeft = new ArcherAnimationStanding(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Standing_Left.png", 16);
        CharacterAnimationBase archerWalkingRight = new ArcherAnimationWalking(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Walking_Right.png", 8);
        CharacterAnimationBase archerWalkingLeft = new ArcherAnimationWalking(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Walking_Left.png", 8);
        CharacterAnimationBase archerDrawingRight = new ArcherAnimationDrawing(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Drawing_Right.png", 8);
        CharacterAnimationBase archerDrawingLeft = new ArcherAnimationDrawing(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Drawing_Left.png", 8);
        CharacterAnimationBase archerHurtLeft = new ArcherAnimationHurting(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Hurt_Left.png", 8);
        CharacterAnimationBase archerHurtRight = new ArcherAnimationHurting(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Hurt_Right.png", 8);
        CharacterAnimationBase archerJumpRight = new ArcherAnimationJumping(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Jump_Right.png", 8);
        CharacterAnimationBase archerJumpLeft = new ArcherAnimationJumping(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Jump_Left.png", 8);
        CharacterAnimationBase archerDyingRight = new ArcherAnimationDying(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Death_Right.png", 9);
        CharacterAnimationBase archerDyingLeft = new ArcherAnimationDying(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Death_Left.png", 9);

        this.archer.addAnimator(AnimationState.DEFAULT_RIGHT, archerDefaultRight);
        this.archer.addAnimator(AnimationState.DEFAULT_LEFT, archerDefaultLeft);
        this.archer.addAnimator(AnimationState.WALKING_RIGHT, archerWalkingRight);
        this.archer.addAnimator(AnimationState.WALKING_LEFT, archerWalkingLeft);
        this.archer.addAnimator(AnimationState.SHOOTING_RIGHT, archerDrawingRight);
        this.archer.addAnimator(AnimationState.SHOOTING_LEFT, archerDrawingLeft);
        this.archer.addAnimator(AnimationState.HURT_RIGHT, archerHurtRight);
        this.archer.addAnimator(AnimationState.HURT_LEFT, archerHurtLeft);
        this.archer.addAnimator(AnimationState.JUMPING_RIGHT, archerJumpRight);
        this.archer.addAnimator(AnimationState.JUMPING_LEFT, archerJumpLeft);
        this.archer.addAnimator(AnimationState.DYING_RIGHT, archerDyingRight);
        this.archer.addAnimator(AnimationState.DYING_LEFT, archerDyingLeft);
    }

}
