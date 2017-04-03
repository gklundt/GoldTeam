/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.builders;

import goldteam.animators.ArcherAnimationDrawing;
import goldteam.animators.ArcherAnimationDying;
import goldteam.animators.ArcherAnimationHurting;
import goldteam.animators.ArcherAnimationJumping;
import goldteam.animators.ArcherAnimationStanding;
import goldteam.animators.ArcherAnimationWalking;
import goldteam.characters.ArcherBow;
import goldteam.characters.ArcherMan;
import goldteam.domain.AnimationState;
import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.CharacterBuilderBase;
import goldteam.domain.Delta;
import goldteam.domain.GameEngine;
import goldteam.domain.ModType;
import java.awt.Point;

public class ArcherBuilder extends CharacterBuilderBase {

    private ArcherMan archer;
    private ArcherBow archerBow;

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
        CharacterAnimationBase archerDefaultRight = new ArcherAnimationStanding(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Standing_Right.png");
        CharacterAnimationBase archerDefaultLeft = new ArcherAnimationStanding(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Standing_Left.png");
        CharacterAnimationBase archerWalkingRight = new ArcherAnimationWalking(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Walking_Right.png");
        CharacterAnimationBase archerWalkingLeft = new ArcherAnimationWalking(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Walking_Left.png");
        CharacterAnimationBase archerDrawingRight = new ArcherAnimationDrawing(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Drawing_Right.png");
        CharacterAnimationBase archerDrawingLeft = new ArcherAnimationDrawing(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Drawing_Left.png");
        CharacterAnimationBase archerHurtLeft = new ArcherAnimationHurting(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Hurt_Left.png");
        CharacterAnimationBase archerHurtRight = new ArcherAnimationHurting(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Hurt_Right.png");
        CharacterAnimationBase archerJumpRight = new ArcherAnimationJumping(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Jump_Right.png");
        CharacterAnimationBase archerJumpLeft = new ArcherAnimationJumping(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Jump_Left.png");
        CharacterAnimationBase archerDyingRight = new ArcherAnimationDying(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Death_Right.png");
        CharacterAnimationBase archerDyingLeft = new ArcherAnimationDying(gameObject, gameData.getVisibleDimensions(), "assets/Archer/Archer_Death_Left.png");
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
    
    public void setArcherBow(ArcherBow archerBow){
        this.archer.setArcherBow(archerBow);
    }

}
