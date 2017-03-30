package goldteam.builders;

import goldteam.animators.FlyerAnimationDefault;
import goldteam.animators.FlyerAnimationFlying;
import goldteam.characters.Flyer;
import goldteam.domain.AnimationState;
import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.CharacterBuilderBase;
import goldteam.domain.GameEngine;
import java.awt.Point;

public class FlyerEnemyBuilder extends CharacterBuilderBase {

    private Flyer enemy;

    public FlyerEnemyBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject() {
        this.gameObject = new Flyer(gameData, point);
        this.enemy = (Flyer) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        CharacterAnimationBase flyerDefault = new FlyerAnimationDefault(gameObject, gameData.getVisibleDimensions(), "assets/Enemies/Flyer_Default.png", 8);
        CharacterAnimationBase flyerFlyingRight = new FlyerAnimationFlying(gameObject, gameData.getVisibleDimensions(), "assets/Enemies/Flyer_Flying_Right.png", 8);
        CharacterAnimationBase flyerFlyingLeft = new FlyerAnimationFlying(gameObject, gameData.getVisibleDimensions(), "assets/Enemies/Flyer_Flying_Left.png", 8);
        this.enemy.addAnimator(AnimationState.DEFAULT, flyerDefault);
        this.enemy.addAnimator(AnimationState.WALKING_RIGHT, flyerFlyingRight);
        this.enemy.addAnimator(AnimationState.WALKING_LEFT, flyerFlyingLeft);
    }

}
