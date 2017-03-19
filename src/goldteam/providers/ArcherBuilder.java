/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.providers;

import goldteam.animators.ArcherAnimation;
import goldteam.characters.ArcherMan;
import goldteam.domain.AnimationState;
import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.CharacterBuilderBase;
import goldteam.domain.GameEngine;
import java.awt.Point;


public class ArcherBuilder extends CharacterBuilderBase {

    private final GameEngine gameData;
    private ArcherMan archer;
    private final Point point;

    public ArcherBuilder(GameEngine gameData, Point point){
    this.gameData = gameData;
    this.point = point;
    }
    @Override
    protected void createObject() {
        this.gameObject = new ArcherMan(gameData, point);
        this.archer = (ArcherMan)this.gameObject;
    }

    @Override
    protected void addAnimations() {
        CharacterAnimationBase archerDefaultRight = new ArcherAnimation(archer, gameData.getVisibleDimensions(), "assets/Archer/Archer_Standing_Right.png",0,0);
        CharacterAnimationBase archerDefaultLeft = new ArcherAnimation(archer, gameData.getVisibleDimensions(), "assets/Archer/Archer_Standing_Left.png",0,0);
        CharacterAnimationBase archerWalkingRight = new ArcherAnimation(archer, gameData.getVisibleDimensions(), "assets/Archer/Archer_Walking_Right.png",0);
        CharacterAnimationBase archerWalkingLeft = new ArcherAnimation(archer, gameData.getVisibleDimensions(), "assets/Archer/Archer_Walking_Left.png",0);
        CharacterAnimationBase archerDrawingRight = new ArcherAnimation(archer, gameData.getVisibleDimensions(), "assets/Archer/Archer_Drawing_Right.png",0);
        CharacterAnimationBase archerDrawingLeft = new ArcherAnimation(archer, gameData.getVisibleDimensions(), "assets/Archer/Archer_Drawing_Left.png",0);
        archer.setAnimator(archerDefaultRight);
        archer.addAnimator(AnimationState.DEFAULT_RIGHT, archerDefaultRight);
        archer.addAnimator(AnimationState.DEFAULT_LEFT, archerDefaultLeft);
        archer.addAnimator(AnimationState.WALKING_RIGHT, archerWalkingRight);
        archer.addAnimator(AnimationState.WALKING_LEFT, archerWalkingLeft);
        archer.addAnimator(AnimationState.SHOOTING_RIGHT, archerDrawingRight);
        archer.addAnimator(AnimationState.SHOOTING_LEFT, archerDrawingLeft);
    }
    
}
