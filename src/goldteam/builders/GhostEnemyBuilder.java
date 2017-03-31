package goldteam.builders;

import goldteam.animators.GhostAnimation;
import goldteam.characters.Ghost;
import goldteam.characters.Walker;
import goldteam.domain.AnimationState;
import goldteam.domain.CharacterBuilderBase;
import goldteam.domain.GameEngine;
import java.awt.Point;

public class GhostEnemyBuilder extends CharacterBuilderBase {

    private Ghost enemy;

    public GhostEnemyBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject() {
        this.gameObject = new Ghost(gameData, point);
        this.enemy = (Ghost) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        GhostAnimation ghostAnimation = new GhostAnimation(enemy, this.gameData.getMapDimensions(), "assets/GameGhostStripe.png");
        enemy.addAnimator(AnimationState.DEFAULT,ghostAnimation);
    }

}
