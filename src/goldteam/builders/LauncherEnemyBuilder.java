package goldteam.builders;

import goldteam.animators.LauncherAnimation;
import goldteam.characters.Launcher;
import goldteam.domain.AnimationState;
import goldteam.domain.CharacterBuilderBase;
import goldteam.domain.GameEngine;

public class LauncherEnemyBuilder extends CharacterBuilderBase {

    private Launcher enemy;

    public LauncherEnemyBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject() {
        this.gameObject = new Launcher(gameData, point);
        this.enemy = (Launcher) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        LauncherAnimation launcherAnimation = new LauncherAnimation(enemy, this.gameData.getMapDimensions(), "assets/Launcher.png");
        enemy.addAnimator(AnimationState.DEFAULT, launcherAnimation);
    }

}
