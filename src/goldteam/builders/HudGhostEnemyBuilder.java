package goldteam.builders;

import goldteam.animators.BigGhostAnimation;
import goldteam.characters.Ghost;
import goldteam.domain.AnimationBase;
import goldteam.domain.AnimationState;
import goldteam.domain.CharacterBuilderBase;
import goldteam.domain.Delta;
import goldteam.domain.GameEngine;
import goldteam.domain.ModType;
import java.awt.Point;

public class HudGhostEnemyBuilder extends CharacterBuilderBase {

    private Ghost enemy;

    public HudGhostEnemyBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject() {
        this.gameObject = new Ghost(gameData, point);
        this.enemy = (Ghost) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        AnimationBase defaultGhostAnimation = new BigGhostAnimation(enemy, this.gameData.getVisibleDimensions(), "assets/GameGhostStripe.png");
        AnimationBase hurtGhostAnimation = new BigGhostAnimation(enemy, this.gameData.getVisibleDimensions(), "assets/GameGhostStripeRed.png");
        enemy.addAnimator(AnimationState.DEFAULT, defaultGhostAnimation);
        enemy.addAnimator(AnimationState.HURT, hurtGhostAnimation);
        enemy.setVelocityScalarDelta(Delta.create(0.0d, ModType.FIXED));
    }

}
