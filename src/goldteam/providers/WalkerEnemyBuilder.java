package goldteam.providers;

import goldteam.animators.GhostAnimation;
import goldteam.characters.Walker;
import goldteam.domain.AnimationState;
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
        GhostAnimation ghostAnimation = new GhostAnimation(enemy, this.gameData.getMapDimensions(), "assets/GameGhostStripe.png");
        enemy.addAnimator(AnimationState.DEFAULT, ghostAnimation);
    }

}
