package goldteam.providers;

import goldteam.animators.GhostAnimation;
import goldteam.characters.Launcher;
import goldteam.domain.AnimationState;
import goldteam.domain.CharacterBuilderBase;
import goldteam.domain.GameEngine;
import java.awt.Point;

public class LauncherEnemyBuilder extends CharacterBuilderBase {

    private Launcher enemy;
    private final Boolean left;

    public LauncherEnemyBuilder(GameEngine gameData, Boolean left) {
        super(gameData);
        this.left = left;
    }

    @Override
    protected void createObject() {
        this.gameObject = new Launcher(gameData, point, left);
        this.enemy = (Launcher) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        GhostAnimation ghostAnimation = new GhostAnimation(enemy, this.gameData.getMapDimensions(), "assets/GameGhostStripe.png");
        enemy.addAnimator(AnimationState.DEFAULT, ghostAnimation);
    }

}
