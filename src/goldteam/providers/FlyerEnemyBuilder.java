package goldteam.providers;

import goldteam.animators.GhostAnimation;
import goldteam.characters.Flyer;
import goldteam.domain.AnimationState;
import goldteam.domain.CharacterBuilderBase;
import goldteam.domain.GameEngine;
import java.awt.Point;

public class FlyerEnemyBuilder extends CharacterBuilderBase {

    private Flyer enemy;

    public FlyerEnemyBuilder(GameEngine gameData, Point point) {
        super(gameData, point);
    }

    @Override
    protected void createObject() {
        this.gameObject = new Flyer(gameData, point);
        this.enemy = (Flyer) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        GhostAnimation ghostAnimation = new GhostAnimation(enemy, this.gameData.getMapDimensions(), "assets/GameGhostStripe.png");
        enemy.addAnimator(AnimationState.DEFAULT,ghostAnimation);
    }

}
