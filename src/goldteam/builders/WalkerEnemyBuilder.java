package goldteam.builders;

import goldteam.animators.GhostAnimation;
import goldteam.animators.WalkerAnimationStanding;
import goldteam.animators.WalkerAnimationWalking;
import goldteam.characters.Walker;
import goldteam.domain.AnimationState;
import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.CharacterBuilderBase;
import goldteam.domain.GameEngine;
import java.awt.Point;

public class WalkerEnemyBuilder extends CharacterBuilderBase {

    private Walker enemy;

    public WalkerEnemyBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject() {
        this.gameObject = new Walker(gameData, point);
        this.enemy = (Walker) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        CharacterAnimationBase walkerDefaultRight = new WalkerAnimationStanding(gameObject, gameData.getVisibleDimensions(), "assets/Enemies/Walker_Standing_Right.png", 1);
        CharacterAnimationBase walkerDefaultLeft = new WalkerAnimationStanding(gameObject, gameData.getVisibleDimensions(), "assets/Enemies/Walker_Standing_Left.png", 1);
        CharacterAnimationBase walkerWalkingRight = new WalkerAnimationWalking(gameObject, gameData.getVisibleDimensions(), "assets/Enemies/Walker_Walking_Right.png", 6);
        CharacterAnimationBase walkerWalkingLeft = new WalkerAnimationWalking(gameObject, gameData.getVisibleDimensions(), "assets/Enemies/Walker_Walking_Left.png", 6);
        this.enemy.addAnimator(AnimationState.DEFAULT_RIGHT, walkerDefaultRight);
        this.enemy.addAnimator(AnimationState.DEFAULT_LEFT, walkerDefaultLeft);
        this.enemy.addAnimator(AnimationState.WALKING_RIGHT, walkerWalkingRight);
        this.enemy.addAnimator(AnimationState.WALKING_LEFT, walkerWalkingLeft);
    }

}
