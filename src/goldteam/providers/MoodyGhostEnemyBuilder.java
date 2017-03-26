package goldteam.providers;

import goldteam.animators.BigGhostAnimation;
import goldteam.animators.GhostAnimation;
import goldteam.characters.Flyer;
import goldteam.characters.MoodyGhost;
import goldteam.domain.AnimationBase;
import goldteam.domain.AnimationState;
import goldteam.domain.CharacterBuilderBase;
import goldteam.domain.GameEngine;
import java.awt.Point;

public class MoodyGhostEnemyBuilder extends CharacterBuilderBase {

    private MoodyGhost enemy;

    public MoodyGhostEnemyBuilder(GameEngine gameData) {
        super(gameData);

    }

    @Override
    protected void createObject() {
        this.gameObject = new MoodyGhost(gameData, point);
        this.enemy = (MoodyGhost) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        AnimationBase defaultGhostAnimation = new GhostAnimation(enemy, this.gameData.getVisibleDimensions(), "assets/GameGhostStripeRed.png");
        AnimationBase slideGhostAnimation = new GhostAnimation(enemy, this.gameData.getVisibleDimensions(), "assets/GameGhostStripeOrange.png");
        AnimationBase runGhostAnimation = new BigGhostAnimation(enemy, this.gameData.getVisibleDimensions(), "assets/GameGhostStripeGreen.png");

        enemy.addAnimator(AnimationState.DEFAULT, defaultGhostAnimation);
        enemy.addAnimator(AnimationState.WALKING_LEFT, slideGhostAnimation);
        enemy.addAnimator(AnimationState.JUMPING_LEFT, runGhostAnimation);
    }

}
